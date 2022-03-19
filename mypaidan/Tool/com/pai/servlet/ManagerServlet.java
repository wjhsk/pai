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
 * Servlet implementation class ManagerServlet
 */
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
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
		
		//处理管理员的点击事件同服务器交互
		String action=request.getParameter("action");
		ConnDB conn=new ConnDB();
		if(action.equals("1"))//获取用户信息
		{
			String uid=request.getParameter("Uid");//获取要注销的用户
			if(uid!=null)
			{		
				ConnDB con=new ConnDB();
				int re=con.dUser(uid);
				if(re<=0)
				{
					request.setAttribute("errorMsg","操作失败");
				}
				con.close();
				
			}
			DoPage dopage=new DoPage();
			String sql="select * from Userr";
			String pageNum=request.getParameter("page");
			int pageNo=0;
			if(pageNum==null) {
				pageNo=1;
			}else {
				pageNo=Integer.parseInt(pageNum);			
			}
			dopage.setNowPage(pageNo);//设置要获取第几页
			dopage.setPageSize(5);
			dopage.userCount();
			String UID=request.getParameter("UID");
			if(UID!=null)
			{
				sql="select * from Userr where UID='"+UID+"'";
			}
			List list=conn.Suserr(sql,5,pageNo);
			conn.close();
			if(list.size()==0||null==list)
			{
				request.setAttribute("errorMsg","暂无用户");
			}
			request.setAttribute("list", list);
			request.setAttribute("dopage", dopage);
			request.getRequestDispatcher("/ManagerFace/ControlUser.jsp").forward(request,response);
		}
		if(action.equals("2"))//获取职工信息
		{
			String Staff=request.getParameter("Staff");//获取要注销的职工
			if(Staff!=null)
			{		
				ConnDB con=new ConnDB();
				int re=con.dStaff(Staff);
				if(re<=0)
				{
					request.setAttribute("errorMsg","操作失败");
				}
				con.close();	
			}
			DoPage dopage=new DoPage();
			String sql="select * from Staff";
			String pageNum=request.getParameter("page");
			int pageNo=0;
			if(pageNum==null) {
				pageNo=1;
			}else {
				pageNo=Integer.parseInt(pageNum);			
			}
			dopage.setNowPage(pageNo);//设置要获取第几页
			dopage.setPageSize(5);
			dopage.sCount();
			String OddNumber=request.getParameter("OddNumber");
			if(OddNumber!=null)
			{
				sql="select * from Staff where OddNumber='"+OddNumber+"'";
			}
			List list=conn.Sstaff(sql,5,pageNo);
			conn.close();
			if(list.size()==0||null==list)
			{
				request.setAttribute("errorMsg","暂无用户");
			}
			request.setAttribute("list", list);
			request.setAttribute("dopage", dopage);
			request.getRequestDispatcher("/ManagerFace/ControlStaff.jsp").forward(request,response);
		}
		if(action.equals("3"))//获取单
		{
			String oddnumber=request.getParameter("oddnumber");//获取要注销的单号
			if(oddnumber!=null)
			{		
				ConnDB con=new ConnDB();
				int re=con.dOdd(oddnumber);
				if(re<=0)
				{
					request.setAttribute("errorMsg","操作失败");
				}
				con.close();	
			}
			DoPage dopage=new DoPage();
			String sql="select * from Odd";
			String pageNum=request.getParameter("page");
			int pageNo=0;
			if(pageNum==null) {
				pageNo=1;
			}else {
				pageNo=Integer.parseInt(pageNum);			
			}
			dopage.setNowPage(pageNo);//设置要获取第几页
			dopage.setPageSize(5);
			dopage.setSql("");
			dopage.doCount();
			String OddNumber=request.getParameter("OddNumber");
			if(OddNumber!=null)
			{
				sql="select * from Odd where OddNumber='"+OddNumber+"'";
			}
			List list=conn.SallOdd(sql,5,pageNo);
			conn.close();
			if(list.size()==0||null==list)
			{
				request.setAttribute("errorMsg","暂无单");
			}
			request.setAttribute("list", list);
			request.setAttribute("dopage", dopage);
			request.getRequestDispatcher("/ManagerFace/ControlOdd.jsp").forward(request,response);
		}
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		
	}

}
