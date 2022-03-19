package com.pai.tools;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.pai.bean.Odd;
import com.pai.bean.User;

public class ConnDB {
	public Connection conn=null;
	public Statement stmt=null;
	public ResultSet rs=null;
	private static String driverClass="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SendOrders";
	private static String username="sa";
	private static String password="123456";
	
	public ConnDB()
	{
		
	}
	//创建数据库链接
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName(driverClass);
			conn=DriverManager.getConnection(url,username,password);
		}catch(Exception e) {
			e.printStackTrace();			
		}
		if(conn==null) {
			System.err.println("获取数据库链接失败");
		}
		return conn;
	}
	
	//查询有多少元组
	public int Scount(String sql)
	{
		int count=0;;
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);			
			if(rs.next())
			{
				count=rs.getInt(1);
			}
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}			
		return count;
	}
	
	//返回所有单
	public List SallOdd(String sql,int pageSize,int nowPage)
	{
		List<Odd> list=new ArrayList<>();
		int judge=pageSize*(nowPage-1);  //用来知道用户想要第几页内容，于是需要跳过多少元组
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);	
			if(judge>1)
			{
				while(rs.next())  //跳过元组
				{
					if(judge==1) break;
					else judge--;
				}
			}
			judge=0;
			while(rs.next())
			{			//获取用户想要的那页内容
				Odd odd=new Odd();
				odd.setOddNumber(rs.getString(1));
				odd.setTown(rs.getString(2));
				odd.setUseUnit(rs.getString(3));
				odd.setMaintenanceUnit(rs.getString(4));
				odd.setEquipmentType(rs.getString(5));
				odd.setQuantity(rs.getString(6));
				odd.setCheckoutType(rs.getString(7));
				odd.setUid(rs.getString(8));
				odd.setState(rs.getString(9));
				String state=rs.getString(9);
				String oddnumber=rs.getString(1);
				if(state.equals("0"))
				{
					odd.setStaff("无");  //为0表示为派单给职工，
				}
				if(state.equals("3"))   //表示以及派单给职工，职工还未进行接受和拒绝处理
				{
					Connection con=getConnection();
					sql="select StaffNumber from Assign where OddNumber='"+oddnumber+"'";
					Statement qstmt=con.createStatement();
					ResultSet re=qstmt.executeQuery(sql);	
					if(re.next())
					{
						odd.setStaff(re.getString(1));
					}
					re.close();
					qstmt.close();
					con.close();
				}
				else //职工以及接受正在进行或以及完成
				{
					Connection con=getConnection();
					sql="select StaffNumber from OrderS where OddNumber='"+oddnumber+"'";
					Statement qstmt=con.createStatement();
					ResultSet re=qstmt.executeQuery(sql);	
					if(re.next())
					{
						odd.setStaff(re.getString(1));
					}
					re.close();
					qstmt.close();
					con.close();
				}	
				list.add(odd);
				judge++;
				if(judge==5) break;
			}			
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return list;
	}
	
	// 查找职工可接的单
	public List SAssign(String sql)
	{
		List<Odd> list=new ArrayList<>();
		
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);			
			if(rs.next())
			{			
				Odd odd=new Odd();
				odd.setOddNumber(rs.getString(1));
				odd.setTown(rs.getString(2));
				odd.setUseUnit(rs.getString(3));
				odd.setMaintenanceUnit(rs.getString(4));
				odd.setEquipmentType(rs.getString(5));
				odd.setQuantity(rs.getString(6));
				odd.setCheckoutType(rs.getString(7));
				odd.setUid(rs.getString(8));
				odd.setState(rs.getString(9));
				list.add(odd);
			}			
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return list;
	}
	//获取单号
	public int GOddNumber(String StaffNumber)  //写完发现没有用的方法，直接超链接附加参数就行Orz
	{
		int OddNumber=0;
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			String sql="select OddNumber from Assign where StaffNumber='"+StaffNumber+"'";
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
				OddNumber=rs.getInt(1);		
				}
			
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return OddNumber;
	}
	//职工接受单
	public int SAccept(String StaffNumber,int OddNumber,String Town,String Type)
	{
		int re=0;
		try {  //写到这发现代码复用率太低了，不想重新改数据库方法Orz
			conn=getConnection();
			String sql="delete from Assign where StaffNumber='"+StaffNumber+"'";//删除派单表内对象
			PreparedStatement pstmt=conn.prepareStatement(sql);
			re=pstmt.executeUpdate();
			Connection cd=getConnection();
			sql="delete from OrderRate where OddNumber='"+OddNumber+"'";//删除接单率里面有关对象，防止下次派单此单又派一次
			PreparedStatement astmt=cd.prepareStatement(sql);
			re=astmt.executeUpdate();			
			astmt.close();
			cd.close();
			Connection conne=getConnection();
			sql="select "+Town+","+Type+" from Staff where StaffNumber='"+StaffNumber+"'";//接受单就可以改变职工对该类型单的接单率
			//姑且用城区所在地以及设备类型作为职工接单因素
			stmt=conne.createStatement();
			rs=stmt.executeQuery(sql);
			float town=0;
			float type=0;
			if(rs.next())
			{
				town=(rs.getFloat(1)+1)/2;//接受单职工关于这些城区和设备的接单意愿上升
				type=(rs.getFloat(2)+1)/2;
			}
			if(re>0)
			{
				Connection con=getConnection();
				sql="insert into OrderS(StaffNumber,OddNumber,Time) values('"+StaffNumber+"','"+OddNumber+"','"+"1')";
				//插入匹配单表里
				PreparedStatement qstmt=con.prepareStatement(sql);
				re=qstmt.executeUpdate();
				qstmt.close();
				con.close();
				Connection co=getConnection();
				sql="update Odd set state='1' where OddNumber='"+OddNumber+"'";
				//更新单状态为1：进行中
				PreparedStatement rstmt=co.prepareStatement(sql);
				re=rstmt.executeUpdate();
				rstmt.close();
				co.close();
				Connection c=getConnection();
				sql="update Staff set state='2',"+Town+"='"+town+"',"+Type+"='"+type+"' where StaffNumber='"+StaffNumber+"'";
				//更新职工的接单意愿
				PreparedStatement jstmt=c.prepareStatement(sql);
				re=jstmt.executeUpdate();
				jstmt.close();
				c.close();
			}
			else re=0;
			pstmt.close();
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}
		return re;
	}
	//职工拒绝单
	public int SRefuse(String StaffNumber,int OddNumber,String Town,String Type)
	{
		int re=0;
		try {
			conn=getConnection();
			String sql="delete from Assign where StaffNumber='"+StaffNumber+"'";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			re=pstmt.executeUpdate();	
			Connection conne=getConnection();
			sql="select "+Town+","+Type+" from Staff where StaffNumber='"+StaffNumber+"'";
			stmt=conne.createStatement();
			rs=stmt.executeQuery(sql);
			float town=0;
			float type=0;
			if(rs.next())
			{
				town=rs.getFloat(1)*2/3;//拒绝单职工关于这些城区和设备的接单意愿下降
				type=rs.getFloat(2)*2/3;
			}
			if(re>0)
			{
				Connection cd=getConnection();
				sql="delete from OrderRate where OddNumber='"+OddNumber+"'";
				//拒绝就将接单率表删除已防再派给他
				PreparedStatement astmt=cd.prepareStatement(sql);
				re=astmt.executeUpdate();			
				astmt.close();
				cd.close();

				Connection c=getConnection();
				sql="update Staff set state='2',"+Town+"='"+town+"',"+Type+"='"+type+"' where StaffNumber='"+StaffNumber+"'";
				PreparedStatement jstmt=c.prepareStatement(sql);
				re=jstmt.executeUpdate();
				jstmt.close();
				c.close();
			}
			else re=0;
			pstmt.close();
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}
		return re;
	}
	//职工查看历史单
	public List ShistoryOdd(String StaffNumber,int pageSize,int nowPage)
	{
		List<Odd> list=new ArrayList<>();
		int judge=pageSize*(nowPage-1);
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			String sql="select * from Odd where OddNumber IN (select OddNumber from OrderS where StaffNumber='"+StaffNumber+"')";
			rs=stmt.executeQuery(sql);		
			if(judge>1)
			{
				while(rs.next())
				{
					if(judge==1) break;
					else judge--;
				}
			}
			judge=0;
			while(rs.next())
			{			
				Odd odd=new Odd();
				odd.setOddNumber(rs.getString(1));
				odd.setTown(rs.getString(2));
				odd.setUseUnit(rs.getString(3));
				odd.setMaintenanceUnit(rs.getString(4));
				odd.setEquipmentType(rs.getString(5));
				odd.setQuantity(rs.getString(6));
				odd.setCheckoutType(rs.getString(7));
				odd.setUid(rs.getString(8));
				odd.setState(rs.getString(9));
				list.add(odd);
				judge++;
				if(judge==5) break;
			}			
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return list;
	}
	//职工查看单
	public List SOdd(String StaffNumber,String OddNumber)
	{
		List<Odd> list=new ArrayList<>();
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			String sql="select * from Odd where OddNumber IN (select OddNumber from OrderS where StaffNumber='"+StaffNumber+"' and OddNumber='"+OddNumber+"')";
			rs=stmt.executeQuery(sql);	
			
			while(rs.next())
			{			
				Odd odd=new Odd();
				odd.setOddNumber(rs.getString(1));
				odd.setTown(rs.getString(2));
				odd.setUseUnit(rs.getString(3));
				odd.setMaintenanceUnit(rs.getString(4));
				odd.setEquipmentType(rs.getString(5));
				odd.setQuantity(rs.getString(6));
				odd.setCheckoutType(rs.getString(7));
				odd.setUid(rs.getString(8));
				odd.setState(rs.getString(9));
				list.add(odd);
			}			
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return list;
	} 
	//获得所有用户
	public List Suserr(String sql,int pageSize,int nowPage ) {
		List<User> list=new ArrayList<>();
		int judge=pageSize*(nowPage-1);
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(judge>1)
			{
				while(rs.next())
				{
					if(judge==1) break;
					else judge--;
				}
			}
			judge=0;
			while(rs.next())
			{			
				User user=new User();
				user.setuid(rs.getString(1));
				user.setPassword(rs.getString(2));
				list.add(user);
				judge++;
				if(judge==5) break;
			}
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return list;
	}
	//获取所有职工
	public List Sstaff(String sql,int pageSize,int nowPage ) {
		List<User> list=new ArrayList<>();
		int judge=pageSize*(nowPage-1);
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(judge>1)
			{
				while(rs.next())
				{
					if(judge==1) break;
					else judge--;
				}
			}
			judge=0;
			while(rs.next())
			{			
				User user=new User();
				user.setuid(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setState(rs.getString(3));
				list.add(user);
				judge++;
				if(judge==5) break;
			}
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return list;
	}
	//用户名验证
	public int UserSelect(String uname,String pwd) {
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select Password from Userr where UID='"+uname+"' and Password='"+pwd+"'");
			if(rs.next()) {
				return 1;
			}
			else {
				return 0;
			}
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return 0;
	}
	//管理员名验证
	public int MSelect(String uname,String pwd) {
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select Password from Manager where ID='"+uname+"' and Password='"+pwd+"'");
			if(rs.next()) {
				return 1;
			}
			else {
				return 0;
			}
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return 0;
	}
		//职工名验证
	public int SSelect(String uname,String pwd) {
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select Password from Staff where StaffNumber='"+uname+"' and Password='"+pwd+"'");
			if(rs.next()) {
				return 1;
			}
			else {
				return 0;
			}
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return 0;
	}
	//用户注册
	public int RUSelect(String uname,String pwd) {
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			String sql="insert into Userr(UID,Password) values('"+uname+"','"+pwd+"')";
			rs=stmt.executeQuery("select Password from Userr where UID='"+uname+"'");
			if(rs.next()) {
				return 0;
			}
			else {
				stmt.close();
				rs.close();
				int re=0;
				PreparedStatement pstmt=conn.prepareStatement(sql);
				re=pstmt.executeUpdate();
				pstmt.close();
				return re;
			}
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return 0;
	}
	//职工注册
	public int RSSelect(String uname,String pwd) {
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select Password from Staff where StaffNumber='"+uname+"'");
			if(rs.next()) {
				return 0;
			}
			else {
				stmt.close();
				rs.close();
				int re=0;
				PreparedStatement pstmt=conn.prepareStatement("insert into Staff(StaffNumber,Password,State,Forklift,Elevator,Crane,baiyun,tianhe,yuexiu,panyu,haizhu,huangpu) values('"+uname+"','"+pwd+"','1','0.5','0.5','0.5','0.5','0.5','0.5','0.5','0.5','0.5')");
				re=pstmt.executeUpdate();
				pstmt.close();
				return re;
			}
		}catch(SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return 0;
	}
    //删除用户以及未派发的单
	public int dUser(String UID)
	{
		int re=0;
		try {
			conn=getConnection();
			String sql="delete from Assign where OddNumber IN (select OddNumber from Odd where UID='"+UID+"' and state='0')";
			//删除派单表数据
			PreparedStatement pstmt=conn.prepareStatement(sql);
			re=pstmt.executeUpdate();			
			pstmt.close();
			Connection con=getConnection();
			sql="delete from OrderRate where OddNumber IN(select OddNumber from Odd where UID='"+UID+"')";
			//删除接单率表数据
			PreparedStatement qstmt=con.prepareStatement(sql);
			re=qstmt.executeUpdate();			
			qstmt.close();
			con.close();
			Connection co=getConnection();;
			sql="delete from Odd where state IN ('0','3') and UID='"+UID+"'";
			//删除未派单和已派单未接单，认为进行中的单和已完成的单可以缓一缓不用删除
			PreparedStatement rstmt=co.prepareStatement(sql);
			re=rstmt.executeUpdate();			
			rstmt.close();
			co.close();
			Connection c=getConnection();;
			sql="delete from Userr where UID='"+UID+"'";//删除用户
			PreparedStatement wstmt=c.prepareStatement(sql);
			re=wstmt.executeUpdate();			
			wstmt.close();
			c.close();
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}
		return re;
	}
	//删除职工以及职工进行与未完成的单情况以及职工的接单率
	public int dStaff(String StaffNumber)
	{
		int re=0;
		try {
			conn=getConnection();
			String sql="delete from Assign where StaffNumber='"+StaffNumber+"'";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			re=pstmt.executeUpdate();			
			pstmt.close();
			Connection con=getConnection();
			sql="delete from OrderRate where StaffNumber='"+StaffNumber+"'";
			PreparedStatement qstmt=con.prepareStatement(sql);
			re=qstmt.executeUpdate();			
			qstmt.close();
			con.close();
			Connection co=getConnection();;
			sql="delete from Staff where StaffNumber='"+StaffNumber+"'";
			PreparedStatement rstmt=co.prepareStatement(sql);
			re=rstmt.executeUpdate();			
			rstmt.close();
			co.close();
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}
		return re;
	}
	//删除单
	public int dOdd(String OddNumber)
	{
		int re=0;
		try {
			conn=getConnection();
			String sql="delete from Assign where OddNumber='"+OddNumber+"'";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			re=pstmt.executeUpdate();			
			pstmt.close();
			Connection con=getConnection();
			sql="delete from OrderRate where OddNumber='"+OddNumber+"'";
			PreparedStatement qstmt=con.prepareStatement(sql);
			re=qstmt.executeUpdate();			
			qstmt.close();
			con.close();
			Connection co=getConnection();;
			sql="delete from Odd where OddNumber='"+OddNumber+"'";
			PreparedStatement rstmt=co.prepareStatement(sql);
			re=rstmt.executeUpdate();			
			rstmt.close();
			co.close();
			Connection cc=getConnection();;
			sql="delete from OrderS where OddNumber='"+OddNumber+"'";
			PreparedStatement ustmt=cc.prepareStatement(sql);
			re=ustmt.executeUpdate();			
			ustmt.close();
			cc.close();
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}
		return re;
	}
	//更改密码
	public int updatepwd(String uname,String pwd,int type)
	{
		try {
			conn=getConnection();
			String sql="";
			if(type==0)
			{
				sql="update Userr set Password='"+pwd+"' where UID='"+uname+"'";
			}
			else if(type==1)
			{
				sql="update Staff set Password='"+pwd+"' where StaffNumber='"+uname+"'";
			}
			else if(type==2)
			{
				sql="update Manager set Password='"+pwd+"' where ID='"+uname+"'";
			}
			int re=0;
			PreparedStatement pstmt=conn.prepareStatement(sql);
			re=pstmt.executeUpdate();
			pstmt.close();
			return re;
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}			
		return 0;
	}
	//插入新单并且计算每个职工接单率
	public int InsertOdd(String uname,String Town,String UseUnit,String MaintenanceUnit,String EquipmentType,String Quantity,String CheckoutType)
	{
		try {
			conn=getConnection();
			String sql="insert into Odd(Town,UseUnit,MaintenanceUnit,EquipmentType,Quantity,CheckoutType,UID,state) values('"+Town+"','"+UseUnit+"','"+MaintenanceUnit+"','"+EquipmentType+"','"+Quantity+"','"+CheckoutType+"','"+uname+"','0')";
			int re=0;
			PreparedStatement pstmt=conn.prepareStatement(sql);
			re=pstmt.executeUpdate();
			pstmt.close();
			Connection co=getConnection();
			int OddNumber=0;
			sql="select OddNumber from Odd where UID ='"+uname+"'"; 
			Statement dtmt=co.createStatement();
			ResultSet rd=dtmt.executeQuery(sql);
			while(rd.next())
			{
				OddNumber=rd.getInt(1);
			}
			rd.close();
			dtmt.close();
			co.close();
			Connection con=getConnection();
			sql="select StaffNumber,"+Town+","+EquipmentType+" from Staff";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);	
			while(rs.next())
			{
				double Rate=rs.getFloat(2)*0.4+rs.getFloat(3)*0.6;//认为城区影响接单率比重为0.4，设备类型影响比重为0.6
				String StaffNumber=rs.getString(1);
				sql="insert into OrderRate(OddNumber,StaffNumber,Rate) values('"+OddNumber+"','"+StaffNumber+"','"+Rate+"')";
				Connection cc=getConnection();;
				PreparedStatement ustmt=cc.prepareStatement(sql);
				ustmt.executeUpdate();			
				ustmt.close();
				cc.close();
			}
			con.close();
			return re;
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}			
		return 0;
	}
	//
	public int Accomplish(String StaffNumber,String OddNumber)//单完成
	{
		//更新单，职工的状态
		try {
			conn=getConnection();
			String sql="update Staff set State='1' where StaffNumber='"+StaffNumber+"'";
			int re=0;
			PreparedStatement pstmt=conn.prepareStatement(sql);
			re=pstmt.executeUpdate();
			pstmt.close();
			Connection con=getConnection();
			sql="update OrderS set Time='2' where OddNumber='"+OddNumber+"'";
			PreparedStatement qstmt=con.prepareStatement(sql);
			re=qstmt.executeUpdate();
			qstmt.close(); 
			con.close();
			Connection co=getConnection();
			sql="update Odd set state='2' where OddNumber='"+OddNumber+"'";
			PreparedStatement tstmt=co.prepareStatement(sql);
			re=tstmt.executeUpdate();
			tstmt.close(); 
			co.close();
			return re;
		}catch(SQLException ex){
			System.err.println(ex.getMessage());
		}			
		return 0;
	}

	//关闭数据库链接
	public void close() {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(stmt!=null) {
				stmt.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace(System.err);
		}
	}
}

