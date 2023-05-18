<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OTP Verification</title>
<%@include file="includes/header.jsp" %>
<style type="text/css">

.about-us-container{
	margin-top:50px;
}
.my-align {
	margin-top:50px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

h1, p {
	color:white;
  text-align: center;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0,0,0,0.2);
  max-width: 400px;
  margin: 0 auto;
}

label {
  display: block;
  font-size: 16px;
  margin-bottom: 5px;
}

input[type="text"] {
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 10px;
  width: 100%;
  box-sizing: border-box;
}

button {
  background-color: #4CAF50;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #3e8e41;
}

button:active {
  background-color: #3e8e41;
  box-shadow: none;
  transform: translateY(2px);
}

body {
	background-image: url("product_images/wonder.jpg");
	background-size: cover;
}
	
	
</style>
</head>
<body>
<%@include file="includes/nav.jsp" %>
	<div>
	<h1 >OTP Verification</h1>
	<p >Please enter the OTP sent to your mobile number:</p>
	<form action="verifyOTP">
		<label for="otp">OTP:</label>
		<input type="text" id="otp" name="otp" required>
		<br><br>
		<button type="submit"   class="btn btn-danger">Verify OTP</button>
	</form>
   </div>
   
   <%@include file="includes/footer.jsp" %>
    <div class="about-us-container">
		<%@include file="includes/aboutus.jsp"%>
	</div>
</body>
</html>