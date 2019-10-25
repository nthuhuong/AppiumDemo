import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class FossilApp {
    private static MobileElement waitForElement(MobileDriver<MobileElement> driver, By by) throws InterruptedException {
        final int timeout = 30 * 1000;
        final int polling = 1000;
        int count = 0;

        while (count < timeout) {
            try {
                return driver.findElement(by);
            } catch (NoSuchElementException e) {
                Thread.sleep(polling);
                count += polling;
            }
        }

        return null;
    }

    public static void singUpApp(AndroidDriver driver) throws InterruptedException{
        final MobileElement element1 = waitForElement(driver, By.id("com.fossil.wearables.fossil.staging:id/fb_get_started"));
        if (element1 != null) {
            element1.click();
        }

        final MobileElement element2 = waitForElement(driver, By.id("com.fossil.wearables.fossil.staging:id/et_email"));
        if (element2 != null) {
            element2.click();
            element2.setValue("thuhuongnguyen160197@gmail.com");
        }

        final MobileElement element3 = waitForElement(driver, By.id("com.fossil.wearables.fossil.staging:id/et_password"));
        if (element3 != null) {
            element3.click();
            element3.setValue("123456nttH");
        }

        driver.getKeyboard().sendKeys(Keys.ENTER);

        final MobileElement element4 = waitForElement(driver, By.id("com.fossil.wearables.fossil.staging:id/bt_continue"));
        if (element4 != null) {
            element4.click();
        }

    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.fossil.wearables.fossil.staging");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.portfolio.platform.uirenew.splash.SplashScreenActivity");
        AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        singUpApp(driver);
    }
}
