package edge;

import io.github.bonigarcia.wdm.EdgeDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Set;

public class SwitchWindowInEdge {

    String driverPath = "C:\\Program Files (x86)\\Microsoft Web Driver\\";
    public WebDriver driver;

    @BeforeMethod
    public void launchBrowser() throws Exception {
        System.out.println("launching Microsoft Edge browser");
        /*System.setProperty("webdriver.edge.driver", driverPath+"MicrosoftWebDriver.exe");
        driver = new EdgeDriver();*/
        EdgeDriverManager.getInstance().setup();
        DesiredCapabilities caps = DesiredCapabilities.edge();
        URL url = new URL("http://172.21.77.24:5555/wd/hub");
        driver = new RemoteWebDriver(url, caps);
    }

    @Test()
    public void openEdgeBrowser() throws Exception {
        driver.get("http://demo.automationtesting.in/Windows.html");
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.cssSelector("a[href*='sakinalium'] > button"));
        element.click();
        // Get and store both window handles in array
        Set<String> AllWindowHandles = driver.getWindowHandles();
        String parentWindow = (String) AllWindowHandles.toArray()[0];
        String childWindow = (String) AllWindowHandles.toArray()[1];
        //Switching to child window
        driver.switchTo().window(childWindow);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.titleIs("Sakinalium | Home"));
    }

    @AfterMethod
    public void closeDriver() {
        if(driver!=null) {
            driver.quit();
        }
    }

}
