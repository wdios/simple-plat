package com.wis.model;

import java.util.ArrayList;
import java.util.List;

public class SysResourceJsonTree {
	
	private long id;
	private long pid;
	private String text;
	private String state;
	private String name;
	private String identity;
	private String url;
	private long parentId;
	private int weight;
	private String defstate;
	private List<SysResourceJsonTree> children;
	
	public SysResourceJsonTree() {
	}
	public SysResourceJsonTree(long id, long pid, String text, String state,
			String name, String identity, String url, long parentId,
			int weight, String defstate, List<SysResourceJsonTree> children) {
		super();
		this.id = id;
		this.pid = pid;
		this.text = text;
		this.state = state;
		this.name = name;
		this.identity = identity;
		this.url = url;
		this.parentId = parentId;
		this.weight = weight;
		this.defstate = defstate;
		this.children = children;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public List<SysResourceJsonTree> getChildren() {
		return children;
	}
	public void setChildren(List<SysResourceJsonTree> children) {
		this.children = children;
	}
	
	public static List<SysResourceJsonTree> getFatherNode(List<SysResourceJsonTree> treeDataList){
		List<SysResourceJsonTree> newTreeDataList = new ArrayList<SysResourceJsonTree>();
		for(SysResourceJsonTree jsonTreeData : treeDataList){
			if(0 == jsonTreeData.getPid()){
				//获取父节点下的子节点
				jsonTreeData.setChildren(getChildrenNode(jsonTreeData.getId(),treeDataList));
				newTreeDataList.add(jsonTreeData);
			}
		}
		return newTreeDataList;
	}
	
	private static List<SysResourceJsonTree> getChildrenNode(long pid,List<SysResourceJsonTree> treeDataList){
		List<SysResourceJsonTree> newTreeDataList = new ArrayList<SysResourceJsonTree>();
		for(SysResourceJsonTree jsonTreeData : treeDataList){
			if(jsonTreeData.getPid() == 0 ){
				continue;
			}
			//子节点
			if(jsonTreeData.getPid() == pid){
				//递归获取子节点下的子节点
				jsonTreeData.setChildren(getChildrenNode(jsonTreeData.getId(), treeDataList));
				newTreeDataList.add(jsonTreeData);
			}
			
		}
		
		return newTreeDataList;
	}
	
	
	

}
