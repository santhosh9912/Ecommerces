package wrkout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/verifyOTP")
public class VerifyOTP extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	boolean result=false;
		String userOtp= req.getParameter("otp");
		Integer sessionOtp=(Integer)req.getSession().getAttribute("OTP");
		if(userOtp.equalsIgnoreCase(sessionOtp.toString()))
			result=true;
		req.getSession().removeAttribute("OTP");
		if(result)
			resp.sendRedirect("login.jsp");
	}
	
}
