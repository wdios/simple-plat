package com.wis.data;

import java.io.Serializable;
import java.util.List;

/**
 * treegrid 树形表格基础对象，后续的该类型的对象均继承该对象
 */
public class BaseTreeGrid implements Serializable {
	private static final long serialVersionUID = -9189631784252440402L;
	
	public static String STATE_OPEN = "open"; 
    public static String STATE_CLOSED = "closed";
	
	public String id;						// 节点id
	
	public String parentId;					// 父节点
	
	public String iconCls = "folder";		// 节点样式，默认即可
	
	public Boolean leaf = true;				// 是否为叶子节点，true表示是叶子节点，false表示不是叶子节点
	
	public Boolean expanded = true; 		// 是否展开，默认true,展开
	private String state = STATE_OPEN;
	
	public List<BaseTreeGrid> children;		// 子节点
	
	public Boolean checked;                 // 是否选中

	public BaseTreeGrid() { }
	
	public BaseTreeGrid(String id, String parentId) {
		this.id = id;
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
		if (expanded) {
		    state = STATE_OPEN;
		} else {
		    state = STATE_CLOSED;
		}
	}

	public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<BaseTreeGrid> getChildren() {
		return children;
	}

	public void setChildren(List<BaseTreeGrid> children) {
		this.children = children;
	}
	
}

