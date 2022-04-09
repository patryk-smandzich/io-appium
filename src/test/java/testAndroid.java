import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class testAndroid {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    By maps = By.xpath("//android.widget.TextView[@content-desc=\"Maps\"]");
    By search = By.id("com.android.quicksearchbox:id/search_widget_text");
    By searchType = By.id("com.android.quicksearchbox:id/search_src_text");
    By searchConfirm = By.id("com.android.quicksearchbox:id/search_go_btn");
    By googleMessage = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.app.Dialog/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]");


    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "Pixel 5 API 30");
        cap.setCapability("udid", "emulator-5554");
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "11.0");
        cap.setCapability("skipUnlock", "true");
        driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), cap);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void firstTest() {
        //click google maps
        wait.until(ExpectedConditions.visibilityOfElementLocated(maps)).click();

        //go back
        driver.navigate().back();

        //click search
        wait.until(ExpectedConditions.visibilityOfElementLocated(search)).click();

        //type search
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchType)).sendKeys("poland");

        //confirm search
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchConfirm)).click();

        //check message
        String checkGoogleMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(googleMessage)).getText();
        Assert.assertTrue(checkGoogleMessage.contains("Before you continue"));
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
