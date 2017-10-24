package com.wis.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 树 json model 数据
 */
public class SysMenuJsonTree {
 
	private long id;
    private long pid;
    private String text;
    private String state;
    
	private long menuid;
    private String menuname;
    private String icon;
    private String url;
    private long parentid;
    private String des;
    private String defstate;
	private List<SysMenuJsonTree> children;
	
	public SysMenuJsonTree() {}
	
	public SysMenuJsonTree(SysMenu sysMenu) {
		this.id = sysMenu.getMenuid();
		this.pid = sysMenu.getParentid();
		this.text = sysMenu.getMenuname();
		this.state = sysMenu.getDefstate();
		
		this.menuid = sysMenu.getMenuid();
		this.menuname = sysMenu.getMenuname();
		this.icon = sysMenu.getIcon();
		this.url = sysMenu.getUrl();
		this.parentid = sysMenu.getParentid();
		this.des = sysMenu.getDes();
		this.defstate = sysMenu.getDefstate();
	}
	
	public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
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
    
    public long getPid() {
        return pid;
    }
    
    public void setPid(long pid) {
        this.pid = pid;
    }

	public long getMenuid() {
		return menuid;
	}

	public void setMenuid(long menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getDefstate() {
		return defstate;
	}

	public void setDefstate(String defstate) {
		this.defstate = defstate;
	}

	public List<SysMenuJsonTree> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenuJsonTree> children) {
		this.children = children;
	}
	
	public static List<SysMenuJsonTree> getfatherNode(List<SysMenuJsonTree> treeDataList) {
        List<SysMenuJsonTree> newTreeDataList = new ArrayList<SysMenuJsonTree>();
        for (SysMenuJsonTree jsonTreeData : treeDataList) {
            if(0 == jsonTreeData.getPid()) {
                //获取父节点下的子节点
                jsonTreeData.setChildren(getChildrenNode(jsonTreeData.getId(),treeDataList));
                // jsonTreeData.setState("open");
                newTreeDataList.add(jsonTreeData);
            }
        }
        return newTreeDataList;
    }
	
	private static List<SysMenuJsonTree> getChildrenNode(long pid , List<SysMenuJsonTree> treeDataList) {
        List<SysMenuJsonTree> newTreeDataList = new ArrayList<SysMenuJsonTree>();
        for (SysMenuJsonTree jsonTreeData : treeDataList) {
            if(jsonTreeData.getPid() == 0)  continue;
            //这是一个子节点
            if(jsonTreeData.getPid() == pid) {
                //递归获取子节点下的子节点
                jsonTreeData.setChildren(getChildrenNode(jsonTreeData.getId(), treeDataList));
                newTreeDataList.add(jsonTreeData);
            }
        }
        return newTreeDataList;
    }
    
}
