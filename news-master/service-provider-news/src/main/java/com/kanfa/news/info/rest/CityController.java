package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.CityBiz;
import com.kanfa.news.info.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController extends BaseController<CityBiz,City> {

    @Autowired
    private CityBiz cityBiz;

    /**
     * 查询城市信息
     * @return
     */
    @RequestMapping(value = "/getCity",method = RequestMethod.GET)
    public List<City> getCity(@RequestParam("provinceId") Integer provinceId){
        City entity=new City();
        entity.setProvinceId(provinceId);
        return cityBiz.selectList(entity);
    }
}