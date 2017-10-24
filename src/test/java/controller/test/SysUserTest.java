package controller.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import test.BaseJunit4Test;

public class SysUserTest extends BaseJunit4Test {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
    public void testList() {
		
    }
	
}
