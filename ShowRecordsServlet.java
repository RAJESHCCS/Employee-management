package com.cetpa;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


@WebServlet("/showrecords")
public class ShowRecordsServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/adv2_4","root","mysql");
			String sql="select * from employeeinfo";
			Statement st=cn.createStatement();
			ResultSet rst=st.executeQuery(sql);
			pw.println("<table border='1'>");
			pw.println("<tr><th>Employee id</th><th>Employee name</th><th>Employee department</th><th>Employee salary</th></tr>"); 
			while(rst.next())
			{
				pw.println("<tr>");
				pw.println("<td>"+rst.getString(1)+"</td>");
				pw.println("<td>"+rst.getString(2)+"</td>");
				pw.println("<td>"+rst.getString(3)+"</td>");
				pw.println("<td>"+rst.getString(4)+"</td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
