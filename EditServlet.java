package com.cetpa;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


@WebServlet("/editrecord")
public class EditServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		String v1=req.getParameter("eid");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/adv2_4","root","mysql");
			
			String sql="select * from employeeinfo where eid=?";
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1, v1);
			ResultSet rst=ps.executeQuery();
			if(rst.next())
			{
				pw.println("<form action='updaterecord' method='post'>");
				pw.println("<table border='1' cellpadding='10px'>");
				pw.println("<tr>");
				pw.println("<th align='left'>Employee Id</th>");
				pw.println("<td><input type='text' name='eid' value='"+rst.getString(1)+"' readonly></td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<th align='left'>Employee name</th>");
				pw.println("<td><input type='text' name='name' value='"+rst.getString(2)+"'></td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<th align='left'>Employee department</th>");
				pw.println("<td><input type='text' name='department' value='"+rst.getString(3)+"'></td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<th align='left'>Employee salary</th>");
				pw.println("<td><input type='text' name='salary' value='"+rst.getString(4)+"'></td>");
				pw.println("</tr>");
				pw.println("<tr><td colspan='2' align='center'><button>Update Record</button></td></tr>");
				pw.println("</table>");
				pw.println("</form>");
			}
			else
				pw.println("<h2 style='color:red'>Record does not exist</h2>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
