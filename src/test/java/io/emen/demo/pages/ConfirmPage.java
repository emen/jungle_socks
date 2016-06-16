package io.emen.demo.pages;

import io.emen.demo.io.emen.demo.support.Sock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.ArrayList;
import java.util.List;

public class ConfirmPage {

    private WebDriver driver;

    @FindBy(how = How.ID, using = "subtotal")
    private WebElement subtotal;

    @FindBy(how = How.ID, using = "taxes")
    private WebElement taxes;

    @FindBy(how = How.ID, using = "total")
    private WebElement total;


    public ConfirmPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public double getSubtotal() {
        return elemToDouble(subtotal);
    }

    public double getTaxes() {
        return elemToDouble(taxes);
    }

    public double getTotal() {
        return elemToDouble(total);
    }

    public Sock[] getSocks() {
        List<WebElement> lineItems = driver.findElements(By.cssSelector(".line_item"));
        ArrayList<Sock> socks = new ArrayList<Sock>();
        for (WebElement lineItem: lineItems) {
            String name     = lineItem.findElement(By.cssSelector("td:nth-child(1)")).getText();
            String price    = lineItem.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String quantity = lineItem.findElement(By.cssSelector("td:nth-child(3)")).getText();
            socks.add(new Sock(name, Double.parseDouble(price), Integer.parseInt(quantity)));
        }
        return socks.toArray(new Sock[socks.size()]);
    }

    private double elemToDouble(WebElement elem) {
        String elemText = elem.getText().substring(1);
        return Double.parseDouble(elemText);
    }
}
