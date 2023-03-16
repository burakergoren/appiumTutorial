import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTutorial {
    private AppiumDriver driver;

    @BeforeEach
    public void before() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "12.0");
        caps.setCapability("deviceName", "samsung");
        caps.setCapability("udid", "R5CW116PCRP");
        caps.setCapability("app", "/opt/sahibinden/app-debug.apk");
        caps.setCapability("newCommandTimeout", "600");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
    }

    @AfterEach
    public void tearDown()
    {
        if (null != driver) {
            driver.quit();
        }
    }

    @Test
    public void firstCase() throws InterruptedException {
        Thread.sleep(100);
        driver.findElement(By.id("com.sahibinden:id/browsing_activity_featured_classifieds_text_view_search")).click();
    }
}
