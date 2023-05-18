<%@page import="wrkout.User"%>
<%@page import="wrkout.Cart"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="wrkout.HelperClass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
String user = (String) request.getSession().getAttribute("username");
String userIdSession = (String)request.getSession().getAttribute("userId");
if (user != null) {
	request.setAttribute("username", user);
}



ArrayList <Cart>cart_list=(ArrayList <Cart>)session.getAttribute("cart-list");
List<Cart>cartProduct=null;
if(cart_list!=null){
	
	request.setAttribute("cart_list", cart_list);
}
%>

<!DOCTYPE html>
<html>
<head>
<title>User Info</title>
<%@include file="includes/header.jsp"%>
<style>
body {
	background-image: url("product_images/wonder.jpg");
	background-size: cover;
}

.edit-button {
	color: white;
	background-color: blue;
	padding: 5px 10px;
	border-radius: 5px;
	text-decoration: none;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
	border: 1px solid #ddd;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #ddd;
}
.about-us-container{
	margin-top: 100px;
}
</style>
</head>
<body>
	<%@include file="includes/nav.jsp"%>
	<h1>User Info</h1>

	<table>
		<tr>
			<th>User ID</th>
			<th>User Name</th>
			<th>User Mail</th>
			<th>User Password</th>
			<th>User Mobile</th>
		</tr>

		<%
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = HelperClass.getmyConnection(); // create a connection to the database
			String sql = "SELECT * FROM MY_INFO WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(userIdSession)); // replace 1 with the user_id you want to display
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String userName = rs.getString("user_name");
				String userMail = rs.getString("user_mail");
				String userPassword = rs.getString("user_password");
				long userMobile = rs.getLong("user_mobile");
		%>

		<tr>
			<td><%=userId%></td>
			<td><%=userName%></td>
			<td><%=userMail%></td>
			<td><%=userPassword%></td>
			<td><%=userMobile%></td>
			<td><a href="editUser.jsp?userId=<%=rs.getInt("user_id")%>"
				class="edit-button">Edit</a></td>
			<!-- edit button -->

		</tr>

		<%
		}
		} catch (Exception e) {
		e.printStackTrace();
		} finally {
		try {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		%>
	</table>
	<div class="about-us-container">
		<%@include file="includes/aboutus.jsp"%>
	</div>
	<%@include file="includes/footer.jsp"%>

</body>
</html>
