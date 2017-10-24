package com.wis.model.user;

import com.wis.common.BaseModel;

/**
 * 为了提高连表性能，使用数据冗余，而不是：组织机构(1)-----(*)职务的中间表
 * 即在该表中，用户--组织机构--职务是唯一的，但用户-组织机构可能重复
 */
public class UserOrganizationJob extends BaseModel<Long> {

    private User user;

    private Long organizationId;

    private Long jobId;


    public UserOrganizationJob() {
    }

    public UserOrganizationJob(Long id) {
        setId(id);
    }

    public UserOrganizationJob(Long organizationId, Long jobId) {
        this.organizationId = organizationId;
        this.jobId = jobId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    @Override
    public String toString() {
        return "UserOrganizationJob{id = " + this.getId() +
                ",organizationId=" + organizationId +
                ", jobId=" + jobId +
                ", userId=" + (user != null ? user.getId() : "null") +
                '}';
    }
}
