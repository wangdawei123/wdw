package com.kanfa.news.app.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author chenjie
 * @email chenjie@kanfanews.com
 * @date 2018-03-18 15:24:35
 */
@Table(name = "kf_live_court")
public class LiveCourt implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //法院名称
    @Column(name = "name")
    private String name;
	
	    //法院级别 1:最高法院 2:高级法院 3:中级法院 4:基层法院
    @Column(name = "court_level")
    private Integer courtLevel;
	
	    //头像地址
    @Column(name = "avatar")
    private String avatar;
	
	    //省-id
    @Column(name = "province_id")
    private Integer provinceId;
	
	    //省名
    @Column(name = "province_name")
    private String provinceName;
	
	    //市-id
    @Column(name = "city_id")
    private Integer cityId;
	
	    //市名
    @Column(name = "city_name")
    private String cityName;
	
	    //直播次数
    @Column(name = "live_count")
    private Integer liveCount;
	
	    //创建人
    @Column(name = "create_uid")
    private Integer createUid;
	
	    //创建日期
    @Column(name = "create_time")
    private Date createTime;
	
	    //最后编辑人
    @Column(name = "update_uid")
    private Integer updateUid;
	
	    //最后更新日期
    @Column(name = "update_time")
    private Date updateTime;
	
	    //源站key
    @Column(name = "key")
    private String key;
	
	    //源站父key
    @Column(name = "Pkey")
    private String pkey;
	
	    //接入
    @Column(name = "EnCourt")
    private Integer encourt;
	
	    //状态(1:正常;0:已删除)
    @Column(name = "stat")
    private Integer stat;
	

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：法院名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：法院名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：法院级别 1:最高法院 2:高级法院 3:中级法院 4:基层法院
	 */
	public void setCourtLevel(Integer courtLevel) {
		this.courtLevel = courtLevel;
	}
	/**
	 * 获取：法院级别 1:最高法院 2:高级法院 3:中级法院 4:基层法院
	 */
	public Integer getCourtLevel() {
		return courtLevel;
	}
	/**
	 * 设置：头像地址
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/**
	 * 获取：头像地址
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * 设置：省-id
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取：省-id
	 */
	public Integer getProvinceId() {
		return provinceId;
	}
	/**
	 * 设置：省名
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	/**
	 * 获取：省名
	 */
	public String getProvinceName() {
		return provinceName;
	}
	/**
	 * 设置：市-id
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取：市-id
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * 设置：市名
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * 获取：市名
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * 设置：直播次数
	 */
	public void setLiveCount(Integer liveCount) {
		this.liveCount = liveCount;
	}
	/**
	 * 获取：直播次数
	 */
	public Integer getLiveCount() {
		return liveCount;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建人
	 */
	public Integer getCreateUid() {
		return createUid;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：最后编辑人
	 */
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	/**
	 * 获取：最后编辑人
	 */
	public Integer getUpdateUid() {
		return updateUid;
	}
	/**
	 * 设置：最后更新日期
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：最后更新日期
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：源站key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 获取：源站key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * 设置：源站父key
	 */
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	/**
	 * 获取：源站父key
	 */
	public String getPkey() {
		return pkey;
	}
	/**
	 * 设置：接入
	 */
	public void setEncourt(Integer encourt) {
		this.encourt = encourt;
	}
	/**
	 * 获取：接入
	 */
	public Integer getEncourt() {
		return encourt;
	}
	/**
	 * 设置：状态(1:正常;0:已删除)
	 */
	public void setStat(Integer stat) {
		this.stat = stat;
	}
	/**
	 * 获取：状态(1:正常;0:已删除)
	 */
	public Integer getStat() {
		return stat;
	}
}
