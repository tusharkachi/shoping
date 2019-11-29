package com.allianz.shopping.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allianz.shopping.dao.AddToCartImpl;
import com.allianz.shopping.dao.OrderDAO;
import com.allianz.shopping.dao.OrderDAOImpl;
import com.allianz.shopping.model.AddToCart;
import com.allianz.shopping.model.Order;

/**
 * Servlet implementation class CheckOutController
 */
@WebServlet("/CheckOutController")
public class CheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderDAOImpl orderDAO=new OrderDAOImpl();
       AddToCartImpl addToCartImpl=new AddToCartImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String username=(String) request.getSession().getAttribute("username");
		String total= (String) request.getParameter("total");
		String action=request.getParameter("action");
		if(action!=null && action.equals("placeOrderToCarts"))
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("bill.jsp");
			//request.setAttribute("product", product);
			dispatcher.forward(request, response);
		}
		String product_id[]=request.getParameterValues("product_id");
		if(product_id!=null && product_id.length>0) {
			
			
			float grantTotal=0;
			for(int i=0;i<product_id.length;i++) {
				String tot=request.getParameter("total"+product_id[i]);
				grantTotal+=Float.parseFloat(tot);
			}
			
			Order order=new Order();
			order.setOrderDate(new Date());
			order.setStatus("Ordered");
			order.setUsername(username);
			order.setTotalPrice(grantTotal);
			
			int order_id=orderDAO.addOrder(order);
			order.setOrder_id(order_id);
			for(int i=0;i<product_id.length;i++) {
				if(product_id[i]!=null && !product_id[i].trim().equals(""))
				{
					String quantity=request.getParameter("quantity"+product_id);
					if(quantity!=null)
					{
						AddToCart addToCart=new AddToCart();
						addToCart.setId(Integer.parseInt("id"));
						addToCart.setOrder_id(order_id);
						addToCart.setQuantity(Integer.parseInt(quantity));
						addToCart.setProduct_id(Integer.parseInt(product_id[i]));
						addToCart.setTotal(Float.parseFloat(total));
						addToCartImpl.addAddToCart(addToCart);
					}
				}
			}
			
			
			
		}
	}

}
