package com.voidmain.servlets;

import java.io.IOException;

import com.voidmain.dao.ProductTable;
import com.voidmain.dao.RequestTable;
import com.voidmain.dao.UserDAO;
import com.voidmain.javabeans.Product;
import com.voidmain.javabeans.Request;
import com.voidmain.javabeans.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/VoidmainServlet")
public class VoidmainServlet extends HttpServlet 
{
	HttpServletRequest request;
	HttpServletResponse response;

	Object obj=null;
	String redirect=null;
	String type;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.request=request;
		this.response=response;

		try {

			type=request.getParameter("type");
			redirect=request.getParameter("redirect");

			if(type!=null && redirect!=null)
			{
				
				Class<?> cls = Class.forName(type); 
				Object obj =cls.getDeclaredConstructor().newInstance();
				  
				//Object object=HttpRequestParser.parseRequest(request, obj);
				Object object=null;
				if(obj instanceof Product)
				{
					Product product=(Product) obj;
					product.setCategory(request.getParameter("category"));
					product.setDescription(request.getParameter("description"));
					product.setName(request.getParameter("name"));
					product.setProductType(request.getParameter("productType"));
					product.setUsername(request.getParameter("username"));
					product.setId(ProductTable.getMaxId());
					
					object=product;
				}
				else if(obj instanceof Request)
				{
					Request req=(Request)obj;
					req.setId(RequestTable.getMaxId());
					req.setRequestedby(request.getParameter("requestedby"));
					req.setPid(Integer.parseInt(request.getParameter("pid")));
					req.setRd(request.getParameter("rd"));
					req.setStatus(request.getParameter("status"));
					req.setDescription(request.getParameter("description"));
					req.setUsername(request.getParameter("username"));
					req.setStatus(request.getParameter("status"));
					object=req;
				}
				else if(obj instanceof User)
				{
					User user=(User)obj;
					user.setName(request.getParameter("name"));
					user.setMobile(request.getParameter("mobile"));
					user.setEmail(request.getParameter("email"));
					user.setPassword(request.getParameter("password"));
					user.setAddress(request.getParameter("address"));
					user.setUserType(request.getParameter("userType"));
					user.setStatus(request.getParameter("status"));
					object=user;
				}
				if(UserDAO.addObject(object)==1)
				{
					response.sendRedirect(redirect+"?status=success");
				}
				else
				{
					response.sendRedirect(redirect+"?status=failed");
				}

			}


		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}