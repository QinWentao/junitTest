package com.azt;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("-------------------------------------------------------------------------------------");
    	System.out.println("                              Test program starts!");
    	System.out.println("-------------------------------------------------------------------------------------");
    	App obj = new App();
    	File file = new File();
        //System.out.println("Unique ID : " + obj.generateUniqueKey());
    	//System.out.println("一级稽核通过响应： " + obj.firstAuditPass());
    	file.readExcel("E:\\安真通\\x.xls",0,0);
    }
    
    public String generateUniqueKey(){    	
    	String id = UUID.randomUUID().toString();
    	return id;  	
    }
    public String firstAuditPass() {
    	String result = "";
    	String real = "";
    	DataBase db = new DataBase();
        Interface jh = new Interface();
        try {
    		String orderID = db.getOrderID("1528435872419");
    		result = jh.firstAudit(orderID);
    		Pattern pattern = Pattern.compile("[0-9]");  
    		Matcher matcher = pattern.matcher(result);  
		    while(matcher.find()){  
		    	real = matcher.group();
		    }
		    if(real.equals("0")) {
		    	System.out.println("一级稽核测试通过");
		    }
		} catch (Exception e) {
			System.out.println("一级稽核测试失败");
			e.printStackTrace();
		}
        return  result;
    }
}
