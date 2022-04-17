package com.pai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.pai.bean.DoPage;
import com.pai.tools.ConnDB;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");

		if(action.equals("1"))//用户发布单加入数据库
		{
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
		if(action.equals("2"))//用户查看历史单
		{
			
			DoPage dopage = new DoPage();
				
			String pageNum=request.getParameter("page");//获取用户想要第几页
			String UID=request.getSession().getAttribute("uname").toString();
			int pageNo=0;
			if(pageNum==null) {
				pageNo=1;
			}else {
				pageNo=Integer.parseInt(pageNum);			
			}
				
			dopage.setNowPage(pageNo);//设置要获取第几页放入对象中
			String sqlStr = "where UID='"+UID+"'";
			int Onu=0;
			String Onumber= request.getParameter("OddNumber");//获取想要查询的单号
			if(Onumber!=null)
			{
				try {
					Onu=Integer.parseInt(Onumber);
					System.out.print(Onu);
					if(Onu == 0) {
						sqlStr="";
					}else {
	                    sqlStr="where OddNumber ='"+Onu+"'";
					}
					dopage.setSql(sqlStr);		
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}	
			dopage.setSql(sqlStr);
			dopage.setPageSize(5);
			int totalCount = dopage.doCount();
			if(totalCount == 0) {
				if(Onumber!=null) {
					request.setAttribute("errorMsg","您无此单号");
				}
				else
				{
					request.setAttribute("errorMsg","当前未发布过单");
				}
				//System.out.print(request);
				request.getRequestDispatcher("/UserFace/ShowOdd.jsp").forward(request,response);
			}else {			
				List list=dopage.doFindall();
				request.setAttribute("list", list);//存放所有单信息
				request.setAttribute("dopage", dopage);
				request.getRequestDispatcher("/UserFace/ShowOdd.jsp").forward(request,response);
			}
		}
		if(action.equals("3"))//撤销单
		{
			ConnDB conn=new ConnDB();
			String OddNumber=request.getParameter("oddnumber");
			int i=conn.dOdd(OddNumber);
			if(i>0)
			{
				conn.close();
				request.getRequestDispatcher("/public/success.jsp").forward(request,response);
			}
			else
			{
				conn.close();
				request.getRequestDispatcher("/public/error.jsp").forward(request,response);
			}
		}
		if(action.equals("4"))//完成单
		{
			ConnDB conn=new ConnDB();
			String OddNumber=request.getParameter("oddnumber");
			String staff=conn.GOddStaff(OddNumber);
			conn.close();
			int i=conn.Accomplish(staff,OddNumber);
			if(i>0)
			{
				conn.close();
				request.getRequestDispatcher("/public/success.jsp").forward(request,response);
			}
			else
			{
				conn.close();
				request.getRequestDispatcher("/public/error.jsp").forward(request,response);
			}
		}
		if(action.equals("5"))//评价单
		{
			String oddnumber=request.getParameter("oddnumber");
			String num1=request.getParameter("n1");
			String num2=request.getParameter("n2");
			String num3=request.getParameter("n3");
			ConnDB conn=new ConnDB();
			boolean i=conn.judge(oddnumber);
			if(i)//单已经评价过
			{
				conn.close();
				request.getRequestDispatcher("/public/evaluated.jsp").forward(request,response);
			}
			else {
				conn.evaluate(oddnumber,num1,num2,num3);
				conn.close();
				request.getRequestDispatcher("/public/success.jsp").forward(request,response);
			}
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		
	}

}
