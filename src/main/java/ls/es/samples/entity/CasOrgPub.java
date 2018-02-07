package ls.es.samples.entity;

import java.io.Serializable;
import java.util.Date;

public class CasOrgPub implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1104278952978280183L;
	private Integer id; // 主键ID
	private Integer orgId; // 组织ID
	private String fullname; // 组织全称
	private String creditCode; // 信用社代码
	private String province; // 省
	private String city; // 市
	private String addr; // 地址
	private String legalPerson; // 法定代表人
	private String contactPersion; // 联系人
	private String contactTel; // 电话
	private Date authorizedTime; // 首次批准时间
	private String licenseNumber; // 许可证号
	private String givenOrg; // 颁证机关
	private Date givenTime; // 颁证时间
	private Date validStart; // 许可开始时间
	private Date validEnd; // 许可结束时间
	private String permitFile;// 许可证附件
	private String website; // 网址
	private Float registeredCapita; // 注册资金
	private String intro; // 组织简介
	private String supervisionOrg; // 监督部门
	private String complaintTel; // 投诉电话
	private String complaintAddr; // 投诉地址
	private String workRange; // 职业范围
	private String searchKeywords; // 索引关键字
	private String lastyCount; // 上一年案件量
	private String lastyOrg; // 司法机关委托
	private String isCountryc; // 国家级认定0参加未通过  1通过   -1从未参加
	private Date countrycTime; // 通过时间
	private String countrycItem; // 通过项目
	private String countrycFile; // 上传附件
	private Integer isProvincec; // 省级认定0参加未通过  1通过   -1从未参加
	private Date provincecTime; // 通过时间
	private String provincecItem; // 通过项目
	private String provincecFile; // 上传附件
	private Integer isOrgc; // 司法鉴定机构认可0参加未通过  1通过   -1从未参加
	private Date orgcTime; // 通过时间
	private String orgcItem; // 通过项目
	private String orgcFile; // 上传附件
	private Integer isLastyac; // 上一年能力验证
	private Date lastyacTime; // 通过时间
	private String lastyacItem; // 通过项目
	private String lastyacResult; // 结果
	private String lastyacFile; // 上传附件
	private Date lastUpdateTime; // 信息更新时间
	private Integer checkedStatus;// 撤销状态：0正常，1申请撤销中，2已撤销
	private String lastyac; // 多条能力验证数据，格式为jsonarray,
    private String rewardDes ;//'受奖励情况';
    private String punishmentDes;//'处罚/处分情况';
	
	public Integer getId() {
		return id;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public String getFullname() {
		return fullname;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getAddr() {
		return addr;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public String getContactPersion() {
		return contactPersion;
	}

	public String getContactTel() {
		return contactTel;
	}

	public Date getAuthorizedTime() {
		return authorizedTime;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public String getGivenOrg() {
		return givenOrg;
	}

	public Date getGivenTime() {
		return givenTime;
	}

	public Date getValidStart() {
		return validStart;
	}

	public Date getValidEnd() {
		return validEnd;
	}

	public String getPermitFile() {
		return permitFile;
	}

	public String getWebsite() {
		return website;
	}

	public Float getRegisteredCapita() {
		return registeredCapita;
	}

	public String getIntro() {
		return intro;
	}

	public String getSupervisionOrg() {
		return supervisionOrg;
	}

	public String getComplaintTel() {
		return complaintTel;
	}

	public String getComplaintAddr() {
		return complaintAddr;
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

	public String getLastyOrg() {
		return lastyOrg;
	}

	public String getIsCountryc() {
		return isCountryc;
	}

	public Date getCountrycTime() {
		return countrycTime;
	}

	public String getCountrycItem() {
		return countrycItem;
	}

	public String getCountrycFile() {
		return countrycFile;
	}

	public Integer getIsProvincec() {
		return isProvincec;
	}

	public Date getProvincecTime() {
		return provincecTime;
	}

	public String getProvincecItem() {
		return provincecItem;
	}

	public String getProvincecFile() {
		return provincecFile;
	}

	public Integer getIsOrgc() {
		return isOrgc;
	}

	public Date getOrgcTime() {
		return orgcTime;
	}

	public String getOrgcItem() {
		return orgcItem;
	}

	public String getOrgcFile() {
		return orgcFile;
	}

	public Integer getIsLastyac() {
		return isLastyac;
	}

	public Date getLastyacTime() {
		return lastyacTime;
	}

	public String getLastyacItem() {
		return lastyacItem;
	}

	public String getLastyacResult() {
		return lastyacResult;
	}

	public String getLastyacFile() {
		return lastyacFile;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public Integer getCheckedStatus() {
		return checkedStatus;
	}

	public String getLastyac() {
		return lastyac;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public void setContactPersion(String contactPersion) {
		this.contactPersion = contactPersion;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public void setAuthorizedTime(Date authorizedTime) {
		this.authorizedTime = authorizedTime;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public void setGivenOrg(String givenOrg) {
		this.givenOrg = givenOrg;
	}

	public void setGivenTime(Date givenTime) {
		this.givenTime = givenTime;
	}

	public void setValidStart(Date validStart) {
		this.validStart = validStart;
	}

	public void setValidEnd(Date validEnd) {
		this.validEnd = validEnd;
	}

	public void setPermitFile(String permitFile) {
		this.permitFile = permitFile;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setRegisteredCapita(Float registeredCapita) {
		this.registeredCapita = registeredCapita;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setSupervisionOrg(String supervisionOrg) {
		this.supervisionOrg = supervisionOrg;
	}

	public void setComplaintTel(String complaintTel) {
		this.complaintTel = complaintTel;
	}

	public void setComplaintAddr(String complaintAddr) {
		this.complaintAddr = complaintAddr;
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

	public void setLastyOrg(String lastyOrg) {
		this.lastyOrg = lastyOrg;
	}

	public void setIsCountryc(String isCountryc) {
		this.isCountryc = isCountryc;
	}

	public void setCountrycTime(Date countrycTime) {
		this.countrycTime = countrycTime;
	}

	public void setCountrycItem(String countrycItem) {
		this.countrycItem = countrycItem;
	}

	public void setCountrycFile(String countrycFile) {
		this.countrycFile = countrycFile;
	}

	public void setIsProvincec(Integer isProvincec) {
		this.isProvincec = isProvincec;
	}

	public void setProvincecTime(Date provincecTime) {
		this.provincecTime = provincecTime;
	}

	public void setProvincecItem(String provincecItem) {
		this.provincecItem = provincecItem;
	}

	public void setProvincecFile(String provincecFile) {
		this.provincecFile = provincecFile;
	}

	public void setIsOrgc(Integer isOrgc) {
		this.isOrgc = isOrgc;
	}

	public void setOrgcTime(Date orgcTime) {
		this.orgcTime = orgcTime;
	}

	public void setOrgcItem(String orgcItem) {
		this.orgcItem = orgcItem;
	}

	public void setOrgcFile(String orgcFile) {
		this.orgcFile = orgcFile;
	}

	public void setIsLastyac(Integer isLastyac) {
		this.isLastyac = isLastyac;
	}

	public void setLastyacTime(Date lastyacTime) {
		this.lastyacTime = lastyacTime;
	}

	public void setLastyacItem(String lastyacItem) {
		this.lastyacItem = lastyacItem;
	}

	public void setLastyacResult(String lastyacResult) {
		this.lastyacResult = lastyacResult;
	}

	public void setLastyacFile(String lastyacFile) {
		this.lastyacFile = lastyacFile;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setCheckedStatus(Integer checkedStatus) {
		this.checkedStatus = checkedStatus;
	}

	public void setLastyac(String lastyac) {
		this.lastyac = lastyac;
	}

	public String getRewardDes() {
		return rewardDes;
	}

	public String getPunishmentDes() {
		return punishmentDes;
	}

	public void setRewardDes(String rewardDes) {
		this.rewardDes = rewardDes;
	}

	public void setPunishmentDes(String punishmentDes) {
		this.punishmentDes = punishmentDes;
	}

}
