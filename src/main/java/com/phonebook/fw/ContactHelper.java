package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import com.phonebook.models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }


    public void openAddContactForm() {
        click(By.cssSelector("[href='/add']"));
    }

    public void clickOnAddLink() {
        openAddContactForm();
    }


    public void fillAddContactForm(Contact contact) {
        fillContactForm(contact);
    }

    public void fillContactForm(Contact contact) {
        type(By.cssSelector("input[placeholder='Name']"),        contact.getName());
        type(By.cssSelector("input[placeholder='Last Name']"),   contact.getLastName());
        type(By.cssSelector("input[placeholder='Phone']"),       contact.getPhone());
        type(By.cssSelector("input[placeholder='email']"),       contact.getEmail());
        type(By.cssSelector("input[placeholder='Address']"),     contact.getAddress());
        type(By.cssSelector("input[placeholder='description']"), contact.getDescription());
    }


    public void clickOnSaveButton() {
        click(By.xpath("//button[.='Save']"));
    }


    public boolean isContactCreatedByText(String text) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
        for (WebElement element : contacts) {
            if (element.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }

    public boolean isContactListPresent() {
        return isElementPresent(By.cssSelector(".contact-item_card__2SOIM"));
    }


    public void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[.='Remove']"));
    }

    public int sizeOfContacts() {
        if (isContactListPresent()) {
            return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        }
        return 0;
    }
}
