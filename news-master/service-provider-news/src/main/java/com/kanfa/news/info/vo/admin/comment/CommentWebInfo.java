package com.kanfa.news.info.vo.admin.comment;

import java.io.Serializable;

/**
 * Created by Chen
 * on 2018/6/14 13:46
 */
public class CommentWebInfo  implements Serializable{


    //创建人
    private String create_user;
    //创建时间
    private String create_time;
    //用户头像
    private String user_image;
    //评论内容
    private String content;

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
