package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.sun.rowset.CachedRowSetImpl;

@SuppressWarnings("restriction")
public class SQLUtil {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/dlms";
	private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    
    private static Connection conn = null;
    
    //connect to DB
    public void dbConnect() throws SQLException, ClassNotFoundException{
    	try {
    		Class.forName(JDBC_DRIVER);
    	}catch(ClassNotFoundException e) {
    		System.out.println("Where is your Oracle JDBC Driver?");
    		e.printStackTrace();
    		throw e;
    	}
    	System.out.println("Oracle JDBC Driver Registered!");
    	
    	try {
    		conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    	}catch(SQLException e) {
    		System.out.println("Connection Failed!");
    		e.printStackTrace();
    		throw e;
    	}
    	System.out.println("Connected to DB");
    }
    
    public String checkRegister(String name, String email, String user, String pass) throws SQLException, ClassNotFoundException{
    	if(checkUsername(user)) {
    		return "Username Exist";
    	}else if(checkUser(name,user)) {
			return "Already Exist";
		}else {
			register(name,email,user,pass);
			return "Registered";
		}
    }
    
    //register user 
    public String register(String name, String email, String user, String pass) throws SQLException, ClassNotFoundException{
		String sqlStmt = "INSERT INTO `user` (`NAME`, `USERNAME`, `PASSWORD`, `EMAILID`) VALUES ( '"+name+"', '"+user+"', '"+pass+"', '"+email+"')";
		Statement stmt = null;
    	try {
			dbConnect();
			stmt = conn.createStatement();
    		stmt.executeUpdate(sqlStmt);
    		stmt.close();
    		conn.close();
		}catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("There is some problem with registeration");
    		throw e;
    	}
    	return "";
	}
    
    //check if the user already have registered to the system
    private boolean checkUser(String name, String user) throws ClassNotFoundException, SQLException {
		try {
			dbConnect();
			PreparedStatement stmt = conn.prepareStatement("SELECT NAME,USERNAME FROM user WHERE NAME = ? and USERNAME = ?");
			stmt.setString(1, name);
    		stmt.setString(2, user);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()) {
    			return true;
    		}
    		stmt.close();
    		conn.close();
		}catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("There is some problem while checking users");
    	}
    	return false;
	}

    //to check if username is already taken by someone
	private boolean checkUsername(String user) throws ClassNotFoundException, SQLException {
		try {
			dbConnect();
			PreparedStatement stmt = conn.prepareStatement("SELECT USERNAME FROM WHERE user USERNAME = ?");
    		stmt.setString(1, user);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()) {
    			return true;
    		}
    		stmt.close();
    		conn.close();
		}catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("There is some problem while checking users");
    	}
		return false;
	}

	//validate login credentials 
    public boolean validate(String username, String password) throws SQLException, ClassNotFoundException{
    	try {
    		dbConnect();
    		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE USERNAME = ? and PASSWORD = ?");
    		stmt.setString(1, username);
    		stmt.setString(2, password);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()) {
    			return true;
    		}
    		stmt.close();
    		conn.close();
    	}catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("There is some problem with login");
    		throw e;
    	}
		return false;
    }
    
    //run query in database using resultSet
    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException{
		Statement stmt = null;
		ResultSet resultSet = null;
		CachedRowSetImpl crs = new CachedRowSetImpl();
    	
		try {
			dbConnect();
			stmt  = conn.createStatement();
			resultSet = stmt.executeQuery(queryStmt);			
			crs.populate(resultSet);
			
		}catch(SQLException e) {
			System.out.println("Problem occurred at executeQuery opration: " + e);
			throw e;
		}finally {
			if(resultSet != null) {
				resultSet.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
    	return crs;
    }
}