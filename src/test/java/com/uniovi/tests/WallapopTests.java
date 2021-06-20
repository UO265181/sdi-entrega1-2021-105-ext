package com.uniovi.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_UsersView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WallapopTests {

	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "E:\\xurde\\Clase\\3\\SDI\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	
	static String Geckdriver024_Alberto = "D:\\Temporal\\_CLASE\\SDI\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8080";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}
	
	@Test
	public void PR01() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "uwu@gmail.com", "Juan", "Perez", "77777", "77777");
		PO_View.checkElement(driver, "text", "uwu@gmail.com");
	}
	
	@Test
	public void PR02() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "", "", "", "77777", "77777");
		PO_View.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
	}
	
	@Test
	public void PR03() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "ewe@gmail.com", "Juan", "Perez", "77777", "77778");
		PO_View.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
	}
	
	@Test
	public void PR04() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "uwu@gmail.com", "Juan", "Perez", "77777", "77777");
		PO_View.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());
	}
	
	@Test
	public void PR05() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.checkElement(driver, "text", "admin@email.com");
	}
	
	@Test
	public void PR06() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "uwu@gmail.com", "77777");
		PO_View.checkElement(driver, "text", "uwu@gmail.com");
	}
	
	@Test
	public void PR07() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "", "");
		PO_View.checkKey(driver, "Error.signup.email.wrong", PO_Properties.getSPANISH());
	}
	
	@Test
	public void PR08() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "uwu@gmail.com", "77778");
		PO_View.checkKey(driver, "Error.login.password.wrong", PO_Properties.getSPANISH());
	}
	
	@Test
	public void PR09() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "ewe@gmail.com", "77777");
		PO_View.checkKey(driver, "Error.signup.email.wrong", PO_Properties.getSPANISH());
	}
	
	@Test
	public void PR10() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "uwu@gmail.com", "77777");
		PO_View.checkElement(driver, "text", "uwu@gmail.com");
		PO_HomeView.clickOption(driver, "logout", "class", "btn-primary");
		PO_View.checkElement(driver, "text", "Identifícate");
	}
	
	@Test
	public void PR11() {
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Desconectar", PO_View.getTimeout());
	}
	
	@Test
	public void PR12() {
		
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "uw1@gmail.com", "Juan", "Perez", "77777", "77777");
		driver.manage().deleteAllCookies();
		driver.navigate().to(URL);
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "uw2@gmail.com", "Juan", "Perez", "77777", "77777");
		driver.manage().deleteAllCookies();
		driver.navigate().to(URL);
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "uw3@gmail.com", "Juan", "Perez", "77777", "77777");
		driver.manage().deleteAllCookies();
		driver.navigate().to(URL);
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "uw4@gmail.com", "Juan", "Perez", "77777", "77777");
		driver.manage().deleteAllCookies();
		driver.navigate().to(URL);
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "uw5@gmail.com", "Juan", "Perez", "77777", "77777");
		driver.manage().deleteAllCookies();
		driver.navigate().to(URL);
		
		
		
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_HomeView.clickOption(driver, "user/list", "class", "btn btn-danger");
		PO_View.checkElement(driver, "text", "Lolas132@yahoo.es");
		PO_View.checkElement(driver, "text", "M4ras@gmail.com");
		PO_View.checkElement(driver, "text", "12_asMar@gmail.com");
		PO_View.checkElement(driver, "text", "uwu@gmail.com");
		PO_View.checkElement(driver, "text", "uw1@gmail.com");
		PO_View.checkElement(driver, "text", "uw2@gmail.com");
		PO_View.checkElement(driver, "text", "uw3@gmail.com");
		PO_View.checkElement(driver, "text", "uw4@gmail.com");
		PO_View.checkElement(driver, "text", "uw5@gmail.com");
	}
	
	@Test
	public void PR13() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_HomeView.clickOption(driver, "user/list", "class", "btn btn-danger");
		PO_UsersView.deleteUser(driver, 0);
		SeleniumUtils.textoNoPresentePagina(driver, "Lolas132@yahoo.es");
		PO_View.checkElement(driver, "text", "M4ras@gmail.com");
		PO_View.checkElement(driver, "text", "12_asMar@gmail.com");
		PO_View.checkElement(driver, "text", "uwu@gmail.com");
		PO_View.checkElement(driver, "text", "uw1@gmail.com");
		PO_View.checkElement(driver, "text", "uw2@gmail.com");
		PO_View.checkElement(driver, "text", "uw3@gmail.com");
		PO_View.checkElement(driver, "text", "uw4@gmail.com");
		PO_View.checkElement(driver, "text", "uw5@gmail.com");
	}
	
	
	@Test
	public void PR14() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_HomeView.clickOption(driver, "user/list", "class", "btn btn-danger");
		PO_UsersView.deleteUser(driver, 7);
		SeleniumUtils.textoNoPresentePagina(driver, "uw5@gmail.com");
		PO_View.checkElement(driver, "text", "M4ras@gmail.com");
		PO_View.checkElement(driver, "text", "12_asMar@gmail.com");
		PO_View.checkElement(driver, "text", "uwu@gmail.com");
		PO_View.checkElement(driver, "text", "uw1@gmail.com");
		PO_View.checkElement(driver, "text", "uw2@gmail.com");
		PO_View.checkElement(driver, "text", "uw3@gmail.com");
		PO_View.checkElement(driver, "text", "uw4@gmail.com");
	}
	
	
	@Test
	public void PR15() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		
		PO_HomeView.clickOption(driver, "user/list", "class", "btn btn-danger");
		
		int[] indexes = {3,4,5};
		
		PO_UsersView.deleteUsers(driver, indexes);
		SeleniumUtils.textoNoPresentePagina(driver, "uw1@gmail.com");
		SeleniumUtils.textoNoPresentePagina(driver, "uw2@gmail.com");
		SeleniumUtils.textoNoPresentePagina(driver, "uw3@gmail.com");
		PO_View.checkElement(driver, "text", "uw4@gmail.com");
		PO_View.checkElement(driver, "text", "M4ras@gmail.com");
		PO_View.checkElement(driver, "text", "12_asMar@gmail.com");
		
		
		
	}
	
	
	
	
	
	@Test
	public void PR16() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "uwu@gmail.com", "77777");
		PO_View.checkElement(driver, "text", "uwu@gmail.com");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'ofertas-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/add')]");
		elementos.get(0).click();
		PO_PrivateView.fillFormAddOferta(driver, "titulo1", "detalle1", "8");
		elementos = PO_View.checkElement(driver, "text", "titulo1");
	}
	
	@Test
	public void PR17() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "uwu@gmail.com", "77777");
		PO_View.checkElement(driver, "text", "uwu@gmail.com");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'ofertas-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/add')]");
		elementos.get(0).click();
		PO_PrivateView.fillFormAddOferta(driver, "", "detalle1", "8");
		PO_View.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
	}
	
	@Test
	public void PR18() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "uwu@gmail.com", "77777");
		PO_View.checkElement(driver, "text", "uwu@gmail.com");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'ofertas-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/add')]");
		elementos.get(0).click();
		PO_PrivateView.fillFormAddOferta(driver, "titulo2", "detalle2", "8");
		elementos = PO_View.checkElement(driver, "text", "titulo1");
		elementos = PO_View.checkElement(driver, "text", "titulo2");
	}
	
	@Test
	public void PR19() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "uwu@gmail.com", "77777");
		PO_View.checkElement(driver, "text", "uwu@gmail.com");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'ofertas-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/list')]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), 'titulo1')]/following-sibling::*/a[contains(@href, 'oferta/delete')]");
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "titulo1", PO_View.getTimeout());
	}
	
	@Test
	public void PR20() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "uwu@gmail.com", "77777");
		PO_View.checkElement(driver, "text", "uwu@gmail.com");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'ofertas-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'oferta/list')]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), 'titulo2')]/following-sibling::*/a[contains(@href, 'oferta/delete')]");
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "titulo2", PO_View.getTimeout());
	}

}
