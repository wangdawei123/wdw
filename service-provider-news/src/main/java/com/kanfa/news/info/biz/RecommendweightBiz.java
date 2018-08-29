package com.kanfa.news.info.biz;

import com.kanfa.news.info.mapper.RecommendweightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/5/14 14:16
 */
@Service
public class RecommendweightBiz {
    @Autowired
    private RecommendweightMapper recommendweightMapper;

    public void start() {
        String startDate = getTimeByHour(-5);
        String endDate = getTimeByHour(-4);
        recommendweightMapper.recommendweightStart(startDate,endDate);

    }

    public void live() {
        String endDate = getTimeByHour(-4);
        recommendweightMapper.recommendweightLive(endDate);

    }



    public  String getTimeByHour(int hour) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }
}
