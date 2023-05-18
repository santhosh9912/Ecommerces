package wrkout;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	String name=HelperClass.isSafeFromSqlInjection(req.getParameter("name"))?req.getParameter("name"):HelperClass.sanitizeString(req.getParameter("name"));
		String email=req.getParameter("mail");
		String password=req.getParameter("password");
		String mobile=req.getParameter("mobile");
			 
			
			boolean result = false;
			
			try {
				HelperClass.getmyConnection();
				result = HelperClass.Add_Database(name,email, password,mobile);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			PrintWriter out =resp.getWriter();
			resp.setContentType("text/html");
			RequestDispatcher dispatcher=null;
			if(result) {
				sendEmailToRegistration.sendOtpForUser(email,req.getSession());
				dispatcher=req.getRequestDispatcher("VerifyEmail.jsp");
				dispatcher.forward(req, resp);
			}
			else
				out.print("Registration failed  plsz try again");
				dispatcher=req.getRequestDispatcher("register.jsp");
				dispatcher.include(req, resp);
			
			    out.close();
		
	}
}
