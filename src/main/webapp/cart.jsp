<%@page import="wrkout.User"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="wrkout.Product"%>
<%@page import="wrkout.HelperClass"%>
<%@page import="wrkout.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="wrkout.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
   
   	String user=(String)request.getSession().getAttribute("username");    
     
    if(user !=null){
    	request.setAttribute("username", user);
    }
    
    ArrayList <Cart>cart_list=(ArrayList <Cart>)session.getAttribute("cart-list");
    List<Cart>cartProduct=null;
    if(cart_list!=null){
    	ProductDao dao=new ProductDao();
    	cartProduct= dao.getProduct(cart_list);
    	double total=dao.getTotalCartPrice(cart_list);
      	request.setAttribute("total", total);
      	request.setAttribute("cart_list", cart_list);
    }
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<title>cart pages</title>
<%@include file="includes/header.jsp" %>
<style type="text/css">
	.about-us-container{
	margin-top: 100px;
}

.table tbody td{
vertical-align: middle;

}
.btn-incre,.btn-decre{
	box-shadow: none;
	font-size: 25px;
}
#design{
	margin-top:15px; 
}
#tops{
	margin-top: 20px;
}
</style>
</head>
<body>
	<%@include file="includes/nav.jsp" %>
	<div class="container">
		<div id="tops"class="d-flex py=3"><h3>Total Price:$ ${total>0?total:0 }</h3><a class="mx-3 btn btn-primary" href="Check-out">CHECKOUT</a></div>
		<table class="table table-light" id="design">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<% if(cart_list!=null){
				for(Cart c:cartProduct){%>
					<tr>
					<td><%=c.getName() %></td>
					<td><%=c.getCategory() %></td>
					<td><%=c.getPrice() %>$</td>
					<td>
						<form action="order-now" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%=c.getId() %>" class="form-input">
						<div class="form-group d-flex justify-content-between w-50">
						<a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
							<input type="text" name="quantity" class="form-control w-50" value="<%=c.getQuantity() %>" readonly>
						<a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a>
							<button  type="submit" class="btn btn-primary btn-sm">Buy</button>
						</div>
						
						</form>
					</td>
					<td>
						<a class="btn btn-sm btn-danger " href="remove-from-cart?id=<%=c.getId()%>">Remove</a>
					</td>
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