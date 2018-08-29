package com.kanfa.news.info.vo.admin.app;

import com.kanfa.news.info.entity.Comment;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/3/19.
 */
public class AppContent {
    private  Integer id ;
    private String title;
    private Integer is_faved;
    private Integer posts_ops;
    private String share_image;
    private String share_url;
    private Map user;
    private List<Map> news;
    private Object extend;
    private String create_time;
    private Integer comments;
    private Boolean comment_tourist;
    private List<Map> bind;
    private String desc;
    private String image;
    private Map activity;
    private Integer activity_static;
    private Integer is_liked;
    private Integer likes;
    private List<Comment> comment;
    private String score;

    public String getScore() {
        return score;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<Map> getNews() {
        return news;
    }

    public void setNews(List<Map> news) {
        this.news = news;
    }

    public Integer getIs_liked() {
        return is_liked;
    }

    public void setIs_liked(Integer is_liked) {
        this.is_liked = is_liked;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getComment_tourist() {
        return comment_tourist;
    }

    public void setComment_tourist(Boolean comment_tourist) {
        this.comment_tourist = comment_tourist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIs_faved() {
        return is_faved;
    }

    public void setIs_faved(Integer is_faved) {
        this.is_faved = is_faved;
    }

    public Integer getPosts_ops() {
        return posts_ops;
    }

    public void setPosts_ops(Integer posts_ops) {
        this.posts_ops = posts_ops;
    }

    public String getShare_image() {
        return share_image;
    }

    public void setShare_image(String share_image) {
        this.share_image = share_image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Map getUser() {
        return user;
    }

    public void setUser(Map user) {
        this.user = user;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map getActivity() {
        return activity;
    }

    public void setActivity(Map activity) {
        this.activity = activity;
    }

    public Integer getActivity_static() {
        return activity_static;
    }

    public void setActivity_static(Integer activity_static) {
        this.activity_static = activity_static;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public List<Map> getBind() {
        return bind;
    }

    public void setBind(List<Map> bind) {
        this.bind = bind;
    }
}
