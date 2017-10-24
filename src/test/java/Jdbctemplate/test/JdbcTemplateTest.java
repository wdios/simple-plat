package Jdbctemplate.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wis.model.user.User;
import com.wis.tookit.sql.SqlHelper;

import test.BaseJunit4Test;

public class JdbcTemplateTest extends BaseJunit4Test {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
    public void testInsert() {
		User aUser = new User();
		aUser.setUsername("wd");
		SqlHelper aSqlHelper = new SqlHelper(aUser, "sys_user");
        
        String sqlStr = aSqlHelper.getQuerySQLAll("|username|", "", "");
        System.out.println("wwwwwwwwwwwwwww   sqlStr = " + sqlStr);
        List<User> list = jdbcTemplate.query(sqlStr, aSqlHelper.getParams(), new BeanPropertyRowMapper<User>(User.class));
    	
    	
        System.out.println("wwwwwwwwwwwwwww   list size = " + list.size());
    }
	
}
