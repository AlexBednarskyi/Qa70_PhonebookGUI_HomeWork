package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    // ===== ЛОКАТОРЫ =====

    private By loginLink         = By.cssSelector("[href='/login']");
    private By signOutButton     = By.xpath("//button[.='Sign Out']");
    private By emailInput        = By.name("email");
    private By passwordInput     = By.name("password");
    private By loginButton       = By.name("login");
    private By registrationButton= By.name("registration");

    // ===== ДЕЙСТВИЯ С ЛОГИНОМ / РЕГИСТРАЦИЕЙ =====

    public void clickOnLoginLink() {
        click(loginLink);
    }

    public void clickOnSignOutButton() {
        click(signOutButton);
    }

    public boolean isLoginLinkPresent() {
        return isElementPresent(loginLink);
    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent(signOutButton);
    }

    public void fillLoginRegisterForm(User user) {
        if (user.getEmail() != null) {
            type(emailInput, user.getEmail());
        }
        if (user.getPassword() != null) {
            type(passwordInput, user.getPassword());
        }
    }

    public void clickOnLoginButton() {
        click(loginButton);
    }

    public void clickOnRegistrationButton() {
        click(registrationButton);
    }


    public void waitForLoginLink(int timeoutSec) {
        waitForElementVisible(loginLink, timeoutSec);
    }

    public void waitForSignOutButton(int timeoutSec) {
        waitForElementVisible(signOutButton, timeoutSec);
    }
}
