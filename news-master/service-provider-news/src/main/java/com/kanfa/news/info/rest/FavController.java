package com.kanfa.news.info.rest;

import com.kanfa.news.common.constant.app.AppCommonMessageEnum;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.msg.AppObjectResponse;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.FavBiz;
import com.kanfa.news.info.entity.Fav;
import com.kanfa.news.info.vo.admin.live.Utils.ChangeMapUtil;
import com.kanfa.news.info.vo.admin.live.changemap.LiveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("fav")
public class FavController extends BaseController<FavBiz, Fav> {
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
    public ObjectRestResponse add(@RequestParam(value = "uid") Integer uid,
                                  @RequestParam(value = "cid") Integer cid,
                                  @RequestParam(value = "type", defaultValue = "0") Integer type) {
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        boolean isOperation = this.favBiz.add(uid, cid, type);
        if(isOperation){
            response.setStatus(AppCommonMessageEnum.OK.key());
            response.setMessage(AppCommonMessageEnum.OK.value());
            return response;
        }else{
            response.setStatus(AppCommonMessageEnum.APP_OPERATION_FAILED.key());
            response.setMessage(AppCommonMessageEnum.APP_OPERATION_FAILED.value());
            return response;
        }
    }

    /**
     *  文章取消收藏
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public AppObjectResponse del(@RequestParam(value = "uid") Integer uid,
                                 @RequestParam(value = "cid") Integer cid,
                                 @RequestParam(value = "type") Integer type) {

        AppObjectResponse<Object> response = new AppObjectResponse<>();

        Integer i = favBiz.delFav(cid ,uid ,type);
        if(i == -2) {
            //只能删除自己的评论
            response.setStatus(AppCommonMessageEnum.APP_DEL_COMMENT_ERROR.key());
            response.setMessage(AppCommonMessageEnum.APP_DEL_COMMENT_ERROR.value());
            return response;
        }else if(i == 1){
            response.setStatus(AppCommonMessageEnum.APP_DEL_COMMENT_SUCCESS.key());
            response.setMessage(AppCommonMessageEnum.APP_DEL_COMMENT_SUCCESS.value());
            return response;
        }else{
            //操作过程出现错误
            response.setStatus(AppCommonMessageEnum.APP_OPERATION_FAILED.key());
            response.setMessage(AppCommonMessageEnum.APP_OPERATION_FAILED.value());
            return response;
        }
    }


    /**
     *  按类型获取我的收藏
     */
    @RequestMapping(value = "/getFavList", method = RequestMethod.POST)
    public AppObjectResponse getFavList(@RequestParam("page") Integer page ,
                                        @RequestParam("pcount") Integer pcount ,
                                        @RequestParam("uid") Integer uid,
                                        @RequestParam("type") Integer type) {
        Map<String ,Object> newmap = new HashMap<>(16);
        AppObjectResponse<Object> response = new AppObjectResponse<>();
        if(type.equals(LiveCommonEnum.CONTENT_BROADCAST)){
            Integer[] irr = {LiveCommonEnum.CONTENT_BROADCAST , LiveCommonEnum.CONTENT_LIVE};
            List<Map<String ,Object>> objectMap = favBiz.getListMultType(page ,pcount ,uid ,irr);
            newmap.put("content",objectMap);
        }else{
            List<Map<String, Object>> listType = favBiz.getListType(page ,pcount ,uid ,type);
            newmap.put("content",listType);
        }
        response.setData(newmap);
        return response;
    }

}