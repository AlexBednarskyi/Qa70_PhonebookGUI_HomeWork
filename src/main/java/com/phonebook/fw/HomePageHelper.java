package com.phonebook.fw;

import com.phonebook.core.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageHelper extends BaseHelper {

    private static final String BASE_URL = "https://telranedu.web.app";

    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        driver.get(BASE_URL);
    }

    public boolean isHomeComponentPresent() {
        return isElementPresent(By.xpath("//h1[contains(text(),'PHONEBOOK')]"));
    }

    public void clickOnHomeLink() {
        click(By.cssSelector("a[href='/']"));
    }
}
