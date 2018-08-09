package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.util.Query;
import com.kanfa.news.info.entity.PcPos;
import com.kanfa.news.info.mapper.PcPosMapper;
import com.kanfa.news.info.vo.admin.adv.AdvOnlineNumInfo;
import com.kanfa.news.info.vo.admin.pcadv.PcadvInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.Pcadv;
import com.kanfa.news.info.mapper.PcadvMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * 广告
 *
 * @author Jqc
 * @email jiqingchao@kanfanews.com
 * @date 2018-05-30 18:51:24
 */
@Service
public class PcadvBiz extends BaseBiz<PcadvMapper,Pcadv> {

    @Autowired
    private PcadvMapper pcadvMapper;
    @Autowired
    private PcPosMapper pcPosMapper;

    /**
     * 新增
     * @param pcadvInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public PcadvInfo addPcAdv(PcadvInfo pcadvInfo) {
        Integer addPcAdv = pcadvMapper.addPcAdv(pcadvInfo);
        if(addPcAdv>0 && pcadvInfo.getId()!=null){
            if(pcadvInfo.getTypes()!=null && pcadvInfo.getTypes().size()>0){
                for (Integer type : pcadvInfo.getTypes()) {
                    PcPos pcPos = new PcPos();
                    pcPos.setPcId(pcadvInfo.getId());
                    pcPos.setType(type);
                    pcPosMapper.insertSelective(pcPos);
                }
            }
            return pcadvInfo;
        }
        Assert.isNull("PcadvInfo","新增失败");
        return null;
    }

    /**
     * 分页
     * @param param
     * @return
     */
    public TableResultResponse<PcadvInfo> getPcAdvPage(Map<String, Object> param) {
        Query query = new Query(param);
        Page<PcadvInfo> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<PcadvInfo> list = pcadvMapper.getPcAdvPage(param);
        return new TableResultResponse<>(result.getTotal(),list);
    }

    /**
     * 删除
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deletePcAdv(Integer id) {
        Pcadv pcadv = new Pcadv();
        pcadv.setId(id);
        pcadv.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.DELETE.key());
        int update = pcadvMapper.updateByPrimaryKeySelective(pcadv);
        if(update>0){
            PcPos pcPos = new PcPos();
            pcPos.setPcId(id);
            pcPosMapper.delete(pcPos);
        }
    }

    /**
     * 统计上下线
     * @return
     */
    public AdvOnlineNumInfo getOnlineNumPcAdv() {
       return pcadvMapper.getOnlineNumPcAdv();
    }

    /**
     * 保存更新
     * @param pcadvInfo
     * @return
     */
    public PcadvInfo upatePcAdv(PcadvInfo pcadvInfo) {
        Pcadv pcadv = new Pcadv();
        BeanUtils.copyProperties(pcadvInfo,pcadv);
        int update = pcadvMapper.updateByPrimaryKeySelective(pcadv);
        if(update>0){
            if(pcadvInfo.getTypes()!=null&&pcadvInfo.getTypes().size()>0){
                PcPos pcPos1 = new PcPos();
                pcPos1.setPcId(pcadvInfo.getId());
                pcPosMapper.delete(pcPos1);
                for (Integer type : pcadvInfo.getTypes()) {
                    PcPos pcPos = new PcPos();
                    pcPos.setPcId(pcadvInfo.getId());
                    pcPos.setType(type);
                    pcPosMapper.insertSelective(pcPos);
                }
            }
            return pcadvInfo;
        }
        Assert.isNull("PcadvInfo","修改失败");
        return null;
    }

    /**
     * ids查询所有
     * @param oldIds
     * @return
     */
    public List<Pcadv> getPcadvInfoByIds(List<Integer> oldIds) {
        return pcadvMapper.getPcadvInfoByIds(oldIds);
    }

    /**
     * 批量更新
     * @param pcadvInfoList
     * @return
     */
    public Integer batchUpdate(List<Pcadv> pcadvInfoList) {
        int num = 0;
        for (Pcadv pcadv : pcadvInfoList) {
            int flag = pcadvMapper.updateByPrimaryKeySelective(pcadv);
            if (flag > 0) {
                num++;
            }
        }
        return num;
    }
}