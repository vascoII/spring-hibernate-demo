package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezome=UTC";
		String user = "dev";
		String pwd = "dev";
		
		try {
			System.out.println("Commecting to db: " + jdbcUrl);
			Connection myCon = 
					DriverManager.getConnection(jdbcUrl, user, pwd);
			System.out.println("Commection successfull");
			myCon.close();
			System.out.println("Commection closed");
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}
