package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver intializBrowser() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + 
				"\\src\\main\\java\\rahulshettyacademy\\resources\\GobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser"):
			prop.getProperty("browser");
		
		if (browserName.contains("chrome")) {
			WebDriverManager.chromedriver().clearDriverCache().setup();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("-remote-allow-origins=*");
			if (browserName.contains("headless")) {
				option.addArguments("headless");
			}
			option.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Lenovo\\Downloads\\firefoxdriver-win64\\geckodriver.exe");
			//FirefoxOptions option = new FirefoxOptions();
			//option.addArguments("-remote-allow-origins=*");
			//option.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String,String>> getJsonDatatoMap(String jsonFile) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File (jsonFile), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;	
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = intializBrowser();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		//driver.close();
		driver.quit();
	}
	
}
