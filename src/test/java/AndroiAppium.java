import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;



public class AndroiAppium {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("device","Android");

        //mandatory capabilities
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);

        //other caps
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.sec.android.app.popupcalculator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,".Calculator");

        AndroidDriver driver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_07")).click();
        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_add")).click();
        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_08")).click();
        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_equal")).click();

       // driver.findElement(By.id("com.sec.android.app.popupcalculator:id/txtCalc")).wait();
        Assert.assertEquals(driver.findElement(By.id("com.sec.android.app.popupcalculator:id/txtCalc")).getText(),"15");

//      Thread.sleep(60000);
    }
}

