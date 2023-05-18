 package wrkout;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/order-now")
public class OrderNowServelet extends HttpServlet{
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		try {
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			Date date= new Date();
			PrintWriter printWriter=resp.getWriter();
			String productId=req.getParameter("id");
			String user=(String)req.getSession().getAttribute("username");
			String  userId=(String)req.getSession().getAttribute("userId");
			if(user!=null) {
				
				int productQuantity=Integer.parseInt(req.getParameter("quantity"));
				if(productQuantity<=0) {
					productQuantity=1;
				}
				
				OrderModel model=new OrderModel();
				model.setOrderId(Integer.parseInt(productId));
				model.setUid(Integer.parseInt(userId));
				model.setQuantity(productQuantity);
				model.setDate(dateFormat.format(date));
				OrderDao dao =new OrderDao();
				
				boolean result=dao.insertOrder(model);
				
				if(result) {
					ArrayList<Cart> cart_list=	(ArrayList<Cart>) req.getSession().getAttribute("cart-list");
					if(cart_list!=null) {
						for(Cart c:cart_list) {
							if(c.getId()==Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
						
					}

					resp.sendRedirect("orders.jsp");
				}else {
					printWriter.println("Order failed");
				}
				
			}else {
				resp.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
