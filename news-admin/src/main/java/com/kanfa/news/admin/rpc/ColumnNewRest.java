package com.kanfa.news.admin.rpc;

import com.kanfa.news.admin.entity.ColumnNew;
import com.kanfa.news.admin.feign.IColumnNewServiceFeign;
import com.kanfa.news.admin.vo.column.ColumnNewPageInfo;
import com.kanfa.news.admin.vo.column.ColumnNewShow;
import com.kanfa.news.common.context.BaseContextHandler;
import com.kanfa.news.common.msg.ObjectRestResponse;
import com.kanfa.news.common.msg.TableResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Chen
 * on 2018/4/25 15:46
 */
@RestController
@RequestMapping("columnNew")
public class ColumnNewRest {

    @Autowired
    private IColumnNewServiceFeign columnNewServiceFeign;


    @RequestMapping(value = "/getColumnPage",method = RequestMethod.POST)
    public TableResultResponse<ColumnNewPageInfo> getColumnPage(@RequestBody ColumnNewPageInfo entity){
        return  columnNewServiceFeign.getColumnPage(entity);
    }

    //ColumnNewShow
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public ObjectRestResponse<ColumnNewShow> getOne(@RequestParam("id") Integer id) {
        ObjectRestResponse<ColumnNew> columnNewObjectRestResponse = columnNewServiceFeign.get(id);
        ColumnNew data = columnNewObjectRestResponse.getData();
        ColumnNewShow columnNewShow = new ColumnNewShow();
        BeanUtils.copyProperties(data,columnNewShow);
        ObjectRestResponse<ColumnNewShow> columnNewShowObjectRestResponse = new ObjectRestResponse<>();
        columnNewShowObjectRestResponse.setData(columnNewShow);
        return columnNewShowObjectRestResponse;
    }



    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ObjectRestResponse<ColumnNew> update(@PathVariable("id") Integer id, @RequestBody ColumnNew entity) {
        entity.setId(id);
        entity.setUpdateTime(new Date());
        entity.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        return columnNewServiceFeign.update(id, entity);
    }


    //隐藏 (0:不隐藏;1:隐藏)
    @RequestMapping(value = "/onhide",method = RequestMethod.GET)
    public ObjectRestResponse onhide(@RequestParam("id")Integer id){
        ColumnNew columnNew = new ColumnNew();
        columnNew.setId(id);
        columnNew.setUpdateTime(new Date());
        columnNew.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        columnNew.setHide(1);
        return columnNewServiceFeign.update(id, columnNew);
    }

    //显示    
    @RequestMapping(value = "/nohide",method = RequestMethod.GET)
    public ObjectRestResponse hide(@RequestParam("id")Integer id){
        ColumnNew columnNew = new ColumnNew();
        columnNew.setId(id);
        columnNew.setUpdateTime(new Date());
        columnNew.setUpdateUid(Integer.valueOf(BaseContextHandler.getUserID()));
        columnNew.setHide(0);
        return columnNewServiceFeign.update(id, columnNew);
    }


}
