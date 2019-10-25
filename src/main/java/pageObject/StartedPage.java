package pageObject;

import org.openqa.selenium.By;
import utils.Utils;

public class StartedPage {
    private static StartedPage startedPageIntance = new StartedPage();
    private static By getStarted = By.id("com.fossil.wearables.fossil.staging:id/fb_get_started");

    public static StartedPage getStartedPage() {
        if (startedPageIntance != null) return startedPageIntance;
        StartedPage startedPageInst = new StartedPage();
        return startedPageInst;
    }

    public static SignUpPage goToSignUp() throws InterruptedException {
        Utils.waitForElement(getStarted).click();
        return SignUpPage.getSignUpPage();
    }
}
