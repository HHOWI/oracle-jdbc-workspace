package com.kh.controller;

import java.sql.SQLException;

import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;

public class MemberController {

	private MemberDAO dao = new MemberDAO();

	public boolean joinMembership(Member m) {

		try {
			if (null == dao.getMember(m.getId())) {
				dao.registerMember(m);
				return true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

		// id가 없다면 회원가입 후 true 반환
		// 없다면 false 값 반환

	}

	public String login(String id, String password) {

		try {
			Member m = dao.getMember(id);

			if (password.equals(m.getPassword())) {
				dao.login(m);
				return m.getName();
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 로그인 성공하면 이름 반환
		// 실패하면 null 반환
		return null;
	}

	public boolean changePassword(String id, String oldPw, String newPw) {

		Member m = new Member();
		m.setId(id);
		m.setPassword(oldPw);
		try {
			if (null != this.login(id, oldPw)) {
				m.setPassword(newPw);
				dao.updatePassword(m);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 로그인 했을 때 null이 아닌 경우
		// 비밀번호 변경 후 true 반환, 아니라면 false 반환

		return false;
	}

	public void changeName(String id, String name) {

		Member m = new Member();
		m.setId(id);
		m.setName(name);
		try {
			dao.updateName(m);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 이름 변경!

	}

}