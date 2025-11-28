package com.phonebook.tests;

import com.phonebook.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @Test
    public void isHomeComponentPresentTest() {
        Assert.assertTrue(
                app.getHomePage().isHomeComponentPresent(),
                "Home component should be present on home page"
        );
    }
}
