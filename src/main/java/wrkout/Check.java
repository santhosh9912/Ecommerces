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

@WebServlet("/Check-out")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		try {
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			Date date= new Date();
			
			//retreive all products in 
			ArrayList<Cart> cart_list=(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
		
			//get user name and id in session
			String user=(String)request.getSession().getAttribute("username");
			String  userId=(String)request.getSession().getAttribute("userId");
			
			//check user and cart list
			
			if(cart_list!=null && user!=null) {
				
					for(Cart c:cart_list) {
						//prepare the order object
					OrderModel order=new OrderModel();
					order.setId(c.getId());
					order.setUid(Integer.parseInt(userId));
					order.setQuantity(c.getQuantity());
					order.setDate(dateFormat.format(date));
					
					//instantiate the doa class
					OrderDao dao=new OrderDao();
					
					//calling insert method
					boolean result=dao.insertOrder(order);
					
					if(!result)break;
				}
				cart_list.clear();
				response.sendRedirect("orders.jsp");
			}else {
				if(user==null)response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
