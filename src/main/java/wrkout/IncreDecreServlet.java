 package wrkout;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/quantity-inc-dec")
public class IncreDecreServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		try {
			PrintWriter printWriter=resp.getWriter();
			String action=req.getParameter("action");
			int id=Integer.parseInt(req.getParameter("id"));
			
		ArrayList<Cart>carts_lists=(ArrayList<Cart>)req.getSession().getAttribute("cart-list");
		if(action!=null && id>=1) {
			if(action.equals("inc")) {
				for(Cart c:carts_lists) {
					if(c.getId()==id) {
						int quantity=c.getQuantity();
						quantity=quantity+1;
						c.setQuantity(quantity);
						
					}
				}
				req.getSession().setAttribute("cart_list",carts_lists);	
				resp.sendRedirect("cart.jsp");
			}
			if(action.equals("dec")) {
				for(Cart c:carts_lists) {
					if(c.getId()==id &&c.getQuantity()>1) {
						int quantity=c.getQuantity();
						quantity=quantity-1;
						c.setQuantity(quantity);
						break;
					}
				}
				req.getSession().setAttribute("cart_list",carts_lists);	
				resp.sendRedirect("cart.jsp");
			}
		}else {
			resp.sendRedirect("cart.jsp");
		}
		} catch (Exception e) {
			resp.sendRedirect("cart.jsp");
		}
		
	}
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	
//		PrintWriter printWriter=resp.getWriter();
//		printWriter.print("Hello");
//	
//	}
}
