package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.constant.AdminCommonConstant;
import com.kanfa.news.admin.entity.Message;
import com.kanfa.news.admin.feign.IContentServiceFeign;
import com.kanfa.news.admin.feign.ILiveServiceFeign;
import com.kanfa.news.admin.feign.IMessageServiceFeign;
import com.kanfa.news.admin.rpc.message.Jdpush;
import com.kanfa.news.admin.vo.content.ContentResponseInfo;
import com.kanfa.news.admin.vo.message.CityInfo;
import com.kanfa.news.admin.vo.message.MessageInfo;
import com.kanfa.news.admin.vo.message.ProvinceInfo;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 消息推送
 * @author Jiqc
 * @date 2018/3/18 17:59
 */
@RestController
@RequestMapping("/message")
public class MessageRest extends BaseRPC{

    private final Logger LOG = Logger.getLogger("MessageRest");

    @Autowired
    private IMessageServiceFeign messageServiceFeign;
    @Autowired
    private IContentServiceFeign contentServiceFeign;
    @Autowired
    private ILiveServiceFeign liveServiceFeign;


    /**
     * 查询分页
     * @param params
     * @return
     */
    @RequestMapping(value = "/getMessagePage", method = RequestMethod.GET)
    public TableResultResponse<MessageInfo> getMessagePage(@RequestParam Map<String, Object> params) {
        TableResultResponse<MessageInfo> messageInfoList=messageServiceFeign.getMessagePage(params);
        for (MessageInfo messageInfo : messageInfoList.getData().getRows()) {
            messageInfo.setTargetName(NewsEnumsConsts.MessageOfTargetType.getType(messageInfo.getTargetType()));
            messageInfo.setTargetType(null);
            if(messageInfo.getType().equals(1)){//状态，1：友盟，2：极光
                messageInfo.setTypeName("友盟");
            }else if (messageInfo.getType().equals(2)){
                messageInfo.setTypeName("极光");
            }
            messageInfo.setType(null);
        }
        return messageInfoList;
    }

    /**
     * 添加推送
     * @param messageInfo
     * @return
     */
    @RequestMapping(value = "/addPushMessage", method = RequestMethod.POST)
    public ObjectRestResponse<Message> addPushMessage(@RequestBody MessageInfo messageInfo) {
        if(messageInfo.getTargetType()==null){
            getObjectRestResponse("消息类型不能为空");
        }else if(StringUtils.isEmpty(messageInfo.getTitle())){
            getObjectRestResponse("消息标题不能为空");
        }else if(messageInfo.getContentId()==null){
            getObjectRestResponse("请选择内容");
        }else if(StringUtils.isEmpty(messageInfo.getContent())){
            getObjectRestResponse("内容消息内容不能空");
        }else if(messageInfo.getPushTargetList()==null && messageInfo.getPushTargetList().size()<=0){
            getObjectRestResponse("推送对象不能空");
        }
        //推送消息
        pushMessage(messageInfo);
        Message message = new Message();
        BeanUtils.copyProperties(messageInfo,message);
        message.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
        message.setCreateTime(new Date());
        return messageServiceFeign.addPushMessage(message);
    }

    //推送消息
    private String pushMessage(MessageInfo messageInfo) {
        //获取内容信息 title，content，city，
        String segment=null;
        String message = "";
        if(messageInfo.getCityId()!=null){
            ObjectRestResponse<CityInfo> cityOne = messageServiceFeign.getCityOne(messageInfo.getCityId());
            segment=cityOne.getData().getGroupId();
        }
        //封装iso数据
        List<Integer> jpushTypeList = messageInfo.getPushTargetList();
        for (Integer pushType : jpushTypeList) {//1-ios,2-android
            if(pushType.equals(1) && messageInfo.getjPushType().equals(2)){//极光推送  2
                if(messageInfo.getTargetType().equals(NewsEnumsConsts.MessageOfTargetType.NewLive.getKey())){//判断内容类型
                    if(StringUtils.isNotEmpty(segment)){//指定城市+
                        Jdpush.pushIOS(segment,messageInfo,setMapValue(messageInfo));
                    }else{//全部
                         message = Jdpush.pushIOS(null, messageInfo, setMapValue(messageInfo));
                    }
                }else{//非新直播（22）
                    if(StringUtils.isNotEmpty(segment)){//指定城市+
                        message = Jdpush.pushIOS(segment,messageInfo,setMapValue(messageInfo));
                    }else{//全部
                        message = Jdpush.pushIOS(null, messageInfo, setMapValue(messageInfo));
                    }
                }
            }else if(pushType.equals(2) && messageInfo.getjPushType().equals(2)){
                if(messageInfo.getTargetType().equals(NewsEnumsConsts.MessageOfTargetType.NewLive.getKey())){//判断内容类型
                    if(StringUtils.isNotEmpty(segment)){//指定城市+
                        message = Jdpush.pushAndroid(segment,messageInfo,setMapValue(messageInfo));
                    }else{//全部
                        message = Jdpush.pushAndroid(null,messageInfo,setMapValue(messageInfo));
                    }
                }else{//非新直播（22）
                    if(StringUtils.isNotEmpty(segment)){//指定城市+
                        message = Jdpush.pushAndroid(segment,messageInfo,setMapValue(messageInfo));
                    }else{//全部
                        message = Jdpush.pushAndroid(null,messageInfo,setMapValue(messageInfo));
                    }
                }
            }
        }
        return message;
    }
    //设置发送内容
    private Map<String,String> setMapValue(MessageInfo messageInfo){
        Map<String,String> map=new HashMap<>(6);
        setMapValue(messageInfo).put("id",messageInfo.getContentId()+"");
        setMapValue(messageInfo).put("type",messageInfo.getTargetType()+"");
        if(NewsEnumsConsts.MessageOfTargetType.Article.getKey().equals(messageInfo.getTargetType())){
            setMapValue(messageInfo).put("url", AdminCommonConstant.SERVICE_DOMAIN+"web/news/index/"+messageInfo.getContentId()+"/1");
        }else if(NewsEnumsConsts.MessageOfTargetType.Subject.getKey().equals(messageInfo.getTargetType())){//专题
            setMapValue(messageInfo).put("url", AdminCommonConstant.SERVICE_DOMAIN+"web/special/index/"+messageInfo.getContentId()+"/1");
        }else if(NewsEnumsConsts.MessageOfTargetType.Atlas.getKey().equals(messageInfo.getTargetType())){//图集
            setMapValue(messageInfo).put("url", AdminCommonConstant.SERVICE_DOMAIN+"web/image/index/"+messageInfo.getContentId()+"/1");
        }else if(NewsEnumsConsts.MessageOfTargetType.Subject.getKey().equals(messageInfo.getTargetType())){//视频
            setMapValue(messageInfo).put("url", AdminCommonConstant.SERVICE_DOMAIN+"web/video/index/"+messageInfo.getContentId()+"/2");
        }else if(NewsEnumsConsts.MessageOfTargetType.Live.getKey().equals(messageInfo.getTargetType())){//直播
            setMapValue(messageInfo).put("url","初始化数据");
        }else if(NewsEnumsConsts.MessageOfTargetType.VR.getKey().equals(messageInfo.getTargetType())){//活动
            setMapValue(messageInfo).put("url","初始化数据");
        }else if(NewsEnumsConsts.MessageOfTargetType.Question.getKey().equals(messageInfo.getTargetType())){//问答
            setMapValue(messageInfo).put("url",AdminCommonConstant.SERVICE_DOMAIN+"web/problem/index/"+messageInfo.getContentId()+"/1");
        }else if(NewsEnumsConsts.MessageOfTargetType.Activity.getKey().equals(messageInfo.getTargetType()) && messageInfo.getjPushType().equals(2)){//活动
            setMapValue(messageInfo).put("url", AdminCommonConstant.SERVICE_DOMAIN+"web/activity/draw/detail.html?activity_id="+messageInfo.getContentId());
        }else if(NewsEnumsConsts.MessageOfTargetType.NewLive.getKey().equals(messageInfo.getTargetType())){//新直播
            setMapValue(messageInfo).put("live_type_id",messageInfo.getLiveTypeId()+"");
            setMapValue(messageInfo).put("custom_url",messageInfo.getLiveTypeId()+"");
        }
        return map;
    }

    /**
     * 获取省份
     * @return
     */
    @RequestMapping(value = "/getProvince", method = RequestMethod.GET)
    public ObjectRestResponse<List<ProvinceInfo>> getProvince() {
        ObjectRestResponse objectRestResponse=new ObjectRestResponse();
        objectRestResponse.setData(messageServiceFeign.getProvince());
        return objectRestResponse;
    }
    /**
     * 获取城市
     * @return
     */
    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    public ObjectRestResponse<List<CityInfo>> getCity(@RequestParam("provinceId") Integer provinceId) {
        ObjectRestResponse<List<CityInfo>> objectRestResponse=new ObjectRestResponse();
        objectRestResponse.setData(messageServiceFeign.getCity(provinceId));
        return objectRestResponse;
    }

    /**
     * 查询单个
     * @param id
     * @return
     */
    @RequestMapping(value = "/getMessageOne", method = RequestMethod.GET)
    public ObjectRestResponse<MessageInfo> getMessageOne(@RequestParam("id") Integer id) {
        ObjectRestResponse<MessageInfo> messageOne = messageServiceFeign.getMessageOne(id);
        MessageInfo data = messageOne.getData();
        data.setTargetName(NewsEnumsConsts.MessageOfTargetType.getType(data.getTargetType()));
        data.setType(null);
        data.setTargetType(null);
        data.setCreateTime(null);
        data.setErrmsg(null);
        data.setIsDelete(null);
        messageOne.setData(data);
        return messageOne;
    }

    /**
     * 绑定内容查询
     * @param targetType
     * @param title
     * @return
     */
    @RequestMapping(value = "/getContentPageForMessage", method = RequestMethod.GET)
    public TableResultResponse<ContentResponseInfo> getContentPageForMessage(@RequestParam("targetType") Integer targetType,@RequestParam("title") String title) {
        if(targetType==null){
            getObjectRestResponse("内容类型不能为空");
        }else if(StringUtils.isEmpty(title)){
            getObjectRestResponse("内容标题不能为空");
        }
        Map<String,Object> params=new HashMap<>(6);
        params.put("title",title);
        params.put("targetType",targetType);
        params.put("page",1);
        params.put("limit",10);
        if(targetType.equals(22)){
            TableResultResponse<ContentResponseInfo> list = liveServiceFeign.getLiveForMessage(params);
            return list;
        }else{
            TableResultResponse<ContentResponseInfo> list = contentServiceFeign.getContentPageForMessage(params);
            return list;
        }
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
