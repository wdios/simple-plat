package com.wis.model;

public class SysResource {
	
	private long id;
	private String name;
	private String identity;
	private String url;
	private long parentId;
	private int weight;
	private String defstate;
	private Boolean isShow = Boolean.FALSE;
	
	
	
	public SysResource() {
	}
	public SysResource(long id, String name, String identity, String url,
			long parentId, int weight, String defstate, Boolean isShow) {
		super();
		this.id = id;
		this.name = name;
		this.identity = identity;
		this.url = url;
		this.parentId = parentId;
		this.weight = weight;
		this.defstate = defstate;
		this.isShow = isShow;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getDefstate() {
		return defstate;
	}
	public void setDefstate(String defstate) {
		this.defstate = defstate;
	}
	public Boolean getIsShow() {
		return isShow;
	}
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}
	
	
	

}
