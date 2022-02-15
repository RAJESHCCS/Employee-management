package com.cetpa;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


@WebServlet("/insertrecord")
public class InsertServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		
		String v1=req.getParameter("eid");
		String v2=req.getParameter("name");
		String v3=req.getParameter("department");
		String v4=req.getParameter("salary");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/adv2_4","root","mysql");
			
			String sql="insert into employeeinfo values(?,?,?,?)";
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1,v1);
			ps.setString(2,v2);
			ps.setString(3,v3);
			ps.setString(4,v4);
			ps.executeUpdate();
			pw.println("<h2>Record has been inserted successfully</h2>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
