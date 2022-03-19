package com.pai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.pai.tools.ConnDB;

/**
 * Servlet implementation class addoddServlet
 */
public class addoddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addoddServlet() {
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
		//把单加进数据库
		String uname=request.getParameter("uname");
		String Town=request.getParameter("Town");
		String UseUnit=request.getParameter("UseUnit");
		String MaintenanceUnit=request.getParameter("MaintenanceUnit");
		String EquipmentType=request.getParameter("EquipmentType");
		String Quantity=request.getParameter("Quantity");
		String CheckoutType=request.getParameter("CheckoutType");
		ConnDB con=new ConnDB();
		int i=con.InsertOdd(uname,Town,UseUnit,MaintenanceUnit,EquipmentType,Quantity,CheckoutType);
		if(i>0)
		{
			request.getRequestDispatcher("/public/success.jsp").forward(request,response);
			con.close();
			return;	
		}
		else
		{
			request.getRequestDispatcher("/public/error.jsp").forward(request,response);
			con.close();
			return;
		}
	}

}
