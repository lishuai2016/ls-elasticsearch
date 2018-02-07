package ls.es.samples.entity;

import java.io.Serializable;

public class CountByProvince implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -725321826500034879L;
	
	private String province;
	private Integer orgSize;
	private Integer userSize;
	public String getProvince() {
		return province;
	}
	public Integer getOrgSize() {
		return orgSize;
	}
	public Integer getUserSize() {
		return userSize;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setOrgSize(Integer orgSize) {
		this.orgSize = orgSize;
	}
	public void setUserSize(Integer userSize) {
		this.userSize = userSize;
	}
	
}
