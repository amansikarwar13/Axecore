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

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;

public class ExampleTest {
	@Rule
	public TestName testName = new TestName();

	private WebDriver driver;

	private static final URL scriptUrl = ExampleTest.class.getResource("/axe.min.js");

	/**
	 * Instantiate the WebDriver and navigate to the test site
	 */
	@Before
	public void setUp() {
		// ChromeDriver needed to test for Shadow DOM testing support
		System.setProperty("webdriver.chrome.driver", "D:\\Personal_Project\\axe-selenium-java\\driver\\chrome-windows.exe");
		driver = new ChromeDriver();
	}

	/**
	 * Ensure we close the WebDriver after finishing
	 */
	@After
	public void tearDown() {
		driver.quit();
	}

	/**
	 * Basic test
	 */
	@Test
	public void testAccessibility() {
		String tagToValidate = "";
		boolean iFlag = false;
		driver.get("http://www.google.com");
		JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");
		
		System.out.println("Sort "+violations);
		
		//Validatin Starting for Input Field:
		String elmt = driver.findElement(By.xpath("//input[@name='q']")).getAttribute("name");
		tagToValidate = "name=\\" + "\"" + elmt + "\\"+"\"";	
		System.out.println("Tag to validate: "+tagToValidate);
		for(int i =0;i<violations.length();i++) {
			if(violations.get(i).toString().contains(tagToValidate)) {
				System.out.println("Violations present for Search Text: "+violations.get(i).toString());
				iFlag = true;
				break;
			}
		}
		
		if(iFlag == false) {
			System.out.println("No Violations present for Input Field for tag"+tagToValidate);
		}

		//Validatin Starting for Search Button
		iFlag = false;
		elmt = driver.findElement(By.xpath("(//input[@name='btnK'])[1]")).getAttribute("value");
		tagToValidate = "name=\\" + "\"" + elmt + "\\"+"\"";	
		System.out.println("Tag to validate: "+tagToValidate);
		for(int i =0;i<violations.length();i++) {
			if(violations.get(i).toString().contains(tagToValidate)) {
				System.out.println("Violations present for: "+violations.get(i).toString());
				iFlag = true;
				break;
			}
		}
		
		if(iFlag == false) {
			System.out.println("No Violations present for Button for tag"+tagToValidate);
			iFlag = true;
		}
	}
}