<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>

<head>
<title>Voidmain</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type"
    content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="style/style.css"
    title="style" />
    <style type="text/css">
        #otperror{
            display: none;
        }
        #pwerror{
            display: none;
        }        
        
    </style>
    <script type="text/javascript">
        function otpverifying() {
            let otp1 = document.getElementById("rotp").value;
            let otp2 = document.getElementById("potp").value;
            if (otp1 === otp2) {
                document.getElementById("otperror").style.display = "none";
                document.getElementById("mySubmit").disabled = false;
            } else {
                document.getElementById("otperror").style.display = "block";
                document.getElementById("mySubmit").disabled = true;
            }
        }
        
        function pwordverify() {
            let pw1 = document.getElementById("pw").value;
            let pw2 = document.getElementById("rpw").value;
            if (pw1 === pw2) {
                document.getElementById("pwerror").style.display = "none";
                document.getElementById("mySubmit").disabled = false;
            } else {
                document.getElementById("pwerror").style.display = "block";
                document.getElementById("mySubmit").disabled = true;
            }
        }
    </script>
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
                    <li><a href="login.jsp?status=success">Login</a></li>
                    <li><a href="registration.jsp">Registration</a></li>
                </ul>
            </div>
        </div>
        <div id="content_header"></div>
        <div id="site_content">
            <div id="content">
    <%
        String otp = request.getParameter("otp");
    	String username = request.getParameter("username");
        out.println("Captcha Code :: " + otp); 
    %>
    <form action="setpassword.jsp" method="post">
        <div class="form_settings">
            <input type="hidden" id="rotp" name="rotp" value="<%= otp %>">
            <input type="hidden" name="username" value="<%= username %>">
            <p>
                <span>Enter Captcha: </span><input id="potp" class="contact" type="text" name="potp" value="" onkeyup="otpverifying()" required="required"/>
           		 <span id="otperror" style="color:red;font-size:11px;"> &nbsp; Enter valid captcha only</span>
            </p>
           
            <p>
                <br><span>Enter New Password: </span><input id="pw" class="contact" type="password" name="pword" value="" />
            </p>
            <p>
               <span>Re-Type Password: </span><input id="rpw" class="contact" type="password" onkeyup="pwordverify()" name="rpword" value="" />
            </p>
            <span id="pwerror" style="color:red;font-size:13px;"> &nbsp; Password mismatched </span>
            <p style="padding-top: 15px">
                <br><span>&nbsp;</span><input class="submit" id="mySubmit" type="submit" name="contact_submitted" value="Submit" disabled />
            </p>
        </div>
    </form>
    
    </div>
        </div>
    </div>
</body>
</html>
