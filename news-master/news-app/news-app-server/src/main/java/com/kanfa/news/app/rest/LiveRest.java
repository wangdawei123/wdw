package com.kanfa.news.app.rest;

import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.biz.*;
import com.kanfa.news.app.entity.Fav;
import com.kanfa.news.app.entity.LiveType;
import com.kanfa.news.app.vo.admin.live.LiveCourtInfo;
import com.kanfa.news.app.vo.admin.live.LiveInfo;
import com.kanfa.news.app.vo.admin.live.Utils.ChangeMapUtil;
import com.kanfa.news.app.vo.admin.live.changemap.LiveStatus;
import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("live")
public class LiveRest extends BaseRest {

    @Autowired
    private LiveBiz liveBiz;

    @Autowired
    private VideoColumnBiz videoColumnBiz;

    @Autowired
    private LiveCourtBiz liveCourtBiz;

    @Autowired
    private LiveTypeBiz liveTypeBiz;

    @Autowired
    private LiveFocusBiz liveFocusBiz;

    @Autowired
    private LiveSpecialBiz liveSpecialBiz;

    @Autowired
    private SettingBiz settingBiz;

    @Autowired
    private AppDeviceBiz appDeviceBiz;

    @Autowired
    private FavBiz favBiz;

    @Autowired
    private CommentBiz commentBiz;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final Boolean is_android = false;

    /**
     * 通过uid获取token
     *
     * @return
     */
   /* @RequestMapping(value = "/getLiveBind", method = RequestMethod.GET)
    public AppObjectResponse getLiveBind() {
        Assert.hasText(appHeader.getDevId(), APPCommonEnum.PARAM_VALIDATE_FAIL.value());
        Assert.hasText(appHeader.getIdFa(), APPCommonEnum.PARAM_VALIDATE_FAIL.value());
        Assert.hasText(appHeader.getPlatform(), APPCommonEnum.PARAM_VALIDATE_FAIL.value());
        ObjectRestResponse objectRestResponse = chatRootServiceFeign.getToken(this.getUserId(),
                appHeader.getDevId(),
                appHeader.getIdFa(),
                appHeader.getPlatform(),
                chatroomId);
        BeanUtils.copyProperties(objectRestResponse, appObjectResponse);
        return appObjectResponse;
    }*/

    /**
     * APP内容-获取预告
     */
    @RequestMapping(value = "/priview",method = RequestMethod.POST)
    public AppObjectResponse<Object> getPriviewList(@RequestParam(value = "live_type_id") Integer liveTypId ,
                                                    @RequestParam(value = "page", defaultValue = "1") Integer page){
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        if(null == liveTypId){
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_MISSING.value());
            return response;
        }else{
            Integer offset = (page- LiveCommonEnum.CONSTANT_ONE)* LiveCommonEnum.CONSTANT_TEN;

            Map<String ,Object> map = liveBiz.selectPriviewList(LiveCommonEnum.PRIVIEW_STATUS ,liveTypId,LiveCommonEnum.CONSTANT_TEN , offset);
            response.setData(map);
            return response;
        }
    }

    /**
     * APP内容-直播列表及筛选
     */
    @RequestMapping(value = "/getfilter" ,method = RequestMethod.POST)
    public AppObjectResponse<Object> getOnlineFilter(@RequestParam(value = "live_type_id") Integer liveTypId ,
                                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(value = "pcount", defaultValue = "20") Integer pcount,
                                                     @RequestParam(value = "case_type" ,required = false) Integer case_type,
                                                     @RequestParam(value = "court" ,required = false) Integer court,
                                                     @RequestParam(value = "court_level" ,required = false) Integer court_level,
                                                     @RequestParam(value = "province_id" ,required = false) Integer province_id,
                                                     @RequestParam(value = "stat" ,required = false) Integer stat,
                                                     @RequestParam(value = "signtime" ,required = false) Integer signtime,
                                                     @RequestParam(value = "type" ,required = false) Integer type) {
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
     * app内容 -- 新版直播首页
     * 2018-7-30
     */
    @RequestMapping(value = "/indexNew",method = RequestMethod.POST)
    public AppObjectResponse<Object> getNewLiveAll(@RequestParam(name = "page",required = false,defaultValue = "1") Integer page,
                                                   @RequestParam(name = "pcount",required = false,defaultValue = "20") Integer pcount,
                                                   @RequestParam(name = "signtime",required = false,defaultValue = "0") Integer signtime){
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        List<Map<String  ,Object>> liveData = liveBiz.getNewLiveData(page,pcount,signtime);
        List<Map<String, Object>> data = new ArrayList<>();

        Map<String  ,Object> map = new HashMap<>(16);
        map.put("signtime" ,signtime==0?System.currentTimeMillis():signtime);

        if(page == 1){
            List<Map<String, Object>> temp = new ArrayList<>();
            List<Map<String, Object>> priviewList = new ArrayList<>();
            //今日预告
            Map<String, Object> dataArray = liveBiz.findDataArray(0, 0, 5, (page - LiveCommonEnum.CONSTANT_ONE) * pcount);
            List<Map<String ,Object>> dataList = (List<Map<String ,Object>>)dataArray.get("data");
            for(Map<String ,Object> entity : dataList){
                try{
                    entity.put("starttime",DateUtil.fromDeadline(DateUtil.dfs.parse((String) entity.get("start_time"))));
                    entity.remove("start_time");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            Map<String  ,Object> mapPriview = new HashMap<>(6);
            mapPriview.put("title","今日预告");
            mapPriview.put("card_type",LiveCommonEnum.CARD_TYPE_PREVIEW);
            mapPriview.put("content",dataList);
            priviewList.add(mapPriview);

            // 栏目数据
            List<Map<String, Object>> columnInfo = videoColumnBiz.getColumnInfo();

            // 数据流组合
            temp.add(0,liveData.get(0));
            temp.add(1,liveData.get(1));
            liveData.remove(0);
            liveData.remove(0);
            Map<String, Object> tempMap = new HashMap<>(6);
            tempMap.put("card_type",LiveCommonEnum.CARD_TYPE_COLUMN);
            tempMap.put("content",columnInfo);
            temp.add(2 ,tempMap);

            liveData.addAll(0,priviewList);
            liveData.addAll(1,temp);
            map.put("list",liveData);
        }else{
            map.put("list",liveData);
        }
        response.setData(map);

        return response;
    }


    /**
     * APP内容-获取直播及庭审首页
     * @param liveTypId
     * @return AppObjectResponse
     */
    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public AppObjectResponse<Object> getLiveAll(@RequestParam("live_type_id") Integer liveTypId,
                                                @RequestParam(value = "uid" ,defaultValue = "0") String uid){
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        if(liveTypId == null){
            //缺少参数
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_MISSING.value());
            return response;
        }else{
            //获取设备等信息，做log日志记录，保存app设备表
            String devid = request.getHeader(requestConfiguration.getDevId());
            String idfa = request.getHeader(requestConfiguration.getIdFa());
            String version = request.getHeader(requestConfiguration.getVersion());

            Integer limit = LiveCommonEnum.CONSTANT_EIGHT;
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
            //预告
            map.put("preview",priviewmap);
            map.put("special",listdd);
            map.put("chanId",LiveCommonEnum.CONTENT_LIVE_ID);
            map.put("custom_id",value);

            Integer device_type = is_android?LiveCommonEnum.APP_DEVICE_ANDRIOID:LiveCommonEnum.APP_DEVICE_IOS;
         /*   // 要写入的文件路径
            //测试路径
            File file = new File("D:\\idfa_live.log");
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
            ps.close();*/

            //保存到appdevice表中
            appDeviceBiz.saveDevice(device_type , devid , idfa ,uid ,version);

            response.setData(map);
            return response;
        }

    }

    /**
     * 直播庭审详情页接口
     * @param liveId
     * @param liveTypeId
     * @return
     */
    @RequestMapping(value = "/getLiveDetail", method = RequestMethod.POST)
    public AppObjectResponse<Map<String, Object>> getLiveDetail(@RequestParam("live_id") Integer liveId ,
                                                                @RequestParam("live_type_id") Integer liveTypeId,
                                                                @RequestParam(value = "sessionid",required = false) String sessionid
    ) {
        AppObjectResponse<Map<String, Object>> res = new AppObjectResponse<>();
        Map<String, Object> detailMap = liveBiz.getLiveDetail(liveId, liveTypeId);
        if(detailMap == null || detailMap.size() ==0){
            res.setData(null);
            return res;
        }

        Object id = detailMap.get("live_id");
        if(id == null){
            res.setStatus(AppCommonMessageEnum.APP_NO_DATA.key());
            res.setMessage(AppCommonMessageEnum.APP_NO_DATA.value());
            return res;
        }

        detailMap.put("is_liked",0);
        detailMap.put("likes",0);

        Object o = this.redisTemplate.opsForValue().get(LiveCommonEnum.APP_USER_SESSIONID + sessionid);
        Integer uid = (Integer) o;
        //增加版本控制，分享数据  -- 当数据是庭审时，没有评论功能，不显示评论框
        if(detailMap.get("live_type_id") != LiveCommonEnum.LIVE_TYPE_COURT){
            if(o != null){
                Fav fav = favBiz.selectFaved(liveId, uid, LiveCommonEnum.CONTENT_LIVE);
                detailMap.put("is_faved",fav==null?0:1);
            }else{
                detailMap.put("is_faved",0);
            }
            detailMap.put("bind_list",liveBiz.getBindLiveList(liveId ,1));
            //comment_status  0允许评论  1不允许评论'
            if((Integer)detailMap.get("comment_status") == 0){
                //在后面处理评论父级评论的代码中，uid==null，才能出现父级评论
                List<Map<String, Object>> list = commentBiz.selectComment(liveId ,  1 , 10 , 0 , 22 , (uid==null)?0:uid);
                if(list != null){
                    detailMap.put("comment_list",list);
                }
            }
            //直播状态是正在直播且开启聊天室则评论框关闭,评论关闭
            //chatroom_status  是否开启聊天室，1开启，0关闭
            if((Integer)detailMap.get("chatroom_status") == 1 && (Integer)detailMap.get("stat") == 1){
                //review_box  评论框是否可以使用 1开启 ，0 关闭
                detailMap.put("review_box",0);
                detailMap.put("comment_status",1);
            }else{
                detailMap.put("review_box",1);
            }
            String comment_tourist = settingBiz.selectByname("comment_tourist");
            if(comment_tourist != null){
                detailMap.put("comment_tourist",true);
                HashMap<String,String> share = (HashMap<String,String>)detailMap.get("share");
                //分享字段的设置
                share.put("url",LiveCommonEnum.URL_PREFIX + "public/live/index.html?id=" + liveId + "&share=1&locf=out");
                detailMap.put("share",share);
            }
            //增加阅读数量
            liveBiz.updateViews(liveId);
        }
        res.setData(detailMap);
        return res;
    }


    
    @RequestMapping("request")
    public String getHeader(HttpServletRequest request){
        Map<String, String> map = new HashMap<>(16);
        Enumeration<String> headerNames = request.getHeaderNames();
        String devid = request.getHeader("DEVID");
        System.out.println(devid);
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();

            String value = request.getHeader(key);
            System.out.println(key+"=========="+value);
            map.put(key, value);
        }
        return null;
    }
}
