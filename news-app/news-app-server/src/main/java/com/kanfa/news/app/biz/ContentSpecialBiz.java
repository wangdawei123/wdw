package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.ContentSpecial;
import com.kanfa.news.app.mapper.ContentSpecialMapper;
import com.kanfa.news.app.vo.admin.info.ContentInfo;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.constant.app.LiveCommonEnum;
import com.kanfa.news.common.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内容附表-专题。一对多
 *
 * @author Jezy
 * @email wangjingzhi@kanfanews.com
 * @date 2018-03-23 15:08:24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ContentSpecialBiz extends BaseBiz<ContentSpecialMapper,ContentSpecial> {
    @Autowired
    private ContentSpecialMapper contentSpecialMapper;
    @Autowired
    private ContentBiz contentBiz;

    /**
     * 查询绑定子目录的内容
     * @param contentSpecial
     * @return
     */
    public List<ContentInfo> selectListSpecialContent(ContentSpecial contentSpecial) {
        return contentSpecialMapper.selectListSpecialContent(contentSpecial);
    }

    public List<Map<String ,Object>> getIndexSpecialData(Integer sid ,Map<String, Object> tags){
        List<Map<String ,Object>> newList = new ArrayList<>();
        List<ContentInfo> specialData = contentSpecialMapper.getIndexSpecialData(sid);
        for(ContentInfo content : specialData){
            Map<String ,Object> data = new HashMap<>(16);
            data.put("id",content.getId());
            data.put("title",content.getTitle());
            data.put("type",content.getType());
            if(content.getType().equals(LiveCommonEnum.SSPECIAL_TYPE_LIVE) && content.getSourceType().equals(LiveCommonEnum.SOURCE_TYPE_ORIGINAL)){
                tags.put("name","原创");
                data.put("tags",tags);
            }
            if(content.getCardType().equals(LiveCommonEnum.CHANNEL_CARDTYPE_SMALL) && StringUtils.isNotEmpty(content.getImage())){
                data.put("card_type",LiveCommonEnum.CHANNEL_CARDTYPE_SMALL);
            }else{
                data.put("card_type",LiveCommonEnum.CHANNEL_CARDTYPE_NO);
            }
            if(content.getPublish_time() != null){
                data.put("pub_time", DateUtil.fromToday(content.getPublish_time()));
            }else{
                data.put("pub_time", "");
            }
            data.put("image",content.getImage());
            data.put("is_adv",LiveCommonEnum.CONSTANT_ZERO);
            data.put("is_hot",LiveCommonEnum.CONSTANT_ZERO);
            data.put("splited",LiveCommonEnum.CONSTANT_ZERO);
            String detailUrl = contentBiz.getDetailUrlSu(content.getId(), content.getType(), content.getCategory());
            data.put("url",detailUrl);
            if(content.getSourceType().equals(LiveCommonEnum.SOURCE_TYPE_ORIGINAL)){
                data.put("url",detailUrl + "?original=1");
            }
            newList.add(data);
        }
        return newList;
    }
}