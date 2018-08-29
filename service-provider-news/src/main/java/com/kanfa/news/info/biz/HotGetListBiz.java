package com.kanfa.news.info.biz;

import com.alibaba.fastjson.JSONArray;
import com.kanfa.news.info.mapper.HotGetListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author Jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-05-09 19:44:49
 */
@Service
public class HotGetListBiz {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private HotGetListMapper hotGetListMapper;

    public void setCatche() {
        Calendar c = Calendar.getInstance();
        //从当前日期的前一天开始
        c.add(Calendar.DATE,   -1);
        Date end =  c.getTime();
        Date start = getPrevius(7);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder startDate = new StringBuilder(formatter.format(start));
        StringBuilder endDate = new StringBuilder(formatter.format(end));
        String startDateStr = startDate.append(" 00:00:00").toString();
        String endDateStr  = endDate.append(" 23:59:59").toString();
//        Integer type,Integer page,Integer limit
        List<Map<String,Object>> article = hotGetListMapper.list(2,0,4,startDateStr,endDateStr);
        List<Map<String,Object>> image = hotGetListMapper.list(3,0,6,startDateStr,endDateStr);
        compsiteData(image);
        List<Map<String,Object>> video = hotGetListMapper.list(4,0,5,startDateStr,endDateStr);
        compsiteData(video);
        List<Map<String,Object>> special = hotGetListMapper.list(1,0,10,startDateStr,endDateStr);
        String key_hot_article_list="pc_index_hot_article_list";
        String key_hot_image_list="pc_index_hot_image_list";
        String key_hot_video_list="pc_index_hot_video_list";
        String key_hot_special_list="pc_index_hot_special_list";
        redisTemplate.opsForValue().set(key_hot_article_list, JSONArray.toJSONString(article));
        redisTemplate.opsForValue().set(key_hot_image_list, JSONArray.toJSONString(image));
        redisTemplate.opsForValue().set(key_hot_video_list, JSONArray.toJSONString(video));
        redisTemplate.opsForValue().set(key_hot_special_list, JSONArray.toJSONString(special));

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


    public void compsiteData(List<Map<String,Object>> list){
        for (int i = 0,length = list.size(); i < length; i++) {
                Map<String,Object> map = list.get(i);
                int cid = Integer.valueOf(map.get("cid").toString());
                int type =  Integer.valueOf(map.get("type").toString());
                if(3 == type){
                    Map<String,Object> imageMap = hotGetListMapper.findImageNum(cid);
                    int num = Integer.valueOf(imageMap.get("num").toString());
                    map.put("imageNum",num);
                }else if(4 == type){
                    //        findImageNum   findDuration
                    Map<String,Object> videoMap = hotGetListMapper.findDuration(cid);
                    String duration = videoMap.get("duration") == null?" 00:00":videoMap.get("duration").toString();
                    map.put("duration",duration);
                }
        }
    }
}