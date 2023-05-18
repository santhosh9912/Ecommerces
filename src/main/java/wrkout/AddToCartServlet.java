package wrkout;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=resp.getWriter()){
			ArrayList<Cart>cartList=new ArrayList<Cart>();
			int id=Integer.parseInt(req.getParameter("id"));
			Cart cm=new Cart();
			cm.setId(id);
			cm.setQuantity(1);
			HttpSession session=req.getSession();
			ArrayList<Cart>cart_list= (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cart_list==null) {
				cartList.add(cm);
				session.setAttribute("cart-list",cartList);
				resp.sendRedirect("index.jsp");
			}else {
				cartList=cart_list;
				boolean exist=false;
			
				for(Cart c:cart_list) {
					if(c.getId()==id) {
						exist=true;
						out.println("<h3 style='color:crimson;text-align:center'>Item already exists in Cart.<a href='cart.jsp'>Go to Cart Page</a></h3>");
						
					}
					
				}
				if(!exist) {
					cartList.add(cm);
					resp.sendRedirect("index.jsp");
					
				}
			}
			
			
		}
	}
}
