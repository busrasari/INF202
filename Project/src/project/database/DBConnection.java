package project.database;

import java.sql.*;


public class DBConnection {

    public static Connection connect;
    private final String dburl = "jdbc:mysql://localhost:3306/proje?useTimezone=true&serverTimezone=UTC";
    private final String username = "root";
    private final String password = "bs4721";
    private PreparedStatement stmt;

    public DBConnection() {

    }

        public Connection getConnection() {
        try {
            connect = DriverManager.getConnection(dburl, username, password);
            Statement stmt = connect.createStatement();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return connect;
    }
    public ResultSet makeQuery(String query) {
        ResultSet rs = null;
        try {
            this.stmt = this.connect.prepareStatement(query);
            rs = this.stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
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

    public void closeConnect() {
        try {
            this.stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}

 
    
