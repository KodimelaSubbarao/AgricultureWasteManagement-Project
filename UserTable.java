package com.voidmain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.voidmain.javabeans.User;

public class UserTable {
    private static final String SELECT_BY_ID = "SELECT MOBILE,PASSWORD,NAME,EMAIL,ADDRESS,USERTYPE,STATUS FROM USER WHERE MOBILE=?";
    private static final String SELECT_ALL_DATA = "SELECT MOBILE,PASSWORD,NAME,EMAIL,ADDRESS,USERTYPE,STATUS FROM USER";
    private static final String UPDATE_STATUS="UPDATE USER SET STATUS=? WHERE MOBILE=?";
    private static final String UPDATE_PASSWORD="UPDATE USER SET PASSWORD=? WHERE MOBILE=?";
	private static final String INSERT_DATA="INSERT INTO USER(MOBILE,PASSWORD,NAME,EMAIL,ADDRESS,USERTYPE,STATUS) VALUES(?, ?, ?, ?, ?, ?, ?)";
    
	public static int addObject(User u)
	{
		int i=0;
		try(Connection con=DBConnection.getConnection();
				PreparedStatement ps=con.prepareStatement(INSERT_DATA);)
			{
				if(con!=null && ps!=null)
				{	
					con.setAutoCommit(false);
					ps.setString(1, u.getMobile());
					ps.setString(2, u.getPassword());
					ps.setString(3, u.getName());
					ps.setString(4, u.getEmail());
					ps.setString(5, u.getAddress());
					ps.setString(6, u.getUserType());
					ps.setString(7, u.getStatus());
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
	
    public static User getObject(String id) {
        User user = null;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {

            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setMobile(rs.getString(1));
                    user.setPassword(rs.getString(2));
                    user.setName(rs.getString(3));
                    user.setEmail(rs.getString(4));
                    user.setAddress(rs.getString(5));
                    user.setUserType(rs.getString(6));
                    user.setStatus(rs.getString(7));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<User> getUsers() {
        List<User> userList = new Vector<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_DATA);
             ResultSet rs = ps.executeQuery()) {
        	
        	if(con!=null && ps!=null && rs!=null)
        	{
	            while (rs.next()) {
	                User user = new User();
	                user.setMobile(rs.getString(1));
	                user.setPassword(rs.getString(2));
	                user.setName(rs.getString(3));
	                user.setEmail(rs.getString(4));
	                user.setAddress(rs.getString(5));
	                user.setUserType(rs.getString(6));
	                user.setStatus(rs.getString(7));
	                userList.add(user);
	            }
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public static int updateObject(User u)
	{
		int i=0;
		try(Connection con=DBConnection.getConnection();
				PreparedStatement ps=con.prepareStatement(UPDATE_STATUS);)
			{
				if(con!=null && ps!=null)
				{	
					con.setAutoCommit(false);
					ps.setString(1, u.getStatus());
					ps.setString(2, u.getMobile());
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
    public static int updatePassword(User u)
   	{
   		int i=0;
   		try(Connection con=DBConnection.getConnection();
   				PreparedStatement ps=con.prepareStatement(UPDATE_PASSWORD);)
   			{
   				if(con!=null && ps!=null)
   				{	
   					con.setAutoCommit(false);
   					ps.setString(1, u.getPassword());
   					ps.setString(2, u.getMobile());
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
}
