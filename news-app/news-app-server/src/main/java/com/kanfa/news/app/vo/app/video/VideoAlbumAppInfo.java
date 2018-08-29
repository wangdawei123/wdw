package com.kanfa.news.app.vo.app.video;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Jezy
 */
public class VideoAlbumAppInfo implements Serializable {
    private Integer id;
    private String title;
    private Integer share_status;
    private List<VideoDate> video_data;
    private Share share = new Share();

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

    public Integer getShare_status() {
        return share_status;
    }

    public void setShare_status(Integer share_status) {
        this.share_status = share_status;
    }

    public List<VideoDate> getVideo_data() {
        return video_data;
    }

    public void setVideo_data(List<VideoDate> video_data) {
        this.video_data = video_data;
    }

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public static class VideoDate {
        private Integer id;
        private String title;
        private Integer type;
        private Integer views;
        private String desc;
        private Date create_time;
        private Integer comments;
        private String con_img;
        private String image;
        private String url;
        private String duration;
        private String source_image;
        private String source_name;
        private Integer is_liked;
        private String share;
        private String share_img;
        private Integer show_view;

        public Integer getShow_view() {
            return show_view;
        }

        public void setShow_view(Integer show_view) {
            this.show_view = show_view;
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

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getViews() {
            return views;
        }

        public void setViews(Integer views) {
            this.views = views;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Date getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Date create_time) {
            this.create_time = create_time;
        }

        public Integer getComments() {
            return comments;
        }

        public void setComments(Integer comments) {
            this.comments = comments;
        }

        public String getCon_img() {
            return con_img;
        }

        public void setCon_img(String con_img) {
            this.con_img = con_img;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getSource_image() {
            return source_image;
        }

        public void setSource_image(String source_image) {
            this.source_image = source_image;
        }

        public String getSource_name() {
            return source_name;
        }

        public void setSource_name(String source_name) {
            this.source_name = source_name;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getShare_img() {
            return share_img;
        }

        public void setShare_img(String share_img) {
            this.share_img = share_img;
        }

        public Integer getIs_liked() {
            return is_liked;
        }

        public void setIs_liked(Integer is_liked) {
            this.is_liked = is_liked;
        }
    }

    public class Share {
        private String title;
        private String subtitle;
        private String url;
        private String img;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}

