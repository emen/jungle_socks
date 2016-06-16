package io.emen.demo.pages;

import io.emen.demo.io.emen.demo.support.Sock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ConfirmPage {

    private WebDriver driver;
    private ArrayList<Sock> socks = new ArrayList<Sock>();

    public ConfirmPage(WebDriver driver) {
        this.driver = driver;
        List<WebElement> lineItems = driver.findElements(By.cssSelector(".line_item"));
        for (WebElement lineItem: lineItems) {
            String name     = lineItem.findElement(By.cssSelector("td:nth-child(1)")).getText();
            String price    = lineItem.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String quantity = lineItem.findElement(By.cssSelector("td:nth-child(3)")).getText();
            socks.add(new Sock(name, Double.parseDouble(price), Integer.parseInt(quantity)));
        }
    }

    public double getSubtotal() {
        String subtotal = driver.findElement(By.id("subtotal")).getText().substring(1);
        return Double.parseDouble(subtotal);
    }

    public double getTaxes() {
        String taxes = driver.findElement(By.id("taxes")).getText().substring(1);
        return Double.parseDouble(taxes);
    }

    public double getTotal() {
        String total = driver.findElement(By.id("total")).getText().substring(1);
        return Double.parseDouble(total);
    }

    public Sock[] getSocks() {
        return socks.toArray(new Sock[socks.size()]);
    }
}
