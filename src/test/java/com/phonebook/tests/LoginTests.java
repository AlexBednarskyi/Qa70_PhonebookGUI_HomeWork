package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.data.UserData;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void ensureOnHomeAndLoggedOut() {
        app.getHomePage().openHomePage();

        if (app.getUser().isSignOutButtonPresent()) {
            app.getUser().clickOnSignOutButton();
            app.getUser().pause(1000);
        }
    }

    @Test
    public void loginNegativeWithoutEmailTest() {
        app.getUser().clickOnLoginLink();

        app.getUser().fillLoginRegisterForm(
                new User().setPassword(UserData.password)
        );

        app.getUser().clickOnLoginButton();
        app.getUser().pause(1000);

        Assert.assertFalse(
                app.getUser().isSignOutButtonPresent(),
                "Ожидаем, что пользователь НЕ залогинился"
        );
    }

    @Test
    public void loginPositiveTest() {
        app.getUser().clickOnLoginLink();

        app.getUser().fillLoginRegisterForm(
                new User()
                        .setEmail(UserData.email)
                        .setPassword(UserData.password)
        );

        app.getUser().clickOnLoginButton();
        app.getUser().pause(1000);

        Assert.assertTrue(
                app.getUser().isSignOutButtonPresent(),
                "После логина должна быть кнопка Sign Out"
        );
    }
}
