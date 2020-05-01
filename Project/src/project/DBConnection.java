package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class DBConnection {
     
      private final String dburl = "jdbc:mysql://localhost:3306/proje?useTimezone=true&serverTimezone=UTC";
	private final String username = "root"; 
	private final String password = "bs4721"; 
	static Connection connect;

	public DBConnection() {

	}

	public Connection getConnection() {
		try {
			connect = DriverManager.getConnection(dburl, username, password);
                        Statement stmt = connect.createStatement();
     
      
		}catch(Exception e) {
			e.printStackTrace();
		}

		return connect;
	}
public int toplam() throws SQLException{
    System.out.println("Bob");
    Statement stmt = connect.createStatement();
    String query = "select count(id) as count from personel";
      ResultSet rs = stmt.executeQuery(query);
      rs.next();
      int count = rs.getInt("count");
      System.out.println("Personel Sayısı: "+ count);
          return count;
}
	public void close(Connection connect, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(connect != null)
				connect.close();
			if(pstmt != null)
				pstmt.close();
			if(rs != null)
				rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close(Connection connect, PreparedStatement pstmt) {
		try {
			close(connect, pstmt, null);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void close(PreparedStatement pstmt) {
		try {
			close(null, pstmt, null);
		}catch(Exception e) {
			e.printStackTrace();
		}
	} 
       
       
        
        
    }

 
    
