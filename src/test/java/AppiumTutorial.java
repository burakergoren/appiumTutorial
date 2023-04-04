import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

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
        //caps.setCapability("app", "/opt/sahibinden/imdb.apk");
        //caps.setCapability("appActivity", "com.imdb.mobile.HomeActivity");
        caps.setCapability("app", "/opt/sahibinden/app-debug.apk");
        caps.setCapability("newCommandTimeout", "600");
        caps.setCapability("autoGrantPermissions", true);
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
    public void case1() throws InterruptedException {
        Thread.sleep(100);
        driver.findElement(By.id("com.sahibinden:id/browsing_activity_featured_classifieds_text_view_search")).click();
    }

    @Test
    public void case2() throws InterruptedException {
        Thread.sleep(1000);
        scrollHalfPageDown();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Acil Acil']"));
    }

    @Test
    public void case3() {
        AndroidElement element = (AndroidElement) driver.findElement(By.id("com.sahibinden:id/browsing_activity_featured_classifieds_text_view_search"));
        waitUntilGenericMethod(element);
        Assertions.assertTrue(element.isDisplayed());
    }

    //******************************** methods **************************************//

    public void scrollHalfPageDown() {
        int screenHeight = driver.manage().window().getSize().getHeight();
        int screenWidth = driver.manage().window().getSize().getWidth();
        int startX, startY, endX, endY;

        TouchAction touchAction = new TouchAction(driver);

        startX = screenWidth / 2;
        endX = startX;
        startY = (int) (0.8 * screenHeight);
        endY = (int) (0.6 * screenHeight);
        touchAction.press(point(startX, startY)).waitAction(waitOptions(Duration.ofMillis(400))).moveTo(point(endX, endY)).release();
        touchAction.perform();
    }

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
}
