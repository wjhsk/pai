package com.pai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.pai.tools.ConnDB;
/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		String usertypes=request.getParameter("usertypes");
		HttpSession session=request.getSession();
		session.setAttribute("uname", uname);//设置为会话对象可以延长存活时间，不用页面交换还得在传参一次
		session.setAttribute("pwd", pwd);
		session.setAttribute("usertypes", usertypes);
		
		if(uname==""||pwd=="") {
			request.setAttribute("errorMsg","用户名或密码为空");			
			request.getRequestDispatcher("/public/login.jsp").forward(request,response);
			return;
			}
		ConnDB con=new ConnDB();
		if("User".equals(usertypes)) { //判断是哪个类型的登录
			int i=con.UserSelect(uname,pwd);
			if(i==1) {
				request.getRequestDispatcher("UserInterface.jsp").forward(request,response);
				con.close();
				return;
			}
			else {
				request.setAttribute("errorMsg","用户名或密码错误");
				request.getRequestDispatcher("/public/login.jsp").forward(request,response);
				con.close();
				return;
			}			
		}
		if("Manager".equals(usertypes)) {
			int i=con.MSelect(uname,pwd);
			if(i==1) {
				request.getRequestDispatcher("MInterface.jsp").forward(request,response);
				con.close();
				return;
			}
			else {
				request.setAttribute("errorMsg","用户名或密码错误");
				request.getRequestDispatcher("/public/login.jsp").forward(request,response);
				con.close();
				return;
			}	
		}
		if("Staff".equals(usertypes)) {
			int i=con.SSelect(uname,pwd);
			if(i==1) {
				request.getRequestDispatcher("StaffInterface.jsp").forward(request,response);
				con.close();
				return;
			}
			else {
				request.setAttribute("errorMsg","用户名或密码错误");
				request.getRequestDispatcher("/public/login.jsp").forward(request,response);
				con.close();
				return;
			}			
		}	
	}

}
