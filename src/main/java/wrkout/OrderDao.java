package wrkout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDao implements AutoCloseable {
	private static Connection con;
	private static String query;
	private  static PreparedStatement pst;
	private static ResultSet resultSet;

	
	
	
	public  boolean insertOrder(OrderModel model) {
		boolean result=false;
		
		
		try {
			con=HelperClass.getmyConnection();
			query="insert into MY_ORDERS(p_id,u_id,o_quantity,o_date) values(?,?,?,?)";
			pst=con.prepareStatement(query);
			pst.setInt(1, model.getOrderId());
			pst.setInt(2, model.getUid());
			pst.setInt(3, model.getQuantity());
			pst.setString(4, model.getDate());
			if(pst.executeUpdate()>0)
				result=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pst !=null)
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println(result);
		return result;
		
	}
	
	public List<OrderModel>userOrders(int id){
		List<OrderModel>list=new ArrayList<>();
		
		try {
			con=HelperClass.getmyConnection();
			query="select * from MY_ORDERS where u_id=? order by MY_ORDERS.o_id desc";
			pst=con.prepareStatement(query);
			pst.setInt(1, id);
			resultSet=pst.executeQuery();
			
			while(resultSet.next()) {
				OrderModel model=new OrderModel();
				
				int pid=resultSet.getInt("p_id");				
				Product product =ProductDao.getSingleProduct(pid);
				model.setOrderId(resultSet.getInt("o_id"));
				if(product !=null) {
				model.setId(pid);
				model.setName(product.getName());
				model.setCategory(product.getCategory());
				model.setPrice(product.getPrice()*resultSet.getInt("o_quantity"));
				model.setQuantity(resultSet.getInt("o_quantity"));
				model.setDate(resultSet.getString("o_date"));
				}
				list.add(model);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void cancelOrder(int id) {
		try {
			con=HelperClass.getmyConnection();
			query="delete from MY_ORDERS where o_id=?";
			pst=con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws Exception {
		 if (con != null) {
	            con.close();
	        }
		
	}
	
}
