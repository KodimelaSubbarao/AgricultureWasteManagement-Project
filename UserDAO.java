package com.voidmain.dao;

import java.util.List;

import com.voidmain.javabeans.Product;
import com.voidmain.javabeans.Request;
import com.voidmain.javabeans.User;

public class UserDAO 
{
	public static String isValidUser(String username,String password)
	{
		String role="";
		User user=getUserById(username);

		if(user!=null && user.getPassword().equals(password) && user.getStatus().equals("yes"))
		{
			role=user.getUserType();
		}

		return role;
	}

	public static User getUserById(String id)
	{
		return UserTable.getObject(id);
	}

	public static List<User> getUsers()
	{
		return UserTable.getUsers();
	}
	public static int updateUserObject(User req)
	{
		return UserTable.updateObject(req);
	}
	//========================================================================
	
	public static int addObject(Object obj)
	{
		int i=0;
		if(obj instanceof Product) {
			i=ProductTable.addObject((Product) obj);
		}
		else if(obj instanceof Request)
		{
			i=RequestTable.addObject((Request)obj);
		}
		else if(obj instanceof User)
		{
			i=UserTable.addObject((User)obj);
		}
		return i;
	}
	public static List<Product> getProducts()
	{
		return ProductTable.getProducts();
	}
	public static Product getProductById(int id)
	{
		return ProductTable.getProductById(id);
	}

	public static int deleteObject(int id)
	{		
		return ProductTable.deleteObject(id);
	}

	//=========================================================================
	
	public static List<Request> getRequests()
	{
		return RequestTable.getRequests();
	}
	public static Request getRequestById(int id)
	{
		return RequestTable.getProductById(id);
	}
	public static int updateObject(Request req)
	{
		return RequestTable.updateObject(req);
	}
	public static int deleteRequestObject(int id)
	{
		return RequestTable.deleteObject(id);
	}

	
	
	
}
