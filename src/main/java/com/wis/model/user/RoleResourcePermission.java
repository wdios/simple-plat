package com.wis.model.user;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * 此处没有使用关联 是为了提高性能（后续会挨着查询资源和权限列表，因为有缓存，数据量也不是很大 所以性能不会差）
 */
public class RoleResourcePermission {
	private Long id;
    /**
     * 角色id
     */
    private Role role;
    /**
     * 资源id
     */
    private Long resourceId;
    /**
     * 权限id列表
     * 数据库通过字符串存储 逗号分隔
     */
    private Set<Long> permissionIds;

    public RoleResourcePermission() {
    }

    public RoleResourcePermission(Long id) {
        this.id = id;
    }

    public RoleResourcePermission(Long resourceId, Set<Long> permissionIds) {
        this.resourceId = resourceId;
        this.permissionIds = permissionIds;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Set<Long> getPermissionIds() {
        if (permissionIds == null) {
            permissionIds = Sets.newHashSet();
        }
        return permissionIds;
    }

    public void setPermissionIds(Set<Long> permissionIds) {
        this.permissionIds = permissionIds;
    }
    
}
