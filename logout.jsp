<%@page import="com.voidmain.dao.DBConnection"%>
<%
	DBConnection.getConnection().close();
	request.getSession().invalidate();
	response.sendRedirect("index.jsp");
	
%>