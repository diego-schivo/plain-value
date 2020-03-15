package org.gradle.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HelloServletFunctionalTest {

	private WebDriver driver;

	@BeforeAll
	public static void setupClass() {
		// WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void setUp() throws MalformedURLException {
		// driver = new ChromeDriver();

		String sauceUserName = System.getenv("SAUCE_USERNAME");
		String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");
		String travisJobNumber = System.getenv("TRAVIS_JOB_NUMBER");
		String sauceURL = "https://ondemand.saucelabs.com/wd/hub";
		// String sauceURL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";

		MutableCapabilities sauceOpts = new MutableCapabilities();
		sauceOpts.setCapability("username", sauceUserName);
		sauceOpts.setCapability("accessKey", sauceAccessKey);
		sauceOpts.setCapability("tunnelIdentifier", travisJobNumber);
		sauceOpts.setCapability("seleniumVersion", "3.141.59");
        sauceOpts.setCapability("name", "4-best-practices");
        sauceOpts.setCapability("tags", Arrays.asList("sauceDemo", "demoTest", "module4", "javaTest"));
        sauceOpts.setCapability("maxDuration", 3600);
        sauceOpts.setCapability("commandTimeout", 600);
        sauceOpts.setCapability("idleTimeout", 1000);
        sauceOpts.setCapability("build", "Onboarding Sample App - Java-Junit5");

		ChromeOptions chromeOpts = new ChromeOptions();

		MutableCapabilities capabilities = new MutableCapabilities();
		capabilities.setCapability("sauce:options", sauceOpts);
		capabilities.setCapability("goog:chromeOptions", chromeOpts);
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("platformVersion", "Windows 10");
		capabilities.setCapability("browserVersion", "latest");

		driver = new RemoteWebDriver(new URL(sauceURL), capabilities);
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("sauce:job-result=" + ("passed"));
			driver.quit();
		}
	}

	@Test
	public void sayHello() throws Exception {
		driver.get("http://localhost:8080/demo");

		driver.findElement(By.id("say-hello-text-input")).sendKeys("Dolly");
		driver.findElement(By.id("say-hello-button")).click();

		assertEquals("Hello Page", driver.getTitle());
		assertEquals("Hello, Dolly!", driver.findElement(By.tagName("h2")).getText());
	}
}