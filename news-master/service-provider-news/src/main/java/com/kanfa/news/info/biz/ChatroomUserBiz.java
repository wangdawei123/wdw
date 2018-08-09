package com.kanfa.news.info.biz;

import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.info.entity.AppUser;
import com.kanfa.news.info.entity.ChatroomUser;
import com.kanfa.news.info.mapper.AppUserMapper;
import com.kanfa.news.info.mapper.ChatroomUserMapper;
import io.rong.RongCloud;
import io.rong.methods.User;
import io.rong.models.ChatRoomInfo;
import io.rong.models.CodeSuccessResult;
import io.rong.models.TokenResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jezy
 * @date 2018-03-28 11:53:56
 */
@Service
@Slf4j
public class ChatroomUserBiz extends BaseBiz<ChatroomUserMapper, ChatroomUser> {

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ChatroomUserMapper chatroomUserMapper;

    @Autowired
    RongCloud rongCloud;

    private static final String DEFAULT_NICK_NAME = "匿名用户";

    @Value("${app.video.album.shareUrl}")
    private String defaultUserImage;
    @Value("${spring.rong-cloud.app-key}")
    private String accessKeyId;
    @Value("${spring.rong-cloud.app-secret}")
    private String accessKeySecret;


    /**
     * 根据用户ID 昵称 刷新融云昵称
     */
    public Boolean refreshNickName(Integer uid , String name){
        ChatroomUser c = new ChatroomUser();
        c.setUid(uid+"");
        ChatroomUser chatroomUser = chatroomUserMapper.selectOne(c);
        if(chatroomUser == null){
            return true;
        }
        //更新融云信息
        RongCloud rySdk = getRYSdk();
        User user = rySdk.user;
        CodeSuccessResult result = null;
        try{
            result = user.refresh(uid + "", name, "http://kanfaimage.oss-cn-beijing.aliyuncs.com/defaultlogo.png");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(result.getCode() == 200){
            //更新本地库用户昵称
            saveToken(uid+"" ,name , "", false);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获取融云SDK实例
     */
    public RongCloud getRYSdk(){
        RongCloud rongCloud = RongCloud.getInstance(accessKeyId, accessKeySecret);
        return rongCloud;
    }


    public String createChatRoom(Integer contentId ){
        String type = "live";
        if(contentId != null) {
            String id = type + "_" + contentId;
            String name = "kf_Chatroom_" + type + "_" + contentId;
            ChatRoomInfo chatRoomInfo = new ChatRoomInfo(id, name);
            ChatRoomInfo[] arr = new ChatRoomInfo[]{chatRoomInfo};
            try{
                CodeSuccessResult result= rongCloud.chatroom.create(arr);
                if(result.getCode()==200){
                    return id;
                }
            }catch (Exception e){
                // log.error(e.getMessage());
            }
        }
    return null;
    }

    public ObjectRestResponse<String> getToken(Integer uid,
                                                String devId,
                                                String IDFA,
                                                String PLATFORM,
                                                String chatroomId) {
        boolean isKanFaUser = true;
        String token = "";
        AppUser appUser = new AppUser();
        appUser.setId(uid);
        appUser.setIsDelete(1);
        AppUser appUserInfo = appUserMapper.selectOne(appUser);
        if (appUserInfo == null) {
            appUserInfo.setNickname(DEFAULT_NICK_NAME);
            isKanFaUser = false;
        }

        Example example = new Example(ChatroomUser.class);
        example.createCriteria().andEqualTo("uid", uid);
        List<ChatroomUser> chatRoomUserList = this.selectByExample(example);
        if (chatRoomUserList != null && chatRoomUserList.size() > 0) {
            token = chatRoomUserList.get(0).getToken();
        } else {
            String image = appUserInfo.getImage() != null ? appUserInfo.getImage() : defaultUserImage;
            try {
                TokenResult tokenResult = rongCloud.user.getToken(String.valueOf(uid), appUserInfo.getNickname(), image);
                Assert.isTrue(tokenResult.getCode().intValue() == HttpStatus.OK.value(), tokenResult.getErrorMessage());
                token = tokenResult.getToken();
            } catch (Exception e) {
  //              log.error(e.getMessage());
               // log.error(e.getMessage());
            }
        }

        if (StringUtils.hasText(token)) {
            boolean isAppUser = false;
            if (isKanFaUser) {
                isAppUser = true;
            }
            Map<String, Object> chatRootMongoMap = new HashMap<>();
            chatRootMongoMap.put("uid", uid);
            chatRootMongoMap.put("chatroomID", chatroomId);
            chatRootMongoMap.put("isAppUser", isAppUser);
            chatRootMongoMap.put("time", LocalDate.now());
            this.mongoTemplate.save(chatRootMongoMap,"chatRootUser");
        }
        saveToken((String.valueOf(uid)), appUserInfo.getNickname(), token, isKanFaUser);
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        Map<String, String> dataMap = new HashMap<>(16);
        dataMap.put("token", token);
        objectRestResponse.setData(dataMap);
        return objectRestResponse;
    }

    public void saveToken(String uid, String nickName, String token, boolean defaultUser) {
        ChatroomUser chatroomUser = new ChatroomUser();
        if (!StringUtils.hasText(token)) {
            chatroomUser.setUid(uid);
            chatroomUser = this.selectOne(chatroomUser);
            chatroomUser.setUsername(nickName);
            chatroomUser.setUpdatetime((int)System.currentTimeMillis());
        } else {
            chatroomUser = new ChatroomUser();
            chatroomUser.setUid(uid);
            chatroomUser.setUsername(nickName);
            chatroomUser.setToken(token);
            chatroomUser.setDefaultuser(defaultUser ? 1 : 0);
            chatroomUser.setBantime(0);
            chatroomUser.setBancycle(0);
            chatroomUser.setCreatetime((int)(System.currentTimeMillis()));
        }
        if (chatroomUser.getId() != null) {
            this.updateSelectiveById(chatroomUser);
        } else {
            this.insert(chatroomUser);
        }
    }

}