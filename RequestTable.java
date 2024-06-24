package com.voidmain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.voidmain.javabeans.Request;

public class RequestTable 
{
	private static final String INSERT_DATA="INSERT INTO REQUEST(ID, USERNAME,REQUESTEDBY, RD, DESCRIPTION, PID , STATUS) VALUES(?, ?,?, ?, ?, ?, ?)";
	private static final String MAX_ID="SELECT MAX(ID) FROM REQUEST";
	private static final String UPDATE_DATA="UPDATE REQUEST SET STATUS=? WHERE ID=?";
	private static final String SELECT_ALL_DATA="SELECT ID, USERNAME,REQUESTEDBY, RD, DESCRIPTION, PID, STATUS FROM REQUEST";
	private static final String DELETE_BY_ID="DELETE FROM REQUEST WHERE ID=?";
	private static final String SELECT_BY_ID="SELECT ID, USERNAME,REQUESTEDBY, RD, DESCRIPTION, PID, STATUS FROM REQUEST WHERE ID=?";
	
	public static int getMaxId() {
	    int maxId = 0;
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(MAX_ID);
	         ResultSet rs = ps.executeQuery()) {
	        if(con!=null && ps!=null && rs!=null) {
		        if (rs.next()) {
		            maxId = rs.getInt(1) + 1;
		        }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return maxId;
	}
	public static int addObject(Request r)
	{
		int i=0;
		try(Connection con=DBConnection.getConnection();
				PreparedStatement ps=con.prepareStatement(INSERT_DATA);)
			{
				if(con!=null && ps!=null)
				{	
					con.setAutoCommit(false);
					ps.setInt(1, r.getId());
					ps.setString(2, r.getUsername());
					ps.setString(3, r.getRequestedby());
					ps.setString(4, r.getRd());
					ps.setString(5, r.getDescription());
					ps.setInt(6, r.getPid());
					ps.setString(7, r.getStatus());
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
	public static int updateObject(Request req)
	{
		int i=0;
		try(Connection con=DBConnection.getConnection();
				PreparedStatement ps=con.prepareStatement(UPDATE_DATA);)
			{
				if(con!=null && ps!=null)
				{	con.setAutoCommit(false);
					ps.setString(1, req.getStatus());
					ps.setInt(2, req.getId());
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
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	public static List<Request> getRequests()
	{
		List<Request> li=new Vector<Request>();;
		try(Connection con=DBConnection.getConnection();
				PreparedStatement ps=con.prepareStatement(SELECT_ALL_DATA);)
			{
				if(con!=null && ps!=null)
				{
					try(ResultSet rs=ps.executeQuery())
					{
						if(rs!=null)
						{
							while(rs.next())
							{
								Request r=new Request();
								r.setId(rs.getInt(1));
								r.setUsername(rs.getString(2));
								r.setRequestedby(rs.getString(3));
								r.setRd(rs.getString(4));
								r.setDescription(rs.getString(5));
								r.setPid(rs.getInt(6));
								r.setStatus(rs.getString(7));
								li.add(r);
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
	public static Request getProductById(int id)
	{
		Request r=null;
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
								r=new Request();
								r.setId(rs.getInt(1));
								r.setUsername(rs.getString(2));
								r.setRequestedby(rs.getString(3));
								r.setRd(rs.getString(4));
								r.setDescription(rs.getString(5));
								r.setPid(rs.getInt(6));
								r.setStatus(rs.getString(7));
							}
						}
					}
				}
			} 
		catch (SQLException e) {
				e.printStackTrace();
			}
		return r;
	}
}
