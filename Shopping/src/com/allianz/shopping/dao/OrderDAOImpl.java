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
import com.allianz.shopping.utility.DButility;
import com.allianz.shopping.utility.DateUtility;

public class OrderDAOImpl implements OrderDAO{

	@Override
	public int addOrder(Order order) {
		Connection conn = null;
		try
		{
			conn=DButility.getConnection();
			String sql="insert into order_table(order_id,ordeDate,totalPrice,status,username)values(?,?,?,?,?)";
			PreparedStatement psmt=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			psmt.setInt(1, order.getOrder_id());
			psmt.setDate(2, DateUtility.convertUtilToSql(order.getOrderDate()));
			psmt.setFloat(3, order.getTotalPrice());
			psmt.setString(4,	order.getStatus());
			psmt.setString(5,	order.getUsername());
			int no=psmt.executeUpdate();
			ResultSet rs=null;
			rs=psmt.getGeneratedKeys();
			if(rs.next()) {
				return rs.getInt(1);
			}
		if(no>0)
		{
			
			
		}
		
		}catch(SQLException e)
		{
		
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Order> getAllOrder() {
		Connection conn = DButility.getConnection();
		 List<Order> orderList=new ArrayList<Order>();
		 try {
			 String sql="SELECT * FROM order_table";
			 
			 PreparedStatement psmt=conn.prepareStatement(sql);
			
			ResultSet rs=psmt.executeQuery(sql);
			while(rs.next())
			{
				Order order=new Order();
				//OrderDAOImpl daoimpl=new OrderDAOImpl();
		
				order.setOrder_id(rs.getInt("order_id"));
				order.setOrderDate(rs.getDate("orderDate"));
				order.setTotalPrice(rs.getFloat("totalPrice"));
				order.setStatus(rs.getString("status"));
				order.setUsername(rs.getString("username"));
				orderList.add(order);
				//product.setCategoryList(daoimpl.getAllCategoryByProductId(product.getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return orderList;
	}

	@Override
	public List<Order> getAllOrderById(int Order_id) {
		Connection conn = DButility.getConnection();
		 List<Order> orderList=new ArrayList<Order>();
		 try {
			 String sql="SELECT * FROM order_table where order_id=?";
			 
			 PreparedStatement psmt=conn.prepareStatement(sql);
			
			ResultSet rs=psmt.executeQuery(sql);
			while(rs.next())
			{
				Order order=new Order();
				//OrderDAOImpl daoimpl=new OrderDAOImpl();
				
				order.setOrder_id(rs.getInt("order_id"));
				order.setOrderDate(rs.getDate("orderDate"));
				order.setTotalPrice(rs.getFloat("totalPrice"));
				order.setStatus(rs.getString("status"));
				order.setUsername(rs.getString("username"));
				orderList.add(order);
				//product.setCategoryList(daoimpl.getAllCategoryByProductId(product.getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return orderList;
	}



}
