package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_SaleView extends PO_NavView {

	static public void fillForm(WebDriver driver, String titlep, String descriptionp, String costp) {
		WebElement title = driver.findElement(By.name("titulo"));
		title.click();
		title.clear();
		title.sendKeys(titlep);

		WebElement description = driver.findElement(By.name("detalle"));
		description.click();
		description.clear();
		description.sendKeys(descriptionp);

		WebElement cost = driver.findElement(By.name("precio"));
		cost.click();
		cost.clear();
		cost.sendKeys(costp);

		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	public static void deleteSale(WebDriver driver, int index) {
		WebElement a = driver.findElement(By.xpath("//tr["+index+"]/td[4]/a[contains(@href, 'sale/delete')]"));
		a.click();
	}

	public static void search(WebDriver driver, String string) {
		WebElement title = driver.findElement(By.name("titleInputSearch"));
		title.click();
		title.clear();
		title.sendKeys(string);
		
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	public static void buy(WebDriver driver, int i) {
		WebElement a = driver.findElement(By.xpath("//tr["+i+"]/td[4]/a[contains(@href, 'sale/buy')]"));
		a.click();
	}

}
