package com.testmile.trishanku;

import java.io.File;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.testmile.daksha.tpi.test.TestConfig;

public class Trishanku {
	
	public static void createLogger(String loggerName, String logFilePath) {
		Logger.getLogger(loggerName).getLoggerRepository().resetConfiguration();
		
		ConsoleAppender console = new ConsoleAppender(); // create appender
		FileAppender fa = new FileAppender();

		String PATTERN = null;
		PATTERN = "(%F:%L)\t%m%n";
		console.setLayout(new PatternLayout(PATTERN));
		console.setThreshold(Level.INFO);
		console.activateOptions();
		Logger.getLogger(loggerName).addAppender(console);

		fa.setName("FileLogger");
		fa.setFile(logFilePath);
		fa.setLayout(new PatternLayout("[%-5p]\t%d{yyyy-MM-dd|HH:mm:ss}\t(%F:%L)\t%m%n"));
		fa.setThreshold(Level.DEBUG);
		fa.setAppend(false);
		fa.activateOptions();
		Logger.getLogger(loggerName).addAppender(fa);
	}
}
