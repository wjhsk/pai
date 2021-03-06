package com.pai.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.pai.bean.DoPage;

/**
 * Servlet implementation class ShowServlet
 */
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//起名应该改成UserServlet,写到后面不想改了，用来处理用户的界面点击事件
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
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		
	}

}
