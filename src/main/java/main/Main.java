package main;

import pageObject.StartedPage;
import server.AppiumServerJava;

import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws InterruptedException, MalformedURLException {

//        AppiumServerJava appiumServer = new AppiumServerJava();

//        int port = 4723;
//        if(!appiumServer.checkIfServerIsRunnning(port)) {
//            appiumServer.startServer();
//            //appiumServer.stopServer();
//        } else {
//            System.out.println("Appium Server already running on Port - " + port);
//        }

//        AppiumServerJava appiumServer = new AppiumServerJava();
//        AppiumDriverLocalService service = appiumServer.startServer();

        try {
            AppiumServerJava.startService();
            AppiumServerJava.getDriver();

            StartedPage.goToSignUp()
                    .goToLogin()
                    .goToSignUp();
        } finally {
            AppiumServerJava.stopService();
        }
    }
}
