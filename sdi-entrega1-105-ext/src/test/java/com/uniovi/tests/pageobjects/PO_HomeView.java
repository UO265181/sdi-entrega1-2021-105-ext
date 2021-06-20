package com.uniovi.tests.pageobjects;

import org.openqa.selenium.WebDriver;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_HomeView extends PO_NavView {
	
	static public void checkWelcome(WebDriver driver, int language) {

		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", language), getTimeout());
	}
}
