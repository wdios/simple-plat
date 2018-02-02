/*
 * Powered By [wd]
 * Since 2014 - 2015
 */
package com.wis.model;

import java.io.Serializable;

/**
 * @author wd
 * @version 1.0
 * @date - 2015-11-12 14:59:25
 */
public class SysMenu implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long menuid;
    private String menucode;
    private String menuname;
    private String icon;
    private String url;
    private Long parentid;
    private String subids;
    private String des;
    private Boolean leaf;
    private String defstate;
    private Integer weight;
    private Boolean deleted;

    public SysMenu() {
    }

	public Long getMenuid() {
		return menuid;
	}

	public void setMenuid(Long menuid) {
		this.menuid = menuid;
	}

	public String getMenucode() {
        return menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode;
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

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getSubids() {
        return subids;
    }

    public void setSubids(String subids) {
        this.subids = subids;
    }

    public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getDefstate() {
		return defstate;
	}

	public void setDefstate(String defstate) {
		this.defstate = defstate;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
    
}
