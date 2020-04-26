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
  private final String password = "bs4721 ";
  Connection connect;
  
  public Connection getConnection() {
    try {
      this.connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/proje?useTimezone=true&serverTimezone=UTC", "root", "bs4721");
    } catch (Exception e) {
      e.printStackTrace();
    } 
    return this.connect;
  }
   public void close(Connection connect, PreparedStatement pstmt, ResultSet rs) {
    try {
      if (connect != null)
        connect.close(); 
      if (pstmt != null)
        pstmt.close(); 
      if (rs != null)
        rs.close(); 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void close(Connection connect, PreparedStatement pstmt) {
    try {
      close(connect, pstmt, null);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public void close(PreparedStatement pstmt) {
    try {
      close(null, pstmt, null);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
