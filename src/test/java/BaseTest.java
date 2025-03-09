import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import static Properties.Properties.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver chromeDriver;

    @BeforeEach
    public void before() {
//        System.setProperty("webdriver.chrome.driver","C:\\tmp\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY,"none");
        //chromeDriver = new ChromeDriver();
        chromeDriver = new ChromeDriver(capabilities);
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(testProperties.defaultTimeout(), TimeUnit.SECONDS);
        //chromeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        //chromeDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
    }

    @AfterEach
    public void after(){
        chromeDriver.quit();

    }
}
