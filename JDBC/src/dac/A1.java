package dac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class A1 {

	public static void main(String[] args) {
	
			try {
				Connection db = DriverManager.getConnection("jdbc:mysql://localhost:3306/advjava","root", "2303");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			

	}

}
