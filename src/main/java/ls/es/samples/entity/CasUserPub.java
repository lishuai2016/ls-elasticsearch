package ls.es.samples.entity;

import java.io.Serializable;
import java.util.Date;

public class CasUserPub implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4807727722782483677L;
	private Integer id; // 主键ID
	private Integer userId; // 用户ID
	private String realname; // 真实姓名
	private Integer sex; // 性别:0女,1男,2未填
	private Date birth; // 生日
	private Integer userType; // 用户类型1鉴定人，2鉴定人助理
	private String degree; // 学历
	private String title; // 职称
	private String zyzh; // 执业证号
	private Date workTime; // 从业时间
	private Integer workType; // 从业类型 1专职 0兼职
	private String workRange; // 职业范围
	private String searchKeywords; // 索引关键字
	private String lastyCount; // 上一年案件量
	private String myHonor; // 所获荣誉
	private String myPunishments; // 所受惩罚
	private String intro; // 鉴定人简介
	private Date workTimeEnd; // 从业时间
	private String idcard; // 身份证号
	private Date registTime; // 首次登记时间
	private Integer orgId; // 所属组织ID
	private String city;
	private String addr;

	private String province;
	private String orgName;
	private Integer orgPubId; // 所属组织发布Id
	
	
	
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getRealname() {
		return realname;
	}

	public Integer getSex() {
		return sex;
	}

	public Date getBirth() {
		return birth;
	}

	public Integer getUserType() {
		return userType;
	}

	public String getDegree() {
		return degree;
	}

	public String getTitle() {
		return title;
	}

	public String getZyzh() {
		return zyzh;
	}

	public Date getWorkTime() {
		return workTime;
	}

	public Integer getWorkType() {
		return workType;
	}

	public String getWorkRange() {
		return workRange;
	}

	public String getSearchKeywords() {
		return searchKeywords;
	}

	public String getLastyCount() {
		return lastyCount;
	}

	public String getMyHonor() {
		return myHonor;
	}

	public String getMyPunishments() {
		return myPunishments;
	}

	public String getIntro() {
		return intro;
	}

	public Date getWorkTimeEnd() {
		return workTimeEnd;
	}

	public String getIdcard() {
		return idcard;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setZyzh(String zyzh) {
		this.zyzh = zyzh;
	}

	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	public void setWorkType(Integer workType) {
		this.workType = workType;
	}

	public void setWorkRange(String workRange) {
		this.workRange = workRange;
	}

	public void setSearchKeywords(String searchKeywords) {
		this.searchKeywords = searchKeywords;
	}

	public void setLastyCount(String lastyCount) {
		this.lastyCount = lastyCount;
	}

	public void setMyHonor(String myHonor) {
		this.myHonor = myHonor;
	}

	public void setMyPunishments(String myPunishments) {
		this.myPunishments = myPunishments;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setWorkTimeEnd(Date workTimeEnd) {
		this.workTimeEnd = workTimeEnd;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getProvince() {
		return province;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getOrgPubId() {
		return orgPubId;
	}

	public void setOrgPubId(Integer orgPubId) {
		this.orgPubId = orgPubId;
	}

}
