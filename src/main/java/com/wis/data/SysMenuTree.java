/*
 * Powered By [wd]
 * Since 2014 - 2015
 */
package com.wis.data;

import java.util.ArrayList;
import java.util.List;

import com.wis.model.SysMenu;


/**
 * @author wd
 * @version 1.0
 * @date - 2015-11-12 14:59:25
 */
public class SysMenuTree extends BaseTreeGrid {
    private static final long serialVersionUID = -1L;

    private String menuname;
    private String icon;
    private String url;
    private String des;

    public SysMenuTree() {
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	/**
	 * 格式化菜单数组为菜单树形数组
	 * @param list
	 * @return
	 */
	public static List<SysMenuTree> formatTree(List<SysMenu> list) {
        List<SysMenuTree> nodeList = new ArrayList<SysMenuTree>();
        for (int i = 0; i < list.size(); i++) {
            SysMenu aSysMenu = list.get(i);
            SysMenuTree aSysMenuTree = new SysMenuTree();
            aSysMenuTree.setId(aSysMenu.getMenuid() + "");
            aSysMenuTree.setParentId(aSysMenu.getParentid() + "");
            aSysMenuTree.setState(aSysMenu.getDefstate());
            aSysMenuTree.setMenuname(aSysMenu.getMenuname());
            aSysMenuTree.setIcon(aSysMenu.getIcon());
            aSysMenuTree.setUrl(aSysMenu.getUrl());
            aSysMenuTree.setDes(aSysMenu.getDes());
            nodeList.add(aSysMenuTree);
        }
        
        List<SysMenuTree> treeList = new ArrayList<SysMenuTree>();
        for (SysMenuTree nodeC : nodeList) {
            boolean mark = false;
            for (SysMenuTree nodeP : nodeList) {
                if (nodeC.getParentId() != null && nodeC.getParentId().equals(nodeP.getId())) {
                    nodeP.setLeaf(false);
                    mark = true;
                    if (nodeP.getChildren() == null) {
                        nodeP.setChildren(new ArrayList<BaseTreeGrid>());
                    }
                    nodeP.getChildren().add(nodeC);
                    nodeP.setExpanded(false);
                    break;
                }
            }
            if (!mark) {
                treeList.add(nodeC);
                nodeC.setExpanded(false);
            }
        }
        return treeList;
    }
    
}
