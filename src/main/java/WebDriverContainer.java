import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;


public class WebDriverContainer {

    private static WebDriverContainer instance;

    private static WebDriver driver;

    public static synchronized WebDriverContainer getInstance() {
        if (instance == null) instance = new WebDriverContainer();
        return instance;
    }

    public WebDriver getWebDriver() {
        return driver;
    }


    public void setDrivers() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capabilities.setBrowserName(BrowserType.CHROME);

        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/test/resources/drivers/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        ChromeOptions options = new ChromeOptions();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.addArguments("--start-maximized");
        options.merge(capabilities);
        driver = new ChromeDriver(service, options);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void closeDriver(){
        this.driver.close();
    }
}