/**
 * Copyright (C) 2015 Deque Systems Inc.,
 *
 * Your use of this Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * This entire copyright notice must appear in every copy of this file you
 * distribute or in any file that contains substantial portions of this source
 * code.
 */

package com.deque.axe;
import org.json.JSONArray;
import org.json.JSONObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

public class Lightening {
	
//	public TestName testName = new TestName();

	private WebDriver driver;

	private static final URL scriptUrl = Lightening.class.getResource("/axe.min.js");

	/**
	 * Instantiate the WebDriver and navigate to the test site
	 */
	@BeforeMethod
	public void setUp() {
		// ChromeDriver needed to test for Shadow DOM testing support
		System.setProperty("webdriver.chrome.driver",
				"D:\\Personal_Project\\axe-selenium-java\\driver\\chrome-windows.exe");
		driver = new ChromeDriver();
	}

	/**
	 * Ensure we close the WebDriver after finishing
	 */
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	/**
	 * Basic test
	 * 
	 * @throws InterruptedException
	 */
	//@Test
	public void testAccessibility() throws InterruptedException {
		String tagToValidate = "";
		boolean iFlag = false;
		try {

			driver.get("https://allegisgroup--junedev20.my.salesforce.com/");
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("asikarwar@teksystems.com.augtest20");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Privacy@123");
			driver.findElement(By.xpath("//input[@id='Login']")).click();

			Thread.sleep(30000);
			WebElement elem = driver.findElement(By.xpath("//a[span[text()='Contacts']]"));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", elem);

			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[text()='CSC Dev Talent 4']")).click();
			Thread.sleep(5000);
			
			JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();
			JSONArray violations = responseJSON.getJSONArray("violations");

			System.out.println("Violations present on Lightening Page are " + violations);

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}

	// Validating on My Acitivities:
	@Test
	public void testLWC() throws InterruptedException {
		String tagToValidate = "";
		boolean iFlag = false;
		try {

			driver.get("https://allegisgroup--junedev20.my.salesforce.com/");
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("asikarwar@teksystems.com.junedev20");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Privacy@123");
			driver.findElement(By.xpath("//input[@id='Login']")).click();

			Thread.sleep(30000);
			WebElement elem1 = driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", elem1);
			
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@type='search' and @class='slds-input']")).sendKeys("SD");
			driver.findElement(By.xpath("//b[text()='SD']")).click();

			Thread.sleep(3000);
			WebElement elem = driver.findElement(By.xpath("//a[span[text()='My Activities']]"));
//			JavascriptExecutor js = (JavascriptExecutor) driver;
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", elem);
			Thread.sleep(3000);
			Thread.sleep(5000);
			JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();
			JSONArray violations = responseJSON.getJSONArray("violations");

			System.out.println("Violations present on LWC Page are" + violations);
			


			/*
			 * //Validatin Starting for Search Button iFlag = false; String elmt =
			 * driver.findElement(By.xpath("//input[@name='ActivityType']")).getAttribute(
			 * "value"); tagToValidate = "name=\\" + "\"" + elmt + "\\"+"\""; for(int i
			 * =0;i<violations.length();i++) {
			 * if(violations.get(i).toString().contains(tagToValidate)) {
			 * System.out.println("Violations present for: "+violations.get(i).toString());
			 * iFlag = true; break; } }
			 * 
			 * if(iFlag == false) {
			 * System.out.println("No Violations present for Button for tag"+tagToValidate);
			 * iFlag = true; }
			 */
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}
}