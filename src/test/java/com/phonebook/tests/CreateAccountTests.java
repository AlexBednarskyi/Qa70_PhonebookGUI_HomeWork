package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.data.MyDataProviders;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        // если уже залогинен – выходим
        if (app.getUser().isSignOutButtonPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test
    public void newUserRegisterPositiveTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail("user" + System.currentTimeMillis() + "@gmail.com")
                .setPassword("Password123$"));
        app.getUser().clickOnRegistrationButton();

        Assert.assertTrue(app.getUser().isSignOutButtonPresent(),
                "Кнопка Sign Out не найдена после регистрации");
    }

    @Test(dataProvider = "validUserFromCsv", dataProviderClass = MyDataProviders.class)
    public void newUserRegisterFromCsvTest(User user) {
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(user);
        app.getUser().clickOnRegistrationButton();

        Assert.assertTrue(app.getUser().isSignOutButtonPresent(),
                "Кнопка Sign Out не найдена после регистрации (CSV)");
    }
}
