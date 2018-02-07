package ls.es.samples.entity;

import java.io.Serializable;
import java.util.Date;

public class CasUserAndOrgPub implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer casUserId;// 主键
	private String realname;
	private Integer sex;
	private Date birth;
	private String degree;
	private String title;
	private Integer workType;
	private String workRange;
	private String zyzh;
	private String userIntro;
	private String userProvince;
	private String orgName;
	private Integer userType;
	private Integer orgId;
	private Date userRegistTime;
	private Integer orgPubId;

	private Integer casOrgId;// 主键
	private String fullname;
	private Integer orgOrgId;

	private String licenseNumber;
	private String creditCode;
	private String website;
	private String supervisionOrg;
	private String contactTel;
	private String complaintTel;
	private String orgIntro;
	private String orgProvince;
	private String city;
	private String addr;
	private String legalPerson;
	private String rewardDes;



	public Integer getOrgOrgId() {
		return orgOrgId;
	}

	public void setOrgOrgId(Integer orgOrgId) {
		this.orgOrgId = orgOrgId;
	}

	public Integer getCasUserId() {
		return casUserId;
	}

	public void setCasUserId(Integer casUserId) {
		this.casUserId = casUserId;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getWorkType() {
		return workType;
	}

	public void setWorkType(Integer workType) {
		this.workType = workType;
	}



	public String getZyzh() {
		return zyzh;
	}

	public void setZyzh(String zyzh) {
		this.zyzh = zyzh;
	}

	public String getUserIntro() {
		return userIntro;
	}

	public void setUserIntro(String userIntro) {
		this.userIntro = userIntro;
	}

	public String getUserProvince() {
		return userProvince;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}


	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Date getUserRegistTime() {
		return userRegistTime;
	}

	public void setUserRegistTime(Date userRegistTime) {
		this.userRegistTime = userRegistTime;
	}

	public Integer getOrgPubId() {
		return orgPubId;
	}

	public void setOrgPubId(Integer orgPubId) {
		this.orgPubId = orgPubId;
	}

	public Integer getCasOrgId() {
		return casOrgId;
	}

	public void setCasOrgId(Integer casOrgId) {
		this.casOrgId = casOrgId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getSupervisionOrg() {
		return supervisionOrg;
	}

	public void setSupervisionOrg(String supervisionOrg) {
		this.supervisionOrg = supervisionOrg;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getComplaintTel() {
		return complaintTel;
	}

	public void setComplaintTel(String complaintTel) {
		this.complaintTel = complaintTel;
	}

	public String getOrgIntro() {
		return orgIntro;
	}

	public void setOrgIntro(String orgIntro) {
		this.orgIntro = orgIntro;
	}

	public String getOrgProvince() {
		return orgProvince;
	}

	public void setOrgProvince(String orgProvince) {
		this.orgProvince = orgProvince;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getWorkRange() {
		return workRange;
	}

	public void setWorkRange(String workRange) {
		this.workRange = workRange;
	}

	public String getRewardDes() {
		return rewardDes;
	}

	public void setRewardDes(String rewardDes) {
		this.rewardDes = rewardDes;
	}

}
