package com.kanfa.news.info.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.*;
import com.kanfa.news.info.config.RequestConfiguration;
import com.kanfa.news.info.entity.*;
import com.kanfa.news.info.mapper.*;
import com.kanfa.news.info.mongodb.entity.ViewContent;
import com.kanfa.news.info.mongodb.mapper.ViewContentDao;
import com.kanfa.news.info.vo.admin.kpicount.KpiCountContentInfo;
import com.kanfa.news.info.vo.admin.live.LiveAddInfo;
import com.kanfa.news.info.vo.admin.live.LiveInfo;
import com.kanfa.news.info.vo.admin.live.LivePageInfo;
import com.kanfa.news.info.vo.admin.live.LiveVideoBindInfo;
import com.kanfa.news.info.vo.admin.live.Utils.JsonChangeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 直播表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 15:15:25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LiveBiz extends BaseBiz<LiveMapper,Live> {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ContentBiz contentBiz;
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private LiveMapper liveMapper;
    @Autowired
    private ChannelContentMapper channelContentMapper;
    @Autowired
    private KpiCountMapper kpiCountMapper;
    @Autowired
    private CorpUserMapper corpUserMapper;
    @Autowired
    private VideoSourceMapper videoSourceMapper;
    @Autowired
    private ChatroomUserBiz chatroomUserBiz;
    @Autowired
    private FavMapper favMapper;
    @Autowired
    private ViewContentDao viewContentDao;
    @Autowired
    private HttpServletRequest request;
    @Resource
    private RequestConfiguration requestConfiguration;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private VideoColumnBindBiz videoColumnBindBiz;
    /**
     * 新版首页接口所用数据
     */
    public String getLiveAllData(){
        Long liveAllData = liveMapper.getLiveAllData();
        String r = "";
        if(liveAllData > 99){
            r = "99+";
        }
        return r;
    }

    /**
     *  直播绑定查询
     */
    public List<Map<String,Object>> getBindLiveList(Integer id , @RequestParam(value = "0") Integer isapi){
        List<Map<String,Object>> data = new ArrayList<>();
        List<Integer> fromtypes = liveMapper.selectFromtype(id);
        if(fromtypes.size() == 0 || fromtypes == null){
            return null;
        }
        Integer liveflag = 1;
        Integer videoflag = 1;
        if(fromtypes.size() == LiveCommonEnum.CONSTANT_ONE && fromtypes.get(0).equals(LiveCommonEnum.FROM_TYPE_CONTENT)){
            liveflag = 0 ;
        }
        if(fromtypes.size() == LiveCommonEnum.CONSTANT_ONE && fromtypes.get(0).equals(LiveCommonEnum.FROM_TYPE_LIVE)){
            videoflag = 0 ;
        }
        List<LiveVideoBindInfo> bindLiveList = null;
        if(liveflag != 0){
            if(isapi.equals(LiveCommonEnum.CONSTANT_ONE)){
                bindLiveList = liveMapper.selectBindList(id);
            }else{
                bindLiveList = liveMapper.selectBindList2(id);
            }
            if(isapi.equals(LiveCommonEnum.CONSTANT_ONE)){
                for(LiveVideoBindInfo bindInfo : bindLiveList){
                    if(bindInfo.getLive_stat().equals(LiveCommonEnum.FROM_TYPE_LIVE)){
                        bindInfo.setDuration(StringToDuration.changeToFormat(bindInfo.getDuration()));
                    }
                    if(bindInfo.getThumbimg() == null || bindInfo.getThumbimg() == ""){
                        bindInfo.setThumbimg("");
                    }
                    bindInfo.setUrl(contentBiz.getShareUrlSu(bindInfo.getBindId() , 22 ,1));
                }
            }
        }

        List<LiveVideoBindInfo> bindVideoList = null;
        if(videoflag != 0){
            if(isapi.equals(LiveCommonEnum.CONSTANT_ONE)){
                bindVideoList = contentMapper.selectConBindList(id);
            }else{
                bindVideoList = contentMapper.selectConBindList2(id);
            }
            if(isapi.equals(LiveCommonEnum.CONSTANT_ONE)){
                for(LiveVideoBindInfo bindInfo : bindVideoList){
                    if(bindInfo.getThumbimg() == null || bindInfo.getThumbimg() == ""){
                        bindInfo.setThumbimg("");
                    }
                    bindInfo.setUrl(contentBiz.getShareUrlSu(bindInfo.getBindId() , 4 ,1));
                }
            }
        }
        bindLiveList.addAll(bindVideoList);
        for(LiveVideoBindInfo bindInfo :bindLiveList){
            Map<String ,Object> map = new HashMap<>(8);
            map.put("bind_id" ,bindInfo.getBindId());
            map.put("title" ,bindInfo.getTitle());
            map.put("live_stat" ,bindInfo.getLive_stat());
            map.put("duration" ,bindInfo.getDuration());
            map.put("type" ,bindInfo.getType());
            map.put("from_type" ,bindInfo.getFromType());
            map.put("thumbimg" ,bindInfo.getThumbimg());
            map.put("views" ,bindInfo.getViews());
            map.put("liveTypeId" ,bindInfo.getLiveTypeId());
            map.put("views" ,bindInfo.getViews());
            data.add(map);
        }
        return data;
    }


    /**
     * 直播列表筛选内容
     */
    public List<Map<String,Object>> getFilterList(Integer liveTypId ,Integer stat ,Integer type ,Integer case_type ,
                                        Integer court_level ,Integer court ,Integer province_id ,
                                                  Integer signtime){
        List<Map<String,Object>> newlist = new ArrayList<>();
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try{
            parse = dfs.parse(signtime + "");
        }catch (Exception e){
            e.printStackTrace();
        }
        List<LiveInfo> list = liveMapper.getFilterList(liveTypId ,stat ,type ,case_type ,court_level ,court ,province_id ,parse);
        if(list.size() > LiveCommonEnum.CONSTANT_ZERO && list != null){
            for(LiveInfo live : list){
                Map<String ,Object> map = new HashMap<>(16);
                map.put("id",live.getId());
                map.put("title",live.getTitle());
                if(live.getLiveTypeId().equals(LiveCommonEnum.LIVE_TYPE_ONLINE)){
                    map.put("subtitle",live.getCourtName());
                }else{
                    map.put("subtitle","");
                }
                map.put("stat",live.getStat());
                if(live.getLiveStatus().equals(LiveCommonEnum.REVIEW_STATUS)){
                    String liveDuration = live.getLiveDuration();
                    String s = StringToDuration.changeToFormat(liveDuration);
                    map.put("duration",s);
                }
                map.put("cover_image",live.getCoverImg());
                map.put("live_type_id",live.getLiveTypeId());
                // 判断跳转类型
                if(StringUtils.isNotEmpty(live.getFlashObj()) && StringUtils.isNotEmpty(live.getSourceUrl())){
                    map.put("jump_type",LiveCommonEnum.JUMP_TYPE_COURT);
                    map.put("url",live.getFlashObj());
                }else{
                    map.put("jump_type",LiveCommonEnum.JUMP_TYPE_LIVE);
                }
                newlist.add(map);
            }
        }
        return newlist;
    }



    public List<Map<String ,Object>> findByIds(List<Integer> cidsKey){
        List<Map<String ,Object>> newList = new ArrayList();
        List<LiveInfo> lives = liveMapper.findByIds(cidsKey);
        for(LiveInfo live : lives){
            Map<String ,Object> cons = new HashMap<>(5);
            cons.put("id",live.getId());
            cons.put("type",22);
            cons.put("live_type_id",live.getLiveTypeId());
            newList.add(cons);
        }
        return newList;
    }

    /**
     * 更新直播阅读量
     */
    public Integer updateViews(Integer liveId){
        ViewContent viewContent = new ViewContent();
        viewContent.setCid(0);
        String header = request.getHeader(requestConfiguration.getUid());
        if(header == null){
            viewContent.setUid(0);
        }else{
            viewContent.setUid(Integer.parseInt(header));
        }
        viewContent.setLive_id(liveId);
        viewContent.setType(22);
        //操作mongo
        viewContentDao.insertViewContent(viewContent);
        //操作mysql
        Live live = liveMapper.selectByPrimaryKey(liveId);
        if(live == null){
            //操作有误
            return LiveCommonEnum.CONSTANT_TWO_FU;
        }
        live.setViewCount(live.getViewCount()+1);
        int i = liveMapper.updateByPrimaryKeySelective(live);
        if(i < 1){
            //操作异常
            return LiveCommonEnum.CONSTANT_ZERO;
        }
        return i;
    }

    /**
     *更新收藏数
     */
    public void updateFavs(Integer id , Integer type ){
            Long count = favMapper.selectCountNum(id,type);

            Live live = new Live();
            live.setId(id);
            Live newLive = liveMapper.selectByPrimaryKey(live);
            newLive.setCollectCount(new Long(count).intValue());
            liveMapper.updateByPrimaryKeySelective(newLive);
        }

    /**
     * 更新评论数
     */
    public Integer updateCommentCount(Integer id , Integer type){
        Live live = new Live();
        live.setId(id);
        Live newLive = liveMapper.selectByPrimaryKey(live);
        if(newLive != null) {
            if (type.equals(LiveCommonEnum.CONSTANT_ONE)) {
                live.setCommentCount(live.getCommentCount() - LiveCommonEnum.CONSTANT_ONE);
            } else {
                live.setCommentCount(live.getCommentCount() + LiveCommonEnum.CONSTANT_ONE);
            }
        }
        int i = liveMapper.updateByPrimaryKeySelective(live);
        return i;
    }

    /**
     * 更新已审核评论数
     */
    public Integer updateCommentCheckedCount(Integer id){
        Long count = commentMapper.selectCountnum(id);

        Live live = new Live();
        live.setId(id);
        Live newLive = liveMapper.selectByPrimaryKey(live);
        if(newLive != null){
            newLive.setCommentCheckedCount(new Long(count).intValue());
            liveMapper.updateByPrimaryKeySelective(newLive);
        }
        return 1;
    }

    /**
     * 直播分页
     * @param liveInfo
     * @return
     */
    public TableResultResponse<LiveInfo> getLivePage(LiveInfo liveInfo) {
        Page<Object> result = PageHelper.startPage(liveInfo.getPage(), liveInfo.getLimit());
        List<LiveInfo> list=liveMapper.getLiveList(liveInfo);
        return new TableResultResponse<>(result.getTotal(),list);
    }

    public Live findFirstById(Integer id){
        return liveMapper.selectByPrimaryKey(id);
    }


    /**
     * 新增直播
     * @param entity
     * @return
     */
    public LiveAddInfo addOneLive(LiveAddInfo entity){
        //默认选中 30 综合
        if (entity.getLiveTypeId()==null){
            entity.setLiveTypeId(30);
        }
        if(entity.getRecommendWeight()==null){
            entity.setRecommendWeight(60);
        }
        if (entity.getLiveRecommendWeight()==null){
            entity.setLiveRecommendWeight(60);
        }
        if (entity.getLiveDuration()!=null){
            String liveDuration = entity.getLiveDuration();
            String[] my =liveDuration.split(":");
            int min =Integer.parseInt(my[0]);
            int sec =Integer.parseInt(my[1]);
            long totalSec =min*60+sec;
            String s = String.valueOf(totalSec);
            entity.setLiveDuration(s);
        }
        if (entity.getIsDuty()==null){
            entity.setIsDuty(0);
        }
        //设置其他属性 发布 未删除 未锁定
        entity.setIsDelete(0);
        entity.setIsLock(0);
        entity.setIsPublish(0);
        entity.setIsTop(0);
        liveMapper.addOneLive(entity);
        //绑定app频道
        if (entity.getAppChannelList() != null && entity.getAppChannelList().size() > 0) {
            for (Integer integer : entity.getAppChannelList()) {
                ChannelContent channelContent = new ChannelContent();
                channelContent.setContentId(entity.getId());
                channelContent.setChannelId(integer);
                channelContent.setCardType(5);
                Integer integer1 = channelContentMapper.selectMaxOrderNumber();
                if (integer1.equals(0)){
                    channelContent.setOrderNumber(1);
                }else{
                    channelContent.setOrderNumber(integer1+1);
                }
                channelContent.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
                //排序值
                //直播
                channelContent.setFromType(2);
                channelContent.setCate(1);
                channelContent.setType(22);
                channelContent.setIsPublish(0);
                channelContent.setRecommendWeight(entity.getRecommendWeight());
                channelContentMapper.insertSelective(channelContent);
            }
        }
        //绑定工作人员
        if (entity.getLiveType() != null && entity.getLiveType() >0){
            List<KpiCountContentInfo> userList = entity.getKpiCountList();
            if (userList != null && userList.size() > 0) {
                for (KpiCountContentInfo kpiCountInfo : userList) {
                    if (kpiCountInfo.getWorkTypeList()!=null && kpiCountInfo.getWorkTypeList().size()>0){
                        for (Integer workType:kpiCountInfo.getWorkTypeList()) {
                            KpiCount kpiCount = new KpiCount();
                            CorpUser corpUser = corpUserMapper.selectByPrimaryKey(kpiCountInfo.getUid());
                            kpiCount.setWorkType(workType);
                            kpiCount.setIsNumPass(1);
                            kpiCount.setArticleType(0);
                            kpiCount.setName(corpUser.getName());
                            kpiCount.setUid(corpUser.getId());
                            kpiCount.setType(9);
                            kpiCount.setTypeId(entity.getId());
                            kpiCount.setCreateTime(new Date());
                            kpiCount.setWeight(BigDecimal.valueOf(1));
                            kpiCount.setJobId(corpUser.getJobId());
                            kpiCount.setDepartmentId(corpUser.getLevel2Id());
                            kpiCountMapper.insertSelective(kpiCount);
                        }
                    }
                }
            }
        }
        //videoColumnBind
        if (entity.getVideoColumnId()!=null){
            VideoColumnBind videoColumnBind = new VideoColumnBind();
            videoColumnBind.setCid(entity.getId());
            videoColumnBind.setFromType(2);
            videoColumnBind.setVideoColumnId(entity.getVideoColumnId());
            videoColumnBind.setCreateTime(entity.getCreateTime());
            videoColumnBind.setCreateUid(entity.getCreateUid());
            videoColumnBind.setTitle(entity.getTitle());
            videoColumnBind.setUpdateUid(entity.getCreateUid());
            long time = entity.getCreateTime().getTime();
            videoColumnBind.setUpdateTime((int) time);
            videoColumnBindBiz.insertSelective(videoColumnBind);
        }
        return entity;
    }

    /**
     * 得到直播的详尽信息
     * @param liveId
     * @return
     */
    public LiveAddInfo getOneLive(Integer liveId) {
        LiveAddInfo liveAddInfo = new LiveAddInfo();
        //得到live表中的信息
        Live live = liveMapper.selectByPrimaryKey(liveId);
        BeanUtils.copyProperties(live, liveAddInfo);
        //得到绑定的app频道信息
        List<Integer> appChannels = channelContentMapper.getAppChannels(liveId);
        liveAddInfo.setAppChannelList(appChannels);
        //获得推荐权重的信息
        ChannelContent channelContent = new ChannelContent();
        channelContent.setContentId(liveId);
        List<ChannelContent> select = channelContentMapper.select(channelContent);
        for (ChannelContent c : select) {
            Integer recommendWeight = c.getRecommendWeight();
            liveAddInfo.setRecommendWeight(recommendWeight);
            break;
        }
        //获得videocolumnBindId信息
        VideoColumnBind videoColumnBind = new VideoColumnBind();
        videoColumnBind.setFromType(2);
        videoColumnBind.setCid(liveId);
        List<VideoColumnBind> select3 =  videoColumnBindBiz.selectList(videoColumnBind);
        Integer videoColumnId;
        for (VideoColumnBind videoColumnBind1:select3) {
            videoColumnId = videoColumnBind1.getVideoColumnId();
            liveAddInfo.setVideoColumnId(videoColumnId);
            break;
        }
        //获得工作人员的信息
        List<KpiCountContentInfo> kpiCountContentInfos = new ArrayList<>();
        KpiCount kpiCount = new KpiCount();
        kpiCount.setType(9);
        kpiCount.setTypeId(liveId);
        List<KpiCount> kpiCounts = kpiCountMapper.select(kpiCount);
        for (KpiCount k : kpiCounts) {
            KpiCountContentInfo kpiCountContentInfo = new KpiCountContentInfo();
            kpiCountContentInfo.setUid(k.getUid());
            Integer uid = k.getUid();
            Integer typeId = k.getTypeId();
            Integer type = k.getType();
            List<Integer> integers = liveMapper.getworkTypeList(uid, typeId, type);
            kpiCountContentInfo.setWorkTypeList(integers);
            kpiCountContentInfos.add(kpiCountContentInfo);
            liveAddInfo.setKpiCountList(kpiCountContentInfos);
        }
        //剔除重复元素 KpiCountContentInfo
        if(liveAddInfo.getKpiCountList()!=null){
            HashSet hashSet = new HashSet(liveAddInfo.getKpiCountList());
            liveAddInfo.getKpiCountList().clear();
            liveAddInfo.getKpiCountList().addAll(hashSet);
            return  liveAddInfo;
        }
        String liveDuration = liveAddInfo.getLiveDuration();
        if (liveDuration !=null){
            Integer integer = Integer.valueOf(liveDuration);
            Integer mins  = integer/60;
            Integer sends = integer%60;
            liveAddInfo.setLiveDuration(mins+":"+sends);
        }
        return liveAddInfo;
    }

    /**
     * 编辑直播
     * @param entity
     * @return
     */
    public LiveAddInfo updateLiveAddInfo(LiveAddInfo entity){
        //未审核
        entity.setCheckState(0);
        if (entity.getLiveDuration()!=null){
            String liveDuration = entity.getLiveDuration();
            String[] my =liveDuration.split(":");
            int min =Integer.parseInt(my[0]);
            int sec =Integer.parseInt(my[1]);
            long totalSec =min*60+sec;
            String s = String.valueOf(totalSec);
            entity.setLiveDuration(s);
        }
        liveMapper.updateLiveAddInfo(entity);
        Integer liveId = entity.getId();
        //更新绑定信息
        //得到绑定的app频道信息
        Integer contentId =liveId;
        List<Integer> appChannels = channelContentMapper.getAppChannels(contentId);
        for (Integer channelId:appChannels) {
            //重置 先删除再增加
            ChannelContent channelContent = new ChannelContent();
            channelContent.setChannelId(channelId);
            channelContent.setContentId(contentId);
            channelContentMapper.delete(channelContent);
        }
        if (entity.getAppChannelList() != null && entity.getAppChannelList().size() > 0) {
            for (Integer integer : entity.getAppChannelList()) {
                ChannelContent channelContent = new ChannelContent();
                channelContent.setContentId(entity.getId());
                channelContent.setChannelId(integer);
                if (entity.getCoverImg() != null) {
                    channelContent.setCardType(5);
                }
                Integer integer1 = channelContentMapper.selectMaxOrderNumber();
                if (integer1.equals(0)){
                    channelContent.setOrderNumber(1);
                }else{
                    channelContent.setOrderNumber(integer1+1);
                }
                channelContent.setIsDelete(NewsEnumsConsts.ChannelOfChannelStatus.NORMAL.key());
                //排序值
                //直播
                channelContent.setFromType(2);
                channelContent.setCate(1);
                channelContent.setType(22);
                channelContent.setIsPublish(0);
                channelContent.setRecommendWeight(entity.getRecommendWeight());
                channelContentMapper.insertSelective(channelContent);
            }
        }
        //更新绑定的员工信息
        KpiCount kpiCount = new KpiCount();
        kpiCount.setType(9);
        kpiCount.setTypeId(liveId);
        List<KpiCount> kpiCounts = kpiCountMapper.select(kpiCount);
        //删除绑定信息
        for (KpiCount kpiCount1:kpiCounts ) {
            kpiCountMapper.delete(kpiCount1);
        }
        //绑定工作人员
        if (entity.getLiveType() != null && entity.getLiveType() >0){
            List<KpiCountContentInfo> userList = entity.getKpiCountList();
            if (userList != null && userList.size() > 0) {
                for (KpiCountContentInfo kpiCountInfo : userList) {
                    if (kpiCountInfo.getWorkTypeList()!=null && kpiCountInfo.getWorkTypeList().size()>0){
                        for (Integer workType:kpiCountInfo.getWorkTypeList()) {
                            KpiCount kpiCount3 = new KpiCount();
                            CorpUser corpUser = corpUserMapper.selectByPrimaryKey(kpiCountInfo.getUid());
                            kpiCount3.setWorkType(workType);
                            kpiCount3.setIsNumPass(1);
                            kpiCount3.setArticleType(0);
                            kpiCount3.setName(corpUser.getName());
                            kpiCount3.setUid(corpUser.getId());
                            kpiCount3.setType(9);
                            kpiCount3.setTypeId(entity.getId());
                            kpiCount3.setCreateTime(new Date());
                            kpiCount3.setWeight(BigDecimal.valueOf(1));
                            kpiCount3.setJobId(corpUser.getJobId());
                            kpiCount3.setDepartmentId(corpUser.getLevel2Id());
                            kpiCountMapper.insertSelective(kpiCount3);
                        }
                    }
                }
            }
        }
        //更新栏目
        VideoColumnBind videoColumnBind = new VideoColumnBind();
        videoColumnBind.setFromType(2);
        videoColumnBind.setCid(contentId);
        List<VideoColumnBind> select3 = videoColumnBindBiz.selectList(videoColumnBind);
        for (VideoColumnBind videoColumnBind1:select3) {
            videoColumnBindBiz.delete(videoColumnBind1);
        }
        if (entity.getVideoColumnId()!=null){
            VideoColumnBind videoColumnBindNew = new VideoColumnBind();
            videoColumnBindNew.setCid(entity.getId());
            videoColumnBindNew.setFromType(2);
            videoColumnBindNew.setVideoColumnId(entity.getVideoColumnId());
            videoColumnBindNew.setCreateTime(entity.getUpdateTime());
            videoColumnBindNew.setCreateUid(entity.getUpdateUid());
            videoColumnBindNew.setTitle(entity.getTitle());
            videoColumnBindNew.setUpdateUid(entity.getUpdateUid());
            long time = entity.getUpdateTime().getTime();
            videoColumnBindNew.setUpdateTime((int) time);
            videoColumnBindBiz.insertSelective(videoColumnBindNew);
        }
        return  entity;
    }

    /**
     * 分页查询直播列表
     * @param entity
     * @return
     */
    public TableResultResponse<LivePageInfo> getLiveSearchPage(LivePageInfo entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage(), entity.getLimit());
        //设置晒出条件
        //'审核状态（0待审核，1审核通过，2审核不通过）
        //entity.setCheckState(1);
        //是否删除 0.正常 1.已删除
        //entity.setIsDelete(0);
        List<LivePageInfo> list = liveMapper.getLiveSearchPage(entity);
        return new TableResultResponse<>(result.getTotal(), list);
    }


    /**
     * 获取预告列表使用
     * @param liveStatus
     * @param liveTypeId
     * @param limit  每页多少条数据
     * @param offset 从第几条数据开始
     * @return
     */
    public Map<String ,Object> selectPriviewList(Integer liveStatus, Integer liveTypeId , Integer limit ,Integer offset) {
        Map<String ,Object>  priviewList= findDataArray(liveStatus, liveTypeId ,limit ,offset);
        SimpleDateFormat hm = new SimpleDateFormat("HH:mm");
        SimpleDateFormat md = new SimpleDateFormat("MM月dd日");
        List<Map<String ,Object>> dataList = (List<Map<String ,Object>>)priviewList.get("data");
        int count = dataList.size();
        List<Map<String ,Object>> startTimeList = new ArrayList<>();
        for(Map<String ,Object> priview : dataList){
            Map<String ,Object> map = new HashMap<>(16);
            //数据库取出的直播开始时间
            Date startTime = (Date)priview.get("start_time");
            //当天23点59分59秒
            Date todayLastDate = DateUtil.todayLastDate();
            //明天23点59分59秒
            Date tomorrowLastDate = DateUtil.tomorrowLastDate();
            //后天23点59分59秒
            Date tomorrow2LastDate = DateUtil.tomorrow2LastDate();
            if(startTime.before(todayLastDate)){
                priview.put("starttime",DateUtil.fromDeadline(startTime));
                map.put("今天 ",priview);
            }else if(startTime.before(tomorrowLastDate)){
                priview.put("starttime",hm.format(startTime));
                map.put("明天 "+ md.format(startTime),priview);
            }else if(startTime.before(tomorrow2LastDate)) {
                priview.put("starttime",hm.format(startTime));
                map.put("后天 " + md.format(startTime), priview);
            }else{
                priview.put("starttime",hm.format(startTime));
                map.put(md.format(startTime), priview);
            }
            startTimeList.add(map);
        }

        Map<String ,Object> lastMap = new HashMap<>(16);
        List<Map<String ,Object>> ll = new ArrayList<>();
        //遍历startTimeList集合
        for(Map<String ,Object> liveInfo : startTimeList){
            //要在循环中判断，在循环外面定义一个临时变量
            Boolean flag = true;
            Map<String ,Object> newMap = new HashMap<>(16);
            List<Object> list = new ArrayList<>();
            //遍历map集合
            for(Map.Entry<String, Object> entry : liveInfo.entrySet()) {
                if(ll.size() > 0){
                    //遍历ll集合，当title值重复时，把content值放到一个list集合中
                    for(Map<String ,Object> llValue : ll){
                        if(llValue.get("title").equals(entry.getKey())){
                            List<Object> content = (List<Object>)llValue.get("content");
                            content.add(entry.getValue());
                            //当title重复时，falg变量为false,不进入正常的方法
                            flag = false;
                            break;
                        }
                    }
                }
                //当title不重复时的正常流程操作
                if(flag){
                    newMap.put("title",entry.getKey());
                    list.add(entry.getValue());
                    newMap.put("content",list);
                    ll.add(newMap);
                }
            }
        }

        lastMap.put("data",ll);
        lastMap.put("count",count);
        return lastMap;
    }

    public Map<String ,Object> findDataArray(Integer liveStatus, Integer liveTypeId , Integer limit ,Integer offset){
        LiveInfo info = new LiveInfo();
        info.setLiveStatus(liveStatus);
        info.setLiveTypeId(liveTypeId);
        info.setLimit(limit);
        info.setOffset(offset);
        List<LiveInfo> infoList = liveMapper.selectPriviewList(info);
        List<Map<String ,Object>> list = new ArrayList<>();
        for(LiveInfo priview :infoList){
            Map<String ,Object> map = new HashMap<>(16);
            if(priview.getLiveStatus().equals(LiveCommonEnum.LIVE_STATUS)){
                map.put("type",LiveCommonEnum.TYPE_STATUS_NINE);
            }else{
                map.put("type",LiveCommonEnum.TYPE_STATUS_FOUR);
            }
            if(StringUtils.isNotEmpty(priview.getFlashObj()) && StringUtils.isNotEmpty(priview.getSourceUrl())){
                map.put("jump_type",LiveCommonEnum.JUMP_TYPE_COURT);
                map.put("source_url",null);
            }else{
                map.put("jump_type",LiveCommonEnum.JUMP_TYPE_LIVE);
                map.put("flash_obj",null);
                map.put("source_url",null);
            }
            if(priview.getLiveDuration() != null){
                String s = StringToDuration.changeToFormat(priview.getLiveDuration());
                map.put("duration",s);
            }
            map.put("id",priview.getId());
            map.put("title",priview.getTitle());
            map.put("subtitle",priview.getSubtitle());
            map.put("court_name",priview.getCourtName());
            map.put("start_time",priview.getStartTime());
            map.put("cover_img",priview.getCoverImg());
            map.put("flash_obj",priview.getFlashObj());
            map.put("stat",priview.getLiveStatus());
            map.put("live_type_id",priview.getLiveTypeId());
            map.put("image_type",LiveCommonEnum.IMAGE_TYPE_SMALL);
            map.put("title_type",LiveCommonEnum.TITLE_TYPE_COURT);
            list.add(map);
        }

        Map<String ,Object> liveMap = new HashMap<>(16);
        if(liveStatus.equals(LiveCommonEnum.LIVE_STATUS)){
            LiveInfo live = new LiveInfo();
            live.setLiveStatus(liveStatus);
            Integer count = liveMapper.selectCountBy(live);
            liveMap.put("data",list);
            liveMap.put("id",liveStatus);
            liveMap.put("subtitle","当前"+ count +"直播，进去看看。");
            return liveMap;
        }
        liveMap.put("data",list);
        liveMap.put("id",liveStatus);
        return liveMap;
    }


    /**
     * 直播首页使用
     * @param liveStatus
     * @param liveTypeId
     * @param limit
     * @return
     */
    public Map<String ,Object> selectPriview(Integer liveStatus, Integer liveTypeId , Integer limit ) {
        LiveInfo info = new LiveInfo();
        info.setLiveStatus(liveStatus);
        info.setLiveTypeId(liveTypeId);
        info.setLimit(limit);
        List<Map<String ,Object>> list = new ArrayList<>();
        List<LiveInfo> priviewList = liveMapper.selectPriview(info);
        for(LiveInfo priview :priviewList){
            Map<String ,Object> priviewMap = new HashMap<>(16);
            priviewMap.put("image_type",LiveCommonEnum.IMAGE_TYPE_SMALL);
            priviewMap.put("title_type",LiveCommonEnum.TITLE_TYPE_COURT);
            if(priview.getLiveStatus().equals(LiveCommonEnum.LIVE_STATUS)){
                priviewMap.put("type",LiveCommonEnum.TYPE_STATUS_NINE);
            }else{
                priviewMap.put("type",LiveCommonEnum.TYPE_STATUS_FOUR);
            }
            if(StringUtils.isNotEmpty(priview.getFlashObj()) && StringUtils.isNotEmpty(priview.getSourceUrl())){
                priviewMap.put("jump_type",LiveCommonEnum.JUMP_TYPE_COURT);
                priviewMap.put("source_url",null);
            }else{
                priviewMap.put("jump_type",LiveCommonEnum.JUMP_TYPE_LIVE);
                priviewMap.put("flash_obj",null);
                priviewMap.put("source_url",null);
            }
            if(priview.getLiveDuration() != null){
                String s = StringToDuration.changeToFormat(priview.getLiveDuration());
                priviewMap.put("duration",s);
            }
            priviewMap.put("id",priview.getId());
            priviewMap.put("title",priview.getTitle());
            priviewMap.put("subtitle",priview.getSubtitle());
            priviewMap.put("court_name",priview.getCourtName());
            priviewMap.put("cteate_time",priview.getCreateTime());
            priviewMap.put("cover_img",priview.getCoverImg());
            priviewMap.put("flash_obj",priview.getFlashObj());
            priviewMap.put("stat",priview.getLiveStatus());
            priviewMap.put("live_type_id",priview.getLiveTypeId());
            list.add(priviewMap);
        }

        Map<String ,Object> map = new HashMap<>(16);
        if(liveStatus.equals(LiveCommonEnum.LIVE_STATUS)){
            LiveInfo live = new LiveInfo();
            live.setLiveStatus(liveStatus);
            Integer count = liveMapper.selectCountBy(live);
            map.put("data",list);
            map.put("id",liveStatus);
            map.put("subtitle","当前"+ count +"直播，进去看看。");
            return map;
        }
        map.put("data",list);
        map.put("id",liveStatus);
        return map;
    }

    public Map<String,Object> getLiveDetail( Integer liveId , Integer liveTypeId) {
        Map<String,Object> map = new HashMap<>(16);
        LiveInfo info = new LiveInfo();
        if((LiveCommonEnum.LIVE_TYPE_COURT).equals(liveTypeId)){
            info.setStat(LiveCommonEnum.LIVE_STATUS);
        }
        info.setId(liveId);
        info.setLiveTypeId(liveTypeId);
        LiveInfo liveDetail = liveMapper.getLiveDetail(info);
        if(liveDetail != null){
            map.put("live_id",liveDetail.getId());
            map.put("live_type_id",liveDetail.getLiveTypeId());
            map.put("title",liveDetail.getTitle());
            map.put("comment_status",liveDetail.getCommentStatus());
            map.put("subtitle",liveDetail.getSubtitle());
            map.put("views",liveDetail.getViewCount());
            map.put("stat",liveDetail.getLiveStatus());
            map.put("cover_img",liveDetail.getCoverImg());
            map.put("comments_ops",liveDetail.getCommentCheckedCount());
            if(liveDetail.getSourceUrl() != null && liveDetail.getFlashObj() != null){
                map.put("flash_obj",liveDetail.getFlashObj());
            }
            if(liveDetail.getLiveStatus().equals(LiveCommonEnum.PRIVIEW_STATUS)){
                map.put("live_url",liveDetail.getPreviewUrl());
            }else if(liveDetail.getLiveStatus().equals(LiveCommonEnum.LIVE_STATUS)){
                map.put("live_url",liveDetail.getLiveUrl());
            }else{
                map.put("live_url",liveDetail.getReviewUrl());
                map.put("duration",StringToDuration.changeToFormat(liveDetail.getLiveDuration()));
            }
            if(liveDetail.getLiveTypeId().equals(LiveCommonEnum.LIVE_TYPE_COURT)){
                map.put("court_name",liveDetail.getName());
                map.put("court_logo",liveDetail.getAvatar());
                map.put("court_live",liveDetail.getLiveCount());
                map.put("case_info",liveDetail.getCaseInfo());
                if(liveDetail.getCourtInfo() != null){
                    map.put("court_info",JsonChangeUtil.change(liveDetail.getCourtInfo()));
                }else{
                    map.put("court_info",new String[]{});
                }
            }else{
                map.put("live_content",liveDetail.getLiveContent());
                map.put("chatroom_status",liveDetail.getChatroomStatus());
                if(liveDetail.getChatroomStatus().equals(LiveCommonEnum.OPEN_CHATROOM_TRUE)){
                    String chatRoomId = chatroomUserBiz.createChatRoom(liveDetail.getId());
                    map.put("chatroomid",chatRoomId);
                    if(liveDetail.getChatroomNotice() == null){
                        liveDetail.setChatroomNotice(LiveCommonEnum.CHATROOM_NOTICE);
                    }
                    map.put("chatroom_notice",liveDetail.getChatroomNotice());
                }else{
                    map.put("chatroomid",null);
                }
                if(liveDetail.getSourceId() == 0){
                    liveDetail.setSourceId(LiveCommonEnum.CONSTANT_TWO);
                }
                VideoSource videoSource = videoSourceMapper.selectByPrimaryKey(liveDetail.getSourceId());
                map.put("source_name",videoSource.getName());
                if(videoSource.getImage() == ""){
                    videoSource.setImage(LiveCommonEnum.DEFAULT_IMAGE);
                }
                map.put("source_image",videoSource.getImage());
                map.put("comments",liveDetail.getCommentCheckedCount());
                map.put("posts_ops",liveDetail.getCommentType()+LiveCommonEnum.CONSTANT_ONE);

            }

            HashMap<String,String> share = new HashMap<>(16);
            share.put("title",liveDetail.getTitle());
            share.put("subtitle",liveDetail.getSubtitle());
            if(liveDetail.getCoverImg()!= null){
                share.put("img",liveDetail.getCoverImg());
            }else{
                share.put("img",LiveCommonEnum.DEFAULT_IMAGE);
            }
            //这里为老版代码，url参数，被替换
            share.put("url",LiveCommonEnum.DEFAULT_IMAGE);

            map.put("share",share);
        }
        return map;
    }

    /**
     * 查询直播分页
     * @param params
     * @return
     */
    public TableResultResponse<Live> getLiveForMessage(Map<String, Object> params) {
        Query query = new Query(params);
        Page<Live> page = PageHelper.startPage(query.getPage(), query.getLimit());
        List<Live> list = liveMapper.getLiveForMessage(params);
        return new TableResultResponse<>(page.getTotal(),list);
    }


    /**
     * @param client_id
     * @param client_secret
     * @return
     */
    public Map<String ,Object> getSignPackage(String client_id ,String client_secret){
        String url = LiveCommonEnum.URL_PREFIX + request.getRemoteHost() + request.getRequestURI();
        Long timestamp = System.currentTimeMillis();
        String nonceStr = UUIDUtils.generateShortUuid();
        String o = (String)this.redisTemplate.opsForValue().get(LiveCommonEnum.APP_WEIXIN_TICKET);
        String ticket = "";
        if(o != null){
            JSONObject object = JSON.parseObject(o);
            ticket = (String)object.get("ticket");
        }
        String params = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
        String signature = StringHelper.sha1(params);
        Map<String ,Object> signPackage = new HashMap<>(9);
        signPackage.put("appId" , client_id);
        signPackage.put("nonceStr" , nonceStr);
        signPackage.put("timestamp" , timestamp);
        signPackage.put("signature" , signature);
        signPackage.put("url" , url);
        return signPackage;
    }


    public List<Integer> selectListId(Integer start,Integer size){
        return liveMapper.selectListId(start,size);
    }


}