package com.kanfa.news.info.vo.admin.kpicount;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jiqc
 * @date 2018/3/18 12:03
 */
public class KpiCountContentInfo implements Serializable{

    private Integer id;

    //员工id
    private Integer uid;

    //内容的数据类型，2:cid 4:vid  9:live_id
    private Integer type;

    //对应的type
    private Integer typeId;

    //员工姓名
    private String name;

    //文章类型： 1 纯文本 2 文字加图片 3 文字+视频4 文本+图片+视频 5 图集６资讯列表里新增视频
    private Integer articleType;

    //文章类型： 16 纯文本 17 图片 18视频 19文字＋图片 20 文字＋视频 21图片＋视频22 文＋图＋视频23图集。对应配置表
    private Integer workType;

    //工作类型的List集合
    private List<Integer> workTypeList;

    public List<Integer> getWorkTypeList() {
        return workTypeList;
    }

    public void setWorkTypeList(List<Integer> workTypeList) {
        this.workTypeList = workTypeList;
    }

    //权重
    private BigDecimal weight;

    //备注
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }
        if (!(o instanceof KpiCountContentInfo)) {
            return false;
        }
        KpiCountContentInfo user = (KpiCountContentInfo) o;
        return user.uid.equals(uid);
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + uid.hashCode();
        return result;
    }

}
