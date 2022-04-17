package com.pai.bean;//查看单，用户，职工多页的时候判断是第几页的

import java.util.ArrayList;
import java.util.List;

import com.pai.tools.ConnDB;


public class DoPage {//用于存放查询结果以及展示页面
     private int count;//共有多少行
     private int pageSize;//一页分配多少行
     private int totalPage;//可分多少页
     private int nowPage;//当前第几页
     private List list;// 存放查询结果
     private String sql;//sql语句
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public int doCount()//获取用户单数
	{
		ConnDB con=new ConnDB();
		this.count =0;
		this.count=con.Scount("select count(*) from Odd "+sql);
		con.close();
		int k=this.count/this.pageSize;
		if(k==0)  this.totalPage=1;
		else this.totalPage=k+1;
		return this.count;
	}
	
	public int staffCount(String StaffNumber)//获取职工单数
	{
		ConnDB con=new ConnDB();
		this.count =0;
		this.count=con.Scount("select count(*) from OrderS where StaffNumber='"+StaffNumber+"'");
		con.close();
		this.totalPage=this.count/this.pageSize+1;
		return this.count;
	}
	
	
	public int userCount()//获取用户数
	{
		ConnDB con=new ConnDB();
		this.count =0;
		this.count=con.Scount("select count(*) from Userr");
		con.close();
		this.totalPage=this.count/this.pageSize+1;
		return this.count;
	}
	
	public int sCount()//获取职工数
	{
		ConnDB con=new ConnDB();
		this.count =0;
		this.count=con.Scount("select count(*) from Staff");
		con.close();
		this.totalPage=this.count/this.pageSize+1;
		return this.count;
	}
	
    public List doFindall() //获取所有单
    {
    	ConnDB con=new ConnDB();
    	String sqltr="select * from Odd "+sql;
    	this.list=con.SallOdd(sqltr,pageSize,nowPage);
    	con.close();
    	return this.list;
    }
}
