package wrkout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelperClass {
	public static String sanitizeString(String str) {
	    // Escape single quotes by replacing them with two single quotes
	    str = str.replace("'", "''");
	    
	    // Escape backslashes by doubling them
	    str = str.replace("\\", "\\\\");

	    // Escape percent signs and underscores by adding a backslash before them
	    str = str.replace("%", "\\%");
	    str = str.replace("_", "\\_");
	    
	    return str;
	}
	public static boolean isSafeFromSqlInjection(String str) {
	    // Check if the string contains any SQL injection keywords or characters
	    String[] sqlKeywords = {"SELECT", "INSERT", "UPDATE", "DELETE", "DROP", "CREATE", "TABLE", ";", "'", "\"", "--"};
	    for (String keyword : sqlKeywords) {
	        if (str.toUpperCase().contains(keyword)) {
	            return false;
	        }
	    }
	    // Check if the string contains any semicolons followed by a comment, which can be used for SQL injection
	    if (str.matches(".*;\\s*--.*")) {
	        return false;
	    }
	    return true;
	}
	public static boolean Add_Database(String name,String email, String pass,String mob) throws ClassNotFoundException{
		
	     
		 
		 boolean result=false;
			
			try {
				
				Connection con=getmyConnection();
				
				
				PreparedStatement pst= con.prepareStatement("Insert into MY_INFO ( user_name,user_mail,user_password,user_mobile)values(?,?,?,?)");
				pst.setString(1, name);
				pst.setString(2, email);
				pst.setString(3,pass);
				pst.setString(4,mob);
				
				int i=pst.executeUpdate();
				
				if(i==1) {
					result=true;
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	
	public static Connection getmyConnection() {
		Connection result=null;
		 
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
		     result=DriverManager.getConnection("jdbc:mysql://localhost:3306/MY_ECOMMERCE?characterEncoding=latin1","root","root");
		     
			 
		 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		return result ;
	}
}
	
