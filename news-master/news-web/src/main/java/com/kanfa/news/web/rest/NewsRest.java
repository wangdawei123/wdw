package com.kanfa.news.web.rest;

import com.kanfa.news.common.constant.CommonConstants;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.util.BrowseTool;
import com.kanfa.news.common.util.ClientUtil;
import com.kanfa.news.web.feign.IContentServiceFeign;
import com.kanfa.news.web.feign.ILoveServiceFeign;
import com.kanfa.news.web.vo.news.ContentDetailInfo;
import com.kanfa.news.web.vo.news.LoveInfo;
import com.kanfa.news.web.vo.news.ViewContentInfo;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * 新闻
 * @author jiqc
 */
@Controller
@RequestMapping("/news")
public class NewsRest {

    @Autowired
    private IContentServiceFeign contentServiceFeign;
    @Autowired
    private ILoveServiceFeign loveServiceFeign;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    protected HttpServletRequest request;

    private final Integer REQUEST_SOURCE=3;

    /**
     * 获取内容详情
     * @param session
     * @param request
     * @param cid
     * @param cate
     * @param model
     * @return
     */
    @RequestMapping(value = "/index/{cid}/{cate}")
    public String getNewIndex(HttpSession session, HttpServletRequest request, @PathVariable("cid") Integer cid , @PathVariable("cate") Integer cate , Model model) {
        String fontsize = request.getParameter("fontsize");
        Integer original=null;
        if(StringUtils.isNotEmpty(request.getParameter("original"))){
            original = Integer.valueOf(request.getParameter("original"));
        }
        Integer activity=null;
        if(StringUtils.isNotEmpty(request.getParameter("activity"))){
            activity = Integer.valueOf(request.getParameter("activity"));
        }
        if(cid == null || cate == null){
            //错误提示页面
            model.addAttribute("img","/static/app/assets/web/img/404.jpg");
            return "app/redirect/test";
        }
        String sessionid = request.getHeader("SESSIONID");
        Integer uid = null;
        if(null!=sessionid){
            //获取用户id
            Object redisUid = redisTemplate.opsForValue().get(CommonConstants.APPUSERSESSIONID + sessionid);
            if(redisUid!=null){
                uid=Integer.valueOf(redisUid.toString());
            }
        }
        String devid="";
        if(request.getHeader("devID")!=null){
            devid = request.getHeader("devID").toString();
        }
        ContentDetailInfo contentDetailInfo=contentServiceFeign.getNewIndex(cid,cate,fontsize,devid,uid);
        if(contentDetailInfo==null){
            model.addAttribute("img","/static/app/assets/web/img/404.jpg");
            return "app/redirect/test";
        }
        contentDetailInfo.setDevid(devid);
        contentDetailInfo.setUid(uid);
        String uuid=null;
        if(uid!=null){
            uuid=uid.toString();
            contentDetailInfo.setUuid(Integer.valueOf(uuid));
        }else {
            uuid=sessionid;
        }
        //查询用户是否点过赞
        LoveInfo love = new LoveInfo();
        love.setCid(cid);
        List<LoveInfo> loves = contentServiceFeign.getLoveList(love);
        if(loves!=null && loves.size()>0){
            contentDetailInfo.setLoveNum(loves.size());
            for (LoveInfo loveInfo : loves) {
                if((uuid!=null && uuid.equals(loveInfo.getUuid()))||(devid!=null && devid.equals(loveInfo.getDevId()))){
                    contentDetailInfo.setIsLove(1);
                }else {
                    contentDetailInfo.setIsLove(0);
                }
            }
        }else {
            contentDetailInfo.setLoveNum(0);
            contentDetailInfo.setIsLove(0);
        }
        if(NewsEnumsConsts.ContentOfSourceType.Original.getKey().equals(contentDetailInfo.getSourceType())
                && NewsEnumsConsts.ContentOfContentType.Article.getKey().equals(contentDetailInfo.getContentType())){
            original=1;
        }
        //判断活动//活动集合
        List<Map<String, Object>> actvitys = getActities(original, activity, contentDetailInfo);
        contentDetailInfo.setActivitys(actvitys);
        //更新浏览量
        String locf = "in";
        getViewContent(request, cid, uid,locf);
        model.addAttribute("contentDetailInfo",contentDetailInfo);
        model.addAttribute("fontsize",fontsize!=null?fontsize:2);
        model.addAttribute("original",original!=null?original:0);
        model.addAttribute("domain","");
        model.addAttribute("nowtime",System.currentTimeMillis());
        if(NewsEnumsConsts.ContentOfContentStyle.judgment.getKey().equals(contentDetailInfo.getContentStyle())){
            return "app/news/judgment";
        }else if (NewsEnumsConsts.ContentOfContentStyle.notice.getKey().equals(contentDetailInfo.getContentStyle())){
            return "app/news/notice";
        }
        return "app/news/index";
    }

    private ObjectRestResponse getViewContent(HttpServletRequest request,Integer cid,Integer uid,String locf) {
        String userAgent = request.getHeader("User-Agent");
        if(StringUtils.isNotEmpty(userAgent) && BrowseTool.checkBrowse(userAgent)){
            return new ObjectRestResponse();
        }
        String clientIp = ClientUtil.getClientIp(request);
        Integer flag = getIsProxy(request);
        String http_referer = request.getHeader("HTTP_REFERER");
        ViewContentInfo viewContentInfo = new ViewContentInfo();
        viewContentInfo.setCid(cid);
        System.out.println(request.getRemoteAddr());
        viewContentInfo.setIp(clientIp);
        viewContentInfo.setUid(uid);
        Long time = (System.currentTimeMillis() / 1000);
        viewContentInfo.setCreate_date(time.intValue());
        viewContentInfo.setUser_agent(userAgent);
        viewContentInfo.setLocf(locf);
        viewContentInfo.setRequest_source(REQUEST_SOURCE);
        viewContentInfo.setIs_proxy(flag);
        viewContentInfo.setReferer(http_referer);
        return contentServiceFeign.updateContentView(viewContentInfo);
    }

    private List<Map<String, Object>> getActities(Integer original, Integer activity, ContentDetailInfo contentDetailInfo) {
        List<Map<String,Object>> actvitys=new ArrayList<>(2);
        if(original!=null && original==1){
            if (activity!=null && activity==1) {
                Map<String,Object> map=new HashMap<>(2);
                map.put("ico","https://kanfaimage.oss-cn-beijing.aliyuncs.com/20171108/raffle_1510139835328GJiFaiJPdMNrDNR.png");
                map.put("title","欲知后事如何？请看APP分解。");
                actvitys.add(map);
                Map<String,Object> map1=new HashMap<>(2);
                map1.put("ico","https://kanfaimage.oss-cn-beijing.aliyuncs.com/20171108/raffle_1510139835328GJiFaiJPdMNrDNR.png");
                map1.put("title","转发新闻赢iPhoneX，千万人都在玩。");
                actvitys.add(map1);
            }else{
                Map<String,Object> map=new HashMap<>(2);
                map.put("ico","https://kanfaimage.oss-cn-beijing.aliyuncs.com/20171108/raffle_1510139835328GJiFaiJPdMNrDNR.png");
                map.put("title","欲知后事如何？请看APP分解。");
                actvitys.add(map);
            }
            //activity_url
            contentDetailInfo.setActivityUrl("");
        }else{
            Map<String,Object> map=new HashMap<>(2);
            map.put("ico","https://kanfaimage.oss-cn-beijing.aliyuncs.com/20171108/raffle_1510139835328GJiFaiJPdMNrDNR.png");
            map.put("title","欲知后事如何？请看APP分解。");
            actvitys.add(map);
        }
        return actvitys;
    }

    private Integer getIsProxy(HttpServletRequest request) {
        String http_proxy_connection = request.getHeader("HTTP_PROXY_CONNECTION");
        String http_via = request.getHeader("HTTP_VIA");
        String http_x_forwarded_for = request.getHeader("HTTP_X_FORWARDED_FOR");
        Integer flag=0;
        if(StringUtils.isEmpty(http_proxy_connection)){
            flag=1;
        }else if(StringUtils.isEmpty(http_via)){
            flag=1;
        }else if (StringUtils.isEmpty(http_x_forwarded_for)){
            String[] split = http_x_forwarded_for.split(",");
            if(split.length>=2){
                flag=1;
            }
        }
        return flag;
    }

    /**
     * 分享详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getNewDetail", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<ContentDetailInfo> getNewDetail(@RequestParam("id") Integer id) {
        if (id==null){
            getObjectRestResponse("id不能为空");
        }
        return contentServiceFeign.getNewDetail(id);
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateviews")
    @ResponseBody
    public ObjectRestResponse updateviews(HttpServletRequest request,Integer id) {
        //HttpServletRequest request,Integer cid, Integer uid,String locf
        String sessionid = request.getHeader("SESSIONID");
        Integer uid = null;
        if(null!=sessionid){
            //获取用户id
            uid=Integer.valueOf(redisTemplate.opsForValue().get(CommonConstants.APPUSERSESSIONID+sessionid).toString());
        }
        ObjectRestResponse restResponse = getViewContent(request, id, uid, "out");
        return restResponse;
    }

    @RequestMapping(value = "/preview")
    @ResponseBody
    public ObjectRestResponse preview() {
        String sessionid = request.getHeader("SESSIONID");
        Integer uid = null;
        if(null!=sessionid){
            //获取用户id
            uid=Integer.valueOf(redisTemplate.opsForValue().get(CommonConstants.APPUSERSESSIONID+sessionid).toString());
        }
        //查询预览信息

//        ObjectRestResponse restResponse = getViewContent(request, id, uid, "out");
        return null;
    }

    /**
     * 点赞
     * @param devID
     * @param cid
     * @return
     */
    @RequestMapping(value = "/ajaxAdd",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse ajaxAdd(@RequestParam(value = "devID",required = false) String devID,@RequestParam("cid") Integer cid) {
        if(StringUtils.isEmpty(devID)||cid==null){
            return getObjectRestResponse("设备id和文章id不能为空");
        }
        String sessionid = request.getHeader("SESSIONID");
        String uid = null;
        if(null!=sessionid){
            //获取用户id
            uid=redisTemplate.opsForValue().get(CommonConstants.APPUSERSESSIONID+sessionid).toString();
        }else {
            uid=sessionid;
        }
        LoveInfo loveInfo=new LoveInfo();
        loveInfo.setUuid(uid);
        loveInfo.setDevId(devID);
        loveInfo.setCid(cid);
        loveInfo.setCreateTime(new Date());
        ObjectRestResponse restResponse = loveServiceFeign.addLove(loveInfo);
        return restResponse;
    }

    private ObjectRestResponse<ContentDetailInfo> getObjectRestResponse(String msg) {
        ObjectRestResponse<ContentDetailInfo> objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        objectRestResponse.setStatus(506);
        return objectRestResponse;
    }
}
