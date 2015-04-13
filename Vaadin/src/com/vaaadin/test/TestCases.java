package com.vaaadin.test;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.app.regex.RegexWrapper;

public class TestCases {
	
	@Test
	public void test(){
		Assert.assertTrue(case1());
	}
	
	private boolean case1(){
		RegexWrapper r1 = new RegexWrapper();
		String regex = r1.test("prd_rthapp11 %-5p %d{HH:mm:ss} %-20.20C{1}: %-20.20M: %-4L %m [%c]%n");
		Pattern p = Pattern.compile(regex);
		boolean b = p.matcher("prd_rthapp11 INFO 22:23:24 RegexTest           : main:  28 this is example test line [com.logix.logic.modules.log4jtest.RegexTest]\n").matches();
		return b;
	}
}
