package pageObject;

import org.openqa.selenium.By;
import utils.Utils;

public class LoginPage {
    private static LoginPage loginPageInstance = new LoginPage();
    private static By getSignUp = By.id("com.fossil.wearables.fossil.staging:id/tv_signup");

    public static LoginPage getSignUpPage() {
        if (loginPageInstance != null) return loginPageInstance;
        LoginPage loginPageInst = new LoginPage();
        return loginPageInst;
    }

    public SignUpPage goToSignUp() throws InterruptedException {
        Utils.waitForElement(getSignUp).click();
        return SignUpPage.getSignUpPage();
    }
}
