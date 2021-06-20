package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_PrivateView extends PO_NavView {
	
	static public void fillFormAddOferta(WebDriver driver, String titulop, String detallep, String cantidadp) {
		WebElement titulo = driver.findElement(By.name("titulo"));
		titulo.clear();
		titulo.sendKeys(titulop);
		WebElement detalle = driver.findElement(By.name("detalle"));
		detalle.click();
		detalle.clear();
		detalle.sendKeys(detallep);
		WebElement cantidad = driver.findElement(By.name("precio"));
		cantidad.click();
		cantidad.clear();
		cantidad.sendKeys(cantidadp);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
