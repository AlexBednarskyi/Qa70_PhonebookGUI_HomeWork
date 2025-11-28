package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactNegativeTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void precondition() {
        // всегда открываем домашнюю страницу
        app.getHomePage().openHomePage();

        // если уже залогинены – ничего не делаем
        if (!app.getUser().isLoginLinkPresent()) {
            return;
        }

        // логинимся
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new User()
                .setEmail(UserData.email)
                .setPassword(UserData.password));
        app.getUser().clickOnLoginButton();
        app.getUser().pause(1000);
    }

    @Test
    public void addContactWithInvalidPhone() {
        int before = app.getContact().sizeOfContacts();

        app.getContact().clickOnAddLink();

        app.getContact().fillContactForm(new Contact()
                .setName("AlexInvalid")
                .setLastName("Test")
                .setPhone("123")
                .setEmail("alex@test.com")
                .setAddress("Berlin")
                .setDescription("Invalid phone test"));

        app.getContact().clickOnSaveButton();
        app.getUser().pause(1000);

        Assert.assertTrue(
                app.getUser().isAlertPresent(),
                "Ожидаем alert при добавлении контакта с некорректным телефоном"
        );

        int after = app.getContact().sizeOfContacts();
        Assert.assertEquals(
                after,
                before,
                "Количество контактов не должно меняться при некорректном телефоне"
        );
    }
}
