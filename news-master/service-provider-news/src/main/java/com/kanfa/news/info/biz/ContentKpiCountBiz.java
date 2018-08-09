package com.kanfa.news.info.biz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.entity.ContentArticle;
import com.kanfa.news.info.entity.ContentImage;
import com.kanfa.news.info.entity.ContentKpiCount;
import com.kanfa.news.info.entity.ContentVideo;
import com.kanfa.news.info.mapper.ContentKpiCountMapper;
import com.kanfa.news.info.mongodb.mapper.ViewContentDao;
import com.kanfa.news.info.mongodb.util.StringDateToInt;
import com.kanfa.news.info.vo.admin.kpicount.ContentKpiCountInfo;
import com.kanfa.news.info.vo.admin.kpicount.ContentKpiInfo;
import com.kanfa.news.info.vo.admin.kpicount.KpiCountListInfo;
import org.apache.commons.lang.StringEscapeUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * 内容表（含专题，文章，图集，视频类型）
 *
 * @author jiqc
 * @email jiqingcchao@kanfanews.com
 * @date 2018-03-05 14:23:28
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class ContentKpiCountBiz extends BaseBiz<ContentKpiCountMapper,ContentKpiCount> {

    @Autowired
    private ContentKpiCountMapper contentKpiCountMapper;

    @Autowired
    private ViewContentDao viewContentDao;

    private final int IS_CONTENTKPIVIEW = 1;

    private Pattern pattern = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
    // 匹配<img>中的src数据


    public TableResultResponse<ContentKpiCountInfo> getPageList(Integer page,
                                                                Integer limit,
                                                                String startDate,
                                                                String endDate,
                                                                Integer sourceType,
                                                                Integer viewType) {
        Page<Object> result = PageHelper.startPage(page, limit);
        List<ContentKpiCountInfo> list = getList(startDate, endDate, sourceType, viewType);
        return new TableResultResponse<ContentKpiCountInfo>(result.getTotal(), list);
    }

    public List<ContentKpiCountInfo> getList(String startDate, String endDate, Integer sourceType, Integer viewType) {
        String start = startDate.split(" ")[0].replace("-", "");
        String end = endDate.split(" ")[0].replace("-", "");
        List<ContentKpiCountInfo> list = contentKpiCountMapper.getContentList(start, end, sourceType, viewType);
        return dataProcess(list, viewType);
    }


    public List<ContentKpiCountInfo> dataProcess(List<ContentKpiCountInfo> list, Integer viewType) {
        List<ContentKpiCountInfo> newList = new ArrayList<ContentKpiCountInfo>();
        Boolean flag = viewType.intValue() == IS_CONTENTKPIVIEW;
        if (flag) {
            for (ContentKpiCountInfo contentKpiCountInfo : list) {
                contentKpiCountInfo = setAppSale(contentKpiCountInfo);
                if (null != contentKpiCountInfo.getWorks()) {
                    JSONObject works = JSON.parseObject(StringEscapeUtils.unescapeJava(contentKpiCountInfo.getWorks()));
                    if (null != works) {
                        for (Map.Entry<String, Object> entry : works.entrySet()) {
                            JSONObject innerWorks = JSON.parseObject(entry.getValue().toString());
                            int tvCount = innerWorks.entrySet().size();
                            double tvScale = new BigDecimal((float) 1 / tvCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            if (null != innerWorks) {
                                for (Map.Entry<String, Object> innerEntry : innerWorks.entrySet()) {
                                    contentKpiCountInfo.setWorkType(entry.getKey());
                                    contentKpiCountInfo.setWorkTypeNum(tvCount);
                                    contentKpiCountInfo.setWorkScale(tvScale);
                                    contentKpiCountInfo.setWorkUsers(innerEntry.getValue().toString());
                                    newList.add(contentKpiCountInfo);
                                }
                            }
                        }
                    }

                } else {
                    contentKpiCountInfo.setWorkUsers("");
                    contentKpiCountInfo.setWorkTypeNum(0);
                    contentKpiCountInfo.setWorkType("");
                    contentKpiCountInfo.setWorkScale(new Double(0));
                    newList.add(contentKpiCountInfo);
                }
            }
        } else {
            for (ContentKpiCountInfo contentKpiCountInfo : list) {
                contentKpiCountInfo = setAppSale(contentKpiCountInfo);
                if (null != contentKpiCountInfo.getWorks()) {
                    JSONObject works = JSON.parseObject(StringEscapeUtils.unescapeJava(contentKpiCountInfo.getWorks()));
                    if (null != works) {
                        for (Map.Entry<String, Object> entry : works.entrySet()) {
                            JSONObject innerWorks = JSON.parseObject(entry.getValue().toString());
                            if (null != innerWorks) {
                                for (Map.Entry<String, Object> innerEntry : innerWorks.entrySet()) {
                                    if (null != contentKpiCountInfo.getWorkUsers()) {
                                        contentKpiCountInfo.setWorkUsers(contentKpiCountInfo.getWorkUsers() + "," + innerEntry.getValue().toString());
                                    } else {
                                        contentKpiCountInfo.setWorkUsers(innerEntry.getValue().toString());
                                    }
                                }
                            }
                        }
                    }
                    newList.add(contentKpiCountInfo);
                } else {
                    contentKpiCountInfo.setWorkUsers("");
                    newList.add(contentKpiCountInfo);
                }
            }

        }
        return newList;
    }

    public ContentKpiCountInfo setAppSale(ContentKpiCountInfo contentKpiCountInfo) {
        if (contentKpiCountInfo.getAppPv().intValue() > 0) {
            int appUv = contentKpiCountInfo.getAppUv();
            int appPv = contentKpiCountInfo.getAppPv();
            Double result = new BigDecimal((float) appUv / appPv).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue() * 100;
            contentKpiCountInfo.setAppScale(result + "%");
        } else {
            contentKpiCountInfo.setAppScale("0");
        }
        return contentKpiCountInfo;
    }

    /**
     * 新的KPI统计，按内容及24小时PV数据统计
     * 例：统计第一次审核时间为20180625这天的数据，则需要统计从发稿到2018-06-27日的0点的pv数据
     * 默认情况下凌晨统计前天的数据，当特殊统计时可传入参数。
     * 传入时间参数第一个为统计哪一天的数据，第二个具体某个文章ID，第三个为是否替换旧数据
     * 如统计20180625的数据,需替换之前数据则如下命令，0是不指定具体文章
     * /usr/local/php/bin/php /data/wwwroot/dev/public/cli.php  contentkpi countContent 20180625 0 1
     */
    public void countContent(String dateStr,Integer contentId,Boolean ifUpdate){
        String start = null;
        String end = null;
        String countDate = null;
        Boolean isUpdate = false;
        String pvEndDate = null;
        if (null != dateStr) {
            //如果有日期参数传入，则重新成传入日期的数据
            start = dateStr + " 00:00:00";
            end = dateStr + " 23:59:59";
            countDate = dateStr.replace("-","");
        } else {
            //如果没有日期参数传入，则生成当时日期的前两天的数据
            Calendar cd = Calendar.getInstance();
            //当前日期的前一天
            cd.add(Calendar.DATE, -1);
            Date datecd = cd.getTime();
            SimpleDateFormat formattercd = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            pvEndDate = formatter.format(datecd);
            Calendar c = Calendar.getInstance();
            //当前日期的前两天
            c.add(Calendar.DATE, -2);
            Date date = c.getTime();

            StringBuilder startDate = new StringBuilder(formatter.format(date));
            StringBuilder endDate = new StringBuilder(formatter.format(date));
            start = startDate.append(" 00:00:00").toString();
            end = endDate.append(" 23:59:59").toString();
            countDate  = formattercd.format(date);
        }
        List<ContentKpiCount> contentKpiInfos = contentKpiCountMapper.getContent(start,end,contentId);
        if (null != contentKpiInfos){
            for (ContentKpiCount contentKpiCount:contentKpiInfos){
                if (null == contentKpiCount.getCreateUid()){
                    contentKpiCount.setCreateName("爬虫工程师");
                }
                ContentKpiCount tmp = countExtent(contentKpiCount.getCid(),contentKpiCount.getType());
                if(null != tmp){
                    //合并 附属属性
                    contentKpiCount.setWorkuserNum(tmp.getWorkuserNum());
                    contentKpiCount.setWorktypeNum(tmp.getWorktypeNum());
                    contentKpiCount.setDid(tmp.getDid());
                    contentKpiCount.setContentType(tmp.getContentType());
                    contentKpiCount.setImgNum(tmp.getImgNum());
                    contentKpiCount.setWorks(tmp.getWorks());
                }

                int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
                int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
                List<Document> result = viewContentDao.getCidPvUvCount("cid",contentKpiCount.getCid(),startDate,endDate);
                int allPv = 0;
                int allUv = 0;
                if(null != result && result.size()>0){
                    for (Document document:result) {
                        if(contentKpiCount.getCid().equals(document.getInteger("cid"))){
                            allPv += document.getInteger("allPv");
                            allUv += document.getInteger("allUv");
                        }
                    }
                    contentKpiCount.setAllPv(allPv);
                    contentKpiCount.setAllUv(allUv);
                }
                contentKpiCount.setCreateDate(Integer.parseInt(countDate));
                insertContent(contentKpiCount,ifUpdate);
            }
        }


        List<ContentKpiCount> liveKpiInfos = contentKpiCountMapper.getLive(start,end,contentId);
        if(null != liveKpiInfos){
            for (ContentKpiCount contentKpiCount:liveKpiInfos){
                ContentKpiCount tmp = countExtent(contentKpiCount.getCid(),contentKpiCount.getType());
                if(null != tmp){
                    //合并 附属属性
                    contentKpiCount.setWorkuserNum(tmp.getWorkuserNum());
                    contentKpiCount.setWorktypeNum(tmp.getWorktypeNum());
                    contentKpiCount.setDid(tmp.getDid());
                    contentKpiCount.setContentType(tmp.getContentType());
                    contentKpiCount.setImgNum(tmp.getImgNum());
                    contentKpiCount.setWorks(tmp.getWorks());
                }
                int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
                int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
                List<Document> result = viewContentDao.getCidPvUvCount("live_id",contentKpiCount.getCid(),startDate,endDate);
                int appPv = 0;
                int appUv = 0;
                if(null != result && result.size()>0){
                    for (Document document:result) {
                        if(contentKpiCount.getCid().equals(document.getInteger("cid"))) {
                            appPv += document.getInteger("appPv");
                            appUv += document.getInteger("appUv");
                        }
                    }
                    contentKpiCount.setAllPv(appPv);
                    contentKpiCount.setAllUv(appUv);
                }
                contentKpiCount.setCreateDate(Integer.parseInt(countDate));
                insertContent(contentKpiCount,ifUpdate);
            }
        }
    }

    public ContentKpiCount countExtent(Integer cid ,Integer type){
        int workUserNum = 0;
//        List<ContentKpiCount> list = new ArrayList<ContentKpiCount>();
        ContentKpiCount contentKpiCount = new ContentKpiCount();
        switch (type){
            case 2:
                ContentArticle contentArticle = contentKpiCountMapper.getOneContentArticle(cid);
                if(null != contentArticle){
                        Matcher m = compile("<img.*src\\\\s*=\\\\s*(.*?)[^>]*?>").matcher(contentArticle.getContent());
                    if (m.find()){
                        contentKpiCount.setContentType("文图");
                    }else{
                        contentKpiCount.setContentType("纯文");
                    }
                    contentKpiCount.setDid(contentArticle.getDid());
                    List<KpiCountListInfo> kpiCountListInfos = contentKpiCountMapper.getKpiCountList(2,cid);
                    JSONObject json = new JSONObject();
                    if (null != kpiCountListInfos){
                        for (KpiCountListInfo kpiCountListInfo:kpiCountListInfos) {
                            JSONObject innerJson = new JSONObject();
                            innerJson.put(kpiCountListInfo.getUid().toString(),kpiCountListInfo.getName());
                            if (null != json.get(kpiCountListInfo.getWtype())){
                                //如果存在相同key的json,则合并
                                Iterator<String> iterator =json.keySet().iterator();
                                while(iterator.hasNext()){
                                    String key = iterator.next();
                                    Object value = json.get(key);
                                    innerJson.put(key,value);
                                }
                            }
                            json.put(kpiCountListInfo.getWtype(),innerJson);
                            workUserNum++;
                        }
                    }
                    if (contentArticle.getDid() > 0){
                        List<KpiCountListInfo> kclis = contentKpiCountMapper.getKpiCountList(4,contentArticle.getDid());
                        if(null != kclis){
                            for (KpiCountListInfo kpiCountListInfo:kclis) {
                                JSONObject innerJson = new JSONObject();
                                innerJson.put(kpiCountListInfo.getUid().toString(),kpiCountListInfo.getName());
                                if (null != json.get(kpiCountListInfo.getWtype())){
                                    //如果存在相同key的json,则合并
                                    Iterator<String> iterator =json.keySet().iterator();
                                    while(iterator.hasNext()){
                                        String key = iterator.next();
                                        Object value = json.get(key);
                                        innerJson.put(key,value);
                                    }
                                }
                                json.put(kpiCountListInfo.getWtype(),innerJson);
                                workUserNum++;
                            }
                        }
                    }
                    contentKpiCount.setWorks(json.toJSONString());

                }
                break;
            case 3:
                ContentImage contentImage =contentKpiCountMapper.getOneContentImage(cid);
                if(null != contentImage){
                    contentKpiCount.setImgNum(contentImage.getNum());
                }
                contentKpiCount.setContentType("图集");
                List<KpiCountListInfo> kpiCountListInfos = contentKpiCountMapper.getKpiCountList(2,cid);
                if (null != kpiCountListInfos){
                    JSONObject json = new JSONObject();
                    for (KpiCountListInfo kpiCountListInfo:kpiCountListInfos) {
                        JSONObject innerJson = new JSONObject();
                        innerJson.put(kpiCountListInfo.getUid().toString(),kpiCountListInfo.getName());
                        if (null != json.get(kpiCountListInfo.getWtype())){
                            //如果存在相同key的json,则合并
                            Iterator<String> iterator =json.keySet().iterator();
                            while(iterator.hasNext()){
                                String key = iterator.next();
                                Object value = json.get(key);
                                innerJson.put(key,value);
                            }
                        }
                        json.put(kpiCountListInfo.getWtype(),innerJson);
                        workUserNum++;
                    }
                    contentKpiCount.setWorks(json.toJSONString());
                }
                break;
            case 4:
                ContentVideo contentVideo =contentKpiCountMapper.getOneContentVideo(cid);
                if (null != contentVideo){
                    contentKpiCount.setDid(contentVideo.getDid());
                }
                contentKpiCount.setContentType("视频");
                List<KpiCountListInfo> kclis = contentKpiCountMapper.getKpiCountList(4,contentVideo.getDid());
                if (null != kclis){
                    JSONObject json = new JSONObject();
                    for (KpiCountListInfo kpiCountListInfo:kclis) {
                        JSONObject innerJson = new JSONObject();
                        innerJson.put(kpiCountListInfo.getUid().toString(),kpiCountListInfo.getName());
                        if (null != json.get(kpiCountListInfo.getWtype())){
                            //如果存在相同key的json,则合并
                            Iterator<String> iterator =json.keySet().iterator();
                            while(iterator.hasNext()){
                                String key = iterator.next();
                                Object value = json.get(key);
                                innerJson.put(key,value);
                            }
                        }
                        json.put(kpiCountListInfo.getWtype(),innerJson);
                        workUserNum++;
                    }
                    contentKpiCount.setWorks(json.toJSONString());
                }
                break;
            case 22:
                contentKpiCount.setContentType("直播");
                List<KpiCountListInfo> kclisList = contentKpiCountMapper.getKpiCountList(9,cid);
                if (null != kclisList){
                    JSONObject json = new JSONObject();
                    for (KpiCountListInfo kpiCountListInfo:kclisList) {
                        JSONObject innerJson = new JSONObject();
                        innerJson.put(kpiCountListInfo.getUid().toString(),kpiCountListInfo.getName());
                        if (null != json.get(kpiCountListInfo.getWtype())){
                            //如果存在相同key的json,则合并
                            Iterator<String> iterator =json.keySet().iterator();
                            while(iterator.hasNext()){
                                String key = iterator.next();
                                Object value = json.get(key);
                                innerJson.put(key,value);
                            }
                        }
                        json.put(kpiCountListInfo.getWtype(),innerJson);
                        workUserNum++;
                    }
                    contentKpiCount.setWorks(json.toJSONString());
                }
                break;
            default:break;

        }

        if (null != contentKpiCount.getWorks()){
            JSONObject json = JSONObject.parseObject(contentKpiCount.getWorks());
            contentKpiCount.setWorktypeNum(json.keySet().size());
            contentKpiCount.setWorkuserNum(workUserNum);
            contentKpiCount.setWorks(StringEscapeUtils.escapeJava(contentKpiCount.getWorks()));
        }

        return contentKpiCount;
    }

    /*
     $params传入需要更新的数据，is_update 是否替换验证，适用于重新计算
     */
    public void insertContent(ContentKpiCount contentKpiCount,Boolean isUpdate){
        if (isUpdate){
            ContentKpiCount contentKpiCountFind = new ContentKpiCount();
            contentKpiCountFind.setCid(contentKpiCount.getCid());
            contentKpiCountFind.setType(contentKpiCount.getType());
            contentKpiCountFind.setCreateDate(contentKpiCount.getCreateDate());

            contentKpiCountFind = selectOne(contentKpiCountFind);
            contentKpiCountFind.setAllUv(contentKpiCount.getAllUv());
            contentKpiCountFind.setAllPv(contentKpiCount.getAllPv());
            contentKpiCountFind.setWorks(contentKpiCount.getWorks());
            contentKpiCountFind.setImgNum(contentKpiCount.getImgNum());
            contentKpiCountFind.setContentType(contentKpiCount.getContentType());
            contentKpiCountFind.setDid(contentKpiCount.getDid());
            contentKpiCountFind.setWorktypeNum(contentKpiCount.getWorktypeNum());
            contentKpiCountFind.setWorkuserNum(contentKpiCount.getWorkuserNum());
            contentKpiCountFind.setCreateName(contentKpiCount.getCreateName());
            contentKpiCountFind.setAppPv(contentKpiCount.getAppPv());
            contentKpiCountFind.setAppUv(contentKpiCount.getAppUv());
            contentKpiCountFind.setCreateUid(contentKpiCount.getCreateUid());
            contentKpiCountFind.setFirstCheckTime(contentKpiCount.getFirstCheckTime());
            updateById(selectOne(contentKpiCountFind));
        }else{
            insert(contentKpiCount);
        }
    }



}