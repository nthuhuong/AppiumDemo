package pageObject;

import org.openqa.selenium.By;
import utils.Utils;

public class SignUpPage {
    private static SignUpPage signUpPageInstance = new SignUpPage();
    private static By getLogin = By.id("com.fossil.wearables.fossil.staging:id/tv_login");

    public static SignUpPage getSignUpPage() {
        if (signUpPageInstance != null) return signUpPageInstance;
        SignUpPage signUpPageInst = new SignUpPage();
        return signUpPageInst;
    }

    public LoginPage goToLogin() throws InterruptedException {
        Utils.waitForElement(getLogin).click();
        return LoginPage.getSignUpPage();
    }
}
