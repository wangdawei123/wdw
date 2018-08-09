package com.kanfa.news.info.vo.admin.info;

import com.kanfa.news.info.entity.CorpDept;
import com.kanfa.news.info.entity.CorpUser;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/14 13:46
 */
public class CorpUserAndDeptInfo implements Serializable{
    private List<CorpUser> corpUsers;
    private List<CorpDept> corpDepts;

    public List<CorpUser> getCorpUsers() {
        return corpUsers;
    }

    public void setCorpUsers(List<CorpUser> corpUsers) {
        this.corpUsers = corpUsers;
    }

    public List<CorpDept> getCorpDepts() {
        return corpDepts;
    }

    public void setCorpDepts(List<CorpDept> corpDepts) {
        this.corpDepts = corpDepts;
    }
}
