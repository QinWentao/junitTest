package com.azt;
import java.util.UUID;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("-------------------------------------------------------------------------------------");
    	System.out.println("                              Main program starts!");
    	System.out.println("-------------------------------------------------------------------------------------");
    	//App obj = new App();
        //System.out.println("Unique ID : " + obj.generateUniqueKey());
        DataBase obj = new DataBase();
        Interface jk = new Interface();
        try {
        	String orderID = obj.getOrderID("1528164313895");
        	
        	jk.firstAudit(orderID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public String generateUniqueKey(){    	
    	String id = UUID.randomUUID().toString();
    	return id;  	
    }
}
