    
 <%
	String username=(String)request.getAttribute("username");
  %>
  
<style>
	#mention{
		color: red;
		font-weight:bold;
	}
</style>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
  <div class="container">
    <a class="navbar-brand" href="index.jsp">
      <img src="https://static.vecteezy.com/system/resources/thumbnails/003/092/544/small/e-commerce-logo-with-pointer-and-shopping-bag-free-vector.jpg" alt="..." height="55">
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">HOME</a>
        </li>
       
         
         <li class="nav-item">
          <a class="nav-link" href="cart.jsp">CART <span class="badge badge-danger px-1" id="mention">${cart_list.size()}</span></a>
        </li>
        
        <%
        	if(username !=null){
        %>
        		 <li class="nav-item">
                   <a class="nav-link" href="orders.jsp">ORDERS</a>
                 </li>
        		<li class="nav-item">
                <a class="nav-link" href="Userinfo.jsp">Welcome <%= username %>!</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="Logout">LOGOUT</a>
              </li>
        	<%
        	}else{
        	%>
        		 <li class="nav-item">
                     <a class="nav-link" href="login.jsp">LOGIN</a>
                 </li>
        	<%}
        %>

         
       
       
       
      </ul>
      </div>
      </div>
      </nav>