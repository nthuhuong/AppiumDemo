package server;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public final class AppiumServerJava {

    private static final String APPIUM_JS = "/usr/local/lib/node_modules/appium/build/lib/main.js";
    private static final int APPIUM_SERVER_PORT = 4723;

    private static AppiumDriverLocalService service;
    private static AndroidDriver<MobileElement> driver;

    private AppiumServerJava() {}

    public static void startService() {
        if (service == null) {
            final AppiumServiceBuilder builder = new AppiumServiceBuilder()
                    .withAppiumJS(new File(APPIUM_JS))
                    .withIPAddress("127.0.0.1")
                    .usingPort(APPIUM_SERVER_PORT)
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "error");

            service = AppiumDriverLocalService.buildService(builder);
        }

        service.start();
    }

    public static AndroidDriver<MobileElement> getDriver() {
        if (driver != null) return driver;

        if (service == null) {
            throw new RuntimeException("Must start Appium service first!!");
        }

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("noReset", "false");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.fossil.wearables.fossil.staging");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.portfolio.platform.uirenew.splash.SplashScreenActivity");

        driver = new AndroidDriver<MobileElement>(service.getUrl(), capabilities);
        return driver;
    }

    public static void stopService() {
        if (service != null) {
            service.stop();
        }
    }
}
