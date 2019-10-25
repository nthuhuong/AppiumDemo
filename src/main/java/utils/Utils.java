package utils;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import server.AppiumServerJava;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public final class Utils {
    private Utils() {

    }

    public static MobileElement waitForElement(By by) throws InterruptedException {
        final int timeout = Integer.parseInt(readFileInFolderResources("Timeout.txt"));
        final int polling = Integer.parseInt(readFileInFolderResources("Polling.txt"));
        int count = 0;

        while (count < timeout) {
            try {
                return AppiumServerJava.getDriver().findElement(by);
            } catch (NoSuchElementException e) {
                Thread.sleep(polling);
                count += polling;
            }
        }

        return null;
    }

    public static String readFileInFolderResources(String name) {
        File file = new File(Utils.class.getClassLoader().getResource(name).getFile());

        if (file == null) return null;
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
            return br.readLine();
        } catch (Exception e){

        }

        return null;
    }
}
