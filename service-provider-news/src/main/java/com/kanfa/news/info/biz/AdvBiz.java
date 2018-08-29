package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanfa.news.info.entity.Adv;
import com.kanfa.news.info.entity.AdvPosition;
import com.kanfa.news.info.mapper.AdvMapper;
import com.kanfa.news.info.mapper.AdvPositionMapper;
import com.kanfa.news.info.vo.admin.adv.AdvInfo;
import com.kanfa.news.info.vo.admin.adv.AdvOnlineNumInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 广告
 *
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-05-30 13:54:21
 */
@Service
public class AdvBiz extends BaseBiz<AdvMapper,Adv> {

    @Autowired
    private AdvMapper advMapper;
    @Autowired
    private AdvPositionMapper advPositionMapper;

    /**
     * 添加
     * @param advInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AdvInfo addAdv(AdvInfo advInfo) {
        int id = advMapper.addAdv(advInfo);
        if(id>0 && advInfo.getId() != null){
            if(advInfo.getPositionList()!=null && advInfo.getPositionList().size()>0){
                for (String position : advInfo.getPositionList()) {
                    String[] split = position.split("-");
                    AdvPosition advPosition = new AdvPosition();
                    advPosition.setAdvertisementId(advInfo.getId());
                    Integer orderNumber=advPositionMapper.selectMaxOrderNumber();
                    advPosition.setOrderNumber(orderNumber!=null?orderNumber+1:1);
                    advPosition.setChannelId(Integer.valueOf(split[0]));
                    advPosition.setType(Integer.valueOf(split[1]));
                    advPositionMapper.insertSelective(advPosition);
                }
            }
        }
        return advInfo;
    }

    /**
     * 分页
     * @param advInfo
     * @return
     */
    public TableResultResponse<AdvInfo> getAdvPage(AdvInfo advInfo) {
        Page<AdvInfo> result = PageHelper.startPage(advInfo.getPage(), advInfo.getLimit());
        List<AdvInfo> list = advMapper.getPage(advInfo);
        return new TableResultResponse<>(result.getTotal(), list);
    }

    /**
     * 更新保存
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AdvInfo updateAdv(AdvInfo advInfo) {
        Adv adv = new Adv();
        BeanUtils.copyProperties(advInfo,adv);
        int update = advMapper.updateByPrimaryKeySelective(adv);
        if(update>0){
            if(advInfo.getPositionList()!=null&&advInfo.getPositionList().size()>0){
                AdvPosition advPosition1 = new AdvPosition();
                advPosition1.setAdvertisementId(adv.getId());
                advPositionMapper.delete(advPosition1);
                for (String position : advInfo.getPositionList()) {
                    String[] split = position.split("-");
                    AdvPosition advPosition = new AdvPosition();
                    advPosition.setAdvertisementId(advInfo.getId());
                    Integer orderNumber=advPositionMapper.selectMaxOrderNumber();
                    advPosition.setOrderNumber(orderNumber!=null?orderNumber+1:1);
                    advPosition.setChannelId(Integer.valueOf(split[0]));
                    advPosition.setType(Integer.valueOf(split[1]));
                    advPositionMapper.insertSelective(advPosition);
                }
            }
            return advInfo;
        }
        Assert.isNull("AdvInfo","更新失败");
        return null;
    }

    /**
     * 上下线统计
     * @return
     */
    public AdvOnlineNumInfo getOnlineNum() {
        return advMapper.getOnlineNum();
    }

    /**
     * 根据ids查询
     * @param oldIds
     * @return
     */
    public List<Adv> getAdvInfoByIds(List<Integer> oldIds) {
        return advMapper.getAdvInfoByIds(oldIds);
    }

    /**
     * 批量更新
     * @param pcadvInfoList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer batchUpdate(List<Adv> pcadvInfoList) {
        int num = 0;
        for (Adv pcadv : pcadvInfoList) {
            int flag = advMapper.updateByPrimaryKeySelective(pcadv);
            if (flag > 0) {
                num++;
            }
        }
        return num;
    }

    /**
     * 删除
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteAdv(Integer id) {
        Adv adv = new Adv();
        adv.setId(id);
        adv.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.DELETE.key());
        int update = advMapper.updateByPrimaryKeySelective(adv);
        if(update>0){
            AdvPosition advPosition = new AdvPosition();
            advPosition.setAdvertisementId(id);
            advPositionMapper.delete(advPosition);
        }
    }
    protected HttpServletRequest request;

    /**
     *  app首页开屏广告
     */
    public Adv getStartUpAdv(){
        List<Adv> advs = advMapper.getStartUpAdv();
        if(advs.size()== LiveCommonEnum.CONSTANT_ZERO){
            return null;
        }
        return advs.get(0);
    }

    /**
     * 获得统计pv跳转页地址
     */
    public String getCoutPvUrl(Integer id){
        return "http://"+request.getRemoteHost()+"/web/adv/show?adv_id="+id;
    }


}