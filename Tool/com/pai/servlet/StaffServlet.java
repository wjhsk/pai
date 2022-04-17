package com.pai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.pai.tools.ConnDB;
import com.pai.bean.DoPage;


/**
 * Servlet implementation class StaffServlet
 */
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffServlet() {
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
		
		//职工的点击事件处理
		String staff=request.getSession().getAttribute("uname").toString();
		String action=request.getParameter("action");
		ConnDB conn=new ConnDB();
		if(action.equals("1"))//获取职工可接的单
		{
			DoPage dopage=new DoPage();
			String pageNum=request.getParameter("page");
			String sql="select * from Odd where OddNumber IN(select OddNumber from Assign where StaffNumber='"+staff+"')";
			int pageNo=0;
			if(pageNum==null) {
				pageNo=1;
			}else {
				pageNo=Integer.parseInt(pageNum);			
			}
			dopage.setNowPage(pageNo);//设置要获取第几页
			dopage.setPageSize(5);
			dopage.staffCount(staff);
			List list=conn.SAssign(sql,5,pageNo);
			conn.close();
			if(list.size()==0||null==list)
			{
				ConnDB con=new ConnDB();
				sql="select * from Odd where OddNumber IN(select OddNumber from OrderS where Time='1' and StaffNumber='"+staff+"')";   
				list=con.SAssign(sql,5,pageNo);
				if(list.size()==0||null==list)
				{
					request.setAttribute("errorMsg","您暂无可接单");
				}
			}
			request.setAttribute("list", list);
			request.setAttribute("dopage", dopage);
			request.getRequestDispatcher("/StaffFace/LookOdd.jsp").forward(request,response);
		}
		if(action.equals("2"))//职工接受单
		{
			int OddNumber=conn.GOddNumber(staff);
			conn.close();
			String Town=request.getParameter("Town");
			String eType=request.getParameter("eType");
			int i=conn.SAccept(staff,OddNumber,Town,eType);
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
		if(action.equals("3"))//职工拒绝单
		{
			int OddNumber=conn.GOddNumber(staff);
			conn.close();
			String Town=request.getParameter("Town");
			String eType=request.getParameter("eType");
			int i=conn.SRefuse(staff,OddNumber,Town,eType);
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
		if(action.equals("4"))//职工历史单
		{
			DoPage dopage=new DoPage();
			String pageNum=request.getParameter("page");
			int pageNo=0;
			if(pageNum==null) {
				pageNo=1;
			}else {
				pageNo=Integer.parseInt(pageNum);			
			}
			dopage.setNowPage(pageNo);//设置要获取第几页
			dopage.setPageSize(5);
			dopage.staffCount(staff);

			List list=conn.ShistoryOdd(staff,5,pageNo);
			conn.close();
			if(list.size()==0||null==list)
			{
				request.setAttribute("errorMsg","您历史未接过单");
			}
			request.setAttribute("list", list);
			request.setAttribute("dopage", dopage);
			request.getRequestDispatcher("/StaffFace/HistoryOdd.jsp").forward(request,response);
		}
		if(action.equals("5"))//职工查询单
		{
			String OddNumber=request.getParameter("OddNumber");
			List list=conn.SOdd(staff,OddNumber);
			conn.close();
			if(list.size()==0||null==list)
			{
				request.setAttribute("errorMsg","查无此单");
			}
			request.setAttribute("list", list);
			request.getRequestDispatcher("/StaffFace/HistoryOdd.jsp").forward(request,response);
		}
		if(action.equals("6"))//职工完成单
		{
			String OddNumber=request.getParameter("OddNumber");
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
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		
	}
}
