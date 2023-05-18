<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="wrkout.HelperClass"%>
<%
    int userId = Integer.parseInt(request.getParameter("userId")); // get the user ID from the query parameter
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        conn = HelperClass.getmyConnection(); // create a connection to the database
        String sql = "SELECT * FROM MY_INFO WHERE user_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userId);
        rs = pstmt.executeQuery();
        if(rs.next()) { // pre-populate the form with user data
            String userName = rs.getString("user_name");
            String userMail = rs.getString("user_mail");
            String userPassword = rs.getString("user_password");
            long userMobile = rs.getLong("user_mobile");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <%@include file="includes/header.jsp" %>
   	<style type="text/css">
   		body {
    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
    background-image: url("product_images/nature.jpg");
    background-repeat: no-repeat;
    background-size: cover;
}

form {
    max-width: 500px;
    margin: 0 auto;
    background-color: grey;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0px 0px 10px rgba(0,0,0,0.2);
}

label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

input[type="text"],
input[type="email"],
input[type="password"],
input[type="tel"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 15px;
    border-radius: 5px;
    border: 1px solid #ccc;
    box-sizing: border-box;
    font-size: 16px;
    background-color: #f5f5f5;
    color: #333;
}

input[type="text"]:focus,
input[type="email"]:focus,
input[type="password"]:focus,
input[type="tel"]:focus {
    outline: none;
    border: 1px solid #4CAF50;
}

input[type="submit"] {
    background-color: #4CAF50;
    color: #fff;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

input[type="submit"]:hover {
    background-color: #3e8e41;
}
   	
   	.about-us-container{
	margin-top: 100px;
}	
   	</style>
</head>
<body>
    <%@include file="includes/nav.jsp" %>
    <h1>Edit User</h1>
    <form action="UpdateUser.jsp" method="post">
        <input type="hidden" name="userId" value="<%= userId %>">
        <label>User Name:</label>
        <input type="text" name="userName" value="<%= userName %>"><br>
        <label>User Mail:</label>
        <input type="email" name="userMail" value="<%= userMail %>"><br>
        <label>User Password:</label>
        <input type="password" name="userPassword" value="<%= userPassword %>"><br>
        <label>User Mobile:</label>
        <input type="tel" name="userMobile" value="<%= userMobile %>"><br>
        <input type="submit" value="Save">
    </form>
    <% } else { %>
    <h2>User not found</h2>
    <% }
    } catch(Exception e) {
    e.printStackTrace();
    } finally {
    try {
        if(rs != null) rs.close();
        if(pstmt != null) pstmt.close();
        if(conn != null) conn.close();
    } catch(Exception e) {
        e.printStackTrace();
    }
}
   %>
    <%@include file="includes/footer.jsp" %>
	<div class="about-us-container">
		<%@include file="includes/aboutus.jsp"%>
	</div>
    </body>
    </html> 
    
