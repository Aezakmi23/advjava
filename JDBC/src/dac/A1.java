package dac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class A1 {

	public static void main(String[] args) {
	
			try {
				Connection dbconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advjava","root", "2303");
				
				Statement st = dbconnection.createStatement();
				
				ResultSet r = st.executeQuery("select * from userdetail");
				while (r.next()) {
					System.out.println(r.getString(1));
					System.out.println(r.getString(2));
					System.out.println(r.getString(3));
					System.out.println(r.getString(4));
					System.out.println(r.getString(5));
					System.out.println("--------------------------");
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	}

}
