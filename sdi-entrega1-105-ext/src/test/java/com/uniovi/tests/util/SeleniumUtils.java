package com.uniovi.tests.util;

import java.util.List;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

	static public void textoPresentePagina(WebDriver driver, String texto) {
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + texto + "')]"));
		assertTrue("Texto " + texto + " no localizado!", list.size() > 0);
	}

	static public void textoNoPresentePagina(WebDriver driver, String texto) {
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + texto + "')]"));
		assertTrue("Texto " + texto + " aun presente !", list.size() == 0);
	}

	static public void EsperaCargaPaginaNoTexto(WebDriver driver, String texto, int timeout) {
		Boolean resultado = (new WebDriverWait(driver, timeout)).until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + texto + "')]")));

		assertTrue(resultado);
	}

	static public List<WebElement> EsperaCargaPaginaxpath(WebDriver driver, String xpath, int timeout) {
		WebElement resultado = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		assertTrue(resultado != null);
		List<WebElement> elementos = driver.findElements(By.xpath(xpath));

		return elementos;
	}

	static public List<WebElement> EsperaCargaPagina(WebDriver driver, String criterio, String text, int timeout) {
		String busqueda;
		if (criterio.equals("id"))
			busqueda = "//*[contains(@id,'" + text + "')]";
		else if (criterio.equals("class"))
			busqueda = "//*[contains(@class,'" + text + "')]";
		else if (criterio.equals("text"))
			busqueda = "//*[contains(text(),'" + text + "')]";
		else if (criterio.equals("free"))
			busqueda = text;
		else
			busqueda = "//*[contains(" + criterio + ",'" + text + "')]";

		return EsperaCargaPaginaxpath(driver, busqueda, timeout);
	}
}
