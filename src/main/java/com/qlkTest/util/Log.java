package com.qlkTest.util;

import org.apache.log4j.Logger;

/**
 * @author kunbu@7lk.com
 * @version 创建时间：2016年2月4日 下午5:06:23 log
 */
public class Log {
	private static Logger Log = Logger.getLogger(Log.class.getName());

	// 定义测试用例开始执行的打印方法，在日志中打印测试用例开始执行的信息
	public static void startTestCase(String testCaseName) {
		Log.info("-----------------              \"" + testCaseName
				+ "\"开始执行        -----------------");
	}

	// 定义测试用例执行完毕后的打印方法，在日志中打印测试用例执行完毕的信息
	public static void endTestCase(String testCaseName) {
		Log.info("-----------------              \"" + testCaseName
				+ "\"测试执行结束        -----------------");
	}
	
	//定义打印  info 级别日志的方法
	public static void info(String message){
		Log.info(message);
	}
	
	//定义打印 error 级别日志的方法
	public static void error(String message){
		Log.error(message);
	}
	
	//定义打印 debug 级别日志的方法
	public static void debug(String message){
		Log.debug(message);
	}
}
