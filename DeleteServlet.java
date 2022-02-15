package com.cetpa;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


@WebServlet("/deleterecord")
public class DeleteServlet extends HttpServlet
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
			
			String sql="delete from employeeinfo where eid=?";
			PreparedStatement ps=cn.prepareStatement(sql);
			ps.setString(1,v1);
			int r=ps.executeUpdate();
			if(r>=1)
				pw.println("<h2>Record has been deleted successfully</h2>");
			else
				pw.println("<h2 style='color:red'>Record does not exist</h2>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
