

<%@page import="wrkout.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="wrkout.Product"%>
<%@page import="java.util.List"%>
<%@page import="wrkout.HelperClass"%>
<%@page import="wrkout.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="wrkout.User"%>



<%
String user = (String) request.getSession().getAttribute("username");

if (user != null) {
	request.setAttribute("username", user);
}

List<Product> products = ProductDao.getAllProducts();

ArrayList <Cart>cart_list=(ArrayList <Cart>)session.getAttribute("cart-list");
List<Cart>cartProduct=null;
if(cart_list!=null){
	
	request.setAttribute("cart_list", cart_list);
}

%>


<!DOCTYPE html>
<html>
<head>
<title>Welcome to shopping cart</title>
<%@include file="includes/header.jsp"%>
<style type="text/css">
body{
	background: linear-gradient(90deg, hsla(208, 33%, 21%, 1) 0%, hsla(211, 36%, 46%, 1) 100%);
}
.carousel-inner img {
	width: auto;
	height: 400px;
	margin-left: auto;
	margin-right: auto;
}

#demo {
	margin-top: 27px;
	margin-bottom: 27px;
}

.card-height {
	height: 420px; /* set the desired height here */
}
.about-us-container{
	margin-top: 100px;
}

.card-title {
  font-size: 1.2rem;
}

.price, .category {
  font-size: 1rem;
}
</style>

</head>
<body>
	<%@include file="includes/nav.jsp"%>

	<div id="demo" class="carousel slide" data-bs-ride="carousel">

		<!-- Indicators/dots -->
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#demo" data-bs-slide-to="0"
				class="active"></button>
			<button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="product_images/deep.jpg" alt="Los Angeles"
					class="d-block ">
			</div>
			<div class="carousel-item">
				<img src="product_images/men.webp" alt="Chicago" class="d-block ">
			</div>

		</div>

		<!-- Left and right controls/icons -->
		<button class="carousel-control-prev" type="button"
			data-bs-target="#demo" data-bs-slide="prev">
			<span class="carousel-control-prev-icon"></span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#demo" data-bs-slide="next">
			<span class="carousel-control-next-icon"></span>
		</button>
	</div>

	<div class="container">
		<div class="card-header my-3" style="color:white">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-4">
				<div class="card w-100 card-height" style="width: 18rem;">
					<img class="card-img-top" src="<%=p.getImage()%>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title">
							Name:<%=p.getName()%></h5>
						<h6 class="price">
							Price:<%=p.getPrice()%></h6>
						<h6 class="category">
							Category:<%=p.getCategory()%></h6>
					
						<div class="mt-3 d-flex justify-content-between">
							<a href="add-to-cart?id=<%=p.getId() %>" class="btn btn-dark">Add to cart</a>
							<a href="order-now?quantity=1&id=<%=p.getId()%> " class="btn btn-primary">Buy Now</a>
						</div>
					</div>
				</div>
			</div>

			<%
			}

			}else{
				out.print("there is no products");
			}
			%>

		</div>
	</div>



	<%@include file="includes/footer.jsp"%>
	<div class="about-us-container">
		<%@include file="includes/aboutus.jsp"%>
	</div>
</body>
</html>