package io.emen.demo.pages;

import io.emen.demo.io.emen.demo.support.Sock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private ArrayList<Sock> socks = new ArrayList<Sock>();

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://jungle-socks.herokuapp.com/");
        List<WebElement> lineItems = driver.findElements(By.cssSelector(".line_item"));
        for (WebElement lineItem: lineItems) {
            String name  = lineItem.findElement(By.cssSelector("td:nth-child(1)")).getText();
            String price = lineItem.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String stock = lineItem.findElement(By.cssSelector("td:nth-child(3)")).getText();
            socks.add(new Sock(name, Double.parseDouble(price), Integer.parseInt(stock)));
        }
    }

    public HomePage enterQuantity(int quantity, String name) {
        driver.findElement(By.id("line_item_quantity_" + name)).sendKeys(Integer.toString(quantity));
        return this;
    }

    public HomePage selectState(String state) {
        Select states = new Select(driver.findElement(By.name("state")));
        states.selectByVisibleText(state);
        return this;
    }

    public ConfirmPage checkout() {
        driver.findElement(By.name("commit")).click();
        return new ConfirmPage(driver);
    }

    public Sock[] getSocks() {
        return socks.toArray(new Sock[socks.size()]);
    }

}
