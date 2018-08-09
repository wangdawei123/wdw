package com.kanfa.news.admin.rpc.burst;

import com.kanfa.news.admin.entity.burst.BurstType;
import com.kanfa.news.admin.feign.burst.IBurstServiceFeign;
import com.kanfa.news.admin.vo.burst.BurstTypeInfo;
import com.kanfa.news.common.constant.news.NewsEnumsConsts;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.common.rest.BaseRPC;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jiqc
 * @date 2018/2/27 13:46
 */
@RestController
@RequestMapping("burstType")
public class BurstTypeRest extends BaseRPC {
    @Autowired
    private IBurstServiceFeign burstServiceFeign;

    /**
     * 添加爆料类型
     * @param entity
     * @return
     */
    @RequestMapping(value = "/addBurstType", method = RequestMethod.POST)
    public ObjectRestResponse<BurstType> addBurstType(@RequestBody BurstType entity) {
        if (StringUtils.isEmpty(entity.getName())){
            return getObjectRestResponse("爆料类型名称不能为空");
        }
        entity.setCreateTime(new Date());
        entity.setCreateUid(Integer.valueOf(getCurrentUserId()));
        entity.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
        return burstServiceFeign.addBurstType(entity);
    }

    /**
     * 更新
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateBurstType", method = RequestMethod.POST)
    public ObjectRestResponse<BurstType> updateBurstType(@RequestBody BurstType entity) {
        if (entity.getId()==null){
            return getObjectRestResponse("爆料类型ID不能为空");
        }
        if (StringUtils.isEmpty(entity.getName())&&entity.getIsDelete()==null){
            return getObjectRestResponse("爆料类型名称或者状态为空");
        }
        if(StringUtils.isNotEmpty(entity.getName())){
           List<BurstType> list = burstServiceFeign.getBurstType(entity);
           if(list!=null && list.size()>0){
                return getObjectRestResponse("类型名有重复");
           }
        }
        entity.setUpdateTime(new Date());
        entity.setUpdateUid(Integer.valueOf(getCurrentUserId()));
        return burstServiceFeign.udpateBurstType(entity.getId(),entity);
    }

    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/burstTypeList", method = RequestMethod.GET)
    public TableResultResponse<BurstTypeInfo> burstTypeList(@RequestParam Map<String, Object> params) {
        params.put("isDelete",1);
        return burstServiceFeign.burstTypePage(params);
    }

    /**
     * 查询类型集合
     * @return
     */
    @RequestMapping(value = "/burstTypeListAll", method = RequestMethod.GET)
    public ObjectRestResponse<List<BurstType>> burstTypeListAll() {
        BurstType burstTypeInfo = new BurstType();
        burstTypeInfo.setIsDelete(NewsEnumsConsts.ContentVideoOfIsDelete.NORMAL.key());
        return burstServiceFeign.burstTypeListAll(burstTypeInfo);
    }

    private ObjectRestResponse getObjectRestResponse(String msg) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        objectRestResponse.setRel(true);
        objectRestResponse.setMessage(msg);
        return objectRestResponse;
    }

}
