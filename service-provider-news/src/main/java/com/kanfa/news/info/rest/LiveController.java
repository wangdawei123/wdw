package com.kanfa.news.info.rest;

import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.*;
import com.kanfa.news.info.config.RequestConfiguration;
import com.kanfa.news.info.entity.*;
import com.kanfa.news.info.vo.admin.live.LiveAddInfo;
import com.kanfa.news.info.vo.admin.live.LiveCourtInfo;
import com.kanfa.news.info.vo.admin.live.LiveInfo;
import com.kanfa.news.info.vo.admin.live.LivePageInfo;
import com.kanfa.news.info.vo.admin.live.Utils.ChangeMapUtil;
import com.kanfa.news.info.vo.admin.live.changemap.LiveStatus;
import com.kanfa.news.info.vo.app.video.UserAuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;


@RestController
@RequestMapping("live")
public class LiveController extends BaseController<LiveBiz,Live> {

    @Autowired
    private LiveBiz liveBiz;
    @Autowired
    private SettingBiz settingBiz;
    @Autowired
    private LiveFocusBiz liveFocusBiz;
    @Autowired
    private LiveTypeBiz liveTypeBiz;
    @Autowired
    private LiveCourtBiz liveCourtBiz;
    @Autowired
    private LiveSpecialBiz liveSpecialBiz;
    @Autowired
    private ContentBiz contentBiz;
    @Autowired
    private FavBiz favBiz;
    @Autowired
    private CommentBiz commentBiz;
    @Resource
    private RequestConfiguration requestConfiguration;
    @Autowired
    private AppDeviceBiz appDeviceBiz;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ChannelContentBiz channelContentBiz;
    @Autowired
    private UserBiz userBiz;
    @Value("${spring.rong-cloud.app-key}")
    private String accessKeyId;
    /**
     * 微博登陆的id
     */
    @Value("${pc.weixin.client_id}")
    private String client_id;
    /**
     * 微博登陆的密钥
     */
    @Value("${pc.weixin.client_secret}")
    private String client_secret;

    private final Boolean is_android = false;
    private final Boolean is_ios = false;

    @RequestMapping(value = "/getLivePage", method = RequestMethod.POST)
    public TableResultResponse<LiveInfo> getLivePage(@RequestBody LiveInfo liveInfo){
        return liveBiz.getLivePage(liveInfo);
    }


    /**
     * 增加视频播放量
     */
    @RequestMapping(value = "/updatePlayViews",method = RequestMethod.GET)
    public void updatePlayViews(@RequestParam(value = "id") Integer id,
                            @RequestParam(value = "fromapp") Integer fromapp){
        Live live = liveBiz.findFirstById(id);
        Integer uid = 0;
        String ip = request.getRemoteAddr();
        Map<String ,Object> liveMap = new HashMap<>(9);
        liveMap.put("videoViews" , 1);
        liveMap.put("live_id" , live.getId());
        if(LiveCommonEnum.CONSTANT_ONE.equals(fromapp)){
            String locf = "";
            liveMap.put("appvideo_view" , live.getAppvideoView() + LiveCommonEnum.CONSTANT_ONE);
        }else{
            String locf = "out";
        }
        //
        contentBiz.insertMongo(liveMap ,uid ,ip ,request);
        //更新数据库
        live.setVideoView(live.getVideoView()+LiveCommonEnum.CONSTANT_ONE);
        liveBiz.updateSelectiveById(live);
    }



    /**
     * web接口-直播详情页
     */
    @RequestMapping(value = "/getWebLiveDetail", method = RequestMethod.POST)
    public AppObjectResponse getWebLiveDetail(@RequestParam(value = "liveId") Integer liveId,
                                              @RequestParam(value = "sessionid",required = false) String sessionid){
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        Map<String, Object> liveDetail = liveBiz.getLiveDetail(liveId, -1);
        if(liveDetail.get("live_id") == null){
            response.setStatus(AppCommonMessageEnum.APP_NO_DATA.key());
            response.setMessage(AppCommonMessageEnum.APP_NO_DATA.value());
            return response;
        }
        if(!LiveCommonEnum.LIVE_TYPE_LSH.equals(liveDetail.get("live_type_id"))){
            Integer uid = 0;
            if(sessionid != null){
                Object o = this.redisTemplate.opsForValue().get(LiveCommonEnum.USER_SESSIONID + sessionid);
                if(o != null){
                    uid = (Integer) o;
                    User u  = userBiz.getInfo(uid);
                    liveDetail.put("user",u);
                }else {
                    liveDetail.put("user","");
                }
            }else {
                liveDetail.put("user","");
            }
            liveDetail.put("rong_id",accessKeyId);
            liveDetail.put("type",LiveCommonEnum.CONTENT_LIVE);
            liveDetail.put("bind_list",liveBiz.getBindLiveList(liveId ,1));
            if(LiveCommonEnum.OPEN_CHATROOM_TRUE.equals(liveDetail.get("chatroom_status")) && LiveCommonEnum.LIVE_STATUS.equals(liveDetail.get("stat"))){
                liveDetail.put("review_box",LiveCommonEnum.OPEN_CHATROOT_FALSE);
                //评论功能关闭
                liveDetail.put("comment_status",LiveCommonEnum.OPEN_CHATROOM_TRUE);
            }else {
                liveDetail.put("review_box",LiveCommonEnum.OPEN_CHATROOM_TRUE);
            }
            if(LiveCommonEnum.OPEN_CHATROOT_FALSE.equals(liveDetail.get("comment_status"))){
                liveDetail.put("comment_list" ,commentBiz.getWebCommentList(liveId ,22 ,1 ,5 ,uid));
            }
            HashMap<String, Object> share = (HashMap<String, Object>)liveDetail.get("share");
            share.put("url" ,LiveCommonEnum.URL_PREFIX + "public/live/index.html?id="+ liveId +"&share=1&locf=out ");
            liveDetail.put("share" ,share);
        }
        liveDetail.put("activitys" ,contentBiz.getWebActivityContent(0 , 0));
        response.setData(liveDetail);
        return  response;
    }



    /**
     * web接口-庭审直播详情页
     */
    @RequestMapping(value = "/getDetailData", method = RequestMethod.POST)
    public Map<String, Object> getDetailData(@RequestParam(value = "liveId") Integer liveId){
        Map<String, Object> liveDetail = liveBiz.getLiveDetail(liveId, -1);
        if(liveDetail.size() == LiveCommonEnum.CONSTANT_ZERO){
            return null;
        }
        //更新阅读数
        liveBiz.updateViews(liveId);
        return liveDetail;
    }

    @RequestMapping(value = "/updateViews",method = RequestMethod.GET)
    Integer updateViews(@RequestParam("id") Integer id){
        return liveBiz.updateViews(id);
    }



    /**
     * APP内容-直播列表及筛选
     */
    @RequestMapping(value = "/getOnlineFilter" ,method = RequestMethod.POST)
    public AppObjectResponse<Object> getOnlineFilter(@RequestParam(value = "liveTypId") Integer liveTypId ,
                                                     @RequestParam(value = "page") Integer page,
                                                     @RequestParam(value = "pcount") Integer pcount,
                                                     @RequestParam(value = "case_type",required = false) Integer case_type,
                                                     @RequestParam(value = "court",required = false) Integer court,
                                                     @RequestParam(value = "court_level",required = false) Integer court_level,
                                                     @RequestParam(value = "province_id",required = false) Integer province_id,
                                                     @RequestParam(value = "stat",required = false) Integer stat,
                                                     @RequestParam(value = "signtime",required = false) Integer signtime,
                                                     @RequestParam(value = "type",required = false) Integer type){
        AppObjectResponse<Object> res = new AppObjectResponse<>();

        Map<String , Object> newmap = new HashMap<>(6);
        Map<String , Object> map = new HashMap<>(6);

        Map<Integer, String> liveMap = LiveCommonEnum.getLiveMap();
        List<LiveStatus> liveList = ChangeMapUtil.changemap(liveMap);
        map.put("stat", liveList);
        if(liveTypId.equals(LiveCommonEnum.LIVE_TYPE_ONLINE)){
            //直播大厅
            //获取直播类型数据
            List<LiveType> liveTypeList = liveTypeBiz.findLiveTypeList();
            map.put("type",liveTypeList);
        }else if(liveTypId.equals(LiveCommonEnum.LIVE_TYPE_COURT)){
            //庭审大厅
            //案件类型
            Map<Integer, String> caseType = LiveCommonEnum.getCaseType();
            caseType.remove(LiveCommonEnum.CONSTANT_ZERO);
            List<LiveStatus> statusList = ChangeMapUtil.changemap(caseType);
            map.put("case_type", statusList);

            //法院级别
            Map<Integer, String> courtLevel = LiveCommonEnum.getCourtLevel();
            courtLevel.remove(LiveCommonEnum.CONSTANT_four);
            List<LiveStatus> levelList = ChangeMapUtil.changemap(courtLevel);
            map.put("court_level",levelList);
            //法院名称
            List<LiveCourtInfo> courtList = liveCourtBiz.getLevelCourtList(court_level ,province_id ,1);
            map.put("court",courtList);
        }
        PageHelper.startPage(page, pcount);
        List<Map<String,Object>> filerList = liveBiz.getFilterList(liveTypId ,stat ,type ,case_type ,court_level ,court ,province_id ,signtime);

        newmap.put("filter",map);
        newmap.put("list",filerList);
        if(signtime != null) {
            newmap.put("signtime", signtime);
        }else{
            newmap.put("signtime", new Date());
        }
        res.setData(newmap);
        return res;
    }

    /**
     * APP内容-获取预告
     */
    @RequestMapping(value = "/getPriviewList",method = RequestMethod.POST)
    public AppObjectResponse<Object> getPriviewList(@RequestParam("liveTypId") Integer liveTypId ,
                                                    @RequestParam("page") Integer page){
        Integer offset = (page-LiveCommonEnum.CONSTANT_ONE)* LiveCommonEnum.CONSTANT_TEN;
        AppObjectResponse<Object> response = new AppObjectResponse<>();

        Map<String ,Object> map = liveBiz.selectPriviewList(LiveCommonEnum.PRIVIEW_STATUS ,liveTypId,LiveCommonEnum.CONSTANT_TEN , offset);
        response.setData(map);
        return response;
    }

    /**
     * APP内容-获取直播及庭审首页
     */
    @RequestMapping(value = "/getLiveAll",method = RequestMethod.POST)
    public AppObjectResponse<Object> getLiveAll(@RequestParam("liveTypId") Integer liveTypId,
                                                @RequestParam("uid") String uid){
        //获取设备等信息，做log日志记录，保存app设备表
        String devid = request.getHeader(requestConfiguration.getDevId());
        String idfa = request.getHeader(requestConfiguration.getIdFa());
        String version = request.getHeader(requestConfiguration.getVersion());

        Integer limit = LiveCommonEnum.CONSTANT_four;
        Map<String,Object> map = new HashMap<>(16);
        //获取轮播图
        LiveInfo focus = new LiveInfo();
        focus.setLiveTypeId(liveTypId);
        focus.setLimit(limit);
        List<Map<String ,Object>> focusList = liveFocusBiz.selectFocus(focus);
        //预告列表
        Map<String ,Object> priviewmap = liveBiz.selectPriview(LiveCommonEnum.PRIVIEW_STATUS ,liveTypId,limit);
        priviewmap.put("title","今日预告");

        //获取热门参数
        List<Object> listdd = new ArrayList<>();
        Map<String,Object> hotList = liveSpecialBiz.selectSpecialByType(liveTypId);
        if(hotList.size() == LiveCommonEnum.CONSTANT_ZERO){
            Map<String ,Object> hot = liveBiz.selectPriview(LiveCommonEnum.LIVE_STATUS ,liveTypId,limit);
            hot.put("title","正在直播");
            listdd.add(hot);
        }else{
            Map<String ,Object> hotmap = new HashMap<>(16);
            hotmap.put("title","正在直播");
            listdd.add(hotList);
            listdd.add(hotmap);
        }
        //经典回顾与专题
        //获取经典回顾与专题
        //直播状态 0: 预告 1:直播中 2:回顾
        Map<String ,Object> reviewList = liveSpecialBiz.selectSpecial(liveTypId,limit);
        listdd.add(reviewList);

        //获取配置表信息
        String value = settingBiz.selectByname("custom_id");

        map.put("focus" , focusList);
        map.put("priview",priviewmap);
        map.put("special",listdd);
        map.put("chanId",LiveCommonEnum.CONTENT_LIVE_ID);
        map.put("custom_id",value);

        Integer device_type = is_android?LiveCommonEnum.APP_DEVICE_ANDRIOID:LiveCommonEnum.APP_DEVICE_IOS;
        // 要写入的文件路径
        //测试路径
        File file = new File("E:\\idfa_live.log");
        //上线环境
//        File file = new File("E:\\idfa_live.log");
        // 判断文件是否存在
        if (!file.exists()) {
            try {
                // 如果文件不存在创建文件
                file.createNewFile();
                System.out.println("文件"+file.getName()+"不存在已为您创建!");
            } catch (IOException e) {
                System.out.println("创建文件异常!");
                e.printStackTrace();
            }
        } else {
            System.out.println("文件"+file.getName()+"已存在!");
        }

        // 遍历listStr集合
        FileOutputStream fos = null;
        PrintStream ps = null;
        try {
            // 文件输出流  追加
            fos = new FileOutputStream(file,true);
            ps = new PrintStream(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // +换行
        String str = device_type + ":" + uid + ":" + devid + ":" + idfa + "  " + new Date() ;
        String string  = str + "\r\n";
        // 执行写操作
        ps.print(string);
        // 关闭流
        ps.close();

        //保存到appdevice表中
        appDeviceBiz.saveDevice(device_type , devid , idfa ,uid ,version);

        AppObjectResponse<Object> response = new AppObjectResponse<>();
        response.setData(map);
        return response;
    }

    /**
     * 直播庭审详情页接口
     * @param liveId
     * @param liveTypeId
     * @return
     */
    @RequestMapping(value = "/getLiveDetail", method = RequestMethod.POST)
    public AppObjectResponse<Map<String, Object>> getLiveDetail(HttpSession session ,
                                                                @RequestParam("liveId") Integer liveId ,
                                                                @RequestParam("liveTypeId") Integer liveTypeId){
        AppObjectResponse<Map<String, Object>> res = new AppObjectResponse<>();
        Map<String, Object> detailMap = liveBiz.getLiveDetail(liveId, liveTypeId);

        Object id = detailMap.get("live_id");
        if(id == null){
            res.setStatus(AppCommonMessageEnum.APP_NO_DATA.key());
            res.setMessage(AppCommonMessageEnum.APP_NO_DATA.value());
            return res;
        }

        detailMap.put("is_liked",0);
        detailMap.put("likes",0);

        //增加版本控制，分享数据
        UserAuthInfo userAuthInfo = (UserAuthInfo) session.getAttribute("userAuthInfo");
            if(detailMap.get("live_type_id") != LiveCommonEnum.LIVE_TYPE_COURT){
                if(userAuthInfo != null){
                    Content content = favBiz.selectFaved(liveId, userAuthInfo.getId(), LiveCommonEnum.CONTENT_LIVE);
                    detailMap.put("is_faved",content==null?0:1);
                }else{
                    detailMap.put("is_faved",0);
                }
                detailMap.put("bind_list",liveBiz.getBindLiveList(liveId ,1));
                Integer uid = null;
                if(userAuthInfo != null){
                    uid = userAuthInfo.getUid();
                }
                if((Integer)detailMap.get("comment_status") == 0){
                    List<Map<String, Object>> list = commentBiz.selectComment(liveId ,  1 , 10 , 0 , 22 , uid);
                    if(list != null){
                        detailMap.put("comment_list",list);
                    }
                }
                //直播状态是正在直播且开启聊天室则评论框关闭,评论关闭
                if((Integer)detailMap.get("chatroom_status") == 1 && (Integer)detailMap.get("stat") == 1){
                    detailMap.put("review_box",0);
                    detailMap.put("comment_status",1);
                }else{
                    detailMap.put("review_box",1);
                }
                String comment_tourist = settingBiz.selectByname("comment_tourist");
                if(comment_tourist != null){
                    detailMap.put("comment_tourist",true);
                    HashMap<String,String> share = (HashMap<String,String>)detailMap.get("share");
                    //最后差设置share中的url字段
                   share.put("url",request.getRemoteHost()+":8704" + "/web/live/index?id="+ liveId);
                   // share.put("url","133.1.11.49:8704" + "/web/live/index?id="+ liveId);
                    detailMap.put("share",share);
                }
                //增加阅读数量
                liveBiz.updateViews(liveId);
            }
        res.setData(detailMap);
        return res;
    }

    /**
     * 新增直播
     * @param
     * @return
     */
    @RequestMapping(value = "/addOneLive",method = RequestMethod.POST)
    public ObjectRestResponse<LiveAddInfo> addOneLive(@RequestBody LiveAddInfo entity){
        LiveAddInfo info = liveBiz.addOneLive(entity);
        ObjectRestResponse<LiveAddInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }

    /**
     * 得到直播的详尽信息
     * @param liveId
     * @return
     */
    @RequestMapping(value = "/getOneLive",method = RequestMethod.GET)
    public LiveAddInfo getOneLive(@RequestParam("liveId") Integer liveId){
        LiveAddInfo info = liveBiz.getOneLive(liveId);
        return info;
    }

    /**
     * 编辑直播
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateLiveInfo",method = RequestMethod.POST)
    public ObjectRestResponse<LiveAddInfo> updateLiveAddInfo(@RequestBody LiveAddInfo entity){
        LiveAddInfo info = liveBiz.updateLiveAddInfo(entity);
        ObjectRestResponse<LiveAddInfo> response = new ObjectRestResponse<>();
        response.setData(info);
        return response;
    }


    /**
     * 直播列表的分页及查询
     * @param
     * @return
     */
    @RequestMapping(value = "/getLiveSearchPage", method = RequestMethod.POST)
    public TableResultResponse<LivePageInfo> getLiveSearchPage(@RequestBody LivePageInfo entity) {
        return liveBiz.getLiveSearchPage(entity);
    }
    /**
     * 查询直播表分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/getLiveForMessage", method = RequestMethod.GET)
    public TableResultResponse<Live> getLiveForMessage(@RequestParam Map<String, Object> params){
        return liveBiz.getLiveForMessage(params);
    }

    /**
     * 分享页
     * @param id
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ObjectRestResponse indexPage(@RequestParam(value = "id") Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        Map<String, Object> detail = liveBiz.getLiveDetail(id, null);
        Object live_id = detail.get("live_id");
        //直播不存在
        if(live_id.equals(null)){
            response.setStatus(AppCommonMessageEnum.NOT_FOUND.key());
            response.setData(LiveCommonEnum.URL_PREFIX + LiveCommonEnum.IMAGE_404_URL);
        }
        //增加阅读数
        liveBiz.updateViews((Integer) live_id);
        //微信分享
        Map<String ,Object> signPackage = liveBiz.getSignPackage(client_id ,client_secret);
        Object live_type_id = detail.get("live_type_id");
        String desc = null;
        if ("1".equals(live_type_id)) {
            String case_info = (String) detail.get("case_info");
            desc = case_info.substring(0, 41);
        }else{
            String live_content = (String) detail.get("live_content");
            desc = live_content.substring(0, 41);
        }
        HashMap<String,String> share = (HashMap<String,String>)detail.get("share");
        String img = share.get("img");
        String image = null;
        if(img == null){
            image = LiveCommonEnum.DEFAULT_IMAGE;
        }else{
            image = img;
        }
        Map<String ,Object> shareMap = new HashMap<>(8);
        shareMap.put("title",detail.get("title"));
        shareMap.put("desc",desc);
        shareMap.put("link",LiveCommonEnum.URL_PREFIX  + request.getRequestURI() + "?locf=out");
        shareMap.put("imgUrl",image);

        Map<String ,Object> data = new HashMap<>(8);
        if ("1".equals(live_type_id)) {
            data.put("url" ,LiveCommonEnum.URL_PREFIX  + "live/court_index" );
        }
        data.put("signPackage" ,signPackage);
        data.put("share" ,share);
        data.put("obj" ,detail);
        data.put("params" ,live_id);
        data.put("nowtime" ,new Date());

        response.setData(data);

        return response;
    }

    @GetMapping("/listIds")
    public List<Integer> listIds(@RequestParam Integer start,@RequestParam Integer size){
        return liveBiz.selectListId(start,size);
    }

    @RequestMapping(value = "/getLiveChannelContents",method = RequestMethod.GET)
    List<ChannelContent> getLiveChannelContents(@RequestParam("contentId")Integer contentId){
        ChannelContent channelContent = new ChannelContent();
        channelContent.setContentId(contentId);
        //未删除 直播表
        channelContent.setIsDelete(1);
        channelContent.setFromType(2);
        return  channelContentBiz.selectList(channelContent);
    }
}