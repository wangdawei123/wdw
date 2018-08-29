package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.CorpUser;
import com.kanfa.news.app.mapper.CorpUserMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司人员表
 *
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-12 14:22:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CorpUserBiz extends BaseBiz<CorpUserMapper,CorpUser> {

    @Autowired
    private CorpUserMapper corpUserMapper;


    //我的 上传视频里面的记者字典
    public List<CorpUser> getCorpUserIdName(){
        ArrayList<CorpUser> corpUsers = new ArrayList<>();
        List<CorpUser> corpUserIdName1 = corpUserMapper.getCorpUserIdName();
        List<CorpUser> cropUserIdName2 = corpUserMapper.selectCropUserIdName();
        corpUsers.addAll(corpUserIdName1);
        corpUsers.addAll(cropUserIdName2);
        return corpUsers;
    }
}