package com.phonebook.core;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class BaseHelper {

    protected WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if (text == null) {
            return;
        }
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    // Ожидание видимости элемента
    public void waitForElementVisible(By locator, int timeoutSec) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementClickable(By locator, int timeoutSec) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSec))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean isElementPresent(By locator) {
        try {
            return driver.findElements(locator).size() > 0;
        } catch (UnhandledAlertException e) {
            try {
                driver.switchTo().alert().accept();
            } catch (NoAlertPresentException ignored) {
            }
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void takeScreenshot(String fileName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            Path dir = Paths.get("screenshots");
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            Path dest = dir.resolve(fileName);
            FileHandler.copy(src, dest.toFile());
        } catch (UnhandledAlertException e) {
            try {
                driver.switchTo().alert().accept();
            } catch (NoAlertPresentException ignored) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
