package project;


import java.sql.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author busra
 */
public class DbaseConnection {
    private static Connection conn=null;
	private static DatabaseConnection databaseConnection=null;
	
	
	
	public  static Connection BaglantiKur()
	{
		try 
		{
		 conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##yalcin","Root1234");
		 return conn;
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
    
}
      







/*public class DPConnect {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public void connect() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","bs4721");
            st = con.createStatement();

        }catch(Exception ex) {
            System.out.println("Erro: " +ex);
        }
    }

    public int getID(String tableName, String name) {
        try {
            String query = "SELECT id FROM ? WHERE name = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, tableName);
            preparedStmt.setString(2, name);
            rs = st.executeQuery(query);
            if(rs.next())
                return rs.getInt(0);
            else
                return -1;

        } catch(Exception ex) {
            System.out.println(("Erro: " + ex));
        }
        return 202;
    }

    public String getName(String tableName, int ID) {
        try{
            String query = "SELECT name FROM ? WHERE id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, tableName);
            preparedStmt.setInt(2, ID);
            rs = st.executeQuery(query);
            if(rs.next())
                return rs.getString(1);
            else
                return "Not Found";
        }catch(Exception ex) {
            System.out.println("Erro: " +ex);
        }
        return ":(";
    }

    public boolean checkPerm(String permType, String ID, String password){
        boolean goodPass = false;
        try {
            connect();
            int IDNumber = Integer.parseInt(ID);
            String query = "SELECT id FROM " + permType + " WHERE id = " + IDNumber + " AND password = " + '"' + password + '"';
            rs = st.executeQuery(query);
            if(rs.next()) {
                goodPass = true;
                System.out.println("Pass found");
            }
            con.close();
        } catch(Exception ex) {
            System.out.println(("Erro: " + ex));
        }


        return goodPass;
    }

    public void addNewCourse(String courseName) {
        try {
            connect();
            String query = "INSERT INTO Courses (courseName) VALUES ( ? )";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, courseName);
            preparedStmt.execute();
            con.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void removeCourse(String courseName) {
        try {
            connect();
            String query = "DELETE FROM Courses WHERE courseName = ?";
            System.out.println(query);
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, courseName);
            preparedStmt.execute();
            con.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        System.out.println("Entry Removed: " + courseName);

        System.out.println("Course removed: " + courseName);
    }

    public void addPersonToClass(String ID, String ad, String soyad) {
        try {
            connect();
            switch (soyad) {
                case "Student":
                    soyad = "studentInCourse";
                    break;
                case "Teacher":
                    soyad = "teacherInCourse";
                    break;
                case "ta":
                    soyad = "taInCourse";
                    break;
                default:
                    System.out.println("Bad humanoidType");
                    return;
            }

            String personName = getName(tableName, Integer.parseInt(ID));

            if(personName.equals("Not Found")) {
                System.out.println("Student not Found");
            }

            String courseID = Integer.toString(getID(tableName, courseName));



            if(courseID.equals("-1")) {
                System.out.println("Course Not Found");
                return;
            }

            String query = "INSERT INTO ? (ID, courseID) VALUE (?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, tableName);
            preparedStmt.setString(2, ID);
            preparedStmt.setString(3, courseID);

            System.out.println("Humanoid Added: " + personName);
            con.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

    }

    public void newPerson(String tableName, String name) {
        if(tableName.equals("TA"))
            tableName = "Teacher";

        try {
            connect();
            String query = "INSERT INTO ? (name) VALUES ( ? )";
            System.out.println(query);
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, tableName);
            preparedStmt.setString(2, name);
            preparedStmt.execute(query);
            con.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void deletePerson(String tableName, String ID) {
        try {
            connect();
            String query = "DELETE FROM ? WHERE id = ?";
            System.out.println(query);
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, tableName);
            preparedStmt.setString(2, ID);
            preparedStmt.execute(query);
            con.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void removeFromClass(String tableName, String courseName, String ID) {
        try {
            connect();
            String query = "DELETE FROM ? WHERE courseName = ? AND id = ?";
            System.out.println(query);
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, tableName);
            preparedStmt.setString(2, courseName);
            preparedStmt.setString(3, ID);
            preparedStmt.execute(query);
            con.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public boolean exists(String tableName, String ID) {

        connect();
        String temp = getName(tableName, Integer.parseInt(ID));
        try {
            con.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

         if(temp.equals("Not Found"))
            return false;
        else
            return true;
    }

    // NOT DONE

    public void getCourseGrades(String courseID, String ID) {
        try {
            String query = "SELECT grade FROM grades WHERE courseID = ? AND studentID = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, courseID);
            preparedStmt.setString(2, ID);
            preparedStmt.execute();
            con.close();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
}


}
