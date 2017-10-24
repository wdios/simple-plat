package com.wis.model.user;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * 组织机构 工作职位  用户  角色 关系表
 * 1、授权的五种情况
 * 只给组织机构授权 (orgnizationId=? and jobId=0)
 * 只给工作职务授权 (orgnizationId=0 and jobId=?)
 * 给组织机构和工作职务都授权 (orgnizationId=? and jobId=?)
 * 给用户授权  (userId=?)
 * 给组授权 (groupId=?)
 * <p/>
 * 因此查询用户有没有权限 就是
 * where (orgnizationId=? and jobId=0) or (organizationId = 0 and jobId=?) or (orgnizationId=? and jobId=?) or (userId=?) or (groupId=?)
 * <p/>
 * <p/>
 * 2、为了提高性能
 * 放到一张表
 * 此处不做关系映射（这样需要配合缓存）
 * <p/>
 * 3、如果另一方是可选的（如只选组织机构 或 只选工作职务） 那么默认0 使用0的目的是为了也让走索引
 * <p/>
 */
public class Auth {
    /**
     * 组织机构
     */
    private Long organizationId = 0L;
    /**
     * 工作职务
     */
    private Long jobId = 0L;
    /**
     * 用户
     */
    private Long userId = 0L;
    /**
     * 组
     */
    private Long groupId = 0L;
    /**
     * 角色
     */
    private Set<Long> roleIds;
    /**
     * 授权类型
     */
    private AuthType type;


    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Long> getRoleIds() {
        if (roleIds == null) {
            roleIds = Sets.newHashSet();
        }
        return roleIds;
    }

    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public void addRoleId(Long roleId) {
        getRoleIds().add(roleId);
    }


    public void addRoleIds(Set<Long> roleIds) {
        getRoleIds().addAll(roleIds);
    }

    public AuthType getType() {
        return type;
    }

    public void setType(AuthType type) {
        this.type = type;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

}