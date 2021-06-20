package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_UsersView extends PO_NavView{
	
	static public void deleteUser(WebDriver driver, int index) {
		WebElement checkBox = driver.findElement(By.name("users["+index+"].selected"));
		checkBox.click();
		WebElement button = driver.findElement(By.name("bEliminar"));
		button.click();
		
		
	}

	public static void deleteUsers(WebDriver driver, int[] indexes) {
		
		for(int index:indexes) {
			WebElement checkBox = driver.findElement(By.name("users["+index+"].selected"));
			checkBox.click();
		}
		
		WebElement button = driver.findElement(By.name("bEliminar"));
		button.click();
		
	}

}
