package ls.es.samples.entity;

import java.io.Serializable;
import java.util.List;

public class Pagination implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9543335600465859L;
	private int curNo;
	private long pageNo;
	private int pageSize;
	private long count;
	private List result;
	public Pagination(){
	}
	public Pagination(int from,int pageSize, long count, List list){
		long mod = count%pageSize;
		this.curNo = from/pageSize +1;
		this.pageNo =  mod > 0 ? count/pageSize+1 : count/pageSize;
		this.pageSize = pageSize;
		this.count = count;
		this.result = list;
	}
	
	public int getCurNo() {
		return curNo = curNo==0? 1 : curNo;
	}
	public void setCurNo(int curNo) {
		this.curNo = curNo;
	}
	public long getPageNo() {
		return pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public long getCount() {
		return count;
	}
	public List getResult() {
		return result;
	}
	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public void setResult(List result) {
		this.result = result;
	}
	
}
