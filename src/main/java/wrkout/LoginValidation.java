package wrkout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class LoginValidation {
	public static User loginVal(String email,String password) {
		User user=null;
		try {
			
			Connection con=HelperClass.getmyConnection();
			PreparedStatement pst=con.prepareStatement("Select * from MY_INFO Where user_mail=? and user_password=? ");
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				user=new User();
				
				user.setName(rst.getString("user_name"));
				user.setId(Integer.parseInt(rst.getString("user_id")));
				
			}
			
		
		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return user;
		
	}
}
