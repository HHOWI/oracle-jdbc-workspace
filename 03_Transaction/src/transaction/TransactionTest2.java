package transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import config.ServerInfo;

public class TransactionTest2 {

	public static void main(String[] args) {

		try {
			// 1. 드라이버 로딩
			Class.forName(ServerInfo.DRIVER_NAME);

			// 2. 데이터베이스 연결
			Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);

			System.out.println("DB 연결...");

			PreparedStatement st1 = conn.prepareStatement("SELECT * FROM bank");
			ResultSet rs = st1.executeQuery();
			System.out.println("================= 은행 조회 ====================");
			while (rs.next()) {
				System.out.println(
						rs.getString("name") + " / " + rs.getString("bankname") + " / " + rs.getInt("balance"));
			}
			System.out.println("==============================================");

			/*
			 * 민소 -> 도경 : 50만원씩 이체 이 관련 모든 쿼리를 하나로 묶는다.. 하나의 단일 트랜잭션 setAutocommit(),
			 * commit(), rollback().. 등등 사용을 해서 민소님의 잔액이 마이너스가 되면 이체 취소
			 */
			conn.setAutoCommit(false);

			PreparedStatement st4 = conn.prepareStatement("SELECT balance FROM bank WHERE name = '김민소'");
			ResultSet rs2 = st4.executeQuery();

			if (rs2.next()) {
				if (rs2.getInt("balance") >= 500000) {
					PreparedStatement st2 = conn
							.prepareStatement("UPDATE bank SET balance = balance - 500000 WHERE name = '김민소'");
					st2.executeUpdate();
					PreparedStatement st3 = conn
							.prepareStatement("UPDATE bank SET balance = balance + 500000 WHERE name = '김도경'");
					st3.executeUpdate();
					System.out.println("이체 성공!");
					conn.commit();
				} else {
					System.out.println("잔액부족 이체 실패!");
					conn.rollback();
				}
			} else {
				System.out.println("잔액부족 이체 실패!");
				conn.rollback();
			}

			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
