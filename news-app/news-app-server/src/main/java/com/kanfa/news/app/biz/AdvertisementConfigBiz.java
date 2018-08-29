package com.kanfa.news.app.biz;

import com.ace.cache.annotation.Cache;
import com.kanfa.news.app.entity.AdvertisementConfig;
import com.kanfa.news.app.mapper.AdvertisementConfigMapper;
import com.kanfa.news.common.biz.BaseBiz;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告配置表
 *
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-04-11 10:43:33
 */
@Service
public class AdvertisementConfigBiz extends BaseBiz<AdvertisementConfigMapper,AdvertisementConfig> {
    @Override
    @Cache(key = "advertisementConfig:list")
    public List<AdvertisementConfig> selectListAll() {
        return mapper.selectAll();
    }
}