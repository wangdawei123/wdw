package com.kanfa.news.info.rest;

import com.kanfa.news.common.rest.BaseController;
import com.kanfa.news.info.biz.CityBiz;
import com.kanfa.news.info.biz.RegionBiz;
import com.kanfa.news.info.entity.City;
import com.kanfa.news.info.entity.Region;
import com.kanfa.news.info.vo.admin.message.ProvinceInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("region")
public class RegionController extends BaseController<RegionBiz,Region> {

    @Autowired
    private RegionBiz regionBiz;
    @Autowired
    private CityBiz cityBiz;

    /**
     * 查询省份信息
     * @return
     */
    @RequestMapping(value = "/getProvince",method = RequestMethod.GET)
    public List<ProvinceInfo> getProvince(){
        List<Region> regions = regionBiz.selectListAll();
        List<City> cities = cityBiz.selectListAll();
        List<ProvinceInfo> provinceInfoList = new ArrayList<>(regions.size());
        for (Region region : regions) {
            ProvinceInfo provinceInfo = new ProvinceInfo();
            BeanUtils.copyProperties(region,provinceInfo);
            List<City> cityList = new ArrayList<>(cities.size());
            for (City city : cities) {
                if(region.getId().equals(city.getProvinceId())){
                    cityList.add(city);
                }
            }
            provinceInfo.setCityList(cityList);
            provinceInfoList.add(provinceInfo);
        }
        return provinceInfoList;
    }

}