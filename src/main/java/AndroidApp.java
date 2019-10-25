import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.temporal.TemporalUnit;

public class AndroidApp {
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

    private static boolean compareValueElement(MobileDriver<MobileElement> diver, String expect) throws InterruptedException {
        final int timeout = 120 * 1000;
        final int polling = 1000;
        int count = 0;

        while (count < timeout) {
            try {
                if (diver.findElement(By.id("com.android.vending:id/right_button")).getText().equals(expect)) {
                    return true;
                } else {
                    Thread.sleep(polling);
                    count += polling;
                }
            } catch (NoSuchElementException e){
                Thread.sleep(polling);
                count += polling;
            }
        }

        return false;
    }

    public static void installApp(AndroidDriver<MobileElement> driver, String app) throws InterruptedException {
        final MobileElement element1 = waitForElement(driver, By.id("com.android.vending:id/search_bar_hint"));
        if (element1 != null) {
            element1.click();
        }

        final MobileElement element2 = waitForElement(driver, By.id("com.android.vending:id/search_bar_text_input"));
        if (element2 != null) {
            element2.setValue(app);
        }

        driver.getKeyboard().sendKeys(Keys.ENTER);

        final MobileElement element3 = waitForElement(driver, By.id("com.android.vending:id/right_button"));
        if (element3 != null) {
            element3.click();
        }

        boolean expectInstall = compareValueElement(driver, "Open");
        if (expectInstall) {
            System.out.print("SUCCESS");
        } else {
            System.out.print("Install is failuer");
        }
    }

    public static void longPressApp(AndroidDriver<MobileElement> driver) throws InterruptedException {
        final MobileElement element = waitForElement(driver, By.id("com.android.vending:id/li_thumbnail"));
        if (element == null) return;

        int middleX = element.getLocation().getX() + element.getSize().getWidth()/2;
        int middleY = element.getLocation().getY() + element.getSize().getHeight()/2;

        System.out.println(middleX);
        System.out.println(middleY);

//        PointOption longPressOption = PointOption.point(middleX, middleY);
//        new TouchAction(driver).longPress(longPressOption).release().perform();

//        TouchAction action = new TouchAction(driver).longPress(LongPressOptions.longPressOptions().
//                withElement(element).withDuration(Duration.ofMillis(10000))).release().perform();

        new TouchAction(driver).longPress(
                LongPressOptions.longPressOptions()
                        .withPosition(PointOption.point(middleX, middleY))
                        .withDuration(Duration.ofMillis(10000)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(10000)))
                .release()
                .perform();

        Thread.sleep(2000);
    }

    public static void removeApp(AndroidDriver<MobileElement> driver) throws InterruptedException {
        //AndroidTouchAction action = new AndroidTouchAction(driver);
        TouchAction action = new TouchAction(driver);

        final MobileElement element1 = waitForElement(driver, By.id("com.sec.android.app.launcher:id/iconview_imageVie"));
        if(element1 != null) {
            int leftX = element1.getLocation().getX();
            int middleX= leftX + element1.getSize().getWidth()/2;
            int upperY = element1.getLocation().getY();
            int middleY = upperY + element1.getSize().getHeight()/2;

            PointOption longPressOption = PointOption.point(middleX, middleY);
            action.longPress(longPressOption).release();
        }
    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
//        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.sec.android.app.launcher");
//        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".activities.LauncherActivity");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.vending");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".AssetBrowserActivity");
        AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        //installApp(driver, "Skype");
        //removeApp(driver);
        longPressApp(driver);

//        final MobileElement element = waitForElement(driver, By.xpath("//*[@text='Top charts']"));
//        if (element != null) {
//            element.click();
//        }

//        List<MobileElement> plusButton = driver.findElements(By.className("android.widget.TextView"));
//        plusButton.get(4).click();


        //Thread.sleep(60000);
    }


}

