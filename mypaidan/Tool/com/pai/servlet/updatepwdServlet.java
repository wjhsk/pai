package com.pai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.pai.tools.ConnDB;

/**
 * Servlet implementation class updatepwdServlet
 */
public class updatepwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatepwdServlet() {
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
		String pwd=request.getParameter("pword");
		String usertypes=request.getParameter("usertypes");
		ConnDB con=new ConnDB();
		if("User".equals(usertypes)) {
			int i=con.updatepwd(uname,pwd,0);
			if(i>0) {
				jakarta.servlet.http.HttpSession session = request.getSession();			
				session.setAttribute("pwd", pwd);
				session.setAttribute("uname", uname);
				session.setAttribute("usertypes", usertypes);				
				request.getRequestDispatcher("/public/success.jsp").forward(request,response);
				con.close();
				return;			
			}
			else {			
				request.getRequestDispatcher("/public/error.jsp").forward(request,response);
				con.close();
				return;
			}				
		}
		if("Staff".equals(usertypes)) {
			int i=con.updatepwd(uname,pwd,1);
			if(i>0) {
				jakarta.servlet.http.HttpSession session = request.getSession();
				session.setAttribute("pwd", pwd);
				session.setAttribute("uname", uname);
				session.setAttribute("usertypes", usertypes);
				request.getRequestDispatcher("/public/success.jsp").forward(request,response);
				con.close();
				return;				
			}
			else {
				request.getRequestDispatcher("/public/error.jsp").forward(request,response);
				con.close();
				return;
			}				
		}
		if("Manager".equals(usertypes)) {
			int i=con.updatepwd(uname,pwd,2);
			if(i>0) {
				jakarta.servlet.http.HttpSession session = request.getSession();
				session.setAttribute("pwd", pwd);
				session.setAttribute("uname", uname);
				session.setAttribute("usertypes", usertypes);
				request.getRequestDispatcher("/public/success.jsp").forward(request,response);
				con.close();
				return;				
			}
			else {
				request.getRequestDispatcher("/public/error.jsp").forward(request,response);
				con.close();
				return;
			}				
		}
	}

}
