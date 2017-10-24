package com.wis.model;

public class SysRole {
	
	private long id;
	private int type;
	private String name;
	private String role;
	private String indexpage;
	private String describution;
	
	private Boolean isShow = Boolean.FALSE;

	
	public SysRole() {
		
	}


	public SysRole(long id, int type, String name, String role,
			String indexpage, String describution, Boolean isShow) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.role = role;
		this.indexpage = indexpage;
		this.describution = describution;
		this.isShow = isShow;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getIndexpage() {
		return indexpage;
	}


	public void setIndexpage(String indexpage) {
		this.indexpage = indexpage;
	}


	public String getDescribution() {
		return describution;
	}


	public void setDescribution(String describution) {
		this.describution = describution;
	}


	public Boolean getIsShow() {
		return isShow;
	}


	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	
	
}
