package wrkout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class ProductDao {
	private static Connection con;
	private static String query;
	private  static PreparedStatement pst;
	private static ResultSet rs;
	
	
	public static List<Product>getAllProducts(){
		List<Product>products=new ArrayList<Product>();
		try {
			con=HelperClass.getmyConnection();
			query="select * from MY_PRODUCTS";
			pst=(PreparedStatement) con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Product row =new Product();
				row.setId(rs.getInt("product_id"));
				row.setName(rs.getString("product_name"));
				row.setCategory(rs.getString("product_category"));
				row.setPrice(rs.getDouble("product_price"));
				row.setImage(rs.getString("product_image"));
				products.add(row);

			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public List<Cart>getProduct(ArrayList<Cart> cartList){
		List<Cart>products=new ArrayList<Cart>();
		
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					con=HelperClass.getmyConnection();
					query="select * from MY_PRODUCTS where product_id=?";
					pst=con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs=pst.executeQuery();
					while(rs.next()) {
						Cart row =new Cart();
						row.setId(rs.getInt("product_id"));
						row.setName(rs.getString("product_name"));
						row.setCategory(rs.getString("product_category"));
						row.setPrice(rs.getDouble("product_price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum=0;
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					con=HelperClass.getmyConnection();
					query="select * from MY_PRODUCTS where product_id=?";
					pst=con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs=pst.executeQuery();
					
					while(rs.next()) {
						sum+=rs.getDouble("product_price")*item.getQuantity();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sum;
		
	}

	public static Product getSingleProduct(int pid) {
		Product row=null;
		
		try {
			
			con=HelperClass.getmyConnection();
			query="select * from MY_PRODUCTS where product_id=?";
			pst=con.prepareStatement(query);
			pst.setInt(1, pid);
			rs=pst.executeQuery();
			
			while(rs.next()) {
				row=new Product();
				row.setId(rs.getInt("product_id"));
				row.setName(rs.getString("product_name"));
				row.setCategory(rs.getString("product_category"));
				row.setPrice(rs.getDouble("product_price"));
				row.setImage(rs.getString("product_image"));
			}
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		return row;
	}
	
}
