package com.qlkTest.configuration;

public class Constants {
	//测试数据相关常亮设置
	public static final String Path_ExcelFile="";
	public static final String Path_ConfigurationFile="";
	
	//测试数据中的Sheet 中的列号常量设定
	//第一列用0表示，测试用例序号列
	public static final int Col_TestCaseID = 0;
	//第四列用 3 表示，关键字列
	public static final int Col_KewWordAction = 3;
	//第五列用 4 表示，操作元素的定位表达式列
	public static final int Col_LocatorExpression = 4;
	//第六列用 5 表示，操作值列
	public static final int Col_ActionValue = 5;
	//第七列用 6 表示，测试结果列
	public static final int Col_TestStepTestResult = 6;
	//测试集合 Sheet 中的列号常量设定
	public static final int Col_FunFlag = 2;
	//测试集合 Sheet 中的测试结果列号常量设定，测试用例执行结果列
	public static final int Col_TestSuiteTestResult = 3;
	//测试用例 Sheet 名称的常量设定
	public static final String Sheet_TestSteps = "发送邮件";
	//测试用例集 Sheet 的常量设定
	public static final String Sheet_TestSuite = "测试用例集合";
	
}
