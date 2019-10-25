import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class AndroidSetup {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("device","Android");

        //mandatory capabilities
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);

        //other caps
//        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,"com.sec.android.app.popupcalculator");
//        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,".Calculator");

        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.sec.android.app.popupcalculator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,".Calculator");

        AndroidDriver<MobileElement> driver =  new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_09")).click();
        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_add")).click();
        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_08")).click();
        //driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_equal")).click();
        List<MobileElement> plusButton = driver.findElements(By.className("android.widget.Button"));
        plusButton.get(20).click();
        if (driver.findElement(By.xpath("//*[@resource-id='com.sec.android.app.popupcalculator:id/txtCalc']")).getText().equals("17")) {
            System.out.println("FOUND");
        } else {
            System.out.println("NOT FOUND!");
        }



//        if (driver.findElement(By.id("com.sec.android.app.popupcalculator:id/txtCalc")).getText().equals("15")) {
//            System.out.println("FOUND");
//        } else {
//            System.out.println("NOT FOUND!");
//        }

        //org.testng.Assert.assertEquals(driver.findElement(By.id("com.sec.android.app.popupcalculator:id/txtCalc")).getText(),"15");

        //Thread.sleep(60000);
    }
}
