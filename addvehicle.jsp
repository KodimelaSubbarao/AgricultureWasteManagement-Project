<%@page import="java.util.Date"%>

<script type="text/javascript">
	
	function validate()
	{
		var name=document.appform.eventName.value;
		var description=document.appform.description.value;
		
		if(name=="" || name==null)
		{
			alert("please enter Event name");
			return false;
		}
		if(description=="" || description==null)
		{
			alert("please enter description");
			return false;
		}
	}
	
</script>

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
		<h1>Add Vehicle</h1>
		<%
			}
		%>
	
		<form action="VoidmainServlet" method="post" name="appform">

			<input type="hidden" name="type" value="com.voidmain.javabeans.Product">
			<input type="hidden" name="redirect" value="addvehicle.jsp">
			<input type="hidden" name="username" value="<%=username%>">
			<input type="hidden" name="productType" value="vehicle">
			
			<div class="form_settings">
				<p>
					<span>Vehicle Name</span><input class="contact" type="text" name="name"
						value="" />
				</p>
				<p>
					<span>Select Category</span>
						<select name="category">
							<option value="tractor">Tractor</option>
							<option value="Lorry">Lorry</option> 
							<option value="bullock Cart">Bullock Cart</option>
						</select>
				</p>
				<p>
					<span>Description</span><textarea class="contact" name="description"></textarea>
				</p>
				
				<p style="padding-top: 15px">
					<span>&nbsp;</span><input class="submit" type="submit"
						name="contact_submitted" value="Add" onclick="return validate()"/>
				</p>
			</div>
		</form>
	</div>
<%@include file="footer.jsp"%>