package com.wis.service;

import java.util.Date;

import net.sf.ehcache.config.Searchable;

import org.springframework.stereotype.Service;

import sun.misc.Sort;

import com.wis.model.user.User;
import com.wis.model.user.UserStatus;
import com.wis.model.user.UserStatusHistory;

/**
 * 用户状态的历史修改记录
 */
@Service
public class UserStatusHistoryService {

    public void log(User opUser, User user, UserStatus newStatus, String reason) {
        UserStatusHistory history = new UserStatusHistory();
        history.setUser(user);
        history.setOpUser(opUser);
        history.setOpDate(new Date());
        history.setStatus(newStatus);
        history.setReason(reason);
        // save(history);
    }

    public UserStatusHistory findLastHistory(final User user) {
        /*Searchable searchable = Searchable.newSearchable()
                .addSearchParam("user_eq", user)
                .addSort(Sort.Direction.DESC, "opDate")
                .setPage(0, 1);

        Page<UserStatusHistory> page = baseRepository.findAll(searchable);

        if (page.hasContent()) {
            return page.getContent().get(0);
        }*/
        return null;
    }

    public String getLastReason(User user) {
        UserStatusHistory history = findLastHistory(user);
        if (history == null) {
            return "";
        }
        return history.getReason();
    }
    
}
