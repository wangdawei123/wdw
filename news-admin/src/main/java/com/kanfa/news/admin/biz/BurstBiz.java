package com.kanfa.news.admin.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.admin.entity.Burst;
import com.kanfa.news.admin.entity.BurstResource;
import com.kanfa.news.admin.mapper.BurstMapper;
import com.kanfa.news.admin.mapper.BurstResourceMapper;
import com.kanfa.news.admin.vo.burst.BurstInfo;
import com.kanfa.news.admin.vo.burst.BurstResourceInfo;
import com.kanfa.news.admin.vo.burst.BurstUpdateInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 新闻爆料表
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-20 17:00:40
 */
@Service
public class BurstBiz extends BaseBiz<BurstMapper,Burst> {

    @Autowired
    private BurstMapper burstMapper;
    @Autowired
    private BurstResourceMapper burstResourceMapper;

    /**
     * 爆料分页
     * @param params
     * @return
     */
    public TableResultResponse<BurstInfo> burstPage(Map<String, Object> params) {
        Query query=new Query(params);
        Page<BurstInfo> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<BurstInfo> list = burstMapper.getPage(params);
        return new TableResultResponse<BurstInfo>(result.getTotal(), list);
    }

    /**
     * 查询爆料信息
     * @param id
     * @return
     */
    public BurstInfo getBurstInfoById(Integer id) {
        return burstMapper.getBurstInfoById(id);
    }

    /**
     * 更新爆料
     * @param burstUpdateInfo
     * @return
     */
    public BurstUpdateInfo updateBurst(BurstUpdateInfo burstUpdateInfo) {
        Burst burst=new Burst();
        BeanUtils.copyProperties(burstUpdateInfo,burst);
        burst.setUpdateTime(new Date());
        int flag = burstMapper.updateByPrimaryKeySelective(burst);
        List<BurstResourceInfo> burstResourceInfoList = burstUpdateInfo.getBurstResourceInfoList();
        if(flag>0 && burstResourceInfoList!=null && burstResourceInfoList.size()>0){
            BurstResource burstResource1 = new BurstResource();
            burstResource1.setBurstId(burstUpdateInfo.getId());
            burstResourceMapper.delete(burstResource1);
            for (BurstResourceInfo burstResourceInfo : burstResourceInfoList) {
                    BurstResource burstResource=new BurstResource();
                    BeanUtils.copyProperties(burstResourceInfo,burstResource);
                    burstResource.setBurstId(burstUpdateInfo.getId());
                    burstResource.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
                    burstResource.setCreateTime(new Date());
                    burstResource.setUploadStatus(NewsEnumsConsts.BurstResourceOfUploadStatus.Success.key());
                    burstResource.setBurstSource(NewsEnumsConsts.BurstResourceOfBurstSource.CorpUser.key());
                    burstResourceMapper.insertSelective(burstResource);
            }
        }else if (flag==0){
            Assert.notNull(burstUpdateInfo,"修改失败请稍后重试");
        }
        return burstUpdateInfo;
    }
}