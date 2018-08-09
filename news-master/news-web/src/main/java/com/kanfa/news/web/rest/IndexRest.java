package com.kanfa.news.web.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kanfa.news.common.constant.web.WebCommonParams;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.util.HttpSendUtil;
import com.kanfa.news.common.util.MD5Util;
import com.kanfa.news.common.util.RandomCodeUtil;
import com.kanfa.news.web.feign.*;
import com.kanfa.news.web.util.ChangListUtil;
import com.kanfa.news.web.vo.channel.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Jezy
 */
@Controller
@RequestMapping("")
public class IndexRest {
    @Autowired
    private IContentServiceFeign contentServiceFeign;
    @Autowired
    private IUserAuthFeign userAuthFeign;
   @Autowired
    private IUserFeign userFeign;
    @Autowired
    private IContentVideoServiceFeign contentVideoServiceFeign;
    @Autowired
    private ValidateFeign validateFeign;
    @Autowired
    private IFeedbackFeign feedbackFeign;
    @Autowired
    private ISearchServiceFeign searchServiceFeign;

    /**
     * 微博登陆的id
     */
    @Value("${pc.weibo.client_id}")
    private String client_id;

    /**
     * 微博登陆的密钥
     */
    @Value("${pc.weibo.client_secret}")
    private String client_secret;

    /**
     * 微博登陆的回调路径
     */
    @Value("${pc.weibo.redirect_uri}")
    private String redirect_uri;

    /**
     * 网页版首页初始路径
     * @param
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String page(HttpSession session, ContentImageInfo entity,String code ,HttpServletRequest request) {
        //对于选择一个月内免登陆的用户
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for(Cookie cookie : cookies){
                if(WebCommonParams.id.equals(cookie.getName())){
                    //到redis缓存中的查找用户信息
                    if(null != cookie.getValue() || "" != cookie.getValue()){
                        UserAuthInfo userAuthInfo =  userFeign.findRedisUser(cookie.getValue());
                        session.setAttribute("userAuthInfo",userAuthInfo);
                    }
                }
            }
        }

        //微博登陆
        if(code != null){
            //通过code，请求得到token
            String path = WebCommonParams.SENDCODEPATH ;
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("client_id", client_id));
            nvps.add(new BasicNameValuePair("client_secret", client_secret));
            nvps.add(new BasicNameValuePair("grant_type", WebCommonParams.granyt_type));
            nvps.add(new BasicNameValuePair("redirect_uri", redirect_uri));
            nvps.add(new BasicNameValuePair("code", code));
            //2.通过code，请求得到token
            String result = HttpSendUtil.sentPost(nvps, path);
            //解析json数据
            JSONObject jsonObject = JSON.parseObject(result);
            String access_token = jsonObject.getString("access_token");
            String uid = jsonObject.getString("uid");

            //通过uid查找数据库，判断是否第一次登陆
            UserAuthInfo info = userAuthFeign.selecetByWiboOpenId(uid ,access_token);
            session.setAttribute("userAuthInfo",info);
        }

        /**
         *  参数中使用ContentImageInfo接收，是为了接收channelId参数，，当根据channelID查询内容时使用
         */

        if(entity.getChannelId()==null){
            //推荐 频道的id
            entity.setChannelId(309);
        }
        HashMap<String,Object> hashMap =contentServiceFeign.getContentPagePC(entity);
        //从map集合中分别取出三个list集合，并强转
        List<ChannelFocusInfo> focusList = (List<ChannelFocusInfo>)hashMap.get("focus");
        List<ContentImageInfo> contentList = (List<ContentImageInfo>)(hashMap.get("content"));
        List<ChannelInfo> channelList = (List<ChannelInfo>)hashMap.get("channel");
        //分别把三个list集合放在model域中
        session.setAttribute("focusList",focusList);
        session.setAttribute("channelList",channelList);
        session.setAttribute("contentList",contentList);
        //这里把channelId放在model域中，为了在页面拿到值，来判断是主页还是根据channelId查找内容
        session.setAttribute("channelId",entity.getChannelId());
        return "webPc/index";
    }

    /**
     * 退出登陆
     */
    @RequestMapping("/logout")
    @ResponseBody
    public void logout(HttpSession session , HttpServletResponse response , HttpServletRequest request){
        //清除redis中的缓存
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for(Cookie cookie : cookies){
                if(WebCommonParams.id.equals(cookie.getName())){
                    //清除redis缓存中的用户
                    userFeign.removeRedisUser(cookie.getValue());
                }
            }
        }
        session.removeAttribute("userAuthInfo");
        //清除cookie
        Cookie cookie = new Cookie(WebCommonParams.id, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        }

    /**
    滚动下拉页面，异步加载请求
     */
    @ResponseBody
    @RequestMapping(value = "/contentLoad",method = RequestMethod.GET)
    public List<ContentImageInfo> contentLoad(ContentImageInfo entity){
        /*
        查询内容
         */
        //是否是删除状态，1.正常 0.删除
        entity.setContentState(WebCommonParams.CONTENT_STATA);
        //审核状态 0.待审核  1.通过审核 3.审核不通过
        entity.setCheckStatus(WebCommonParams.CHECK_STATUS);
        //这里把页数转换为从第几条开始的数据，不在sql语句中计算了
        entity.setPage((entity.getPage()-1)*entity.getLimit());
        List<ContentImageInfo> rows=contentServiceFeign.getContentActive(entity);
        List<ContentImageInfo> list = ChangListUtil.changeList(rows);
        return list;
    }



    /**
     * 内容跳转到详情页
     */
    @RequestMapping("/detail/{id}/{contentType}")
    public String toDetail(Model model, @PathVariable(name = "id") Integer id, @PathVariable(name = "contentType") Integer contentType){
        List<ContentInfo> hots = contentServiceFeign.hotgetlist(contentType);
        model.addAttribute("hots",hots);
        if(contentType == WebCommonParams.CONTENT_TYPE_SUBJECT || contentType == WebCommonParams.CONTENT_TYPE_ARTICLE ){
            //图文
            ContentInfo content = contentServiceFeign.getContentArticle(id);
            model.addAttribute("content",content);
            return "webPc/newsDetailsArticle";
        }
        if(contentType == WebCommonParams.CONTENT_TYPE_MAPS){
            //图集
            List<ContentImageInfo> rows=contentServiceFeign.getContentImageById(id);
            model.addAttribute("contentImageList",rows);
            return "webPc/newsDetailsImage";
        }
        if(contentType == WebCommonParams.CONTENT_TYPE_VIDEO ){
            //视频
            ContentInfo video = contentVideoServiceFeign.getContentVideoById(id);
            model.addAttribute("video",video);
            return "webPc/newsDetailsVideo";
        }
        return null;
    }

    /**
     * 跳转到关于我们页面
     */
    @RequestMapping("toAbout")
    public String toAbout(){
        return "webPc/about";
    }

    /**
     * 跳转到搜索页面
     */
    @RequestMapping(value = "search/{searchkey}" ,method = RequestMethod.GET)
    public String toSearch(@PathVariable(name = "searchkey") String searchKey ,Model model){
        if(StringUtils.isNotEmpty(searchKey)){
            List<Map<String, Object>> list = (List<Map<String, Object>>)searchServiceFeign.searchDataPage("kf_news_dev_content",
                                                "kf_content" ,1 ,  8 ,searchKey);
            List<ContentImageInfo> data = changeList(list ,searchKey);
            model.addAttribute("contentList",data);
            model.addAttribute("searchKey",searchKey);
        }
        return "webPc/search";
    }

    private  List<ContentImageInfo> changeList(List<Map<String, Object>> list ,String searchkey){
        List<ContentImageInfo> data = new ArrayList<>();
        for(Map<String, Object> map : list){
            ContentImageInfo con = new ContentImageInfo();
            con.setChannelId(Integer.parseInt((String)map.get("chan_id")) );
            con.setImage((String)map.get("image"));
            con.setImageList((List<String>)map.get("images"));
            con.setCreatetime((String)map.get("pub_time"));
            con.setTitle((String)map.get("title"));
            con.setDuration((String) map.get("duration"));
            con.setContentType(Integer.parseInt((String)map.get("content_type")));
            con.setId(Integer.parseInt((String)map.get("id")));
            data.add(con);
        }
        return data;
    }


    /**
      * 搜索页面--滚动下拉页面，异步加载请求
      */
    @ResponseBody
    @RequestMapping(value = "/searchLoad",method = RequestMethod.GET)
    public   List<ContentImageInfo> searchLoad(@RequestParam(name = "searchKey") String searchKey ,
                                             @RequestParam(name = "page",defaultValue = "1") Integer page,
                                             @RequestParam(name = "limit",defaultValue = "8") Integer limit){
        String newKey = null;
        try{
            newKey = URLDecoder.decode(searchKey, "utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
      //  List<ContentImageInfo> rows=contentServiceFeign.searchLoad(newKey,(page-1)*limit ,limit);
        List<Map<String, Object>> list = (List<Map<String, Object>>)searchServiceFeign.searchDataPage("kf_news_dev_content",
                                        "kf_content" ,page ,  limit ,newKey);
        List<ContentImageInfo> data = changeList(list,searchKey);
        return data;
    }

    /**
     * 获取图片验证码
     */
    @RequestMapping("createCode")
    public void createCode(HttpSession session, HttpServletResponse response){
        try{
            Object[] objs = RandomCodeUtil.createImage();
            //将验证码存入Session
            session.setAttribute("code",objs[0]);
            //将图片输出给浏览器
            BufferedImage image = (BufferedImage) objs[1];
            response.setContentType("image/png");
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 验证图片验证码
     */
    @ResponseBody
    @RequestMapping("validCode")
    public String  validCode(String code , HttpSession session){
        Object o = session.getAttribute("code");
        String s = String.valueOf(o);
        if(code.equalsIgnoreCase(s)){
            return "true";
        }else{
            return "false";
        }
    }

    /**
     * 获取手机号码，发送验证码
     */
    @ResponseBody
    @RequestMapping(value = "sendPhoneCode" ,method = RequestMethod.POST)
    public void sendPhoneCode(String number, HttpSession session){
        ObjectRestResponse response = validateFeign.sendPhoneCode(number);
        Map<String, String> data = (Map<String, String>)response.getData();
        String sessionId = data.get("sessionid");
        session.setAttribute("sessionId",sessionId);
    }

    /**
     * 登陆验证-验证手机验证码
     */
    @ResponseBody
    @RequestMapping(value = "checkPhoneCode", method = RequestMethod.POST)
    public ObjectRestResponse checkPhoneCode(String number,String code , HttpServletResponse r, HttpSession session ,Boolean isRemeber){
        String sessionId = (String)session.getAttribute("sessionId");
        //判断输入的手机验证码是否正确
        ObjectRestResponse response = validateFeign.checkPhoneCode(code, sessionId);
        //返回状态码为200，说明输入的验证码正确
        String cookieId = "";
        if(response.getStatus() == WebCommonParams.RETURN_STATUS){
            //设置cookie
            if(isRemeber){
                //调用设置cookie的方法
                cookieId = setCookie(number, r);
            }
            UserAuthInfo u = userFeign.selectUserByPhone(isRemeber ,number ,cookieId);
            session.setAttribute("userAuthInfo",u);
        }
        return response;
    }

    /**
     * 设置cookie
     */
    private String setCookie(String number,HttpServletResponse response){
        String value = MD5Util.md5(number);
        Cookie c = new Cookie(WebCommonParams.id,value);
        //参数表示存活的秒数-30天
        c.setMaxAge(WebCommonParams.CAPTCHA_EXPIRES);
        response.addCookie(c);
        return value;
    }


    /**
     * 用户意见反馈
     */
    @ResponseBody
    @RequestMapping("feedBack")
    private ObjectRestResponse feedBack(String advice , String phone ,HttpSession session ){
        UserAuthInfo o = (UserAuthInfo)session.getAttribute("userAuthInfo");
        ObjectRestResponse result=new ObjectRestResponse();
        if(o == null){
            //用户未登陆
            result.setStatus(WebCommonParams.NO_LOGIN);
            return result;
        }else{
            //用户已经登陆
            Integer id = o.getId();
            result = feedbackFeign.insertMessage(id ,advice ,phone);
            return result;
        }
    }


}

