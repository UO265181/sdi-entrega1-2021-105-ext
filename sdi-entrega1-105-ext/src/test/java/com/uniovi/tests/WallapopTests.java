package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

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

import com.uniovi.entities.Sale;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_SaleView;
import com.uniovi.tests.pageobjects.PO_UsersView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

@FixMethodOrder(MethodSorters.DEFAULT)
public class WallapopTests {

	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";

	static String Geckdriver024 = "D:\\Temporal\\_CLASE\\SDI\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	static public void begin() {
	}

	@AfterClass
	static public void end() {
		driver.quit();
	}

	// PR01. Registro de Usuario con datos válidos
	@Test
	public void PR01() {
		// Navegamos al registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

		// Introducimos datos válidos
		PO_RegisterView.fillForm(driver, "mrSaladFingers@rusty.com", "MrSalad", "Fingers", "123", "123");

		// Compruebo que aparece la página de inicio con el nombre y el saldo del nuevo
		// usuario
		PO_View.checkKey(driver, "inicio.titulo", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "inicio.bienvenido", PO_Properties.getSPANISH());
		PO_View.checkElement(driver, "text", "100.0");
		PO_View.checkElement(driver, "text", "mrSaladFingers@rusty.com");

	}

	// PR02. Registro de Usuario con datos inválidos (email vacío, nombre vacío,
	// apellidos vacíos)
	@Test
	public void PR02() {
		// Navegamos al registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

		// Introducimos datos inválidos
		PO_RegisterView.fillForm(driver, "", "", "", "123", "123");

		// Comprobamos que aparece el error
		PO_RegisterView.checkKey(driver, "Error.vacio", PO_Properties.getSPANISH());

	}

	// PR03.Registro de Usuario con datos inválidos (repetición de contraseña
	// inválida).
	@Test
	public void PR03() {
		// Navegamos al registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

		// Introducimos datos inválidos
		PO_RegisterView.fillForm(driver, "jcDenton@unatco.ilu", "JC", "Denton", "123", "321");

		// Comprobamos que aparece el error
		PO_RegisterView.checkKey(driver, "Error.signup.password2", PO_Properties.getSPANISH());
	}

	// PR04.Registro de Usuario con datos inválidos (email existente).
	@Test
	public void PR04() {
		// Navegamos al registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");

		// Introducimos datos inválidos
		PO_RegisterView.fillForm(driver, "mrSaladFingers@rusty.com", "Yvonne", "Fingers", "123", "123");

		// Comprobamos que aparece el error
		PO_RegisterView.checkKey(driver, "Error.signup.email.repetido", PO_Properties.getSPANISH());
	}

	// PR05. Inicio de sesión con datos válidos (administrador).
	@Test
	public void PR05() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos datos del admin
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");

		// Comprobamos que hemos iniciado sesión
		PO_View.checkKey(driver, "inicio.titulo", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "inicio.bienvenido", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "nav.usuarios", PO_Properties.getSPANISH());
		PO_View.checkElement(driver, "text", "admin@email.com");

	}

	// PR06.Inicio de sesión con datos válidos (usuario estándar).
	@Test
	public void PR06() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos datos de usuario
		PO_LoginView.fillForm(driver, "mrSaladFingers@rusty.com", "123");

		// Comprobamos que hemos iniciado sesión
		PO_View.checkKey(driver, "inicio.titulo", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "inicio.bienvenido", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "nav.ofertas", PO_Properties.getSPANISH());
		PO_View.checkElement(driver, "text", "mrSaladFingers@rusty.com");

	}

	// PR07.Inicio de sesión con datos inválidos (usuario estándar, campo email y
	// contraseña vacíos).
	@Test
	public void PR07() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos datos inválidos
		PO_LoginView.fillForm(driver, "", "");

		// Comprobamos que aparece el error
		PO_LoginView.checkKey(driver, "Error.vacio", PO_Properties.getSPANISH());
	}

	// PR08. Inicio de sesión con datos válidos (usuario estándar, email existente,
	// pero contraseña incorrecta).
	@Test
	public void PR08() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos datos inválidos
		PO_LoginView.fillForm(driver, "mrSaladFingers@rusty.com", "321");

		// Comprobamos que aparece el error
		PO_LoginView.checkKey(driver, "Error.login.contrasena", PO_Properties.getSPANISH());
	}

	// PR09. Inicio de sesión con datos inválidos (usuario estándar, email no
	// existente en la aplicación)
	@Test
	public void PR09() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos datos inválidos
		PO_LoginView.fillForm(driver, "404@noExist.ence", "123");

		// Comprobamos que aparece el error
		PO_LoginView.checkKey(driver, "Error.login.email", PO_Properties.getSPANISH());
	}

	// PR12. Hacer click en la opción de salir de sesión y comprobar que se redirige
	// a la página de inicio de sesión (Login).
	@Test
	public void PR10() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos datos válidos
		PO_LoginView.fillForm(driver, "mrSaladFingers@rusty.com", "123");

		// Salimos de sesión
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		// Comrobamos que hemos sido redirigidos al login
		PO_LoginView.checkKey(driver, "login.titulo", PO_Properties.getSPANISH());
		PO_LoginView.checkKey(driver, "login.email", PO_Properties.getSPANISH());
		PO_LoginView.checkKey(driver, "login.contrasena", PO_Properties.getSPANISH());
		PO_LoginView.checkKey(driver, "login.aceptar", PO_Properties.getSPANISH());
	}

	// PR11.Comprobar que el botón cerrar sesión no está visible si el usuario no
	// está autenticado.
	@Test
	public void PR11() {
		// Comprobamos que no está
		PO_View.checkNotElement(driver, "Salir");
	}

	// PR12. HMostrar el listado de usuarios y comprobar que se muestran to dos los
	// que existen en el sistema.
	@Test
	public void PR12() {

		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos datos del admin
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-user')]/a");
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "user/list", "class", "btn btn-primary");

		// Comprobamos que estamos
		PO_LoginView.checkKey(driver, "usuarios.titulo", PO_Properties.getSPANISH());

		// Comprobamos los usuarios
		PO_UsersView.checkElement(driver, "text", "mrSaladFingers@rusty.com");
		PO_UsersView.checkElement(driver, "text", "Lolas132@yahoo.es");
		PO_UsersView.checkElement(driver, "text", "M4ras@gmail.com");
		PO_UsersView.checkElement(driver, "text", "12_asMar@gmail.com");
		PO_UsersView.checkElement(driver, "text", "Jaime@gmail.com");
		PO_UsersView.checkElement(driver, "text", "Juan@gmail.com");
		PO_UsersView.checkElement(driver, "text", "Pepe@gmail.com");
		PO_UsersView.checkElement(driver, "text", "Ramona@gmail.com");
		PO_UsersView.checkElement(driver, "text", "Estrella@gmail.com");
	}

	// PR13. Ir a la lista de usuarios, borrar el primer usuario de la lista,
	// comprobar que la lista se actualiza y que el usuario desaparece
	@Test
	public void PR13() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos datos del admin
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-user')]/a");
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "user/list", "class", "btn btn-primary");

		// Comprobamos que estamos
		PO_LoginView.checkKey(driver, "usuarios.titulo", PO_Properties.getSPANISH());

		// Borramos el usuario de la primera fila
		PO_UsersView.deleteUser(driver, 1);

		// Comprobamos que se ha eliminado
		PO_UsersView.checkNotElement(driver, "Lolas132@yahoo.es");

	}

	// PR14.Ir a la lista de usuarios, borrar el último usuario de la lista,
	// comprobar que la lista se actualiza y que el usuario desaparece
	@Test
	public void PR14() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos datos del admin
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-user')]/a");
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "user/list", "class", "btn btn-primary");

		// Comprobamos que estamos
		PO_LoginView.checkKey(driver, "usuarios.titulo", PO_Properties.getSPANISH());

		// Borramos el usuario de la ultima fila
		PO_UsersView.deleteUser(driver, 8);

		// Comprobamos que se ha eliminado
		PO_UsersView.checkNotElement(driver, "mrSaladFingers@rusty.com");

	}

	// PR15.Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la lista se
	// actualiza y que losusuarios desaparecen.
	@Test
	public void PR15() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos datos del admin
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-user')]/a");
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "user/list", "class", "btn btn-primary");

		// Comprobamos que estamos
		PO_LoginView.checkKey(driver, "usuarios.titulo", PO_Properties.getSPANISH());

		// Borramos los tres últimos
		int indexes[] = { 7, 6, 5 };
		PO_UsersView.deleteUsers(driver, indexes);

		// Comprobamos que se ha eliminado
		PO_UsersView.checkNotElement(driver, "Estrella@gmail.com");
		PO_UsersView.checkNotElement(driver, "Ramona@gmail.com");
		PO_UsersView.checkNotElement(driver, "Pepe@gmail.com");
	}

	// PR16. Ir al formulario de alta de oferta, rellenarla con datos validos y
	// comprobar tras su envio que la oferta sale en el listado del propio usuario
	@Test
	public void PR16() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos dato de usuario
		PO_LoginView.fillForm(driver, "Jaime@gmail.com", "123456");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-sale')]/a");
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "sale/add", "class", "btn btn-primary");

		// Formulario con datos con datos validos
		PO_SaleView.fillForm(driver, "Disco de OSI", "Free", "15,99");

		// Comprobamos que estamos
		PO_SaleView.checkKey(driver, "ofertas.titulo", PO_Properties.getSPANISH());

		// Comprobamos que esta
		PO_SaleView.checkElement(driver, "text", "Disco de OSI");
	}

	// PR17.Ir al formulario de alta de oferta, rellenarla con datos inválidos
	// (campo título vacío) y pulsar
	// el botón Submit. Comprobar que se muestra el mensaje de campo obligatorio.

	@Test
	public void PR17() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos dato de usuario
		PO_LoginView.fillForm(driver, "Jaime@gmail.com", "123456");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-sale')]/a");
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "sale/add", "class", "btn btn-primary");

		// Formulario con datos vacios
		PO_SaleView.fillForm(driver, "", "", "15,99");

		// Comprobamos que sale el error
		PO_SaleView.checkKey(driver, "Error.vacio", PO_Properties.getSPANISH());

		// Formulario con precio invalido
		PO_SaleView.fillForm(driver, "Messhugga", "Bleed", "-25");

		// Comprobamos que sale el error
		PO_SaleView.checkKey(driver, "Error.precio", PO_Properties.getSPANISH());

	}

	// PR18. Mostrar el listado de ofertas para dicho usuario y comprobar que se
	// muestran todas las ofertas que existen
	@Test
	public void PR18() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos dato de usuario
		PO_LoginView.fillForm(driver, "Jaime@gmail.com", "123456");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-sale')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'sale/list')]");
		elementos.get(0).click();

		// Comprobamos que estamos
		PO_SaleView.checkKey(driver, "ofertas.titulo", PO_Properties.getSPANISH());

		// Comprobamos que esta su oferta
		PO_SaleView.checkElement(driver, "text", "Disco de OSI");
		PO_SaleView.checkElement(driver, "text", "Free");
		PO_SaleView.checkElement(driver, "text", "15.99");
	}

	// PR19. Ir a la lista de ofertas, borrar la primera oferta de la lista,
	// comprobar que la lista se actualiza y
	// que la oferta desaparece.
	@Test
	public void PR19() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos dato de usuario
		PO_LoginView.fillForm(driver, "Jaime@gmail.com", "123456");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-sale')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'sale/list')]");
		elementos.get(0).click();

		// Eliminamos la oferta
		PO_SaleView.deleteSale(driver, 1);

		// Comprobamos que desaparece
		PO_SaleView.checkNotElement(driver, "Disco de OSI");
		PO_SaleView.checkNotElement(driver, "Free");
		PO_SaleView.checkNotElement(driver, "15,99");
	}

	// PR20. Ir a la lista de ofertas, borrar la última oferta de la lista,
	// comprobar que la lista se actualiza y
	// que la oferta desaparece.
	@Test
	public void PR20() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos dato de usuario
		PO_LoginView.fillForm(driver, "M4ras@gmail.com", "123456");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-sale')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'sale/list')]");
		elementos.get(0).click();

		// Eliminamos la oferta
		PO_SaleView.deleteSale(driver, 1);

		// Comprobamos que desaparece
		PO_SaleView.checkNotElement(driver, "Libro de Sintaxis");
		PO_SaleView.checkNotElement(driver, "Nivel 4ºESO");
		PO_SaleView.checkNotElement(driver, "22.11");
	}

	// PR21. Hacer una búsqueda con el campo vacío y comprobar que se muestra la
	// página que corresponde con el listado de las ofertas existentes en el sistema
	@Test
	public void PR21() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos dato de usuario
		PO_LoginView.fillForm(driver, "M4ras@gmail.com", "123456");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-sale')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'sale/search')]");
		elementos.get(0).click();

		// Buscamos con vacio
		PO_SaleView.search(driver, "");

		// Comprobamos que esten todas
		PO_SaleView.checkElement(driver, "text", "Dark Souls 3");
		PO_SaleView.checkElement(driver, "text", "Dishonored");
		PO_SaleView.checkElement(driver, "text", "Deus Ex");
		PO_SaleView.checkElement(driver, "text", "El Quijote");
		PO_SaleView.checkElement(driver, "text", "MGS3 Snake Eater");

		// Siguiente página
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '?page=" + 1 + "')]");
		elementos.get(0).click();

		PO_SaleView.checkElement(driver, "text", "Monopatín");
		PO_SaleView.checkElement(driver, "text", "Skate");

	}

	// PR22.Hacer una búsqueda escribiendo en el campo un texto que no exista y
	// comprobar que se
	// muestra la página que corresponde, con la lista de ofertas vacía.
	@Test
	public void PR22() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos dato de usuario
		PO_LoginView.fillForm(driver, "M4ras@gmail.com", "123456");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-sale')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'sale/search')]");
		elementos.get(0).click();

		// Buscamos con algo que no de resultados
		PO_SaleView.search(driver, "Tridente Azul Intergalactico");

		// Comprobamos que no se muestran ofertas
		PO_SaleView.checkNotElement(driver, "El Quijote");
		PO_SaleView.checkNotElement(driver, "Dark Souls 3");
		PO_SaleView.checkNotElement(driver, "MGS3 Snake Eater");
		PO_SaleView.checkNotElement(driver, "Dishonored");
		PO_SaleView.checkNotElement(driver, "Deus Ex");
		PO_SaleView.checkNotElement(driver, "Skate");
		PO_SaleView.checkNotElement(driver, "Monopatín");
	}

	// PR23. Sobre una búsqueda determinada (a elección del desarrollador), comprar
	// una oferta que deja
	// un saldo positivo en el contador del comprador. Comprobar que el contador se
	// actualiza correctamente
	// en la vista del comprador
	@Test
	public void PR23() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos dato de usuario
		PO_LoginView.fillForm(driver, "M4ras@gmail.com", "123456");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-sale')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'sale/search')]");
		elementos.get(0).click();

		// Busco una oferta
		PO_SaleView.search(driver, "MGS3");

		// Compramos la oferta
		PO_SaleView.buy(driver, 1);

		// Comprobamos que el precio se ha actualizado
		PO_View.checkElement(driver, "text", "80.0");

		// Comprobamos que figura como vendida
		PO_SaleView.search(driver, "MGS3");
		PO_SaleView.checkKey(driver, "buscarOferta.vendida", PO_Properties.getSPANISH());
	}

	// PR24. Sobre una búsqueda determinada (a elección del desarrollador), comprar
	// una oferta que deja
	// un saldo 0 en el contador del comprador. Comprobar que el contador se
	// actualiza correctamente en la
	// vista del comprador
	@Test
	public void PR24() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos dato de usuario
		PO_LoginView.fillForm(driver, "M4ras@gmail.com", "123456");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-sale')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'sale/search')]");
		elementos.get(0).click();

		// Busco una oferta
		PO_SaleView.search(driver, "Skate");

		// Compramos la oferta
		PO_SaleView.buy(driver, 1);

		// Comprobamos que el precio se ha actualizado
		PO_View.checkElement(driver, "text", "0.0");

		// Comprobamos que figura como vendida
		PO_SaleView.search(driver, "Skate");
		PO_SaleView.checkKey(driver, "buscarOferta.vendida", PO_Properties.getSPANISH());
	}

	// PR25. Sobre una búsqueda determinada (a elección del desarrollador), intentar
	// comprar una oferta
	// que esté por encima de saldo disponible del comprador. Y comprobar que se
	// muestra el mensaje de
	// saldo no suficiente.
	@Test
	public void PR25() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos dato de usuario con 0 euros
		PO_LoginView.fillForm(driver, "M4ras@gmail.com", "123456");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-sale')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'sale/search')]");
		elementos.get(0).click();

		// Busco una oferta
		PO_SaleView.search(driver, "Dark Souls 3");

		// Compramos la oferta
		PO_SaleView.buy(driver, 1);

		// Comprobamos que muestra el mensaje
		PO_SaleView.checkKey(driver, "buscarOferta.noDinero", PO_Properties.getSPANISH());

		// Comprobamos que el precio no se ha actualizado
		PO_View.checkElement(driver, "text", "0.0");

		// Comprobamos que no figura como vendida
		PO_SaleView.search(driver, "Dark Souls 3");
		PO_SaleView.checkKey(driver, "buscarOferta.comprar", PO_Properties.getSPANISH());
	}

	// PR16. Ir a la opción de ofertas compradas del usuario y mostrar la lista.
	// Comprobar que aparecen
	// las ofertas que deben aparecer
	@Test
	public void PR26() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos dato de usuario
		PO_LoginView.fillForm(driver, "M4ras@gmail.com", "123456");

		// Entramos en el menú
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-sale')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'sale/purchased')]");
		elementos.get(0).click();

		// Compruebo que estoy en la página
		PO_SaleView.checkKey(driver, "compradas.titulo", PO_Properties.getSPANISH());

		// Compruebo las ofertas compradas
		PO_View.checkElement(driver, "text", "MGS3 Snake Eater");
		PO_View.checkElement(driver, "text", "Skate");
	}

	// PR27. Visualizar al menos cuatro páginas haciendo el cambio es
	// pañol/inglés/español
	@Test
	public void PR27() {
		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos dato de usuario
		PO_LoginView.fillForm(driver, "M4ras@gmail.com", "123456");

		// Comprobamos la internacionalizacion en home
		// Cambiamos a ingles
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-languaje')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id, 'btnEnglish')]");
		elementos.get(0).click();

		PO_SaleView.checkKey(driver, "inicio.titulo", PO_Properties.getENGLISH());
		PO_SaleView.checkKey(driver, "inicio.bienvenido", PO_Properties.getENGLISH());

		// Cambiamos a castellano
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-languaje')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id, 'btnSpanish')]");
		elementos.get(0).click();

		PO_SaleView.checkKey(driver, "inicio.titulo", PO_Properties.getSPANISH());
		PO_SaleView.checkKey(driver, "inicio.bienvenido", PO_Properties.getSPANISH());

		// Entramos en añadir oferta
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-sale')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'sale/add')]");
		elementos.get(0).click();

		// Comprobamos castellano
		PO_SaleView.checkKey(driver, "nuevaOferta.titulo", PO_Properties.getSPANISH());
		PO_SaleView.checkKey(driver, "nuevaOferta.titulo.atributo", PO_Properties.getSPANISH());
		PO_SaleView.checkKey(driver, "nuevaOferta.detalle", PO_Properties.getSPANISH());

		// Cambiamos a ingles
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-languaje')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id, 'btnEnglish')]");
		elementos.get(0).click();

		// Comprobamos ingles
		PO_SaleView.checkKey(driver, "nuevaOferta.titulo", PO_Properties.getENGLISH());
		PO_SaleView.checkKey(driver, "nuevaOferta.titulo.atributo", PO_Properties.getENGLISH());
		PO_SaleView.checkKey(driver, "nuevaOferta.detalle", PO_Properties.getENGLISH());

		// Entramos en buscar ofertas
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-sale')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'sale/search')]");
		elementos.get(0).click();

		// Comprobamos ingles
		PO_SaleView.checkKey(driver, "buscarOferta.titulo", PO_Properties.getENGLISH());

		// Cambiamos a castellano
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-languaje')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id, 'btnSpanish')]");
		elementos.get(0).click();

		// Comprobamos castellano
		PO_SaleView.checkKey(driver, "buscarOferta.comprar", PO_Properties.getSPANISH());

		// Salimos de sesion (estamos en logIn)
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		// Comprobamos castellano
		PO_SaleView.checkKey(driver, "login.titulo", PO_Properties.getSPANISH());
		PO_SaleView.checkKey(driver, "login.email", PO_Properties.getSPANISH());
		PO_SaleView.checkKey(driver, "login.contrasena", PO_Properties.getSPANISH());
		PO_SaleView.checkKey(driver, "login.aceptar", PO_Properties.getSPANISH());

		// Cambiamos a ingles
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-languaje')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id, 'btnEnglish')]");
		elementos.get(0).click();

		// Comprobamos ingles
		PO_LoginView.checkKey(driver, "login.titulo", PO_Properties.getENGLISH());
		PO_LoginView.checkKey(driver, "login.email", PO_Properties.getENGLISH());
		PO_LoginView.checkKey(driver, "login.contrasena", PO_Properties.getENGLISH());
		PO_LoginView.checkKey(driver, "login.aceptar", PO_Properties.getENGLISH());

		// Introducimos datos del admin
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");

		// Entramos en lista de usuarios
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-user')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
		elementos.get(0).click();

		// Comprobamos ingles
		PO_UsersView.checkKey(driver, "usuarios.titulo", PO_Properties.getENGLISH());
		PO_UsersView.checkKey(driver, "usuarios.casilla", PO_Properties.getENGLISH());
		PO_UsersView.checkKey(driver, "usuarios.eliminar", PO_Properties.getENGLISH());

		// Cambiamos a castellano
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'menu-languaje')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id, 'btnSpanish')]");
		elementos.get(0).click();

		// Comprobamos castellano
		PO_UsersView.checkKey(driver, "usuarios.titulo", PO_Properties.getSPANISH());
		PO_UsersView.checkKey(driver, "usuarios.casilla", PO_Properties.getSPANISH());
		PO_UsersView.checkKey(driver, "usuarios.eliminar", PO_Properties.getSPANISH());
	}

	// PR28. Intentar acceder sin estar autenticado a la opción de listado de
	// usuarios del administrador. Se deberá volver al formulario de login.
	@Test
	public void PR28() {
		// Intentamos ir a listar usuarios
		driver.navigate().to(URL + "/user/list");
		
		// Comprobamos que estamos en el login
		PO_View.checkKey(driver, "login.titulo", PO_Properties.getSPANISH());

	}

	// PR29. Intentar acceder sin estar autenticado a la opción de listado de
	// ofertas propias de un usuario estándar. Se deberá volver al formulario de
	// login.
	@Test
	public void PR29() {
		// Intentamos ir a la lista de ofertas
		driver.navigate().to(URL + "/sale/list");
		
		// Comprobamos que estamos en el login
		PO_View.checkKey(driver, "login.titulo", PO_Properties.getSPANISH());

	}

	// PR30.Estando autenticado como usuario estándar intentar acceder a la opción
	// de listado de usuarios del administrador. Se deberá indicar un mensaje de
	// acción prohibida.
	@Test
	public void PR30() {

		// Navegamos al login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Introducimos dato de usuario estandar
		PO_LoginView.fillForm(driver, "M4ras@gmail.com", "123456");


		// Intentamos ir a listar usuarios
		driver.navigate().to(URL + "/user/list");
		
		// Comprobamos que no podemos
		PO_View.checkElement(driver, "h1", "HTTP Status 403 – Forbidden");

	}

}
