package com.kanfa.news.app.biz;

import com.kanfa.news.app.mapper.CountCateMapper;
import com.kanfa.news.app.mapper.CountChannelContentMapper;
import com.kanfa.news.app.mapper.CountContentViewMapper;
import com.kanfa.news.app.mongoDB.entity.*;
import com.kanfa.news.app.mongoDB.mapper.CountCateDao;
import com.kanfa.news.app.mongoDB.mapper.CountChannelContentMongoDao;
import com.kanfa.news.app.mongoDB.mapper.CountContentViewMongoDao;
import com.kanfa.news.app.mongoDB.mapper.YesterdayCountDao;
import com.kanfa.news.app.mongoDB.util.StringDateToInt;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/4/28 17:52
 */
@Service
public class CountContentViewBiz {
    @Autowired
    private YesterdayCountDao yesterdayCountDao;

    @Autowired
    private CountContentViewMapper countContentViewMapper;

    @Autowired
    private CountChannelContentMapper countChannelContentMapper;

    @Autowired
    private CountCateMapper countCateMapper;

    @Autowired
    private CountContentViewMongoDao countContentViewMongoDao;

    @Autowired
    private CountChannelContentMongoDao countChannelContentMongoDao;

    @Autowired
    private CountCateDao countCateMongoDao;

    /**
     * 每日文章 视频 图集等统计
     */
    public void getCountContentView(){
        int page = 0;
        int limit = 2000;

        String TYPETOW = "2";
        String TYPETHREE = "3";
        String TYPEFOUR = "4";

        List<Document> list = yesterdayCountDao.getYesterdayCount(page,limit);
        List<Map<String,Object>> baseInfoList = null;
        CountContentView countContentView = new CountContentView();
        for (int i = 0,length = list.size(); i < length; i++) {
            Document document = list.get(i);
            int cid = document.getInteger("cid");
            baseInfoList = countContentViewMapper.listBaseInfoByCid(cid);
            if(null != baseInfoList && baseInfoList.size() > 0){
                for (int j = 0,innerLength = baseInfoList.size(); j < innerLength; j++) {
                    String type = baseInfoList.get(j).get("type").toString();
                    List<Map<String,Object>> authors = null;
                    if(TYPETOW.equals(type) || TYPETHREE.equals(type)){
                        authors = countContentViewMapper.listAuthorInfoByCid(cid);
                        if(null == authors){
                            authors = countContentViewMapper.listAuthorInfoByCidFromKpiContent(cid);
                        }
                    }else if(TYPEFOUR.equals(type)){
                        authors = countContentViewMapper.listAuthorInfoByCid(cid);
                        if(null == authors){
                            authors = countContentViewMapper.listAuthorInfoByCidFromKpiVideo(cid);
                        }
                    }else{
                        authors = countContentViewMapper.listAuthorInfoByCidFromContentReporter(cid);
                    }
                    //插入作者数据
                    if(null != authors){
                        int authorListSize = 16;
                        List<Author> authorList = new ArrayList<Author>(authorListSize);
                        for (int k = 0,authorsLength = authors.size(); k < authorsLength; k++) {
                            Author author = new Author();
                            author.setId(Integer.valueOf(authors.get(k).get("uid").toString()));
                            author.setName(authors.get(k).get("name").toString());
                            author.setWeight(Double.valueOf(authors.get(k).get("weight").toString()));
                            authorList.add(author);
                        }
                        countContentView.setAuthor(authorList);
                    }

                    List<Map<String,Object>> channels = countContentViewMapper.listChannelInfoByCid(cid);
                    //插入频道数据
                    if(null != channels){
                        int initialCapacity = 16;
                        List<Channel> channelList = new ArrayList<>(initialCapacity);
                        for (int k = 0,channelsLength = channels.size(); k < channelsLength; k++) {
                            Channel channel = new Channel();
                            channel.setId(Integer.valueOf(channels.get(k).get("id").toString()));
                            channel.setName(channels.get(k).get("name").toString());
                            channelList.add(channel);
                        }
                        countContentView.setChannel(channelList);
                    }

                    countContentView.setPv(list.get(i).getInteger("pv"));
                    countContentView.setUv(list.get(i).getInteger("uv"));
                    countContentView.setProxy_count(list.get(i).getInteger("proxyCount"));
                    countContentView.setApp_count(list.get(i).getInteger("appCount"));
                    countContentView.setPc_count(list.get(i).getInteger("pcCount"));
                    countContentView.setM_count(list.get(i).getInteger("mCount"));
                    countContentView.setSrc(list.get(i).getString("src"));
                    countContentViewMongoDao.save(countContentView);
                }
            }
        }
    }

    /**
     * 每日渠道发稿量统计
     */
    public void getCountChannelContent() {
        Date today = new Date();
        Long yesterday = today.getTime() - 86400000L;
        String startDate = DateFormatUtils.format(new Date(yesterday), "yyyy-MM-dd 00:00:00");
        String endDate = DateFormatUtils.format(new Date(yesterday), "yyyy-MM-dd 23:59:59");
        List<Map<String,Object>> countChannelContentList = countChannelContentMapper.listChannelContent(startDate,endDate);
        int listLength = countChannelContentList.size();
        if(null != countChannelContentList && 0 < listLength){
            for (int i = 0; i < listLength; i++) {
                CountChannelContent countChannelContent = new CountChannelContent();
                countChannelContent.setCate(Integer.valueOf(countChannelContentList.get(i).get("cate").toString()));
                countChannelContent.setChannel_id(Integer.valueOf(countChannelContentList.get(i).get("channelId").toString()));
                countChannelContent.setChannel_name(countChannelContentList.get(i).get("channelName").toString());
                countChannelContent.setContent_count(Integer.valueOf(countChannelContentList.get(i).get("contentCount").toString()));
                countChannelContent.setCount_date(StringDateToInt.transForMilliSecondByTim(countChannelContentList.get(i).get("countDate").toString(),"yyyy-MM-dd HH:mm:ss"));
                countChannelContentMongoDao.save(countChannelContent);
            }
        }

    }

    /**
     * 每日类目文章统计
     */
    public void getCountCate() {
        Date today = new Date();
        Long yesterday = today.getTime() - 86400000L;
        String startDate = DateFormatUtils.format(new Date(yesterday), "yyyy-MM-dd 00:00:00");
        String endDate = DateFormatUtils.format(new Date(yesterday), "yyyy-MM-dd 23:59:59");
        int countDate  = StringDateToInt.transForMilliSecondByTim(startDate,"yyyy-MM-dd HH:mm:ss");
        //总的文章
        final int COUNT_CATE_ARTICLE_ALL=1;
        // 视频
        final int COUNT_CATE_VIDEO=2;
        // VR
        final int COUNT_CATE_VR=3;
        // 原创文章
        final int COUNT_CATE_ARTICLE_ORIGINAL=20;
        // 转载文章
        final int COUNT_CATE_ARTICLE_TRANSFORM=21;
        // 抓取文章
        final int COUNT_CATE_ARTICLE_GRAB=22;
        // VR
        final int COUNT_CATE_ARTICLE_LEGAL=23;

        Map<String,Object> result = null;
        CountCategoryContent countCategoryContent =null;

        // 文章统计
        countCategoryContent = new CountCategoryContent();
        countCategoryContent.setCount_date(countDate);
        result = countCateMapper.categoryCount(2,startDate,endDate);
        countCategoryContent.setType(COUNT_CATE_ARTICLE_ALL);
        countCategoryContent.setContent_count(Integer.valueOf(result.get("count").toString()));
        countCateMongoDao.save(countCategoryContent);

        // 原创文章统计
        countCategoryContent = new CountCategoryContent();
        countCategoryContent.setCount_date(countDate);
        result = countCateMapper.categorySourceCount(2,1,startDate,endDate);
        countCategoryContent.setType(COUNT_CATE_ARTICLE_ORIGINAL);
        countCategoryContent.setContent_count(Integer.valueOf(result.get("count").toString()));
        countCateMongoDao.save(countCategoryContent);

        // 转载文章统计
        countCategoryContent = new CountCategoryContent();
        countCategoryContent.setCount_date(countDate);
        result = countCateMapper.categorySourceCount(2,2,startDate,endDate);
        countCategoryContent.setType(COUNT_CATE_ARTICLE_TRANSFORM);
        countCategoryContent.setContent_count(Integer.valueOf(result.get("count").toString()));
        countCateMongoDao.save(countCategoryContent);

        // 采集数据统计
        countCategoryContent = new CountCategoryContent();
        countCategoryContent.setCount_date(countDate);
        result = countCateMapper.categorySourceCount(2,3,startDate,endDate);
        countCategoryContent.setType(COUNT_CATE_ARTICLE_GRAB);
        countCategoryContent.setContent_count(Integer.valueOf(result.get("count").toString()));
        countCateMongoDao.save(countCategoryContent);

        // 法制类文件统计
        countCategoryContent = new CountCategoryContent();
        countCategoryContent.setCount_date(countDate);
        result = countCateMapper.categoryLegalCount(2,1,startDate,endDate);
        countCategoryContent.setType(COUNT_CATE_ARTICLE_LEGAL);
        countCategoryContent.setContent_count(Integer.valueOf(result.get("count").toString()));
        countCateMongoDao.save(countCategoryContent);

        // 视频总数
        countCategoryContent = new CountCategoryContent();
        countCategoryContent.setCount_date(countDate);
        result = countCateMapper.categoryCount(4,startDate,endDate);
        countCategoryContent.setType(COUNT_CATE_VIDEO);
        countCategoryContent.setContent_count(Integer.valueOf(result.get("count").toString()));
        countCateMongoDao.save(countCategoryContent);

        // VR统计
        countCategoryContent = new CountCategoryContent();
        countCategoryContent.setCount_date(countDate);
        result = countCateMapper.categoryCount(11,startDate,endDate);
        countCategoryContent.setType(COUNT_CATE_VR);
        countCategoryContent.setContent_count(Integer.valueOf(result.get("count").toString()));
        countCateMongoDao.save(countCategoryContent);

    }
}
