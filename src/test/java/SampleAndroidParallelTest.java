import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Execution(ExecutionMode.CONCURRENT)
public class SampleAndroidParallelTest {

    private AppiumDriver driver;
    private DesiredCapabilities caps;

    @BeforeEach
    public void before() throws MalformedURLException {
        caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", "/opt/sahibinden/app-debug.apk");
        caps.setCapability("appPackage", "com.sahibinden");
        caps.setCapability("appActivity", "com.sahibinden.ui.supplementary.SplashScreenActivity");
        caps.setCapability("keystorePath", "/opt/sahibinden/debug.keystore");
        caps.setCapability("newCommandTimeout", "600");
        caps.setCapability("autoGrantPermissions", true);
    }

    @AfterEach
    public void tearDown()
    {
        if (null != driver) {
            driver.quit();
        }
    }

    @Test
    public void case1() throws InterruptedException, MalformedURLException {
        caps.setCapability("platformVersion", "12.0");
        caps.setCapability("deviceName", "Samsung Galaxy S21 5G");
        caps.setCapability("udid", "R5CR80A1D6H");
        caps.setCapability("systemPort", "1058");
        URL url = new URL("http://192.168.12.35:" + "8979" + "/wd/hub");
        driver = new AndroidDriver(url, caps);
        Thread.sleep(10000);
        driver.findElement(By.id("com.sahibinden:id/browsing_activity_featured_classifieds_text_view_search")).click();
    }

    @Test
    public void case2() throws MalformedURLException, InterruptedException {
        caps.setCapability("platformVersion", "10");
        caps.setCapability("deviceName", "samsung s20");
        caps.setCapability("udid", "RF8NA296QXK");
        caps.setCapability("systemPort", "8388");
        URL url = new URL("http://192.168.12.35:" + "8492" + "/wd/hub");
        driver = new AndroidDriver(url, caps);
        Thread.sleep(10000);
        AndroidElement element = (AndroidElement) driver.findElement(By.id("com.sahibinden:id/browsing_activity_featured_classifieds_text_view_search"));
        waitUntilGenericMethod(element);
        Assertions.assertTrue(element.isDisplayed());
    }

    //******************************** methods **************************************//

    public void waitUntilGenericMethod(WebElement webElement) {
        FluentWait<AppiumDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(10))
                        .pollingEvery(Duration.ofSeconds(1))
                        .ignoring(NoSuchElementException.class)
                        .withMessage("element couldn't found in fluent wait..");

        wait.until(ExpectedConditions.visibilityOf(webElement));
        Assertions.assertTrue(webElement.isDisplayed());
    }

    /*
    *  vm options parameters for parallel test running
    *
    * -Djunit.jupiter.execution.parallel.enabled=true
    * -Djunit.jupiter.execution.parallel.mode.default=concurrent
    * -Djunit.jupiter.execution.parallel.config.strategy=fixed
    * -Djunit.jupiter.execution.parallel.config.fixed.parallelism=2
    *
    *
    */
}
