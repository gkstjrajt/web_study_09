package web_study_09.ds;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import web_study_09.ds.JdbcUtilJNDI;

public class JdbcUtilJNDITest {

	@Test
	public void testGetConnection() {
		Connection con = JdbcUtilJNDI.getConnection();
		Assert.assertNotNull(con);
		System.out.println(con);
	}

}
