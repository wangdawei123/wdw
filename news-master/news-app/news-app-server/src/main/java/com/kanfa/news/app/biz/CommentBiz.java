package com.kanfa.news.app.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.app.config.RequestConfiguration;
import com.kanfa.news.app.entity.*;
import com.kanfa.news.app.mapper.*;
import com.kanfa.news.app.vo.admin.comment.CommentConstant;
import com.kanfa.news.app.vo.admin.comment.CommentInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.ClientUtil;
import com.kanfa.news.common.util.DateUtil;
import com.kanfa.news.common.util.IPv4Util;
import com.kanfa.news.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 评论表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-16 20:01:59
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentBiz extends BaseBiz<CommentMapper, Comment> {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private SettingMapper settingMapper;
    @Autowired
    private CommentBadwordsMapper commentBadwordsMapper;
    @Autowired
    private CommentBlacklistMapper commentBlacklistMapper;
    @Autowired
    private SettingBiz settingBiz;
    @Autowired
    private ContentBiz contentBiz;
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private LiveBiz liveBiz;
    @Autowired
    private LiveMapper liveMapper;
    @Autowired
    private AppUserBiz userBiz;
    @Autowired
    private AppUserMapper userMapper;
    @Autowired
    protected HttpServletRequest request;

    @Resource
    protected RequestConfiguration requestConfiguration;

    /**
     * 增加评论
     */
    public Integer add(Integer uid ,String nickName ,
                                         String devID , Integer cid ,
                                         Integer pid ,String content ,
                                         Integer type ,boolean sens,
                       HttpServletRequest request1){
        Integer puid = 0;
        if(null != pid){
            Comment comment = commentMapper.selectById(pid);
            if(comment != null){
                if(comment.getCreateUid() != null){
                    puid = comment.getCreateUid();
                }
            }
        }
        CommentInfo comm = new CommentInfo();
        if(null == pid){
            comm.setPid(0);
        }else{
            comm.setPid(pid);
        }
        comm.setSens(sens?1:0);
        comm.setContent(content);
        comm.setPUid(puid);
        comm.setCreateDevid(devID);
        comm.setCid(cid);
        comm.setType(type);
        comm.setCreateUser(nickName);
        comm.setCreateUid(uid);
        comm.setCreateTime(new Date());
        String clientIp = ClientUtil.getClientIp(request1);
        comm.setCreateip(clientIp);
        comm.setOps(LiveCommonEnum.OPS_FALSE);
        if(type.equals(LiveCommonEnum.LIVE_COMMENT)){
            Live live = liveBiz.selectById(cid);
            if(live.getCommentType().equals(LiveCommonEnum.COMMENT_ANSWER)){
                comm.setOps(LiveCommonEnum.OPS_TRUE);
            }
        }else{
            if(cid.equals(LiveCommonEnum.EXTENSION_LIVE_ID)){
                comm.setOps(LiveCommonEnum.OPS_TRUE);
            }
        }
        commentMapper.insertSelectiveComment(comm);
        return comm.getId();
    }


    /**
     * App--用户删除一条评论
     */
    public Integer delOne(Integer uid , String userName ,Integer id){
        Comment comm = commentMapper.selectById(id);
        if(comm == null){
            //删除的评论不存在
            return CommentConstant.COMMENT_NOT_EXIST;
        }
        if(comm.getCreateUid().equals(uid) ||  comm.getCreateUser().equals(userName)){
            Integer cid = comm.getCid();
            comm.setStat(LiveCommonEnum.IS_DEL_STATUS);
            int i = commentMapper.updateStat(comm);
            if(comm.getType().equals(LiveCommonEnum.CONTENT_LIVE)){
                liveBiz.updateCommentCount(cid, LiveCommonEnum.CONSTANT_ONE);
                liveBiz.updateCommentCheckedCount(cid);
            }else{
                contentBiz.updateCommentCount(cid, LiveCommonEnum.CONSTANT_ZERO);
                contentBiz.updateCommentCheckedCount(cid);
            }
            return i;
        }else{
            return CommentConstant.DEL_COMMENT_ERROR;
        }
    }


    public TableResultResponse<CommentInfo> getPage(Map<String, Object> map) {
        Query query = new Query(map);
        Page<CommentInfo> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<CommentInfo> list = commentMapper.getPage(map);
        //是否区分子父关系
        return new TableResultResponse<>(result.getTotal(), list);
    }

    /**
     * 修改未读状态
     */
    public void updateread(Integer uid){
        //检查审核方式
        String value = settingBiz.getByName("comment_ops");
        Comment c = new Comment();
        if("1".equals(value)){
            c.setOps(1);
        }
        c.setCreateUid(uid);
        c.setRead(0);
        c.setSens(0);
        c.setStat(1);
        Integer integer = commentMapper.updateread(c);
    }



    public List<Map<String, Object>> getListByCreator(Integer uid , Integer page ,Integer pcount){
        //检查审核方式
        String value = settingBiz.getByName("comment_ops");
        Integer ops = null;
        //评论审核规则:  0-先审后发, 1-先发后审
        if(LiveCommonEnum.COMMENT_SHEN.equals(value)){
            //是否已审核，1：已审核，0：未审核
            ops = 1;
        }
        Integer offset = (page-1)*pcount;
        Map<String ,Object> params = new HashMap<>(5);
        params.put("uid",uid);
        //自己查看自己的评论，不论是不是审核都应该能查出来
//        params.put("ops",c.getOps());
        params.put("offset",offset);
        params.put("pcount",pcount);
        List<CommentInfo> comments = commentMapper.getByCreator(params);
        if(comments == null || comments.size() == 0){
            return null;
        }
        List<Map<String, Object>>  comList = buildList(comments , ops ,true ,uid);
        return comList;
    }

    /**
     * 获取某uid接收的评论
     * @return [type] [description]
     */
    public List<Map<String, Object>>  getListByReceiver(Integer uid , Integer page ,Integer pcount){
        //检查审核方式
        String value = settingBiz.getByName("comment_ops");
        Integer ops = null;
        //评论审核规则:  0-先审后发, 1-先发后审
        if(LiveCommonEnum.COMMENT_SHEN.equals(value)){
            //是否已审核，1：已审核，0：未审核
            ops = 1;
        }
        Integer offset = (page-1)*pcount;
        Map<String ,Object> params = new HashMap<>(5);
        params.put("uid",uid);
        params.put("ops",ops);
        params.put("offset",offset);
        params.put("pcount",pcount);
        List<CommentInfo> comments = commentMapper.getByReceiver(params);
        if(comments.size() == 0){
            return null;
        }
        List<Map<String, Object>> comList = buildList2(comments , ops ,true ,uid);
        return comList;
    }

    /**
     * 获取别人回复的将评论列表补充上父评论以及用户信息并返回
     * @return [type]       [description]
     */
    public List<Map<String, Object>> buildList2(List<CommentInfo> comments , Integer ops ,boolean r ,Integer uid) {
        List<Integer> pids = new ArrayList<>();
        for (CommentInfo comm : comments) {
            if (null != comm.getPid()) {
                pids.add(comm.getPid());
            }
        }
        //获取评论的父级内容
        Map<String, Object> params = new HashMap<>(5);
        params.put("ops", ops);
        params.put("pids", pids);
        List<CommentInfo> comParentList = null;
        if (pids.size() > 0) {
            comParentList = commentMapper.getByCreator(params);
        }

        List< Map<Integer, Object>> parentList = new ArrayList();
        Map<Integer, Object> pmap = new HashMap<>(5);
        if (comParentList.size() > 0) {
            for (CommentInfo m : comParentList) {
                pmap.put(m.getId(), m);
                parentList.add(pmap);
            }
        }

        Map<Integer, Object> uids = new HashMap<>(5);
        Map<Integer, Object> live_id = new HashMap<>(5);
        Map<Integer, Object> cids = new HashMap<>(5);
        List<Map<String, Object>> cList = new ArrayList();
        for (CommentInfo m : comments) {
            uids.put(m.getCreateUid(), 1);
            if (m.getType().equals(LiveCommonEnum.CONTENT_LIVE)) {
                live_id.put(m.getCid(), m.getType());
            } else {
                cids.put(m.getCid(), m.getType());
            }
        }
        for ( CommentInfo comm : comParentList) {
            uids.put(comm.getCreateUid(), 1);
            if ((comm.getType()).equals(LiveCommonEnum.CONTENT_LIVE)) {
                live_id.put(comm.getCid(),  comm.getType());
            } else {
                cids.put(comm.getCid(),  comm.getType());
            }
        }

        //获取关联的内容
        Map<String, Object> newMap = new HashMap<>(5);
        List<Map<String, Object>> commentList = new ArrayList<>();
        if (r) {
            List<Integer> cidsLiset = new ArrayList<>();
            for(Map.Entry cid : cids.entrySet()){
                if(cid.getValue().equals(LiveCommonEnum.NEWS_COMMENT)){
                    cidsLiset.add((Integer)cid.getKey());
                }
            }
            List<Integer> liveIdLiset = new ArrayList<>();
            for(Map.Entry liveId : live_id.entrySet()){
                if(liveId.getValue().equals(LiveCommonEnum.LIVE_COMMENT)){
                    liveIdLiset.add((Integer)liveId.getKey());
                }
            }
            if (cidsLiset != null && cidsLiset.size() > 0) {
            }
            List<Map<String ,Object>> liveList = null;
            List<Map<String ,Object>> contentList = null;
            if (cidsLiset != null && cidsLiset.size() > 0) {
                contentList = contentBiz.getList(cidsLiset);
            }
            if (liveIdLiset != null && liveIdLiset.size() > 0) {
                liveList = liveBiz.findByIds(liveIdLiset);
            }
            if(liveList != null){
                if(contentList != null){
                    contentList.addAll(liveList);
                }else {
                    contentList = liveList;
                }
            }

            for (Map<String, Object> cmap : contentList) {
                Map<String, Object> map = new HashMap<>(5);
                map.put(cmap.get("id") + "", cmap);
                cList.add(map);
            }
        }
        List<Map<String ,Object>> userList = userBiz.findByIds(uids.keySet());

        //将内容、父级评论、用户信息等整合到评论列表中

        Map<String, Object> relates = null;
        for (CommentInfo comment : comments) {
            Map<String ,Object> commentMap = new HashMap<>(5);
            Map<String ,Object> uMap = new HashMap<>(5);
            //评论创建者用户整理
            if (!comment.getCreateUid().equals(LiveCommonEnum.CONSTANT_ZERO)) {
                for(Map<String ,Object> map : userList){
                    AppUser umap = (AppUser)map.get(comment.getCreateUid()+"");
                    if(umap != null){
                        uMap.put("name", umap.getNickname());
                        uMap.put("nickname", umap.getNickname());
                        uMap.put("image", umap.getImage());
                    }
                }
            }else{
                uMap.put("name", comment.getCreateUser());
                uMap.put("nickname", comment.getCreateUser());
                uMap.put("image",  request.getRequestURI() + LiveCommonEnum.IMAGE_URL);
            }

            if(r){
                for(Map<String ,Object> map : cList){
                    if(map.containsKey(comment.getCid()+"")){
                        relates = (Map<String, Object>)map.get(comment.getCid()+"");
                    }
                }
            }
            //父级评论内容整理
            if(!LiveCommonEnum.CONSTANT_ZERO.equals(comment.getPid())){
                Comment comm = commentMapper.selectById(comment.getPid());
                Map<String ,Object> comment2Map = new HashMap<>(5);
                Map<String, Object> user =  new HashMap<>(5);
                AppUser appU = userMapper.selectByPrimaryKey(comment.getPUid());
                if (appU != null) {
                    user.put("name", appU.getNickname());
                    user.put("nickname", appU.getNickname());
                    if (appU.getImage().contains("/large/")) {
                        user.put("image", appU.getImage());
                    }else {
                        user.put("image", appU.getImage());
                    }
                }else{
                    user.put("name", comm.getCreateUser());
                    user.put("nickname", comm.getCreateUser());
                    user.put("image", request.getRequestURI() + LiveCommonEnum.IMAGE_URL);
                }
                if(comm != null){
                    comment2Map.put("id",comm.getId());
                    comment2Map.put("cid",comm.getCid());
                    comment2Map.put("type",comm.getType());
                    comment2Map.put("pid",comm.getPid());
                    comment2Map.put("puid",comm.getPUid());
                    comment2Map.put("content",comm.getContent());
                    comment2Map.put("loves",comm.getLoves());
                    comment2Map.put("create_time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comm.getCreateTime()));
                    comment2Map.put("create_uid",comm.getCreateUid());
                    comment2Map.put("create_user",comm.getCreateUser());
                    comment2Map.put("create_devid",comm.getCreateDevid());
                    comment2Map.put("user",user);
                    Boolean flag = false;
                    if(comm.getRead() == 1){
                        //已读
                        flag = true;
                    }
                    comment2Map.put("read",flag);
                    comment2Map.put("comment_id",comm.getCommentId());
                }
                ArrayList<Object> objects = new ArrayList<>();
                objects.add(comment2Map);
                commentMap.put("comment",objects);
                commentMap.put("id",comment.getId());
                commentMap.put("cid",comment.getCid());
                commentMap.put("type",comment.getType());
                commentMap.put("pid",comment.getPid());
                commentMap.put("puid",comment.getpUid());
                commentMap.put("content",comment.getContent());
                commentMap.put("loves",comment.getLoves());
                commentMap.put("create_time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(comment.getCreateTime()));
                commentMap.put("create_uid",comment.getCreateUid());
                commentMap.put("create_user",comment.getCreateUser());
                commentMap.put("create_devid",comment.getCreateDevid());
                Boolean flag = false;
                if(comment.getREAD() == 1){
                    //已读
                    flag = true;
                }
                commentMap.put("read",flag);
                if(comment.getCommentId() == null){
                    commentMap.put("comment_id","");
                }else{
                    commentMap.put("comment_id",comment.getCommentId());
                }
                newMap.put("user",uMap);
                newMap.put("related",relates);
                commentMap.putAll(newMap);
                commentList.add(commentMap);
            }
        }
        return commentList;

    }


    /**
     * 将评论列表补充上父评论以及用户信息并返回
     * @param   comments [description]
     * @param   ops  评论审核方式
     * @param   r 是否带着被评论的内容信息
     * @return [type]       [description]
     */
    public List<Map<String, Object>>  buildList(List<CommentInfo> comments , Integer ops ,boolean r ,Integer uid) {

        Set<Integer> pids = new HashSet<>();
        for (CommentInfo comm : comments) {
            if (!LiveCommonEnum.CONSTANT_ZERO.equals(comm.getPid())) {
                pids.add(comm.getPid());
            }
        }
        //获取评论的父级内容
        Map<String, Object> params = new HashMap<>(5);
        params.put("ops", ops);
        params.put("pids", pids);
        List<CommentInfo> comParentList = null;
        if (pids.size() > 0) {
            comParentList = commentMapper.getByCreator(params);
        }

        List< Map<Integer, Object>> parentList = new ArrayList();
        Map<Integer, Object> pmap = new HashMap<>(5);
        if ( null != comParentList) {
            for (CommentInfo m : comParentList) {
                pmap.put(m.getId(), m);
                parentList.add(pmap);
            }
        }
        Map<Integer, Object> uids = new HashMap<>(5);
        Map<Integer, Object> live_id = new HashMap<>(5);
        Map<Integer, Object> cids = new HashMap<>(5);
        List<Map<String, Object>> cList = new ArrayList();
        for (CommentInfo m : comments) {
            uids.put(m.getCreateUid(), 1);
            if (m.getType().equals(LiveCommonEnum.CONTENT_LIVE)) {
                live_id.put(m.getCid(), m.getType());
            } else {
                cids.put(m.getCid(), m.getType());
            }
        }
        if (comParentList != null && comParentList.size() > LiveCommonEnum.CONSTANT_ZERO ) {
            for (CommentInfo m : comParentList) {
                uids.put(m.getCreateUid(), 1);
                cids.put(m.getCid(), m.getType());
            }
        }
        //获取关联的内容
        if (r) {
            List<Integer> cidsLiset = new ArrayList<>();
            for (Map.Entry cid : cids.entrySet()) {
                if (cid.getValue().equals(LiveCommonEnum.NEWS_COMMENT)) {
                    cidsLiset.add((Integer) cid.getKey());
                }
            }
            List<Integer> liveIdLiset = new ArrayList<>();
            for (Map.Entry liveId : live_id.entrySet()) {
                if (liveId.getValue().equals(LiveCommonEnum.LIVE_COMMENT)) {
                    liveIdLiset.add((Integer) liveId.getKey());
                }
            }
            List<Map<String, Object>> liveList = null;
            List<Map<String, Object>> contentList = null;
            if (cidsLiset != null && cidsLiset.size() > 0) {
                contentList = contentBiz.getList(cidsLiset);
            }
            if (liveIdLiset != null && liveIdLiset.size() > 0) {
                liveList = liveBiz.findByIds(liveIdLiset);
            }
            if(liveList != null){
                if(contentList != null){
                    contentList.addAll(liveList);
                }else {
                    contentList = liveList;
                }
            }

            for (Map<String, Object> cmap : contentList) {
                Map<String, Object> map = new HashMap<>(5);
                map.put(cmap.get("id") + "", cmap);
                cList.add(map);
            }
        }
        List<Map<String, Object>> commentList = new ArrayList<>();
        Map<String, Object> newMap = new HashMap<>(5);
        //将内容、父级评论、用户信息等整合到评论列表中

        Map<String, Object> relates = new HashMap<>(5);
        for (CommentInfo comment : comments) {
            Map<String ,Object> commentMap = new HashMap<>(5);
            Map<String ,Object> uMap = new HashMap<>(5);
            //评论创建者用户整理
            if (!comment.getCreateUid().equals(LiveCommonEnum.CONSTANT_ZERO)) {
                AppUser appUser = userMapper.selectByPrimaryKey(comment.getCreateUid());
                uMap.put("name", appUser.getNickname());
                uMap.put("nickname", appUser.getNickname());
                if (appUser.getImage().contains("/large/")) {
                    uMap.put("image", appUser.getImage());
                }else {
                    uMap.put("image", appUser.getImage());
                }
            }else{
                uMap.put("name", comment.getCreateUser());
                uMap.put("nickname", comment.getCreateUser());
                uMap.put("image",   LiveCommonEnum.IMAGE_URL);
            }

            if(r){
                Map<String, Object> conns = null;
                for(Map<String ,Object> map : cList){
                    if(map.containsKey(comment.getCid()+"")){
                        conns = (Map<String, Object>)map.get(comment.getCid()+"");
                    }
                }
                if(comment.getType().equals(LiveCommonEnum.POLITICAL_COMMENT)){
                    relates.put("title",conns.get("title"));
                    relates.put("content_url",request.getRequestURI()+"/web/political/detail?id="+ conns.get("id"));
                }else if(comment.getType().equals(LiveCommonEnum.ACTIVITY_COMMENT)){
                    relates.put("title",conns.get("title"));
                    relates.put("content_url",request.getRequestURI()+"/web/event/evenList/"+ conns.get("id"));
                }else if(comment.getType().equals(LiveCommonEnum.REVELATION_COMMENT)){
                    relates.put("title",conns.get("title"));
                }else{
                    relates = conns;
                }
            }

            //父级评论内容整理
            if(uid == null){
                if(!LiveCommonEnum.CONSTANT_ZERO.equals(comment.getPid())){
                    Map<String, Object> user =  new HashMap<>(5);
                    Comment comm = commentMapper.selectById(comment.getPid());
                    AppUser appU = userMapper.selectByPrimaryKey(comment.getPUid());
                    if (appU != null) {
                        user.put("name", appU.getNickname());
                        user.put("nickname", appU.getNickname());
                        if (appU.getImage().contains("/large/")) {
                            user.put("image", appU.getImage());
                        }else {
                            user.put("image", appU.getImage());
                        }
                    }else{
                        user.put("name", comm.getCreateUser());
                        user.put("nickname", comm.getCreateUser());
                        user.put("image", request.getRequestURI() + LiveCommonEnum.IMAGE_URL);
                    }
                    Map<String ,Object> comment2Map = new HashMap<>(5);
                    if(comm != null){
                        comment2Map.put("id",comm.getId());
                        comment2Map.put("cid",comm.getCid());
                        comment2Map.put("type",comm.getType());
                        comment2Map.put("pid",comm.getPid());
                        comment2Map.put("puid",comm.getPUid());
                        comment2Map.put("content",comm.getContent());
                        comment2Map.put("loves",comm.getLoves());
                        String s = DateUtil.fromTodayFormat(comm.getCreateTime());
                        comment2Map.put("create_time",s);
                        comment2Map.put("create_uid",comm.getCreateUid());
                        comment2Map.put("create_user",comm.getCreateUser());
                        comment2Map.put("create_devid",comm.getCreateDevid());
                        comment2Map.put("user",user);
                        Boolean flag = false;
                        if(comm.getRead() == 1){
                            //已读
                            flag = true;
                        }
                        comment2Map.put("read",flag);
                        comment2Map.put("comment_id",comm.getCommentId());
                    }else{
                        comment2Map.put("content","该评论已经被删除");
                    }
                    ArrayList<Object> objects = new ArrayList<>();
                    objects.add(comment2Map);
                    commentMap.put("comment",objects);
                }
            }

            Map<String, Object> checkBadwords = checkBadwords(comment.getContent());

            commentMap.put("id",comment.getId());
            commentMap.put("cid",comment.getCid());
            commentMap.put("type",comment.getType());
            commentMap.put("pid",comment.getPid());
            commentMap.put("puid",comment.getpUid());
            commentMap.put("content",checkBadwords.get("content"));
            commentMap.put("loves",comment.getLoves());
            String s = DateUtil.fromTodayFormat(comment.getCreateTime());
            commentMap.put("create_time",s);
            commentMap.put("create_uid",comment.getCreateUid());
            commentMap.put("create_user",comment.getCreateUser());
            commentMap.put("create_devid",comment.getCreateDevid());

            Boolean flag = false;
            if(comment.getREAD() == 1){
                //已读
                flag = true;
            }
            commentMap.put("read",flag);
            if(comment.getCommentId() == null){
                commentMap.put("comment_id","");
            }else{
                commentMap.put("comment_id",comment.getCommentId());
            }
            newMap.put("user",uMap);
            newMap.put("related",relates);
            commentMap.putAll(newMap);
            commentList.add(commentMap);
        }
        return commentList;
    }


    /**
     * 检查是否在黑名单中
     */
    public Boolean checkBlacklist(Integer uid ,String devId){
        Boolean flag = false;
        if(null != uid){
            CommentBlacklist c = new CommentBlacklist();
            c.setUid(uid);
            int i = commentBlacklistMapper.selectCount(c);
            if(i > 0){
                flag = true;
            }
        }else if(devId != null){
            CommentBlacklist c = new CommentBlacklist();
            c.setDeviceId(devId);
            int i = commentBlacklistMapper.selectCount(c);
            if(i > 0){
                flag = true;
            }
        }
        return flag;
    }


    /**
     * 检查是否包含敏感词和非法词：对于敏感词，替换成星号
     * @param
     * @return [type]          [description]
     */
    public Map<String, Object> checkBadwords(String content){
        CommentBadwords badwords = commentBadwordsMapper.selectOne(null);
        Boolean bads = false;
        Boolean sens = false;
        String bad = badwords.getIllegalWord();
        String sensitive = badwords.getSensitiveWord();
        String[] badA = bad.split(",");
        String[] sensitiveA = sensitive.split(",");
        for(int i = 0 ; i < badA.length ; i ++){
            if(content.contains(badA[i])){
                bads = true;
                content.replace(badA[i] , "*");
            }
        }
        for(int i = 0 ; i < sensitiveA.length ; i ++){
            if(content.contains(sensitiveA[i])){
                sens = true;
            }
        }
        Map<String, Object> map = new HashMap<>(5);
        map.put("bads",bads);
        map.put("sens",sens);
        map.put("content",content);
        return map;
    }


    /**
     * 分页查询（对应审核）
     *
     * @param commentInfo
     * @return
     */
    public TableResultResponse<CommentInfo> getPageForCheck(CommentInfo commentInfo) {
        Page<CommentInfo> result = PageHelper.startPage(commentInfo.getPage(), commentInfo.getLimit());
        List<CommentInfo> list = commentMapper.getPageForCheck(commentInfo);
        return new TableResultResponse<>(result.getTotal(), list);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public Integer batchDelete(List<Integer> ids) {
        int deleteCount = 0;
        Map<Integer, Integer> idsOfAllCount=new HashMap<>(ids.size());
        Map<Integer, Integer> idsOfCheck = new HashMap<>(ids.size());
        for (Integer id : ids) {
            Comment comment = new Comment();
            comment.setId(id);
            comment.setStat(0);
            int flag = commentMapper.updateByPrimaryKeySelective(comment);
            if (flag > 0) {
                deleteCount++;
                //更新内容的总评论数和审核评论数
                Comment comment1 = commentMapper.selectById(id);
                if(comment1.getOps().equals(NewsEnumsConsts.ContentOfCheckStatus.PASS.key()));{
                    Integer integer = idsOfCheck.get(comment1.getCid());
                    if (integer == null) {
                        idsOfCheck.put(comment1.getCid(), 1);
                    } else {
                        idsOfCheck.put(comment1.getCid(), integer + 1);
                    }
                }
                //总评论数
                Integer integer = idsOfAllCount.get(comment1.getCid());
                if (integer == null) {
                    idsOfAllCount.put(comment1.getCid(), 1);
                } else {
                    idsOfCheck.put(comment1.getCid(), integer + 1);
                }
            }
        }
        //更新内容审核评论数量
        updateContentCommentCheckCount(idsOfCheck,0);
        //更新内容评论总数
        updateCommentAllCount(idsOfAllCount);
        return deleteCount;
    }

    //更新内容评论总数减少
    private void updateCommentAllCount(Map<Integer,Integer> map) {

        ArrayList<Integer> idsOfAllCount = new ArrayList<>(map.size());
        map.forEach((k,v)->idsOfAllCount.add(k));
        List<Content> contents = contentMapper.selectCommentCountByIds(idsOfAllCount);
        if(contents!=null){
            for (Content content : contents) {
                Content content1=new Content();
                content1.setId(content.getId());
                if(content.getCommentCheckedCount()>0 && content.getCommentCheckedCount()>map.get(content1.getId())){
                    content1.setCommentCount(content.getCommentCount()-map.get(content1.getId()));
                    contentMapper.updateByPrimaryKeySelective(content1);
                }
            }
        }
    }

    //更新内容审核评论数量
    private void updateContentCommentCheckCount(Map<Integer,Integer> map,Integer type) {

        ArrayList<Integer> ids = new ArrayList<>(map.size());
        map.forEach((k,v)->ids.add(k));
        List<Content> contentList = contentMapper.selectCommentCountByIds(ids);
        if(contentList!=null){
            for (Content cont : contentList) {
                Content content=new Content();
                content.setId(cont.getId());
                if(type.equals(1)){//增加
                    content.setCommentCheckedCount(cont.getCommentCheckedCount()+map.get(cont.getId()));
                    contentMapper.updateByPrimaryKeySelective(content);
                }else{//减少
                    if(cont.getCommentCheckedCount()>0 && cont.getCommentCheckedCount()>map.get(cont.getId())){
                        content.setCommentCheckedCount(cont.getCommentCheckedCount()-map.get(cont.getId()));
                        contentMapper.updateByPrimaryKeySelective(content);
                    }
                }
            }
        }
    }



    /**
     * 评论设置
     * @param params
     * @return
     */
    public Integer saveCommentSetting(Map<String, Object> params) {
        Setting commentOps = settingMapper.selectSettingByName("comment_ops");
        Integer flag = 1;
        if (commentOps != null) {
            commentOps.setValue(params.get("commentOps").toString());
            commentOps.setDesc("评论审核规则:0-先审后发,1-先发后审");
            flag = flag * settingMapper.updateSettingByName(commentOps);
        } else {
            Setting setting = new Setting();
            setting.setName("comment_ops");
            setting.setValue(params.get("commentOps").toString());
            setting.setDesc("评论审核规则:0-先审后发,1-先发后审");
            flag = flag * settingMapper.insertSetting(setting);
        }
        List<CommentBadwords> commentBadwords = commentBadwordsMapper.selectAll();
        if (commentBadwords != null && commentBadwords.size() > 0) {
            CommentBadwords commentBadwords1 = commentBadwords.get(0);
//            if (StringUtils.isNotEmpty(commentBadwords1.getSensitiveWord())) {// sensitiveWord(敏感)   illegalWord（非法）
//                commentBadwords1.setSensitiveWord(commentBadwords1.getSensitiveWord() + "," + params.get("sensitiveWord"));
//            } else {
//            }
            commentBadwords1.setSensitiveWord(params.get("sensitiveWord").toString());
//            if (StringUtils.isNotEmpty(commentBadwords1.getIllegalWord())) {// sensitiveWord(敏感)   illegalWord（非法）
//                commentBadwords1.setIllegalWord(commentBadwords1.getIllegalWord() + "," + params.get("illegalWord"));
//            } else {
//            }
            commentBadwords1.setIllegalWord(params.get("illegalWord").toString());
            flag = flag * commentBadwordsMapper.updateByPrimaryKeySelective(commentBadwords1);
        } else {
            CommentBadwords commentBadwords1 = new CommentBadwords();
            commentBadwords1.setSensitiveWord(params.get("sensitiveWord").toString());
            commentBadwords1.setIllegalWord(params.get("illegalWord").toString());
            flag = flag * commentBadwordsMapper.insertSelective(commentBadwords1);
        }
        return flag;
    }




    public  List<Map<String, Object>> selectComment(Integer cid , Integer p , Integer pcount , Integer ctype , Integer type ,Integer uid){
        //评论审核规则:   0-先审后发, 1-先发后审
        String opsType = null;
        //如果是文章的评论，就查询setting表，确定评论规则
        if(LiveCommonEnum.NEWS_COMMENT.equals(type)){
            opsType = settingBiz.selectByname("comment_ops");
        }else if(LiveCommonEnum.LIVE_COMMENT.equals(type)){
            //如果是直播的评论，就查直播表里的相应字段
            opsType =liveMapper.selectByPrimaryKey(cid).getCommentType()+"";
        }else{
            System.out.println("查询评论时，传入的type!=0 && type!=22.....");
        }
        //是否已审核，1：已审核，0：未审核
        Integer ops = null;
        //如果要  0-先审后发
        if(LiveCommonEnum.COMMENT_SHEN.equals(opsType)){
            //审核状态必须是已审核
            ops = 1;
        }

        // uid == 0 说明是游客  uid != 0 说明用户是登陆状态
        // 如果用户是登陆状态，他自己发表的评论不管有没有审核都可以查看

        List<CommentInfo> list = commentMapper.getListByConditions(cid,ops,  type ,uid ,(p-1)*pcount ,pcount);

        if(list.size() == (LiveCommonEnum.CONSTANT_ZERO)){
            return new ArrayList<>();
        }
        List<Map<String, Object>> commentInfos = buildList(list, 1, true, null);
        return  commentInfos;
    }

    /**
     * 通过审核
     * @param id
     * @return
     */
    public Comment passComment(Integer id) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setOps(NewsEnumsConsts.ContentOfCheckStatus.PASS.key());
        int flag = commentMapper.updateByPrimaryKeySelective(comment);
        if(flag>0){
            Comment comment1 = commentMapper.selectById(id);
            Map<Integer, Integer> idsOfCheck = new HashMap<>(1);
            idsOfCheck.put(comment1.getCid(),1);
            //更新审核平路数量
            updateContentCommentCheckCount(idsOfCheck,1);
            return comment1;
        }
        return null;
    }

    /**
     * 批量通过
     * @param ids
     * @return
     */
    public Comment batchPassComment(List<Integer> ids) {
        if (ids.size() > 0) {
            Map<Integer, Integer> idsOfCheck = new HashMap<>(ids.size());
            for (Integer id : ids) {
                Comment comment = new Comment();
                comment.setId(id);
                comment.setOps(NewsEnumsConsts.ContentOfCheckStatus.PASS.key());
                int flag = commentMapper.updateByPrimaryKeySelective(comment);
                if (flag > 0) {
                    Comment comment1 = commentMapper.selectById(id);
                    Integer integer = idsOfCheck.get(comment1.getCid());
                    if (integer == null) {
                        idsOfCheck.put(comment1.getCid(), 1);
                    } else {
                        idsOfCheck.put(comment1.getCid(), integer + 1);
                    }
                }
            }
            //更新审核平路数量
            updateContentCommentCheckCount(idsOfCheck, 1);
        }
        return new Comment();
    }

    /**
     * 删除单个评论
     * @param id
     * @return
     */
    public Comment deleteComment(Integer id) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setStat(NewsEnumsConsts.ContentOfContentState.DELETE.key());
        int flag = commentMapper.updateByPrimaryKeySelective(comment);
        if(flag>0){
            Comment comment1 = commentMapper.selectById(id);
            Map<Integer, Integer> idsOfCheck = new HashMap<>(1);
            Map<Integer, Integer>  idsOfAll=new HashMap<>(1);
            if(comment1.getOps().equals(NewsEnumsConsts.ContentOfCheckStatus.PASS.key())){
                idsOfCheck.put(comment1.getCid(),1);
                //更新审核平路数量减少
                updateContentCommentCheckCount(idsOfCheck,0);
            }
            idsOfAll.put(comment1.getCid(),1);
            updateCommentAllCount(idsOfAll);
            return comment1;
        }
        return null;
    }

    /**
     * 屏蔽
     * @param commentInfo
     * @return
     */
    public CommentInfo updateCommentOfBlock(CommentInfo commentInfo) {
        //屏蔽
        CommentBlacklist commentBlacklist = new CommentBlacklist();
        commentBlacklist.setUid(commentInfo.getpUid());
        commentBlacklist.setDeviceId(commentInfo.getCreateDevid());
        List<CommentBlacklist> select = commentBlacklistMapper.select(commentBlacklist);
        if(select!=null && select.size()>0){
            return commentInfo;
        }
        CommentBlacklist commentBlacklist1=new CommentBlacklist();
        if(commentInfo.getpUid()!=null){
            AppUser appUser = userMapper.selectByPrimaryKey(commentInfo.getpUid());
            if(appUser!=null){
                commentBlacklist1.setUid(commentInfo.getpUid());
                commentBlacklist1.setName(appUser.getNickname());
                commentBlacklist1.setCreateTime(new Date());
                commentBlacklistMapper.insertSelective(commentBlacklist1);
            }
        }else{
            commentBlacklist1.setDeviceId(commentInfo.getCreateDevid());
            commentBlacklist1.setName(commentInfo.getCreateUser());
            commentBlacklist1.setCreateTime(new Date());
            commentBlacklistMapper.insertSelective(commentBlacklist1);
        }
        //删除评论
        deleteComment(commentInfo.getId());
        return commentInfo;
    }
}