package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	
	static public void fillFormAddOferta(WebDriver driver, String titulop, String detallep, String cantidadp) {
		SeleniumUtils.esperarSegundos(driver, 5);
		WebElement titulo = driver.findElement(By.name("titulo"));
		titulo.clear();
		titulo.sendKeys(titulop);
		WebElement detalle = driver.findElement(By.name("detalle"));
		detalle.click();
		detalle.clear();
		detalle.sendKeys(detallep);
		WebElement cantidad = driver.findElement(By.name("cantidad"));
		cantidad.click();
		cantidad.clear();
		cantidad.sendKeys(cantidadp);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
