package web_study_09.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import web_study_09.dao.MemberDao;
import web_study_09.ds.JdbcUtil;
import web_study_09.dto.Member;

public class MemberDaoImpl implements MemberDao {
	
// ============== sington 패턴 기본작업 ===================
	private static final MemberDaoImpl instance = new MemberDaoImpl();

	public MemberDaoImpl() {}

	public static MemberDaoImpl getInstance() {
		return instance;
	}
// ====================================================
	@Override
	public List<Member> selectMemberByAll() {
		String sql = "SELECT NAME, USERID, PWD, EMAIL, PHONE, ADMIN, JOINDATE FROM MEMBER";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Member> list = new ArrayList<Member>();
				do {
					list.add(getMember(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	private Member getMember(ResultSet rs) throws SQLException {
		String name = rs.getString("NAME");
		String userId = rs.getString("USERID");
		String pwd = rs.getString("PWD");
		String email = rs.getString("EMAIL");
		String phone = rs.getString("PHONE");
		int admin = rs.getInt("ADMIN");
		Date joinDate = rs.getTimestamp("JOINDATE");
		return new Member(name, userId, pwd, email, phone, admin, joinDate);
	}

	@Override
	public Member selectMemberByUserId(Member member) {
		String sql = "SELECT NAME, USERID, PWD, EMAIL, PHONE, ADMIN, JOINDATE FROM MEMBER WHERE USERID = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, member.getUserId());
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					return getMember(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public int insertMember(Member member) {
		String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
					pstmt.setString(1, member.getName());
					pstmt.setString(2, member.getUserId());
					pstmt.setString(3, member.getPwd());
					pstmt.setString(4, member.getEmail());
					pstmt.setString(5, member.getPhone());
					pstmt.setInt(6, member.getAdmin());
					pstmt.setTimestamp(7, new Timestamp(member.getJoinDate().getTime()));
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public int updateMember(Member member) {
		String sql = "UPDATE MEMBER SET NAME = ?, USERID = ?, PWD = ?,"
				+ " EMAIL = ?, PHONE = ?, ADMIN = ?, JOINDATE = ? WHERE USERID = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
					pstmt.setString(1, member.getName());
					pstmt.setString(2, member.getUserId());
					pstmt.setString(3, member.getPwd());
					pstmt.setString(4, member.getEmail());
					pstmt.setString(5, member.getPhone());
					pstmt.setInt(6, member.getAdmin());
					pstmt.setTimestamp(7, new Timestamp(member.getJoinDate().getTime()));
					pstmt.setString(8, member.getUserId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public int deleteMember(Member member) {
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
					pstmt.setString(1, member.getUserId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
