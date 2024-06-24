<!DOCTYPE HTML>
<%@page import="java.util.Random"%>
<html>

<head>
<title>Voidmain</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type"
	content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="style/style.css"
	title="style" />
</head>

<body>
	<div id="main">
		<div id="header">
			<div id="logo">
				<div id="logo_text">
					<!-- class="logo_colour", allows you to change the colour of the text -->
					<h1>
						<a href="#">Agriculture <span class="logo_colour">Waste Management</span></a>
					</h1>
				</div>
			</div>
			<div id="menubar">
				<ul id="menu">
				
					<li><a href="login.jsp">Login</a></li>
					<li><a href="registration.jsp">Registration</a></li>

				</ul>
			</div>
		</div>
		<div id="content_header"></div>
		<div id="site_content">
			<div id="content">
				<!-- insert the page content here -->
				<%
					String status=request.getParameter("status");
									
					if(status!=null)
					{
				%>
						<h1><%=status%></h1>
				<%		
					}
					else
					{
				%>
						<h1>Enter Your UserName</h1>
				<%		
					}
				%>
				<%!
					  public static String getOtp() {
				        String pattern = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				        StringBuilder otp = new StringBuilder();
				        Random random = new Random();
				        
				        for (int i = 0; i < 6; i++) {
				            otp.append(pattern.charAt(random.nextInt(pattern.length())));
				        }
				        return otp.toString();
				    }
				%>
				<%
					String otp=getOtp();
				%>
				<form action="otpverify.jsp">
					<div class="form_settings">
						<input type="hidden" name="otp" value="<%=otp %>">
						<p>
							<span>User Name :</span><input class="contact" type="text"
								name="username" value="" />
						</p>
						<p style="padding-top: 15px">
							<span>&nbsp;</span><input class="submit" type="submit"
								name="contact_submitted" value="Set Password" />
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
