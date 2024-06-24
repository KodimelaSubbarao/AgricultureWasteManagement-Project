<%@page import="com.voidmain.javabeans.Request"%>
<%@page import="com.voidmain.javabeans.Product"%>
<%@page import="com.voidmain.dao.UserDAO"%>
<%@page import="java.util.List"%>
<%@include file="header.jsp"%>
<div id="content">
	<!-- insert the page content here -->

	<%
		String status = request.getParameter("status");

		if (status != null) {
	%>
	<h1><%=status%></h1>
	<%
		} else {
	%>
	<h1>View Products</h1>
	<%
		}
	%>

	<form action="viewproducts.jsp">
		<div class="form_settings">

			<p>
				<span>Select Type</span> <select name="type" required="required">
					<option value="">--select--</option>
					<option value="transporter">Transporter</option>
					<option value="industry">Industry Machine</option>
					<option value="farmer">Waste</option>
				</select>
			</p>
			<p style="padding-top: 15px">
				<span>&nbsp;</span><input class="submit" type="submit"
					name="contact_submitted" value="View" />
			</p>
		</div>
	</form>
	
	<%
		String type=request.getParameter("type");
	
		if(type!=null)
		{
	%>

	<table style="width: 100%; border-spacing: 0;">
		<tr>
			<th>Name</th>
			<th>User Name</th>
			<th>Category</th>
			<th>Description</th>
			<th>Send Request</th>
			<th>Delete</th>
		</tr>

		<%
			for (Product product : UserDAO.getProducts()) {
		%>
		<tr>
			<td><%=product.getName()%></td>
			<td><%=product.getUsername()%></td>
			<td><%=product.getCategory()%></td>
			<td><%=product.getDescription()%></td>
			<%
				if (product.getUsername().equals(username)) {
			%>
					<td><a href="view.jsp?id=<%=product.getId()%>">delete</a></td>
			<%
				} else {
			%>
				<td><a href="view.jsp?rpid=<%=product.getId()%>">send request </a></td>
			<%
				}
			%>
			
		</tr>

		<%
			}
		%>
	</table>
	
	<%
		}
	%>

	<%
		String id = request.getParameter("id");

		if (id != null) {
			if (UserDAO.deleteObject(Integer.parseInt(id)) == 1) {
				response.sendRedirect("view.jsp?status=success");
			} else {
				response.sendRedirect("view.jsp?status=failed");
			}
		}
	%>

	 <%
		String rpid = request.getParameter("rpid");

		if (rpid != null) {
			
			Product product = UserDAO.getProductById(Integer.parseInt(rpid));
			
			Request req=new Request();
	
			if (product != null) {
				req.setStatus("yes");

				if (UserDAO.updateObject(req) == 1) {
					response.sendRedirect("viewproducts.jsp?status=success");
				} else {
					response.sendRedirect("viewproducts.jsp?status=failed");
				}
			}
		}
	%> 

</div>
<%@include file="footer.jsp"%>