package com.kanfa.news.app.biz;

import com.kanfa.news.app.entity.KpiCount;
import com.kanfa.news.app.entity.KpiTypeConfig;
import com.kanfa.news.app.mapper.KpiCountMapper;
import com.kanfa.news.app.mapper.KpiTypeConfigMapper;
import com.kanfa.news.app.mongoDB.entity.CountVideoView;
import com.kanfa.news.app.mongoDB.mapper.CountVideoViewMongoDao;
import com.kanfa.news.app.mongoDB.mapper.YesterdayCountDao;
import com.kanfa.news.app.mongoDB.util.StringDateToInt;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/14 17:28
 */
@Service
public class VideokpiBiz {

    @Autowired
    private CountVideoViewMongoDao countVideoViewMongoDao;

    @Autowired
    private YesterdayCountDao yesterdayCountDao;

    @Autowired
    private KpiTypeConfigMapper kpiTypeConfigMapper;

    @Autowired
    private KpiCountMapper kpiCountMapper;

    public void start(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String startDate =sdf.format(d);
        yesterdayPv(startDate);
        yesterdayPvFinish();

    }
    /**
     * 昨天视频pv统计
     */
    public void yesterdayPv(String startDate){
        int countDate = StringDateToInt.transForMilliSecondByTim(startDate,"yyyy-MM-dd HH:mm:ss");
        int yesterday =countDate - 86400;
        if(todayIsRuned(yesterday)){
            return;
        }

        // 视频统计
        List<Document> videoList = yesterdayCountDao.getYesterdayVideoLiveCount("video");
        int videoListSize = videoList.size();
        if(null != videoList &&  videoListSize> 0){
            for (int i = 0; i < videoListSize; i++) {
                Document document = videoList.get(i);
                CountVideoView countVideoView = new CountVideoView();
                countVideoView.setVid(document.getInteger("vid"));
                countVideoView.setLive_id(0);
                countVideoView.setPv(document.getInteger("pv"));
                countVideoView.setUv(document.getInteger("uv"));
                countVideoView.setApp_pv_in(document.getInteger("appPvIn"));
                countVideoView.setApp_pv_out(document.getInteger("appPvOut"));
                countVideoView.setApp_video_in(document.getInteger("appVideoIn"));
                countVideoView.setApp_video_out(document.getInteger("appVideoOut"));
                countVideoView.setCount_date(yesterday);
                countVideoViewMongoDao.save(countVideoView);
            }
        }

        // 直播统计
        List<Document> liveList = yesterdayCountDao.getYesterdayVideoLiveCount("live");
        int liveListSize = liveList.size();
        if(null != liveList &&  liveListSize> 0){
            for (int i = 0; i < liveListSize; i++) {
                Document document = videoList.get(i);
                CountVideoView countVideoView = new CountVideoView();
                countVideoView.setVid(0);
                countVideoView.setLive_id(document.getInteger("liveVid"));
                countVideoView.setPv(document.getInteger("pv"));
                countVideoView.setUv(document.getInteger("uv"));
                countVideoView.setApp_pv_in(document.getInteger("appPvIn"));
                countVideoView.setApp_pv_out(document.getInteger("appPvOut"));
                countVideoView.setApp_video_in(document.getInteger("appVideoIn"));
                countVideoView.setApp_video_out(document.getInteger("appVideoOut"));
                countVideoView.setCount_date(yesterday);
                countVideoViewMongoDao.save(countVideoView);
            }
        }
    }
    /**
     * 查询7日内达标视频 在mysql中做标记
     */
    public void yesterdayPvFinish(){
        // 获得达标配置
        List<KpiTypeConfig> listIdLimit = kpiTypeConfigMapper.selectIdLimit();
        int listIdLimitSize = listIdLimit.size();
        Calendar c = Calendar.getInstance();
        //从当前日期的前7天开始
//        c.add(Calendar.DATE,   -1);
        Date end =  c.getTime();
        Date start = getPrevius(7);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder startDate = new StringBuilder(formatter.format(start));
        StringBuilder endDate = new StringBuilder(formatter.format(end));
        String startDateStr = startDate.append(" 00:00:00").toString();
        String endDateStr  = endDate.append(" 23:59:59").toString();
        // 查询7日内未达标的视频id
        List<KpiCount> unStandardList = kpiCountMapper.selectUnStandard(startDateStr,endDateStr);
        int unStandardListSize  = unStandardList.size();
        if(null != unStandardList && unStandardListSize > 0 ){
            for (int i = 0; i < unStandardListSize; i++) {
                KpiCount kpiCount = unStandardList.get(i);
                int pvData = 0;
                if( 4 == kpiCount.getType().intValue()){
                    AggregateIterable<Document> aggregate = countVideoViewMongoDao.sumView("video",Integer.valueOf(kpiCount.getTypeId()),StringDateToInt.transForMilliSecondByTim(startDateStr,"yyyy-MM-dd HH:mm:ss"),StringDateToInt.transForMilliSecondByTim(endDateStr,"yyyy-MM-dd HH:mm:ss"));
                    MongoCursor<Document> iterator=aggregate.iterator();
                    Document document = null;
                    while(iterator.hasNext()) {
                        document = iterator.next();
                        pvData = Integer.valueOf(document.get("pv").toString());
                    }
                }else if ( 9 == kpiCount.getType().intValue()){
                    AggregateIterable<Document> aggregate = countVideoViewMongoDao.sumView("live",Integer.valueOf(kpiCount.getTypeId()),StringDateToInt.transForMilliSecondByTim(startDateStr,"yyyy-MM-dd HH:mm:ss"),StringDateToInt.transForMilliSecondByTim(endDateStr,"yyyy-MM-dd HH:mm:ss"));
                    MongoCursor<Document> iterator=aggregate.iterator();
                    Document document = null;
                    while(iterator.hasNext()) {
                        document = iterator.next();
                        pvData = Integer.valueOf(document.get("pv").toString());
                    }
                }
                for (int j = 0; j < listIdLimitSize; j++) {
                    KpiTypeConfig kpiTypeConfig = listIdLimit.get(j);
                    if(kpiTypeConfig.getId().equals(kpiCount.getWorkType()) && pvData != 0 && kpiCount.getWorkType() <=pvData ){
                        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        c.add(Calendar.DATE,   -1);
                        StringBuilder finishTime = new StringBuilder(f.format(c.getTime()));
                        kpiCountMapper.updateKpiCount(finishTime.toString(),kpiCount.getId());
                    }
                }

            }
        }


    }

    public boolean todayIsRuned(int yesterday){

        Query query = new Query();
        query.addCriteria(Criteria.where("count_date").is(yesterday));
        List<CountVideoView> list = countVideoViewMongoDao.find(query);
        if(null != list || list.size() > 0){
            return true;
        }
        return false;
    }

    /**
     * 当前日期往前推days天数
     * @param days
     * @return
     */
    public static Date getPrevius(int days){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -days);
        Date startDate = c.getTime();
        return startDate;
    }
}
