package com.phonebook.core;

import com.phonebook.fw.ContactHelper;
import com.phonebook.fw.HomePageHelper;
import com.phonebook.fw.UserHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager {

    WebDriver driver;

    private UserHelper userHelper;
    private ContactHelper contactHelper;
    private HomePageHelper homePageHelper;
    private BaseHelper baseHelper;

    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://telranedu.web.app/");

        baseHelper = new BaseHelper(driver);
        userHelper = new UserHelper(driver);
        contactHelper = new ContactHelper(driver);
        homePageHelper = new HomePageHelper(driver);
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }

    public UserHelper getUser() {
        return userHelper;
    }

    public ContactHelper getContact() {
        return contactHelper;
    }

    public HomePageHelper getHomePage() {
        return homePageHelper;
    }

    public BaseHelper getBase() {
        return baseHelper;
    }
}
