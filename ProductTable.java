package com.voidmain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.voidmain.javabeans.Product;

public class ProductTable 
{
	private static final String INSERT_DATA="INSERT INTO PRODUCT(NAME, PRODUCTTYPE, DESCRIPTION, USERNAME, CATEGORY, ID) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ALL_DATA="SELECT ID,NAME,PRODUCTTYPE,DESCRIPTION,USERNAME,CATEGORY FROM PRODUCT";
	private static final String MAX_ID="SELECT MAX(ID) FROM PRODUCT";
	private static final String DELETE_BY_ID="DELETE FROM PRODUCT WHERE ID=?";
	private static final String SELECT_BY_ID="SELECT ID,NAME,PRODUCTTYPE,DESCRIPTION,USERNAME,CATEGORY FROM PRODUCT WHERE ID=?";
	
	public static List<Product> getProducts()
	{
		List<Product> li=new Vector<Product>();;
		try(Connection con=DBConnection.getConnection();
				PreparedStatement ps=con.prepareStatement(SELECT_ALL_DATA);)
			{
				if(con!=null && ps!=null)
				{
					try(ResultSet rs=ps.executeQuery())
					{
						if(rs!=null) {
							while(rs.next())
							{
								Product p=new Product();
								p.setId(rs.getInt(1));
								p.setName(rs.getString(2));
								p.setProductType(rs.getString(3));
								p.setDescription(rs.getString(4));
								p.setUsername(rs.getString(5));
								p.setCategory(rs.getString(6));
								li.add(p);
							}
						}
					}
				}
			} 
		catch (SQLException e) {
				e.printStackTrace();
			}
		return li;
	}
	public static Product getProductById(int id)
	{
		Product p=null;
		try(Connection con=DBConnection.getConnection();
				PreparedStatement ps=con.prepareStatement(SELECT_BY_ID);)
			{
				if(con!=null && ps!=null)
				{
					ps.setInt(1, id);
					try(ResultSet rs=ps.executeQuery())
					{
						if(rs!=null)
						{
							while(rs.next())
							{
								p=new Product();
								p.setId(id);
								p.setName(rs.getString(1));
								p.setProductType(rs.getString(2));
								p.setDescription(rs.getString(3));
								p.setUsername(rs.getString(4));
								p.setCategory(rs.getString(5));
								
							}
						}
					}
				}
			} 
		catch (SQLException e) {
				e.printStackTrace();
			}
		return p;
	}
	public static int addObject(Product p)
	{
		int i=0;
		try(Connection con=DBConnection.getConnection();
				PreparedStatement ps=con.prepareStatement(INSERT_DATA);)
			{
				if(con !=null && ps!=null)
				{
					con.setAutoCommit(false);
					ps.setString(1, p.getName());
					ps.setString(2, p.getProductType());
					ps.setString(3, p.getDescription());
					ps.setString(4, p.getUsername());
					ps.setString(5, p.getCategory());
					ps.setInt(6, p.getId());
					i=ps.executeUpdate();
					if(i>0)
					{
						con.commit();
					}
					else
					{
						con.rollback();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return i;
	}
	
	public static int getMaxId() {
	    int maxId = 0;
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(MAX_ID);
	         ResultSet rs = ps.executeQuery()) {
	    	if(con!=null && ps!=null && rs!=null)
	    	{
		        if (rs.next()) {
		            maxId = rs.getInt(1) + 1;
		        }
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
	    return maxId;
	}

	public static int deleteObject(int id)
	{
		int i=0;
		try(Connection con=DBConnection.getConnection();
		PreparedStatement ps=con.prepareStatement(DELETE_BY_ID);)
		{
			if(con!=null && ps!=null)
			{
				con.setAutoCommit(false);
				ps.setInt(1, id);
				i=ps.executeUpdate();
				if(i>0)
				{
					con.commit();
				}
				else
				{
					con.rollback();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

}
