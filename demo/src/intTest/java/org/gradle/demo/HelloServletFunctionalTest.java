package org.gradle.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

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
		String travisBuildNumber = System.getenv("TRAVIS_BUILD_NUMBER");
		// String sauceURL = "https://ondemand.saucelabs.com/wd/hub";
		String sauceURL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";

		MutableCapabilities sauceOpts = new MutableCapabilities();
		sauceOpts.setCapability("username", sauceUserName);
		sauceOpts.setCapability("accessKey", sauceAccessKey);

		if (travisJobNumber != null) {
			String tunnelIdentifier = travisJobNumber.replaceFirst("\\..*", "");
			sauceOpts.setCapability("tunnel-identifier", tunnelIdentifier);
		}

		sauceOpts.setCapability("seleniumVersion", "3.141.59");
		sauceOpts.setCapability("name", "plainValue");
		sauceOpts.setCapability("tags", Arrays.asList("helloServlet"));
		sauceOpts.setCapability("maxDuration", 3600);
		sauceOpts.setCapability("commandTimeout", 600);
		sauceOpts.setCapability("idleTimeout", 1000);
		sauceOpts.setCapability("build", travisBuildNumber != null ? travisBuildNumber : "plainValue");
		sauceOpts.setCapability("startConnect", false);

		ChromeOptions chromeOpts = new ChromeOptions();
		chromeOpts.setExperimentalOption("w3c", true);

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