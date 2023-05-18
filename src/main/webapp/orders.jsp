<%@page import="wrkout.Product"%>
<%@page import="wrkout.OrderModel"%>
<%@page import="wrkout.OrderDao"%>
<%@page import="java.util.List"%>
<%@page import="wrkout.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="wrkout.User"%>
  <%
   	String user=(String)request.getSession().getAttribute("username");    
  	String userId=(String)request.getSession().getAttribute("userId");
	List<OrderModel>orders=null;
    if(user !=null){
    	request.setAttribute("username", user);
    	OrderDao dao =new OrderDao();
    	orders=dao.userOrders(Integer.parseInt(userId));
    }
    
    
    ArrayList <Cart>cart_list=(ArrayList<Cart>)session.getAttribute("cart-list");
    List<Cart>cartProduct=null;
    if(cart_list!=null){
    	
    	request.setAttribute("cart_list", cart_list);
    }
    %>
<!DOCTYPE html>
<html>
<head>
<title>Orders page</title>
<%@include file="includes/header.jsp" %>
<style type="text/css">
	.about-us-container{
	margin-top: 100px;
}
</style>
</head>
<body>
	<%@include file="includes/nav.jsp" %>
	
	<div class="container">
	
		<div class="card-header my-3">All the orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(orders!=null){
						for(OrderModel o:orders){%>
						<tr>
							<td><%=o.getDate() %></td>
							<td><%=o.getName() %></td>
							<td><%=o.getCategory() %></td>
							<td><%=o.getQuantity() %></td>
							<td><%=o.getPrice() %></td>
							<td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrderId()%>">Cancel</a></td>
						</tr>
						<%}
					}
				%>
			</tbody>
		</table>
	</div>

<%@include file="includes/footer.jsp" %>
<div class="about-us-container">
		<%@include file="includes/aboutus.jsp"%>
	</div>
</body>
</html>