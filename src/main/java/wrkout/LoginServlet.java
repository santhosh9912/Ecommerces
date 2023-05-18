package wrkout;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mail=req.getParameter("email");
		String password=req.getParameter("password");
		HelperClass.getmyConnection();
		User user=LoginValidation.loginVal(mail, password);
		
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		RequestDispatcher reqs=null;
		if(user.getName()==null || user.getName().isEmpty()) {
			reqs=req.getRequestDispatcher("login.jsp");
			out.print("Invalid user details");              
			reqs.include(req,resp);
			
		
		}else {
			System.out.println("User-->"+user);
			
			
			req.getSession().setAttribute("username",user.getName());
			req.getSession().setAttribute("userId",String.valueOf(user.getId()));
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			//req.getRequestDispatcher("Ottp.jsp").forward(req, resp);
			

		}
	}
}
