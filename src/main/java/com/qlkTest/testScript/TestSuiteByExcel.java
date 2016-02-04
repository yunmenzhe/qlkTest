package com.qlkTest.testScript;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.junit.BeforeClass;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

import com.qlkTest.configuration.*;


/**
 * @author kunbu@7lk.com
 * @version 创建时间：2016年2月4日 下午3:47:35
 */
public class TestSuiteByExcel {
	public static Method methond;
	public static String keyword;
	public static String locatorExpression;
	public static String value;
	public static KeyWordsAction keyWordsAction;
	public static int testStep;
	public static int testLastStep;
	public static String testCaseID;
	public static String testCaseRunFlag;
	public static boolean testResult;
}
