package io.emen.demo.tests;

import io.emen.demo.io.emen.demo.support.DriverFactory;
import io.emen.demo.io.emen.demo.support.Sock;
import io.emen.demo.pages.ConfirmPage;
import io.emen.demo.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Random;

public class ConfirmTest {

    private WebDriver driver;
    private Sock[] socks;
    private String[] states;

    @BeforeSuite
    public void setup() {
        driver = DriverFactory.getDriver();
        HomePage homePage = new HomePage(driver);
        socks  = homePage.getSocks();
        states = homePage.getStates();
    }

    @AfterSuite
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testTotalAndTaxWithSingleSock() {
        Random rand = new Random();

        Sock theSock = socks[rand.nextInt(socks.length)];
        String name  = theSock.getName();
        int quantity = rand.nextInt(theSock.getQuantity()-1) + 1;

        String state = states[rand.nextInt(states.length)];
        double taxRate = 0.05;
        if (state == "CA")
            taxRate = 0.08;
        else if (state == "NY")
            taxRate = 0.06;
        else if (state == "MN")
            taxRate = 0.00;

        HomePage homePage = new HomePage(driver);
        ConfirmPage confirmPage = homePage.enterQuantity(quantity, name).selectState(state).checkout();

        Sock[] confirmSocks = confirmPage.getSocks();
        Sock   confirmSock  = confirmSocks[0];
        double expectedSubtotal = quantity * theSock.getPrice();
        double expectedTaxes    = expectedSubtotal * taxRate;
        double expectedTotal    = expectedSubtotal + expectedTaxes;

        Assert.assertEquals(1, confirmSocks.length);
        Assert.assertEquals(theSock.getName(), confirmSock.getName());
        Assert.assertEquals(quantity, confirmSock.getQuantity());
        Assert.assertEquals(expectedSubtotal, confirmPage.getSubtotal(), 0.001);
        Assert.assertEquals(expectedTaxes, confirmPage.getTaxes(), 0.001);
        Assert.assertEquals(expectedTotal, confirmPage.getTotal(), 0.001);
    }

    @Test
    public void testTotalAndTaxWithSomeSocks() {
    }
}
