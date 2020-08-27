package web_study_09.test;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import web_study_09.ds.JdbcUtil;

public class JdbcUtilTest {

	@Test
	public void testGetConnection() {
		Connection con = JdbcUtil.getConnection();
		Assert.assertNotNull(con);
		System.out.println(con);
	}

}
