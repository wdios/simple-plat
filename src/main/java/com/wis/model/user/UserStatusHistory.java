package com.wis.model.user;

import java.util.Date;

import com.wis.common.BaseModel;

/**
 * 用户状态的历史修改记录
 */
public class UserStatusHistory extends BaseModel<Long> {
    /**
     * 锁定的用户
     */
    private User user;
    /**
     * 备注信息
     */
    private String reason;
    /**
     * 操作的状态
     */
    // @Enumerated(EnumType.STRING)
    private UserStatus status;
    /**
     * 操作的管理员
     */
    private User opUser;
    /**
     * 操作时间
     */
    private Date opDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getOpUser() {
        return opUser;
    }

    public void setOpUser(User opUser) {
        this.opUser = opUser;
    }

    public Date getOpDate() {
        return opDate;
    }

    public void setOpDate(Date opDate) {
        this.opDate = opDate;
    }
    
}
