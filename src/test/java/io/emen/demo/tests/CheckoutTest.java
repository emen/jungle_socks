package io.emen.demo.tests;

import io.emen.demo.pages.ConfirmPage;
import io.emen.demo.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CheckoutTest {

    private WebDriver driver;

    @BeforeSuite
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterSuite
    public void teardown() {
        driver.quit();
    }

    @Test
    public void checkoutWithoutSelectingState() {
        HomePage homePage = new HomePage(driver);
        homePage.checkout();
        Assert.assertEquals("We're sorry, but something went wrong (500)", driver.getTitle());
    }

    @Test
    public void checkoutWithoutSocks() {
        HomePage homePage = new HomePage(driver);
        homePage.selectState("Texas");
        ConfirmPage confirmPage = homePage.checkout();
        Assert.assertEquals(0.0, confirmPage.getSubtotal());
        Assert.assertEquals(0.0, confirmPage.getTaxes());
        Assert.assertEquals(0.0, confirmPage.getTotal());
        Assert.assertEquals(0, confirmPage.getSocks().length);
    }

    @Test
    public void checkoutWithSingleSock() {

    }

    @Test
    public void checkoutWithMultipleSock() {

    }

    @Test
    public void checkoutWithAllSock() {

    }

    @Test
    public void checkoutWithInvalidQuantity() {

    }

    @Test
    public void checkoutWithOutofStockQuantity() {
        // TODO expect proper error page
    }
}
