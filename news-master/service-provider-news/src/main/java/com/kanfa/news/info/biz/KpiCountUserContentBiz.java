package com.kanfa.news.info.biz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kanfa.news.common.biz.BaseBiz;
import com.kanfa.news.common.msg.TableResultResponse;
import com.kanfa.news.info.entity.ContentArticle;
import com.kanfa.news.info.entity.CountUserContent;
import com.kanfa.news.info.mapper.ContentArticleMapper;
import com.kanfa.news.info.mapper.CountUserContentMapper;
import com.kanfa.news.info.mapper.KpiCountUserContentMapper;
import com.kanfa.news.info.vo.admin.kpicount.ContentUserInfo;
import com.kanfa.news.info.vo.admin.kpicount.KpiCountUserContentInfo;
import com.kanfa.news.info.vo.admin.kpicount.LiveUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

/**
 * @author jiayw
 * @email jiayunwei@kanfanews.com
 * @date 2018-03-14 11:46:04
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class KpiCountUserContentBiz extends BaseBiz<ContentArticleMapper, ContentArticle> {

    @Autowired
    private KpiCountUserContentMapper kpiCountUserContentMapper;

    @Autowired
    private CountUserContentMapper countUserContentMapper;

    public TableResultResponse<KpiCountUserContentInfo> getPageList(Integer page,
                                                                    Integer limit,
                                                                    String startDate,
                                                                    String endDate,
                                                                    Integer dayShow,
                                                                    Integer uid,
                                                                    Integer sourceType,
                                                                    Integer dutyType
    ) {
        Page<Object> result = PageHelper.startPage(page, limit);
        List<KpiCountUserContentInfo> list = getList(startDate, endDate, dayShow, uid, sourceType, dutyType);
        return new TableResultResponse<KpiCountUserContentInfo>(result.getTotal(), list);
    }

    public List<KpiCountUserContentInfo> getList(String startDate, String endDate, Integer dayShow, Integer uid, Integer sourceType, Integer dutyType) {
        String start = startDate.split(" ")[0].replace("-", "");
        String end = endDate.split(" ")[0].replace("-", "");
        return kpiCountUserContentMapper.getCountUserContent(start, end, dayShow, uid, sourceType, dutyType);
    }

    public void saveCountUserContent(String dateStr) {
        String start = null;
        String end = null;
        if (null != dateStr) {
            //如果有日期参数传入，则重新成传入日期的数据
            start = dateStr + " 00:00:00";
            end = dateStr + " 23:59:59";
        } else {
            //如果没有日期参数传入，则生成当时日期的前一天的数据
            Calendar c = Calendar.getInstance();
            //当前日期的前一天
            c.add(Calendar.DATE, -1);
            Date date = c.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            StringBuilder startDate = new StringBuilder(formatter.format(date));
            StringBuilder endDate = new StringBuilder(formatter.format(date));
            start = startDate.append(" 00:00:00").toString();
            end = endDate.append(" 23:59:59").toString();
        }
        List<ContentUserInfo> contentUserInfos = kpiCountUserContentMapper.getContent(start, end);
        if (null != contentUserInfos) {
            for (ContentUserInfo contentUserInfo : contentUserInfos) {
                CountUserContent countUserContent = new CountUserContent();
                countUserContent.setCid(contentUserInfo.getId());
                countUserContent.setCountDate(Integer.parseInt(start.split(" ")[0].replace("-", "")));
                countUserContent.setUid(contentUserInfo.getCreateUid());
                countUserContent.setEditName(contentUserInfo.getRealName());
                int category = contentUserInfo.getCategory();
                if (2 == category) {
                    ContentArticle contentArticle = new ContentArticle();
                    contentArticle.setCid(contentUserInfo.getId());
                    contentArticle = selectOne(contentArticle);
                    // 匹配<img>中的src数据
                    Matcher m = compile("<img.*src\\\\s*=\\\\s*(.*?)[^>]*?>").matcher(contentArticle.getContent());
                    if (m.find()) {
                        countUserContent.setArticle(1);
                    } else {
                        countUserContent.setPureText(1);
                    }
                } else if (3 == category) {
                    countUserContent.setImages(1);
                } else if (4 == category) {
                    countUserContent.setVideo(1);
                }

                if (1 == contentUserInfo.getSourceType()) {
                    countUserContent.setIsOriginal(1);
                }
                if (1 == contentUserInfo.getIsDuty()) {
                    countUserContent.setIsWork(1);
                }
                countUserContentMapper.insert(countUserContent);
            }
        }


        //live
        List<LiveUserInfo> liveUserInfos = kpiCountUserContentMapper.getLive(start, end);
        if (null != liveUserInfos) {
            for (LiveUserInfo liveUserInfo : liveUserInfos) {
                CountUserContent countUserContent = new CountUserContent();
                countUserContent.setCid(liveUserInfo.getId());
                countUserContent.setCountDate(Integer.parseInt(start.split(" ")[0].replace("-", "")));
                countUserContent.setUid(liveUserInfo.getCreateUid());
                countUserContent.setEditName(liveUserInfo.getRealName());
                countUserContent.setLive(1);
                countUserContent.setIsOriginal(1);
                if (1 == liveUserInfo.getIsDuty()) {
                    countUserContent.setIsWork(1);
                }
                countUserContentMapper.insert(countUserContent);
            }
        }
    }

}