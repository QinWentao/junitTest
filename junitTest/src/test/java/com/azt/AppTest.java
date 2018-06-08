package com.azt;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;


public class AppTest 
    extends TestCase
{
	@org.junit.Test
	public void testLengthOfTheUniqueKey() {
		App obj = new App();
		Assert.assertEquals(36, obj.generateUniqueKey().length());
	}
}
