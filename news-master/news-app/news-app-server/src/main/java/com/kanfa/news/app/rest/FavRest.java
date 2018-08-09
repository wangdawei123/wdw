package com.kanfa.news.app.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kanfa.news.app.biz.FavBiz;
import com.kanfa.news.app.biz.app.BroadcastBiz;
import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.AppCommonType;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jezy
 */
@RestController
@RequestMapping("fav")
public class FavRest extends BaseRest {
    @Autowired
    private BroadcastBiz broadcastBiz;
    @Autowired
    private FavBiz favBiz;

    /**
     * 文章和直播增加收藏
     *
     * @param uid  用户ID
     * @param cid  文章或视频ID
     * @param type 跳转类型直播需要必填文章可不设置或者设置为0，直播目前设置为22
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ObjectRestResponse getalbum(@RequestParam(value = "uid") Integer uid,
                                       @RequestParam(value = "cid") Integer cid,
                                       @RequestParam(value = "type", defaultValue = "0") Integer type) {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        if (uid == null || cid == null) {
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_MISSING.value());
            return response;
        } else {
            boolean isOperation = this.favBiz.add(uid, cid, type);
            if (isOperation) {
                response.setStatus(AppCommonMessageEnum.OK.key());
                response.setMessage(AppCommonMessageEnum.OK.value());
                return response;
            } else {
                response.setStatus(AppCommonMessageEnum.APP_OPERATION_FAILED.key());
                response.setMessage(AppCommonMessageEnum.APP_OPERATION_FAILED.value());
                return response;
            }
        }
    }


    /**
     * 文章取消收藏
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public AppObjectResponse del(@RequestParam(value = "uid") Integer uid,
                                 @RequestParam(value = "cid") Integer cid,
                                 @RequestParam(value = "type", defaultValue = "0") Integer type) {
         AppObjectResponse<Object> response = new AppObjectResponse<>();
        if (uid == null || cid == null) {
            response.setStatus(AppCommonMessageEnum.APP_PARAMETERS_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_PARAMETERS_MISSING.value());
            return response;
        } else {
            Integer i = favBiz.delFav(cid, uid, type);
            if (i == -2) {
                //只能删除自己的评论
                response.setStatus(AppCommonMessageEnum.APP_DEL_COMMENT_ERROR.key());
                response.setMessage(AppCommonMessageEnum.APP_DEL_COMMENT_ERROR.value());
                return response;
            } else if (i == 1) {
                response.setStatus(AppCommonMessageEnum.APP_DEL_COMMENT_SUCCESS.key());
                response.setMessage(AppCommonMessageEnum.APP_DEL_COMMENT_SUCCESS.value());
                return response;
            } else {
                //操作过程出现错误
                response.setStatus(AppCommonMessageEnum.APP_OPERATION_FAILED.key());
                response.setMessage(AppCommonMessageEnum.APP_OPERATION_FAILED.value());
                return response;
            }
        }
    }


    /**
     * 按类型获取我的收藏
     */
    @RequestMapping(value = "/getFavList", method = RequestMethod.POST)
    public AppObjectResponse getFavList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "pcount", defaultValue = "8") Integer pcount,
                                        @RequestParam(value = "uid") Integer uid, Integer type) {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        Map<String, Object> newmap = new HashMap<>(16);
        if (uid == null) {
            response.setStatus(AppCommonMessageEnum.APP_USER_NAME_MISSING.key());
            response.setMessage(AppCommonMessageEnum.APP_USER_NAME_MISSING.value());
            return response;
        }
        if (type == null) {
            String fav = AppCommonType.fav;
            JSONObject parse = JSON.parseObject(fav);
            JSONArray value = parse.getJSONArray("value");
            newmap.put("cate", value);
            JSONObject json = value.getJSONObject(0);
            String typeJson = json.getString("type");
            type = Integer.parseInt(typeJson);
        }
        Map<String, Object> newmap1 = new HashMap<>(16);
        if (type.equals(LiveCommonEnum.CONTENT_BROADCAST)) {
            Integer[] irr = {LiveCommonEnum.CONTENT_BROADCAST, LiveCommonEnum.CONTENT_LIVE};
            List<Map<String, Object>> objectMap = favBiz.getListMultType(page, pcount, uid, irr);
            newmap1.put("content", objectMap);
        } else {
            List<Map<String, Object>> listType = favBiz.getListType(page, pcount, uid, type);
            newmap1.put("content", listType);
        }
        response.setData(newmap1);
        return response;
    }


    /**
     * 单篇内容收藏状态
     *
     * @param uid
     * @param cid
     * @return
     */
    @RequestMapping(value = "/getOneFavStatus", method = RequestMethod.POST)
    public ObjectRestResponse getOneFavStatus(@RequestParam(value = "uid") Integer uid,
                                              @RequestParam(value = "cid") Integer cid) {
        try {
            return broadcastBiz.getOneFavStatus(uid, cid);
        } catch (Exception e) {
            ObjectRestResponse<Map<String, Object>> result = new ObjectRestResponse<>();
            result.setStatus(CommonConstants.EX_OTHER_CODE);
            result.setMessage("请求接口异常！请联系开发人员");
            return result;
        }
    }
}
