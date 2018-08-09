package com.kanfa.news.info.biz;

import com.alibaba.fastjson.JSONArray;
import com.kanfa.news.info.mapper.ChartDataMapper;
import com.kanfa.news.info.mongodb.biz.CountViewContentService;
import com.kanfa.news.info.mongodb.biz.ViewContentService;
import com.kanfa.news.info.mongodb.entity.Check;
import com.kanfa.news.info.mongodb.entity.ContentDetailInfo;
import com.kanfa.news.info.mongodb.entity.Editor;
import com.kanfa.news.info.mongodb.mapper.ContentDetailInfoDao;
import com.kanfa.news.info.mongodb.util.StringDateToInt;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * app直播 Live broadcast 模块接口
 *
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-3-29 11:23:28
 */
@Service
public class ChartBiz  {
//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;
//    @Autowired
//    private RedisOne redisOne;
    @Autowired
    private StringRedisTemplate redisOneTemplate;


    @Resource
    private ViewContentService viewContentService;

    @Resource
    private CountViewContentService countViewContentService;

    @Autowired
    private ChartDataMapper chartDataMapper;
    @Autowired
    private ContentDetailInfoDao contentDetailInfoDao;



    private static Pattern linePattern = Pattern.compile("_(\\w)");
    /**
     * 日发布Pv和播放键
     */
    private static final String CHART_DATA = "chart_data";
    /**
     * 日期
     */
    private static final String DDATE = "ddate";
    /**
     * pv统计
     */
    private static final String DPV = "dpv";
    /**
     * vv 统计
     */
    private static final String DPLAY = "dplay";
    /**
     * 视频非原创
     */
    private static final String DVIDEONOOIGINAL = "dvideo_no_oiginal";
    /**
     * 视频原创
     */
    private static final String DVIDEOOIGINAL = "dvideo_oiginal";
    /**
     * 文章非原创
     */
    private static final String DNEWNOOIGINAL = "dnew_no_oiginal";
    /**
     * 文章原创
     */
    private static final String DNEWOIGINAL = "dnew_oiginal";
    /**
     * 日发布Pv和播放结果长度
     */
    private static final Integer CHARDATAMAPLENGTH = 6;
    /**
     * 视频源
     */
    private static final Integer VIDEOORIGINAL = 4;
    /**
     * 原创
     */
    private static final Integer SOURCETYPE = 1;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 根据键值获取日发布Pv和播放
     * @param keys
     * @return
     */
    public Map<String,Object> getChartData(List<String> keys){
        Map<String,Object> map = new HashMap<String,Object>(CHARDATAMAPLENGTH);
        for (int i = 0,length = keys.size(); i < length ; i++) {
            String mkey = lineToHump(keys.get(i));
            Object mvalue = redisOneTemplate.opsForHash().get(CHART_DATA,keys.get(i)).toString().split(",");
            map.put(mkey,mvalue);
        }
        map.put("date",getHour());
        return map;
    }

    /**
     * 日统计获取小时时间段
     * @return
     */
    public List<String> getHour(){
        Calendar cal = Calendar.getInstance();
        List<String> list = new ArrayList<String>();
        int h = cal.get(Calendar.HOUR_OF_DAY);
        StringBuilder str = null;
        int step = 2;
        for (int i = 0; i < h; i+=step){
            str = new StringBuilder();
            str.append(i).append(":00").append("-").append(i+1).append(":00");
            list.add(str.toString());
        }
        return list;
    }

    /**下划线转驼峰*/
    public static String lineToHump(String str){
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    /**日统计发稿量排行榜*/
    public Map<String,Object> getTopPress(String startDate,String endDate){
        List<Map<String,Object>> listMap = chartDataMapper.pressTop(startDate,endDate);
        int length = listMap.size();
        String[] nameArray = new String[length];
        Integer[] numArray = new Integer[length];
        for (int i = 0; i < length; i++) {
            nameArray[i] = listMap.get(i).get("realname").toString();
            numArray[i] = Integer.parseInt(listMap.get(i).get("num").toString());
        }
        Map<String,Object> map = new HashMap<>(2);
        map.put("titles",nameArray);
        map.put("pvs",numArray);
        return map;
    }

    public Map<String,Object> getMonthChartData(Integer days){
        //获取 ddate 长度，如果小于60，则从mongodb中获取pv、vv 统计,mysql中获取原创非原创的文章、视频统计
        long length = redisOneTemplate.opsForList().size(DDATE);
        int ddateLength =60;
        //如果redis 中ddate 长度小于60
        if(ddateLength != length){
            getDataToRedis(ddateLength);
        }
        Map<String,Object> map = new HashMap<String,Object>(7);
        map.put("date",redisOneTemplate.opsForList().range(DDATE,-days,-1));
        map.put("pv",redisOneTemplate.opsForList().range(DPV,-days,-1));
        map.put("play",redisOneTemplate.opsForList().range(DPLAY,-days,-1));
        map.put("newOiginal",redisOneTemplate.opsForList().range(DNEWOIGINAL,-days,-1));
        map.put("newNoOiginal",redisOneTemplate.opsForList().range(DNEWNOOIGINAL,-days,-1));
        map.put("videoOiginal",redisOneTemplate.opsForList().range(DVIDEOOIGINAL,-days,-1));
        map.put("videoNoOiginal",redisOneTemplate.opsForList().range(DVIDEONOOIGINAL,-days,-1));
        return map;

    }

    /**
     * 从Mongo、Mysql中获取数据，写入mongo
     * @param ddateLength
     */
    public void getDataToRedis( int ddateLength) {
        Calendar c = Calendar.getInstance();
        //从当前日期的前一天开始
        c.add(Calendar.DATE,   -1);
        Date end =  c.getTime();
        Date start = getPrevius(ddateLength);

        StringBuilder startDate = new StringBuilder(formatter.format(start));
        StringBuilder endDate = new StringBuilder(formatter.format(end));
        //查询mongo获取pv vv
        Map<String,Object> pvVvMap = viewContentService.getPvAndVv(startDate.append(" 00:00:00").toString(),endDate.append(" 23:59:59").toString());
        List<Document> pvList = (List<Document>)pvVvMap.get("pv");
        List<Document> vvList = (List<Document>)pvVvMap.get("vv");
        //查询数据库，获取原创非原创文章、视频统计
        List<Map<String,Object>> originalOrNoList = chartDataMapper.originalOrNo(startDate.append(" 00:00:00").toString(),endDate.append(" 23:59:59").toString());
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>(ddateLength);

        List <Date> listDate = findDates(start,end);
        for (int listLength =listDate.size(),i = listLength-1 ; i >= 0; i--) {
            Map<String,Object> innerMap = new HashMap<>(7);
            innerMap.put("date",formatter.format(listDate.get(i).getTime()));
            innerMap.put("pv",0);
            innerMap.put("play",0);
            innerMap.put("new_oiginal",0);
            innerMap.put("new_no_oiginal",0);
            innerMap.put("video_oiginal",0);
            innerMap.put("video_no_oiginal",0);
            int newOiginalCount = 0;
            int newNoOiginalCount = 0;
            StringBuilder date = new StringBuilder(formatter.format(listDate.get(i)));
            for (int j = 0,pvListLength = pvList.size(); j < pvListLength; j++) {
                if(date.equals(pvList.get(j).get("date").toString())){
                    innerMap.put("pv",Integer.valueOf(pvList.get(j).get("pv").toString()));
                }
            }
            for (int j = 0,vvListLength = vvList.size(); j < vvListLength; j++) {
                if(date.equals(vvList.get(j).get("date").toString())){
                    innerMap.put("play",Integer.valueOf(vvList.get(j).get("vv").toString()));
                }
            }

            //遍历数据库结果
            for (int j = 0 ,originalOrNoListLength = originalOrNoList.size(); j < originalOrNoListLength ; j++) {
                if(date.equals(originalOrNoList.get(j).get("date").toString())){
                    //视频
                    if(VIDEOORIGINAL.equals(Integer.valueOf(originalOrNoList.get(j).get("category").toString()))){
                        //原创
                        if(SOURCETYPE.equals(Integer.valueOf(originalOrNoList.get(j).get("sourceType").toString()))){
                            innerMap.put("video_oiginal",originalOrNoList.get(j).get("count"));
                        }else{
                            //非原创
                            innerMap.put("video_no_oiginal",originalOrNoList.get(j).get("count"));
                        }

                    }else{
                        //原创
                        if(SOURCETYPE.equals(Integer.valueOf(originalOrNoList.get(j).get("sourceType").toString()))){
                            newOiginalCount +=Integer.valueOf(originalOrNoList.get(j).get("count").toString());
                            innerMap.put("new_oiginal",newOiginalCount);
                        }else{
                            //非原创
                            newNoOiginalCount +=Integer.valueOf(originalOrNoList.get(j).get("count").toString());
                            innerMap.put("new_no_oiginal",newNoOiginalCount);
                        }
                    }

                }
            }
            list.add(innerMap);

        }
        if(ddateLength ==1){
            //插入redis
            redisAppend(list);
        }else{
            //插入redis
            redisAdd(list);
        }
    }


    /**
     * 当月直播数统计
     * @return
     */
    public List<JSONArray>  getLiveCount(){
        Date date = new Date();
        //当月日期列表
        List<String> dateList = dayReport(date);
        int length = dateList.size();
        List<JSONArray> mapList = new ArrayList<JSONArray>(length);

        String startDate = getMonthFirstDay();
        String endDate = getMonthLastDay();
        //从mysql获取数据
        List<Map<String,Object>> liveCountList = chartDataMapper.liveCount(startDate,endDate);
        int llength = liveCountList.size();
        for (int i = 0; i < length; i++) {
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(dateList.get(i));
            int sum = 0;
            if(0 < llength){
                for (int j = 0; j < llength; j++) {
                    Map<String,Object> liveCount = liveCountList.get(j);
                    if(null == liveCount.get("nowDate")){
                        continue;
                    }
                    if(dateList.get(i).equals(liveCount.get("nowDate").toString())){
                        sum += Integer.valueOf(liveCount.get("sum").toString());
                    }
                }
            }
            jsonArray.add(sum);
            mapList.add(jsonArray);
        }
        return mapList;
    }

    public String getMonthLastDay() {
        //获取前月的最后一天
        Calendar endc = Calendar.getInstance();
        //设置为0号,当前日期既为本月最后一天
        endc.set(Calendar.DAY_OF_MONTH, endc.getActualMaximum(Calendar.DAY_OF_MONTH));
        StringBuilder endDate = new StringBuilder(formatter.format(endc.getTime()));
        endDate.append(" 23:59:59");
        return endDate.toString();
    }

    public String getMonthFirstDay(){
        //获取当前月的第一天
        Calendar   startc = Calendar.getInstance();
        startc.add(Calendar.MONTH, 0);
        //设置为1号,当前日期既为本月第一天
        startc.set(Calendar.DAY_OF_MONTH,1);
        StringBuilder startDate = new StringBuilder(formatter.format(startc.getTime()));
        startDate.append(" 00:00:00");
        return startDate.toString();
    }
    /**
     * 获取某月所有日期
     * @param month
     */
    public List<String> dayReport(Date month) {

        Calendar cal = Calendar.getInstance();
        //month 为指定月份任意日期
        cal.setTime(month);
        int year = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int dayNumOfMonth = getDaysOfMonth(month);

        // 从一号开始
        cal.set(Calendar.DAY_OF_MONTH, 1);
        List<String> list = new ArrayList<String>(dayNumOfMonth);
        for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
            Date d = cal.getTime();
            StringBuilder df = new StringBuilder(formatter.format(d));
            list.add(df.toString());
        }
        return list;
    }
    /**
     * 获取月份天数
     * @param date
     * @return
     */
    public  int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
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

    /**
     * 获取某段时间内的所有日期
     * @param dBegin
     * @param dEnd
     * @return
     */
    public  List <Date> findDates(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    /**
     *redis存储
     */
    private void redisAdd(List<Map<String,Object>> list){
        redisOneTemplate.delete(DDATE);
        redisOneTemplate.delete(DPV);
        redisOneTemplate.delete(DPLAY);
        redisOneTemplate.delete(DVIDEONOOIGINAL);
        redisOneTemplate.delete(DVIDEOOIGINAL);
        redisOneTemplate.delete(DNEWNOOIGINAL);
        redisOneTemplate.delete(DNEWOIGINAL);

        for (int i = 0,length = list.size(); i < length ; i++) {
            Map<String,Object> map = list.get(i);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                redisOneTemplate.opsForList().leftPush("d"+entry.getKey(),entry.getValue().toString());
            }
        }
    }

    private void redisAppend(List<Map<String, Object>> list) {
        for (int i = 0,length = list.size(); i < length ; i++) {
            Map<String,Object> map = list.get(i);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                redisOneTemplate.opsForList().rightPush("d"+entry.getKey(),entry.getValue().toString());
            }
        }
    }

    /**
     * 获取文章与视频排名
     */
    public Map<String,Object> getArticleOrVideoTop(Integer type){
        Integer articleType = 1;
        Integer videoType = 2;
        Integer liveType = 3;
        String startDate = getMonthFirstDay();
        String endDate = getMonthLastDay();
        List<Map<String,Object>> mongoList = null;
        if(articleType.equals(type)){
            mongoList = countViewContentService.getTopCountContentView(startDate,endDate);
        }else if (videoType.equals(type)){
            mongoList = viewContentService.getTopViewContentVideo(startDate,endDate);
        }else if(liveType.equals(type)){
            mongoList = viewContentService.getTopViewContentLive(startDate,endDate);
        }
        int length = mongoList.size();
        if(null == mongoList || 0 == length){
            return null;
        }
        List<Integer> idsList = new ArrayList<Integer>(length);
        //遍历获取id集合
        for (int i = 0; i < length; i++) {
            Map<String,Object> map = mongoList.get(i);
            Integer id = Integer.valueOf(map.get("cid").toString());
            idsList.add(id);
        }

        //查询mysql 获取title
        List<Map<String,Object>> sqlList = chartDataMapper.getTitle(idsList);
        List<String> titleArray = new ArrayList<String>(length);
        List<Integer> pvArray = new ArrayList<Integer>(length);
        for (int i = 0; i < length; i++) {
            Map<String,Object> map = mongoList.get(i);
            pvArray.add(Integer.valueOf(map.get("pv").toString()));
            String id = map.get("cid").toString();
            boolean flag = false;
            for (int j = 0,sqlListLength = sqlList.size(); j < sqlListLength; j++) {
                Map<String,Object> sqlMap = sqlList.get(j);
                String sqlId = sqlMap.get("id").toString();
                if(id.equals(sqlId)){
                    titleArray.add(sqlMap.get("title").toString());
                    flag = true;
                }
            }
            if(!flag){
                titleArray.add("");
            }

        }
        Map<String,Object> result = new HashMap<String,Object>(2);
        result.put("titles",titleArray.toArray());
        result.put("pvs",pvArray.toArray());
        return result;


    }

    public void redisToMongo() {
        //获取 redis-- content_redis_list 中的值
        List<String> contentsIds =  new ArrayList<>(redisOneTemplate.opsForSet().members("content_redis_list"));
        getContentDataSaveToMongo(contentsIds);
        //获取 redis-- live_redis_list 中的值
        List<String> livesIds =  new ArrayList<>(redisOneTemplate.opsForSet().members("live_redis_list"));
        getLiveDataSaveToMongo(livesIds);

        redisOneTemplate.opsForSet().remove("content_redis_list");
        redisOneTemplate.opsForSet().remove("live_redis_list");

    }

    /**
     * 获取 mysql中文章信息
     */
    public void getContentDataSaveToMongo(List<String> contentsIds){
        List<Map<String,Object>> contents = chartDataMapper.getContentData(contentsIds);
        int contentsSize = contents.size();
        if(null != contents && contentsSize > 0){
            for (int i = 0; i < contentsSize; i++) {
                Map<String,Object> content = contents.get(i);
                contentSaveToMongo(content);
            }
        }
    }
    /**
     * 获取 mysql中直播信息
     */
    public void getLiveDataSaveToMongo(List<String> livesIds){
        List<Map<String,Object>> lives = chartDataMapper.getLiveData(livesIds);
        int livesSize = lives.size();
        if(null != lives && livesSize > 0){
            for (int i = 0; i < livesSize; i++) {
                Map<String,Object> live = lives.get(i);
                liveSaveToMongo(live);
            }
        }
    }


    /**
     * 文章信息写入Mongo
     * @param contentMap
     */
    public void contentSaveToMongo(Map<String,Object> contentMap){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        int cid = Integer.valueOf(contentMap.get("cid").toString());
        ContentDetailInfo contentDetailInfo = new ContentDetailInfo();
        //查找id是否存在
        contentDetailInfo.setFrom_id(1);
        contentDetailInfo.setCid(cid);
        List<ContentDetailInfo> list = contentDetailInfoDao.findByCondition(contentDetailInfo);
        int listSize = list.size();
        if(null != list && listSize > 0){
            for (int i = 0; i < listSize; i++) {
                ObjectId id = list.get(i).getId();
                Check check = new Check();
                check.setUid(Integer.valueOf(contentMap.get("id").toString()));
                check.setName(contentMap.get("realname").toString());
                Date date =  formatter.parse(contentMap.get("publishTime").toString(),pos);
                check.setTime(StringDateToInt.transForMilliSecond(date));
                contentDetailInfo.setTitle(contentMap.get("title").toString());
                contentDetailInfo.setCheck(check);
                contentDetailInfoDao.updateById(id.toString(),contentDetailInfo);

            }
        }else{
            Date createTime =  formatter.parse(contentMap.get("createTime").toString(),pos);
            Date firstCheckTime =  formatter.parse(contentMap.get("firstCheckTime") == null?contentMap.get("checkTime").toString():contentMap.get("firstCheckTime").toString(),pos);
            Date publistTime =  formatter.parse(contentMap.get("publistTime").toString(),pos);
            contentDetailInfo.setTitle(contentMap.get("title").toString());
            contentDetailInfo.setCreate_time(StringDateToInt.transForMilliSecond(createTime));
            contentDetailInfo.setFirst_check_time(StringDateToInt.transForMilliSecond(firstCheckTime));
            contentDetailInfo.setSource_type(Integer.valueOf(contentMap.get("sourceType").toString()));
            contentDetailInfo.setType(Integer.valueOf(contentMap.get("category").toString()));
            Check check = new Check();
            check.setUid(Integer.valueOf(contentMap.get("id").toString()));
            check.setName(contentMap.get("realname").toString());
            check.setTime(StringDateToInt.transForMilliSecond(publistTime));
            contentDetailInfo.setCheck(check);
            Editor editor = new Editor();
            editor.setUid(Integer.valueOf(contentMap.get("createUid").toString()));
            editor.setName(contentMap.get("realname").toString());
            editor.setBranch("");
            editor.setStation("");
            contentDetailInfo.setEditor(editor);

            contentDetailInfoDao.save(contentDetailInfo);
        }
    }

    /**
     * 直播信息写入Mongo
     * @param liveMap
     */
    public void liveSaveToMongo(Map<String,Object> liveMap){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        int cid = Integer.valueOf(liveMap.get("cid").toString());
        ContentDetailInfo contentDetailInfo = new ContentDetailInfo();
        //查找id是否存在
        contentDetailInfo.setFrom_id(2);
        contentDetailInfo.setCid(cid);
        List<ContentDetailInfo> list = contentDetailInfoDao.findByCondition(contentDetailInfo);
        int listSize = list.size();
        if(null != list && listSize > 0){
            for (int i = 0; i < listSize; i++) {
                ObjectId id = list.get(i).getId();
                Check check = new Check();
                check.setUid(Integer.valueOf(liveMap.get("id").toString()));
                Date date =  formatter.parse(liveMap.get("publishTime").toString(),pos);
                check.setTime(StringDateToInt.transForMilliSecond(date));
                contentDetailInfo.setTitle(liveMap.get("title").toString());
                contentDetailInfo.setLive_type(Integer.valueOf(liveMap.get("liveType").toString()));
                contentDetailInfo.setStat(Integer.valueOf(liveMap.get("stat").toString()));
                contentDetailInfo.setCheck(check);
                contentDetailInfoDao.updateById(id.toString(),contentDetailInfo);

            }
        }else{
            Date createTime =  formatter.parse(liveMap.get("createTime").toString(),pos);
            Date updateTime =  formatter.parse(liveMap.get("updateTime").toString(),pos);
            Date publistTime =  formatter.parse(liveMap.get("publistTime").toString(),pos);
            contentDetailInfo.setTitle(liveMap.get("title").toString());
//            contentDetailInfo.setCreate_time(StringDateToInt.transForMilliSecond(createTime));
            contentDetailInfo.setFirst_check_time(StringDateToInt.transForMilliSecond(createTime));
            contentDetailInfo.setSource_type(Integer.valueOf(liveMap.get("sourceType").toString()));
            contentDetailInfo.setType(22);
            contentDetailInfo.setLive_type(Integer.valueOf(liveMap.get("liveType").toString()));
            contentDetailInfo.setStat(Integer.valueOf(liveMap.get("stat").toString()));
            Check check = new Check();
            check.setUid(Integer.valueOf(liveMap.get("update_uid").toString()));
            check.setName(liveMap.get("checkName").toString());
            check.setTime(StringDateToInt.transForMilliSecond(updateTime));
            contentDetailInfo.setCheck(check);
            Editor editor = new Editor();
            editor.setUid(Integer.valueOf(liveMap.get("createUid").toString()));
            editor.setName(liveMap.get("realname").toString());
            editor.setBranch("");
            editor.setStation("");
            contentDetailInfo.setEditor(editor);

            contentDetailInfoDao.save(contentDetailInfo);
        }
    }

    /**
     * 图表--获取每小时数据
     */
    public void getHoursData() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
//        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        int startDate = StringDateToInt.transForMilliSecond(start);
        Date endd = new Date();
        int endDate = StringDateToInt.transForMilliSecond(endd);
        Map<String,Integer> pvAndPlay = viewContentService.getPvAndPlayCount(startDate,endDate);
        int pv = pvAndPlay.get("pv");
        int play = pvAndPlay.get("play");
        Date date = new Date();
        SimpleDateFormat startFormatter = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        SimpleDateFormat endFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDateStr = startFormatter.format(date);
        String endDateStr = endFormatter.format(date);
        List<Map<String,Object>> mysqlData = chartDataMapper.getHoursMysqlData(startDateStr,endDateStr);
        int mysqlDataSize = mysqlData.size();
        int newOiginal = 0;
        int newNoOiginal = 0;
        int videoOiginal = 0;
        int videoNoOiginal = 0;
        if(null != mysqlData && mysqlDataSize > 0){
            for (int i = 0; i < mysqlDataSize; i++) {
                Map<String,Object> map = mysqlData.get(i);
                if(map.get("sourceType").toString().equals("1") &&(map.get("category").toString().equals("2") || map.get("category").toString().equals("3"))){
                    newOiginal += Integer.valueOf(map.get("count").toString()).intValue();
                }
                if(!map.get("sourceType").toString().equals("1") &&(map.get("category").toString().equals("2") || map.get("category").toString().equals("3"))){
                    newNoOiginal += Integer.valueOf(map.get("count").toString()).intValue();
                }
                if(map.get("sourceType").toString().equals("1") &&map.get("category").toString().equals("4") ){
                    videoOiginal += Integer.valueOf(map.get("count").toString()).intValue();
                }
                if(!map.get("sourceType").toString().equals("1") &&map.get("category").toString().equals("4") ){
                    videoNoOiginal += Integer.valueOf(map.get("count").toString()).intValue();
                }
            }
        }

        Map<Object,Object> chartData =  redisOneTemplate.opsForHash().entries("chart_data");
        if(null != chartData){
            chartData.put("pv",chartData.get("pv").toString()+","+pv);
            chartData.put("play",chartData.get("play").toString()+","+play);
            chartData.put("new_oiginal",chartData.get("new_oiginal").toString()+","+newOiginal);
            chartData.put("new_no_oiginal",chartData.get("new_no_oiginal").toString()+","+newNoOiginal);
            chartData.put("video_oiginal",chartData.get("video_oiginal").toString()+","+videoOiginal);
            chartData.put("video_no_oiginal",chartData.get("video_no_oiginal").toString()+","+videoNoOiginal);
            redisOneTemplate.opsForHash().putAll("chart_data",chartData);
        }else{
            chartData.put("pv",pv);
            chartData.put("play",play);
            chartData.put("new_oiginal",newOiginal);
            chartData.put("new_no_oiginal",newNoOiginal);
            chartData.put("video_oiginal",videoOiginal);
            chartData.put("video_no_oiginal",videoNoOiginal);
            redisOneTemplate.opsForHash().putAll("chart_data",chartData);
        }


    }

    /**
     * 清除前一天每小时获得的数据
     */
    public void delRedisKey() {
        redisOneTemplate.opsForHash().delete("chart_data");
    }

    /**
     * 月统计数据--每天凌晨执行
     */
    public void dayChartsRedisToMongo() {
        //获取柱状图
        Long leng = redisOneTemplate.opsForList().size("dnew_oiginal");
        if(60 != leng){
            getDataToRedis(60);
        }else{
            getDataToRedis(1);
        }
    }

    /**
     * 图表查询- 每天统计pv or 播放量
     */
    public void dayPvPlay() {
        Long date = System.currentTimeMillis();
        int startDate  = date.intValue() - 86400;
        int endDate  = date.intValue();
        getViewContent(startDate,endDate,1);
    }

    /**
     * 图表查询- 每天统计pv or 播放量
     * @param startDate
     * @param endDate
     * @param days
     */
    private void getViewContent(int startDate, int endDate, Integer days) {

        Map<String,List<Document>> resultMap = countViewContentService.getViewContent(startDate,endDate,days);
        if(null != days){
//            同步每天pv&播放量
            countViewContentService.saveDayPvPlayData(resultMap,startDate);
        }else{
            countViewContentService.savePvData(resultMap);
        }
        redisOneTemplate.opsForValue().set("pv_update_time",String.valueOf(System.currentTimeMillis()));
    }

    /**
     * 同步content_detail_info pv/play
     */
    public void setPvViews() {
        int startDate = Integer.valueOf(redisOneTemplate.opsForValue().get("pv_update_time").toString());
        int endDate = Integer.valueOf(String.valueOf(System.currentTimeMillis()));
        getViewContent(startDate,endDate,null);
    }
}