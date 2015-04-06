package com.vaadin.app;

import java.util.regex.Pattern;

public class MainTest {

	public static void main(String[] args) {
//		  Pattern p = Pattern.compile("(?=\\w)(.* \\[.*)(?=\\w)(.*\\] .*)(?=.*(INFO|ERROR|WARNING|FATAL|OFF|DEBUG|TRACE|ALL).*)(?=\\w)(?=\\w)(.* - .*)(?=\\w)(.*\\n)+");
//		//boolean isMatch = p.matcher("0 [main] INFO com.logix.logic.modules.log4jtest.RegexTest  - this is example test line\n").matches();
//		  boolean isMatch = p.matcher("greg [main] INFO com.logix.logic.modules.log4jtest.RegexTest  - this is example test line\n").matches();
//		  System.out.println(isMatch);
		
		  Pattern p = Pattern.compile("(.*\\[.*)(?=.*(INFO|ERROR|WARNING|FATAL|OFF|DEBUG|TRACE|ALL).*)(.*\\] .*)(?=([0-2][0-9]):([0-5][0-9]|60):([0-5][0-9]|60),([0-9]{3}))(?=\\w)(.* - .*)(?=\\w)(.*\\n)+");
		//boolean isMatch = p.matcher("0 [main] INFO com.logix.logic.modules.log4jtest.RegexTest  - this is example test line\n").matches();
		  boolean isMatch = p.matcher("[INFO] 2015-04-02 23:12:53,856 com.logix.logic.modules.log4jtest.RegexTest - this is example test line\n").matches();
		  System.out.println(isMatch);
		
	}

}
