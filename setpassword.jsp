<%@page import="com.voidmain.javabeans.User"%>
<%@page import="com.voidmain.dao.UserDAO"%>
<%@page import="com.voidmain.dao.UserTable"%>
<%
		String otp = request.getParameter("rotp");
		String username = request.getParameter("username");
        String pword = request.getParameter("pword");
        if (otp != null && otp.equals(request.getParameter("potp"))) {
            User user = UserTable.getObject(username);
            if (user != null && user.getEmail() != null && user.getPassword() != null) {
                try {
                    user.setPassword(pword);
                    int i = UserTable.updatePassword(user);
                    if (i > 0) {
                        response.sendRedirect("login.jsp?status=Success");
                    } else {
                        out.println("Try Again...");
                        response.sendRedirect("forgotpassword.jsp");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("Internal Server Problem. Please try again later.");
                }
            } else {
                out.println("User not found or email/password is missing.");
            }
        }
    %>