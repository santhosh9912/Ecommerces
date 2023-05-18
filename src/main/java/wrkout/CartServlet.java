package wrkout;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/remove-from-cart")
public class CartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try {
			PrintWriter out=resp.getWriter();
			String id=req.getParameter("id");
			if(id!=null) {
			ArrayList<Cart> cartList=	(ArrayList<Cart>) req.getSession().getAttribute("cart-list");
			if(cartList!=null) {
				for(Cart c:cartList) {
					if(c.getId()==Integer.parseInt(id)) {
						cartList.remove(cartList.indexOf(c));
						break;
					}
				}
				resp.sendRedirect("cart.jsp");
			}
				
			}else {
				resp.sendRedirect("cart.jsp");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
