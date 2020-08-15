package GridDemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumMultiBrowserTest {
	WebDriver driver;
	String nodeURL;

	@Parameters({ "Port" })
	@BeforeMethod()
	public void setUp(String Port) throws MalformedURLException {

		if (Port.equalsIgnoreCase("4100")) {

			System.out.println("Chrome Browser Initiated");
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");

			ChromeOptions options = new ChromeOptions();
			options.merge(cap);

			nodeURL = "http://localhost:4100/wd/hub";
			driver = new RemoteWebDriver(new URL(nodeURL), options);
		}

		else if (Port.equalsIgnoreCase("4200")) {

			System.out.println("Firefox Browser Initiated");
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("firefox");

			FirefoxOptions options = new FirefoxOptions();
			options.merge(cap);

			nodeURL = "http://localhost:4200/wd/hub";
			driver = new RemoteWebDriver(new URL(nodeURL), options);

		}

		else if (Port.equalsIgnoreCase("4300")) {

			System.out.println("Internet Browser Initiated");
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("internetexplorer");

			InternetExplorerOptions options = new InternetExplorerOptions();
			options.merge(cap);

			nodeURL = "http://localhost:4300/wd/hub";
			driver = new RemoteWebDriver(new URL(nodeURL), options);
		}
		driver.get("https://www.apple.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void AppleSite() throws InterruptedException {
		try {

			driver.findElement(By.xpath("//*[@id=\'ac-globalnav\']/div/ul[2]/li[3]")).click();
			Thread.sleep(5000);

			driver.findElement(
					By.cssSelector("#chapternav > div > ul > li.chapternav-item.chapternav-item-ipad-air > a > figure"))
					.click();
			Thread.sleep(5000);

			driver.findElement(By.linkText("Why iPad")).click();
			Thread.sleep(5000);
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@AfterMethod()
	public void tearDown() {
		driver.quit();
		System.out.println("Browser Closed");
	}

}
