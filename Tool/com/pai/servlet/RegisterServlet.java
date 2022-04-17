package com.pai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.pai.tools.ConnDB;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		if(uname==""||pwd=="") {
			request.setAttribute("regError","用户名或密码为空");			
			request.getRequestDispatcher("register.jsp").forward(request,response);
			return;
			}
		ConnDB con=new ConnDB();
		if("User".equals(usertypes)) {
			int i=con.RUSelect(uname,pwd);

			if(i>0) {
				request.setAttribute("regError","注册成功！");
				request.getRequestDispatcher("register.jsp").forward(request,response);
				con.close();
				return;
				
			}
			else {
				request.setAttribute("regError","账户已存在");
				request.getRequestDispatcher("register.jsp").forward(request,response);
				con.close();
				return;
			}				
		}
		//设置职工对每个区的接单率都是0.5，每个修理类型的接单率也是0.5，由之后职工接单情况再进行接单率的更改
		if("Staff".equals(usertypes)) {
			int i=con.RSSelect(uname,pwd);
			if(i>0) {
				request.setAttribute("regError","注册成功！");
				request.getRequestDispatcher("register.jsp").forward(request,response);
				con.close();
				return;				
			}
			else {
				request.setAttribute("regError","账户已存在");
				request.getRequestDispatcher("register.jsp").forward(request,response);
				con.close();
				return;
			}				
		}
	}

}
