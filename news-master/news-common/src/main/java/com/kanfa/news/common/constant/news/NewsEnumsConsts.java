package com.kanfa.news.common.constant.news;

import com.kanfa.news.common.util.DateHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jiqc
 * @date 2018/2/28 17:05
 */
public class NewsEnumsConsts {

    /**
     * 频道的状态 命名规则：实体名+Of+字段属性名
     */
    public enum ChannelOfChannelStatus{
        NORMAL(1,"","正常"), DELETE(0,"","删除");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ChannelOfChannelStatus( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }
    /**
     * 频道的发布状态 命名规则：实体名+Of+字段属性名
     */
    public enum ChannelOfIsPublish{
        PUBLISHED(1,"","已发布"), NOTPUBLISH(0,"","未发布");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ChannelOfIsPublish( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }

    //kf_content的content_type的数据类型
    public enum ContentOfContentType{
        Project(1,"专题"),Article(2,"文章"),Atlas(3,"图集"),
        Video(4,"视频"),Live(9,"直播"),VR(11,"vr"),Question(12,"问答"),Activity(13,"活动"),PoliticalAndLaw(25,"政法先锋");

        private Integer key;
        private String value;

        ContentOfContentType(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        // set get
        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        // 获得contentType类型的方法
        public static String getType(Integer key) {
            for (ContentOfContentType c : ContentOfContentType.values()) {
                if (c.getKey().equals(key)) {
                    return c.getValue();
                }
            }
            return "";
        }
    }

    //kf_content的content_style  抓取文章类型(0:默认;1:判决书;2:公告)
    public enum ContentOfContentStyle{
        deft(0,"默认"),judgment(1,"判决书"),notice(2,"公告");

        private Integer key;
        private String value;

        ContentOfContentStyle(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    //kf_content的SearchType的数据类型 1-标题，2-文章id，3-创建人
    public enum ContentOfSearchType{
        Title(1,"标题"),ContentId(2,"内容id"),CreateUser(3,"创建人");

        private Integer key;
        private String value;

        ContentOfSearchType(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        // set get
        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        // 获得contentType类型的方法
        public static String getType(Integer key) {
            for (ContentOfContentType c : ContentOfContentType.values()) {
                if (c.getKey() == key) {
                    return c.getValue();
                }
            }
            return "";
        }
    }
    //审核列表显示 1-显示,0-不显示
    public enum ContentOfIsCheck{
        Show(1,"显示"),Noshow(0,"不显示");

        private Integer key;
        private String value;

        ContentOfIsCheck(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }
        public String getValue() {
            return value;
        }
    }

    //1 原创 2 转载 3 抓取
    public enum ContentOfSourceType{
        Original(1,"原创"),Reprinted (2,"转载"),Grab(3,"抓取");

        private Integer key;
        private String value;

        ContentOfSourceType(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }
        public String getValue() {
            return value;
        }
    }

    /**
     * 频道的类别
     */
    public enum ChannelOfCategory{
        INFO_APP(1,"","APP首页资讯"), VIDEO_APP(2,"","APP视频"),VR_APP(3,"","APPVR"),INFO_PC(4,"","PC资讯");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ChannelOfCategory( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }
    /**
     *0:无图,1：小图，2：大图，3：并列三图，4图集大图,5,视频大图6：图文广告，7：文字广告，8：外链广告,9:直播，10：并列四图,11:投票活动',
     */
    public enum ChannelContentOfCardType{
        None(0,"","无图"), SmallImg(1,"","小图"),BigImg(2,"","大图"),ThreeImg(3,"","并列三图")
        ,GroupImg(4,"","图集大图")
        ,VideoImg(5,"","视频大图")
        ,AdImg(6,"","图文广告")
        ,AdChar(7,"","文字广告")
        ,OutUrl(8,"","外链广告")
        ,Live(9,"","直播")
        ,FourImg(10,"","并列四图")
        ,VoteActivity(11,"","投票活动");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ChannelContentOfCardType( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }

    /**
     * 频道绑定表来源 内容来源.1：content表，2：live表
     */
    public enum ChannelContentOfFromType{
        Content(1,"","content表"),Live(2,"","live表");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ChannelContentOfFromType( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }

    /**
     * 内容的类别0待审核，1审核通过，2审核不通过
     */
    public enum ContentOfCheckStatus{
        WAITCHECK(0,"","待审核"), PASS(1,"","审核通过"), CHECKFAIL(2,"","审核不通过"), OTHER(3,"","其他");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ContentOfCheckStatus( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }

        // 获得VideoLibraryOfStatus类型的方法
        public static String getStatus(Integer key) {
            for (ContentOfCheckStatus c : ContentOfCheckStatus.values()) {
                if (c.key() == key) {
                    return c.value();
                }
            }
            return "";
        }
    }


    /**
     * 视频库的状态 status 1：上传失败 2：上传完成 3：转码中 4：转码完成'
     */
    public enum VideoLibraryOfStatus{
        UploadFailed(1,"上传失败"),UploadComplete(2,"上传完成"),Transcoding(3,"转码中"),
        zhuangccheng(4,"转码完成");

        private Integer key;
        private String value;

        VideoLibraryOfStatus(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        // set get
        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        // 获得VideoLibraryOfStatus类型的方法
        public static String getType(Integer key) {
            for (VideoLibraryOfStatus c : VideoLibraryOfStatus.values()) {
                if (c.getKey() == key) {
                    return c.getValue();
                }
            }
            return "";
        }
    }

    /**
     * 直播的状态 live_status 直播状态 0: 预告 1:直播 2:回顾
     */
    public enum LiveOfStatus{
        Trailer(0,"预告"),OnLive(1,"直播"),Review(3,"回顾");

        private Integer key;
        private String value;

        LiveOfStatus(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        // set get
        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        // 获得LiveOfStatus类型的方法
        public static String getType(Integer key) {
            for (LiveOfStatus c : LiveOfStatus.values()) {
                if (c.getKey() == key) {
                    return c.getValue();
                }
            }
            return "";
        }
    }
    /**
     * 直播法院的级别 courtLevel  1:最高法院 2:高级法院 3:中级法院 4:基层法院
     */
    public enum CourtOfLevel{
        TheSupreme(1,"最高法院"),TheHigh(2,"高级法院"),Intermediate(3,"中级法院")
        ,TheGrassRoots(4,"基层法院");

        private Integer key;
        private String value;

        CourtOfLevel(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        // set get
        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        // 获得courtLevel类型的方法
        public static String getType(Integer key) {
            for (CourtOfLevel c : CourtOfLevel.values()) {
                if (c.getKey() == key) {
                    return c.getValue();
                }
            }
            return "";
        }
    }

    /**
     * 直播类表下的 from_type   1:视频  2:直播
     */
    public enum fromOfType {
        Video(1,"视频"),Live(2,"直播");

        private Integer key;
        private String value;

        fromOfType(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        // set get
        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        // 获得courtLevel类型的方法
        public static String getType(Integer key) {
            for (fromOfType c : fromOfType.values()) {
                if (c.getKey() == key) {
                    return c.getValue();
                }
            }
            return "";
        }
    }
    /**
     * 内容的状态，1：正常，0：删除
     */
    public enum ContentOfContentState{
        NORMAL(1,"","正常"), DELETE(0,"","删除");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ContentOfContentState( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }
    /**
     * 内容的状态，1：正常，0：删除
     */
    public enum ContentVideoOfIsDelete{
        NORMAL(1,"","正常"), DELETE(0,"","删除");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ContentVideoOfIsDelete( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }
    /**
     * 上传状态(1:上传失败;2:上传成功)
     */
    public enum BurstResourceOfUploadStatus{
        Failed(1,"","上传失败"), Success(2,"","上传成功");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        BurstResourceOfUploadStatus( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }

    //爆料状态 新闻爆料状态(1:未受理;2:完成;3:拒绝)
    public enum BurstOfStatus{
        NotHandle(1,"","未受理"), Finished(2,"","完成"), Refuse(3,"","拒绝");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        BurstOfStatus( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }

    //类型(1:图片;2:视频;3:文件)
    public enum BurstResourceOfType{
        Image(1,"","图片"), Video(2,"","视频"), File(3,"","文件");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        BurstResourceOfType( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }

    /**
     * 	来源(1:用户;2:小编)
     */
    public enum BurstResourceOfBurstSource{
        User(1,"","用户"), CorpUser(2,"","小编");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        BurstResourceOfBurstSource( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }

    /**
     * 焦点图的状态，1、站内内容 2、url 3、app 4、URL不跳转(全景图) 5、直播
     */
    public enum ChannelFocusOfJump{
        Content(1,"","站内内容"), Url(2,"","url"),App(3,"","app"),Fullshot(4,"","URL不跳转(全景图)"),Live(5,"","直播"),;

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ChannelFocusOfJump( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }
    /**
     * 内容的数据类型，2:cid 4:vid  9:live_id
     */
    public enum KpiCountOfType{
        Cid(2,"","cid"),Vid(4,"","vid"),Live_id(9,"","live_id");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        KpiCountOfType( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }
    public enum DepartmentOfName{
        Dept1(1,"","运营中心"),Dept2(2,"","采访中心"),Dept3(3,"","视频直播运营部"),Dept4(4,"","全国采访部"),Dept5(5,"","机动即时部");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        DepartmentOfName( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }

    //1在职 2 离职 3删除
    public enum CorpUserOfStatus{
        OnPosition(1,"在职"),Resignation(2,"离职"),Delete(3,"删除");

        private Integer key;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        CorpUserOfStatus( Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }
    //状态 ： 1  正常  2  停用
    public enum CorpDeptOfStat{
        Normal(1,"正常"),Discontinuation(2,"停用");

        private Integer key;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        CorpDeptOfStat( Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }

    //搜索类型 1-评论，2-ip，3-日期，4-文章id，5-直播Id
    public enum CommentInfoOfKeywordType{
        Content(0,"","评论"),Ip(2,"","ip"),Date(3,"","日期"),ContentId(4,"","文章id"),LiveId(5,"","直播Id");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        CommentInfoOfKeywordType( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }

    //是否包含敏感词，1：是，0：否
    public enum CommentOfSens{
        Sens(0,"","是敏感"),NoSens(2,"","不包含敏感");

        private Integer key;
        private String desc;
        private String value;

        CommentOfSens( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }

    //mongo日志event类型
    public enum ContentArticleLogOfEvent{
        create("新增","create"),edit("编辑","edit"),check("审核","check");

        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ContentArticleLogOfEvent(String desc,String value) {
            this.desc = desc;this.value = value;
        }

        public String desc() {
            return desc;
        }
        public String value() {
            return value;
        }
    }

    //0-全部，1-今天，2-本周，3-本月
    public enum CommentInfoOfTimeType{
        All(0,"全部", ""),Today(1,"今天",DateHandler.getTimesmorning().toLocaleString()+"="+DateHandler.getTimesnight().toLocaleString())
        ,Week(2,"本周",DateHandler.getTimesWeekmorning().toLocaleString()+"="+DateHandler.getTimesWeeknight().toLocaleString()),
        Month(3,"本月",DateHandler.getTimesMonthmorning().toLocaleString()+"="+DateHandler.getTimesMonthnight().toLocaleString());

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        CommentInfoOfTimeType( Integer key,String desc,String value) {
            this.key = key;
            this.desc = desc;
            this.value = value;
        }

        public Date getBeginTime() {
            String[] split = value.split("=");
            try {
                SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sDateFormat.parse(split[0]);
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
        public Date getEndTime() {
            String[] split = value.split("=");
            try {
                SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sDateFormat.parse(split[1]);
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }

    //目标类型 1 专题 2 文章 3 图集 4 视频 9直播 11VR 12问答 13活动 25政法先锋 22直播 33爆料
    public enum MessageOfTargetType{
        Subject(1,"专题"),Article(2,"文章"),Atlas(3,"图集"),
        Video(4,"视频"),Live(9,"直播"),VR(11,"vr"),Question(12,"问答"),Activity(13,"活动")
        ,PoliticalAndLaw(25,"政法先锋"),NewLive(22,"直播"),Burst(33,"爆料");

        private Integer key;
        private String value;

        MessageOfTargetType(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
        // 获得contentType类型的方法
        public static String getType(Integer key) {
            for (MessageOfTargetType c : MessageOfTargetType.values()) {
                if (c.getKey().equals(key)) {
                    return c.getValue();
                }
            }
            return "";
        }
    }

    //广告类型，1：图文，2：文字，3：APP下载'  type字段
    public enum AdvOfType{
        ImgAndWord(1,"","图文"),Word(2,"","文字"),App(3,"","APP下载"),StartUpPage(4,"","启动页");

        private Integer key;
        private String desc;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        AdvOfType(Integer key,String desc,String value) {
            this.key = key;this.desc = desc;this.value = value;
        }

        public Integer key() {
            return key;
        }
        public String desc() {
            return desc;
        }
        public String value() {
            return value;
        }
    }

    //1在职 2 离职 3删除
    public enum BaseUserOfStatus{
        OnPosition("1","在职"),Resignation("2","离职");

        private String key;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        BaseUserOfStatus( String key,String value) {
            this.key = key;
            this.value = value;
        }

        public String key() {
            return key;
        }

        public String value() {
            return value;
        }
    }
    //是否使用导语来做图片介绍，1：是，0：否
    public enum ContentImageOfDescType{
        Yes(1,"是"),No(0,"否");

        private Integer key;
        private String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ContentImageOfDescType( Integer key,String value) {
            this.key = key;
            this.value = value;
        }

        public Integer key() {
            return key;
        }

        public String value() {
            return value;
        }
    }

    public static void main(String[] args) {
        System.out.println(CommentInfoOfTimeType.Month.getBeginTime());
        System.out.println(CommentInfoOfTimeType.Month.getEndTime());

//        String strDate="2005-4-22 9:10:29";
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
//        //必须捕获异常
//        try {
//            Date date=simpleDateFormat.parse(strDate);
//            System.out.println(date);
//            System.out.println(sDateFormat.parse(strDate));
//        } catch(ParseException px) {
//            px.printStackTrace();
//        }
    }
}
