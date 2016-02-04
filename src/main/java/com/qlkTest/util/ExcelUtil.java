package com.qlkTest.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qlkTest.configuration.Constants;
import com.qlkTest.testScript.TestSuiteByExcel;

/**
 * @author kunbu@7lk.com
 * @version 创建时间：2016年2月4日 下午3:52:56 本类主要实现扩展名为.xlsx的Excel文件操作
 */
public class ExcelUtil {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;

	// 设定要操作的Excel的文件路径
	// 在读/写Excel文件的时候，需要先设定要操作的Excel文件路径
	public static void setExcelFile(String Path) {

		FileInputStream ExcelFile;
		try {
			// 实例化 Excel 文件的FileInputStream对象
			ExcelFile = new FileInputStream(Path);
			// 实例化 Excel 文件的 XSSFWorkbook 对象
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {
			TestSuiteByExcel.testResult = false;
			System.out.println("Excel 路径设定失败");
			e.printStackTrace();
		}
	}

	// 设定要操作的 Excel 的文件路径和 Excel 文件中的 Sheet 名称
	// 在读/写 Excel 文件的时候，设定要操作的 Excel 文件路径和要操作的 Sheet 名称
	public static void setExcelFile(String Path, String SheetName) {
		FileInputStream ExcelFile;
		try {
			// 实例化 Excel 文件的FileInputStream对象
			ExcelFile = new FileInputStream(Path);
			// 实例化 Excel 文件的 XSSFWorkbook 对象
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			/*
			 * 实例化 XSSFSheet 对象，指定 Excel 文件中的 Sheet 名称， 后续用于 Sheet 中行、列和单元格的操作
			 */
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			TestSuiteByExcel.testResult = false;
			System.out.println("Excel 路径设定失败");
		}
	}

	// 读取 Excel 文件指定单元格的函数，此函数只支持扩展名为 .xlsx 的 Excel文件
	public static String getCellData(String SheetName, int RowNum, int ColNum) {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try {
			// 通过函数指定单元格的行号和列号，获取指定的单元格对象
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			// 如果单元格的内容为字符串类型，则使用 getStringCellValue 方法获取单元格的内容
			// 如果单元格的内容为数字类型，则使用 getNumericCellValue 方法获取单元格的内容
			String CellData = Cell.getCellType() == XSSFCell.CELL_TYPE_STRING
					? Cell.getStringCellValue() + ""
					: String.valueOf(Math.round(Cell.getNumericCellValue()));
			// 函数返回指定单元格的字符串内容
			return CellData;
		} catch (Exception e) {
			TestSuiteByExcel.testResult = false;
			e.printStackTrace();
			// 读取遇到异常，则返回空字符串
			return "";
		}
	}

	// 获取 Excel 文件的最后一行的行号
	public static int getLastRowNum() {
		// 函数返回 Sheet 中最后一行的行号
		return ExcelWSheet.getLastRowNum();
	}

	// 获取指定 Sheet 中数据的总行数
	public static int getRowCount(String SheetName) {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int number = ExcelWSheet.getLastRowNum();
		return number;
	}

	// 在Excel 的指定Sheet 中，获取第一次包含指定测试用例序号文字的行号
	public static int getFirstRowContainTestCaseID(String sheetName,
			String testCaseName, int colNum) {
		int i;
		try {
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			int rowCount = ExcelUtil.getRowCount(sheetName);
			for (i = 0; i < rowCount; i++) {
				// 使用循环的方法遍历测试用例序号列的所有行，判断是否包含某个测试用例序号关键字
				if (ExcelUtil.getCellData(sheetName, i, colNum)
						.equalsIgnoreCase(testCaseName)) {
					// 如果包含，则退出For 循环，并返回包含测试用例序号关键字的行号
					break;
				}
			}
			return i;
		} catch (Exception e) {
			TestSuiteByExcel.testResult = false;
			return 0;
		}
	}

	public static int getTestCaseLastStepRow(String sheetName,
			String testCaseID, int testCaseStartRowNumber) {
		try {
			ExcelWSheet = ExcelWBook.getSheet(sheetName);

			/*
			 * 从包含指定测试用例序号的第一行开始逐行遍历，知道某一行不出现指定测试用例序号， 此时的遍历次数就是此测试用例步骤的个数
			 */
			for (int i = testCaseStartRowNumber; i <= ExcelUtil
					.getRowCount(sheetName) - 1; i++) {
				if (!testCaseID.equals(ExcelUtil.getCellData(sheetName, i,
						Constants.Col_TestCaseID))) {
					int number = i;
					return number;
				}
			}
			int number = ExcelWSheet.getLastRowNum() + 1;
			return number;
		} catch (Exception e) {
			TestSuiteByExcel.testResult = false;
			return 0;
		}
	}

	public static void setCellData(String sheetName, int rowNum, int colNum,String result){
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		try{
			//获取 Excel文件中的行对象
			Row = ExcelWSheet.getRow(rowNum);
			//如果单元格为空，则返回 null
			Cell = Row.getCell(colNum, Row.RETURN_BLANK_AS_NULL);
			
			if(Cell ==null){
				//当单元格对象是 null 的时候，则创建单元格
				//如果单元格为空，无法直接调用单元格对象的setCellValue 方法设定单元格的值
				Cell = Row.createCell(colNum);
				//创建单元格后可以调用单元格对象的setCellValue 方法设定单元格的值
				Cell.setCellValue(result);
			}else {
				//单元格中有内容，则可以直接调用单元格对象的setCellValue 方法设定单元格的值
				Cell.setCellValue(result);
			}
			//实例化写入Excel 文件的文件输出流对象
			FileOutputStream fileOut = new FileOutputStream(Constants.Path_ExcelFile);
			//将内容写入Excel 文件中
			ExcelWBook.write(fileOut);
			//调用flush 方法强制刷新写入文件
			fileOut.flush();
			//关闭文件输出流对象
			fileOut.close();
		} catch (Exception e){
			TestSuiteByExcel.testResult = false;
			e.printStackTrace();
		}
		
	}
}
