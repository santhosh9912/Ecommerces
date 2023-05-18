package wrkout;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {
	@Override
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            try  {
            	
            	OrderDao orderDao = new OrderDao();
                int orderId = Integer.parseInt(id);
                orderDao.cancelOrder(orderId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("orders.jsp");
    }
}
