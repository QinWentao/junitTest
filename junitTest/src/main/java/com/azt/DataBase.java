package com.azt;
import java.sql.*;

public class DataBase {
	//驱动
	static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";  
	//连接
	static final String DB_URL = "jdbc:oracle:thin:@192.168.0.131:1521:ORCL";
	//数据库用户名;
	static final String USER = "AZTSOFT";
	//密码
	static final String PASS = "AZTSOFT";
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	/*建立数据库连接
	 * */
	public void dbConnect() {
		System.out.println("Connecting to database...");
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);	
			System.out.println("Creating statement...");
		} catch (SQLException e) {
			System.out.println("Fail to connect DB!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to load driver!");
			e.printStackTrace();
		}
	}
	/*执行SQL
	 * */
	public ResultSet sqlExecute(String sql) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);	
		} catch (SQLException e) {
			System.out.println("Fail to execute sql!");
			e.printStackTrace();
		}
		return rs;
	}
	/*关闭数据库连接
	 * */
	public void closeConnect() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Fail to close connect!");
			e.printStackTrace();
		}		
	}
	/*获取工单号
	 * */
	public String getOrderID(String orderID) {
		try {
			System.out.println("Start to get OrderID...");
			DataBase db = new DataBase();
			db.dbConnect();
			String sql = "select id from SAFERYUSERSHYD1.SBT_DOCMAIN where ORDERID = '"+ orderID +"'";
			rs = db.sqlExecute(sql);
			while(rs.next()){
				  orderID  = rs.getString("ID");		         
			}
			db.closeConnect();
			System.out.println("Get orderID successfully!  ----->  " + orderID);
			}
		 catch (SQLException e) {
			System.out.println("Fail to get OrderID!");
			e.printStackTrace();
		 }
		
		return orderID;
	}
	
	
}
