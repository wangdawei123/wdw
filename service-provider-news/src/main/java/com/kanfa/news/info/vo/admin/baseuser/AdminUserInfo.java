package com.kanfa.news.info.vo.admin.baseuser;

/**
 * @author Jiqc
 * @date 2018/3/7 13:38
 */
public class AdminUserInfo {
    private Integer id;

    //用户名
    private String nickname;

    //是否选中
    private Integer isSelect;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Integer isSelect) {
        this.isSelect = isSelect;
    }
}
