package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_UsersView extends PO_NavView{
	
	static public void deleteUser(WebDriver driver, int index) {
		WebElement checkBox = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[4]/input"));
		checkBox.click();
		By boton = By.className("btn");
		driver.findElement(boton).click();
		
		
	}

	public static void deleteUsers(WebDriver driver, int[] indexes) {
		
		for(int index:indexes) {
			WebElement checkBox = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[4]/input"));
			checkBox.click();
		}
		
		By boton = By.className("btn");
		driver.findElement(boton).click();
		
	}

}
