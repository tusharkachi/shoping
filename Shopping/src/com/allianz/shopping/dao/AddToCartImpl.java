package com.allianz.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.allianz.shopping.model.AddToCart;
import com.allianz.shopping.model.Order;
import com.allianz.shopping.model.Product;
import com.allianz.shopping.utility.DButility;
import com.allianz.shopping.utility.DateUtility;

public class AddToCartImpl implements AddToCartInterface{

	@Override
	public boolean addAddToCart(AddToCart addToCart) {
		Connection conn = null;
		try
		{
			conn=DButility.getConnection();
			String sql="insert into cart(id, product_id, quantity, order_id,total)values(?,?,?,?,?)";
			PreparedStatement psmt=conn.prepareStatement(sql);
			psmt.setInt(1, addToCart.getId());
			psmt.setInt(2, addToCart.getProduct_id());
			psmt.setInt(3, addToCart.getQuantity());
			psmt.setInt(4, addToCart.getOrder_id());
			psmt.setFloat(5, addToCart.getTotal());
			
		int no=psmt.executeUpdate();
		if(no>0)
		{
			conn.commit();
			return true;
		}
		
		}catch(SQLException e)
		{
		
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<AddToCart> getAllAddToCartByOrderId(int order_id) {
		Connection conn = DButility.getConnection();
		 List<AddToCart> addToCartList=new ArrayList<AddToCart>();
		 try {
			 String sql="SELECT * FROM cart where order_id=?";
			 PreparedStatement psmt=conn.prepareStatement(sql);
			psmt.setInt(1,order_id);
			ResultSet rs=psmt.executeQuery(sql);
			while(rs.next())
			{
				
				AddToCart addToCart=new AddToCart();
				//OrderDAOImpl daoimpl=new OrderDAOImpl();
		

				addToCart.setId(rs.getInt("id"));
				addToCart.setOrder_id(rs.getInt("product_id"));
				addToCart.setProduct_id(rs.getInt("order_id"));
				addToCart.setQuantity(rs.getInt("quantity"));
				addToCart.setTotal(rs.getFloat("total"));

				addToCartList.add(addToCart);
				//product.setCategoryList(daoimpl.getAllCategoryByProductId(product.getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return addToCartList;
	}


}
