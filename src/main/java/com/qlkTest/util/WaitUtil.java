package com.qlkTest.util;

import io.appium.java_client.android.AndroidDriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author kunbu@7lk.com
 * @version 创建时间：2016年2月4日 下午5:21:15 等待
 */
public class WaitUtil {

	private static AndroidDriver<WebElement> driver;

	// 用于测试执行过程中暂停程序执行的休眠方法
	public static void sleep(long millisecond) {
		try {
			// 线程休眠 millisecond 参数定义的毫秒数
			Thread.sleep(millisecond);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void implicitlyWait(long millisecond) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
	}

	public static void waitElement(final String idExpression) {
		new AndroidDriverWait(driver, 60)
				.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(AndroidDriver d) {
						return d.findElement(By.id(idExpression));
					}
				});
	}

}
