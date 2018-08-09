package com.kanfa.news.info.mongodb.biz;


import com.kanfa.news.info.entity.AdminUser;
import com.kanfa.news.info.entity.User;
import com.kanfa.news.info.excel.entity.ExcelData;
import com.kanfa.news.info.excel.utils.ExportExcelUtils;
import com.kanfa.news.info.mongodb.mapper.ContentDetailInfoDao;
import com.kanfa.news.info.mongodb.mapper.CountChannelContentDao;
import com.kanfa.news.info.mongodb.util.StringDateToInt;
import com.kanfa.news.info.vo.admin.kpicount.CountChannelContentInfo;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018/3/29 18:02
 */
@Service("contentDetailInfoService")
public class ContentDetailInfoService {

    @Resource
    private ContentDetailInfoDao contentDetailInfoDao;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 发稿查询统计
     * @param page
     * @param limit
     * @param start
     * @param end
     * @return
     */
    public Map<String,Object> getList(Integer page,Integer limit,Integer uid,String start,String end,String pvEnd,Integer ascDesc,String orderColumn,List<AdminUser> userList) {
        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
        int pvEndDate  = StringDateToInt.transForMilliSecondByTim(pvEnd,"yyyy-MM-dd HH:mm:ss");
        List<Document> aggregate = contentDetailInfoDao.getList(startDate,endDate,pvEndDate,uid,page,limit,ascDesc,orderColumn);
        if(null == aggregate ){
            return null;
        }
        AdminUser adminUser = null;
        for (int i = 0,length = aggregate.size(); i < length ; i++) {
            for (int j = 0,userListLength = userList.size(); j < userListLength; j++) {
                adminUser = userList.get(j);
                if(Integer.valueOf(aggregate.get(i).get("_id").toString()).equals(adminUser.getId())){
                    aggregate.get(i).put("userName",adminUser.getRealname());
                }
            }
        }
        Map<String,Object> result = new HashMap<String,Object>(2);
        result.put("total",countContentSum(uid, start, end,null,null,null,false));
        result.put("rows",aggregate);
        return result;
    }
    /**
     * 发稿查询明细
     * @param page
     * @param limit
     * @param start
     * @param end
     * @return
     */
    public Map<String,Object> getDetailList(Integer page,Integer limit,Integer uid,String start,String end,String pvEnd,Integer ascDesc,String orderColumn,Integer type,Integer sourceType,String title) {
        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
        int pvEndDate  = StringDateToInt.transForMilliSecondByTim(pvEnd,"yyyy-MM-dd HH:mm:ss");
        List<Document> aggregate = contentDetailInfoDao.getDetailList(startDate,endDate,pvEndDate,uid,page,limit,ascDesc,orderColumn,type,sourceType,title);
        if(null == aggregate ){
            return null;
        }
        for (int i = 0,length = aggregate.size(); i < length; i++) {
            Document document = aggregate.get(i);
            document.remove("_id");
        }
        adaptorDocumentList(aggregate);
        Map<String,Object> result = new HashMap<String,Object>(2);
        result.put("total",countContentSum(uid, start, end,type,sourceType,title,true));
        result.put("rows",aggregate);
        return result;
    }

    /**
     * 统计总条数
     * @param uid
     * @param start
     * @param end
     * @return
     */
    public Integer countContentSum(Integer uid,String start,String end,Integer type,Integer sourceType,String title,Boolean isDetail){
        int startDate  = StringDateToInt.transForMilliSecondByTim(start,"yyyy-MM-dd HH:mm:ss");
        int endDate  = StringDateToInt.transForMilliSecondByTim(end,"yyyy-MM-dd HH:mm:ss");
        return contentDetailInfoDao.getTotalCount(startDate,endDate,uid,type,sourceType,title,isDetail);
    }

    /**
     * 日期、稿件类型、稿件类别 转换
     * @param list
     */
    public void adaptorDocumentList(List<Document> list){
        String typeArticle = "2";
        String typePic = "3";
        String typeVideo = "4";
        String typeLive = "22";
        String sourceTypeOiginal = "1";
        for (int i = 0,length = list.size(); i < length; i++) {
            Document document  = list.get(i);

            if(document.get("type").toString().equals(typeArticle) || document.get("type").toString().equals(typePic) || document.get("type").toString().equals(typeVideo)){
                    if(null != document.get("sourceType") && document.get("sourceType").toString().equals(sourceTypeOiginal)){
                        document.put("sourceType","原创");
                    }else{
                        document.put("sourceType","非原创");
                    }
            }

            if(document.get("type").toString().equals(typeArticle)){
                document.put("type","文章");
            }
            if(document.get("type").toString().equals(typePic)){
                document.put("type","图集");
            }
            if(document.get("type").toString().equals(typeVideo)){
                document.put("type","视频");
            }
            if(document.get("type").toString().equals(typeLive)){
                document.put("type","直播");
            }
            long d = Long.valueOf(document.get("checkTime").toString())*1000;
            document.put("checkTime",formatter.format(new Date(d)));
            d = Long.valueOf(document.get("firstCheckTime").toString())*1000;
            document.put("firstCheckTime",formatter.format(new Date(d)));
        }
    }


}
