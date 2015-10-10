package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.function.GetConnectionToSQL;
import com.model.User;
import com.server.UserManager;

public class Register extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Register() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = new User();
		String username=request.getParameter("username").trim();//获取用户名
		username=handleString(username);//将用户名转换为中文格式
		String password=request.getParameter("password").trim();//获取密码
		String confirmpassword=request.getParameter("confirmpassword").trim();//获取密码确认值
		if(!password.equals(confirmpassword))
		{
			response.setContentType("text/html");
			response.setContentType("text/html; charset=utf-8");   
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>密码不同!<br></TITLE>");
			out.println("<meta http-equiv=\"Refresh\" content=\"5;url=index.jsp\" /></HEAD>");
			out.println("  <BODY>");
			out.println("两次密码不相同，5秒后返回");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
			return ;
		}
		user.setName(username);
		user.setPassWord(password);
		UserManager userManager=new UserManager();
		int result=userManager.addUser(user);
		if(1==result)
		{//添加成功
			response.setContentType("text/html");
			response.setContentType("text/html; charset=utf-8");   
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
			out.println("  <BODY>");
			out.println("新增用户成功，      用户名："+username+"  密码："+password);
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}
		else if(result==-1)
		{//数据库连接失败
			response.setContentType("text/html");
			response.setContentType("text/html; charset=utf-8");   
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>Connect SQL failed!<br></TITLE>");
			out.println("<meta http-equiv=\"Refresh\" content=\"5;url=index.jsp\" /></HEAD>");
			out.println("  <BODY>");
			out.println("连接数据库失败，5秒后返回");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}
		else if(result==-2)
		{//新增用户失败，用户名已经存在
			response.setContentType("text/html");
			response.setContentType("text/html; charset=utf-8");   
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>Connect SQL failed!<br></TITLE>");		
			out.println("<meta http-equiv=\"Refresh\" content=\"5;url=index.jsp\" /></HEAD>");
			out.println("  <BODY>");
			out.println("用户名已经存在 <br> 新增用户失败，检查用户名，5秒后返回");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}
		else 
		{//其它的错误
			switch(result)
			{
				//,....................
			}
		}


	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	public String handleString(String s){
		if (s==null)
			return new String("nocontent");
		try {
			s= new String(s.getBytes("iso-8859-1") , "utf-8" );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

}
