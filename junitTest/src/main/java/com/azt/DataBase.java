package com.azt;
import java.sql.*;

public class DataBase {
	//����
	static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";  
	//����
	static final String DB_URL = "jdbc:oracle:thin:@192.168.0.131:1521:ORCL";
	//���ݿ��û���;
	static final String USER = "AZTSOFT";
	//����
	static final String PASS = "AZTSOFT";
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	/*�������ݿ�����
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
	/*ִ��SQL
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
	/*�ر����ݿ�����
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
	/*��ȡ������
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
