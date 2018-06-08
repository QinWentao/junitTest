package com.azt;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;


public class AppTest 
    extends TestCase
{
	@org.junit.Test
	public void testLengthOfTheUniqueKey() {
		App obj = new App();
		Assert.assertEquals(36, obj.generateUniqueKey().length());
	}
	@org.junit.Test
	public void testFirstAuditPass() {
		String result = "";
    	String real = "";
    	DataBase db = new DataBase();
        Interface jh = new Interface();
        try {
    		String orderID = db.getOrderID("1528435839396");
    		result = jh.firstAudit(orderID);
    		Pattern pattern = Pattern.compile("[0-9]");  
    		Matcher matcher = pattern.matcher(result);  
		    while(matcher.find()){  
		    	real = matcher.group();
		    }
		    Assert.assertEquals("0", real);
		} catch (Exception e) {
			System.out.println("“ªº∂ª¸∫À≤‚ ‘ ß∞‹");
			e.printStackTrace();
		}
	}
}
