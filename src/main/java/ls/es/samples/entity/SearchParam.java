package ls.es.samples.entity;

import java.io.Serializable;

public class SearchParam implements Serializable , Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -725321826500034879L;
	public SearchParam clone(){
		SearchParam param = null;
        try {
        	param = (SearchParam) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return param;
    }
	private String key;
	private String province;
	private String range;
	private Integer start;
	private Integer size;
	public String getKey() {
		return key;
	}
	public String getProvince() {
		return province;
	}
	public String getRange() {
		return range;
	}
	public Integer getStart() {
		return start == null ? 0 : start;
	}
	public Integer getSize() {
		return size == null ? 15 : size;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
}
