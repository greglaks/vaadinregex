package com.vaaadin.test;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.app.regex.RegexWrapper;

public class TestCases {
	
	@Test
	public void test(){
		Assert.assertTrue(case1());
		Assert.assertTrue(case2());
		Assert.assertFalse(case3());
		Assert.assertTrue(case4());
		Assert.assertTrue(case5());
		Assert.assertTrue(case6());
	}
	
	private boolean case1(){
		RegexWrapper r1 = new RegexWrapper();
		String regex = r1.test("prd_rthapp11 %-5p %d{HH:mm:ss} %-20.20C{1}: %-20.20M: %-4L %m [%c]%n");
		Pattern p = Pattern.compile(regex);
		boolean b = p.matcher("prd_rthapp11 INFO 22:23:24 RegexTest           : main:  28 this is example test line [com.logix.logic.modules.log4jtest.RegexTest]\n").matches();
		return b;
	}
	
	private boolean case2(){
		RegexWrapper r1 = new RegexWrapper();
		String regex = r1.test("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
		Pattern p = Pattern.compile(regex);
		boolean b = p.matcher("2015-04-01 00:03:35 INFO RegexTest:20 - this is example test line\n").matches();
		return b;
	}
	
	private boolean case3(){
		RegexWrapper r1 = new RegexWrapper();
		String regex = r1.test("%r [%t] %p %c %x - %m%n");
		Pattern p = Pattern.compile(regex);
		boolean b = p.matcher("mm0 [main] INFO com.logix.logic.modules.log4j.RegexTest - this is example test line\n").matches();
		return b;
	}
	
	private boolean case4(){
		RegexWrapper r1 = new RegexWrapper();
		String regex = r1.test("%r [%t] %p %c %x - %m%n");
		Pattern p = Pattern.compile(regex);
		boolean b = p.matcher("0 [main] INFO com.logix.logic.modules.log4j.RegexTest - this is example test line\n").matches();
		return b;
	}
	
	private boolean case5(){
		RegexWrapper r1 = new RegexWrapper();
		String regex = r1.test("%d{yyyy-MM-dd HH:mm:ss,SSS} %-5p [%c] (%t) %n");
		Pattern p = Pattern.compile(regex);
		boolean b = p.matcher("2015-04-02 23:06:16,393 INFO [com.logix.logic.modules.log4jtest.RegexTest] (main) \n").matches();
		return b;
	}
	
	private boolean case6(){
		RegexWrapper r1 = new RegexWrapper();
		String regex = r1.test("[%-5p] %d %c - %m%n");
		Pattern p = Pattern.compile(regex);
		boolean b = p.matcher("[INFO] 2015-04-02 23:12:53,856 com.logix.logic.modules.log4jtest.RegexTest - This is example test line\n").matches();
		return b;
	}

}
