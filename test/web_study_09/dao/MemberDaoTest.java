package web_study_09.dao;

import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import web_study_09.dao.impl.MemberDaoImpl;
import web_study_09.dto.Member;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MemberDaoTest {
	MemberDao dao = MemberDaoImpl.getInstance();

	@Test
	public void testSelectMemberByAll() {
		System.out.println("testSelectMemberByAll");
		List<Member> list = dao.selectMemberByAll();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testSelectMemberByUserId() {
		System.out.println("testSelectMemberByUserId");
		Member member = dao.selectMemberByUserId(new Member("somi"));
		Assert.assertNotNull(member);
		System.out.println(member);
	}

	@Test
	public void test01InsertMember() {
		System.out.println("test01InsertMember");
		Calendar c = Calendar.getInstance();
		Member member = new Member("윤한석", "gkstjr", "1234", "gkstjr", "010-1234-5678", 1, c.getTime());
		int res = dao.insertMember(member);
		Assert.assertEquals(1, res);
		System.out.println(member);
	}

	@Test
	public void test02UpdateMember() {
		System.out.println("test02UpdateMember");
		Calendar c = Calendar.getInstance();
		c.set(2020, 7, 28);
		Member member = dao.selectMemberByUserId(new Member("gkstjr"));
		member.setName("양찬우");
		member.setUserId("jjang");
//		member.setPwd(pwd);
		member.setEmail("jjang");
//		member.setPhone(phone);
		member.setAdmin(0);
		member.setJoinDate(c.getTime());
		member.setUserId("gkstjr");
		int res = dao.updateMember(member);
		Assert.assertEquals(1, res);
		System.out.println(member);
	}

	@Test
	public void test03DeleteMember() {
		System.out.println("test03DeleteMember");
		Member member = new Member("gkstjr");
		int res = dao.deleteMember(member);
		Assert.assertEquals(1, res);
		System.out.println(member);
	}

}
