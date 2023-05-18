<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="wrkout.HelperClass"%>
<%
int userId = Integer.parseInt(request.getParameter("userId")); // get the user ID from the form parameter
String userName = request.getParameter("userName"); // get the user name from the form parameter
String userMail = request.getParameter("userMail"); // get the user mail from the form parameter
String userPassword = request.getParameter("userPassword"); // get the user password from the form parameter
long userMobile = Long.parseLong(request.getParameter("userMobile")); // get the user mobile from the form parameter
Connection conn = null;
PreparedStatement pstmt = null;

try {
    conn = HelperClass.getmyConnection(); // create a connection to the database
    String sql = "UPDATE MY_INFO SET user_name=?, user_mail=?, user_password=?, user_mobile=? WHERE user_id=?";
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, userName);
    pstmt.setString(2, userMail);
    pstmt.setString(3, userPassword);
    pstmt.setLong(4, userMobile);
    pstmt.setInt(5, userId);
    int rowsAffected = pstmt.executeUpdate();
    if(rowsAffected > 0) { // if the update was successful, redirect to the user profile page
    	request.getSession().setAttribute("username",userName);
        response.sendRedirect("Userinfo.jsp?userId=" + userId);
    } else { // if the update was not successful, display an error message
        out.println("Error updating user information.");
    }
} catch(Exception e) {
    e.printStackTrace();
} finally {
    try {
        if(pstmt != null) pstmt.close();
        if(conn != null) conn.close();
    } catch(Exception e) {
        e.printStackTrace();
    }
}
%>
    