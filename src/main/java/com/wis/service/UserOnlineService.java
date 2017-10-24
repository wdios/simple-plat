package com.wis.service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wis.model.user.UserOnline;

/**
 * 用户登录状态操作
 */
@Service
public class UserOnlineService {
    /**
     * 上线
     */
    public void online(UserOnline userOnline) {
        // save(userOnline);
    }
    /**
     * 下线
     */
    public void offline(String sid) {
        /*UserOnline userOnline = findOne(sid);
        if (userOnline != null) {
            delete(userOnline);
        }*/
        // 游客 无需记录上次访问记录
        // 此处使用数据库的触发器完成同步
    	// if(userOnline.getUserId() == null) {
    		// userLastOnlineService.lastOnline(UserLastOnline.fromUserOnline(userOnline));
    	// }
    }
    /**
     * 批量下线
     */
    public void batchOffline(List<String> needOfflineIdList) {
        // getUserOnlineRepository().batchDelete(needOfflineIdList);
    }

    /**
     * 无效的UserOnline
     */
    public List<UserOnline> findExpiredUserOnlineList(Date expiredDate, Pageable pageable) {
        return null;
    }

}
