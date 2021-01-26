package com.web.blog.model.dto;

public class Curriculum {
 
	private int crid;
	private int crtid;
	private int rmid;
	private int parentid;
	private String memo;
	private Curriculumtext curriculumtext;
	
	public Curriculum() {
		super();
	}
	
	public Curriculum(int crid, int crtid, int rmid, int parentid, String memo) {
		super();
		this.crid = crid;
		this.crtid = crtid;
		this.rmid = rmid;
		this.parentid = parentid;
		this.memo = memo;
	}
	
	public int getCrid() {
		return crid;
	}
	public void setCrid(int crid) {
		this.crid = crid;
	}
	public int getCrtid() {
		return crtid;
	}
	public void setCrtid(int crtid) {
		this.crtid = crtid;
	}
	public int getRmid() {
		return rmid;
	}
	public void setRmid(int rmid) {
		this.rmid = rmid;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "Curriculum [crid=" + crid + ", crtid=" + crtid + ", rmid=" + rmid + ", parentid=" + parentid + ", memo="
				+ memo + "]";
	}
	
	
}
