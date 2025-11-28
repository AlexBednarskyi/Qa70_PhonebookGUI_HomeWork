package com.phonebook.tests;

import com.phonebook.core.TestBase;
import com.phonebook.data.ContactData;
import com.phonebook.data.UserData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void precondition() {
        // 1. Всегда открываем главную страницу
        app.getHomePage().openHomePage();

        // 2. Если не залогинен – логинимся
        if (!app.getUser().isSignOutButtonPresent()) {
            app.getUser().clickOnLoginLink();
            app.getUser().fillLoginRegisterForm(
                    new User()
                            .setEmail(UserData.email)
                            .setPassword(UserData.password)
            );
            app.getUser().clickOnLoginButton();
            app.getUser().pause(1000);
        }
    }

    @Test(groups = "smoky")
    public void addContactPositiveTest() {

        app.getContact().clickOnAddLink();

        app.getContact().fillContactForm(new Contact()
                .setName(ContactData.name)
                .setLastName(ContactData.lastName)
                .setPhone(ContactData.phone)
                .setEmail(ContactData.email)
                .setAddress(ContactData.address)
                .setDescription(ContactData.description));

        app.getContact().clickOnSaveButton();

        Assert.assertTrue(
                app.getContact().isContactCreatedByText(ContactData.name),
                "Контакт с именем " + ContactData.name + " должен быть создан"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        // удаляем контакт только если он вообще есть
        if (app.getContact().isContactListPresent()) {
            app.getContact().removeContact();
        }
    }
}
