package io.emen.demo.pages;

import io.emen.demo.io.emen.demo.support.Sock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class HomePage {

    private WebDriver driver;
    private ArrayList<String> availableStates = new ArrayList<String>();
    private Select statesSelect;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://jungle-socks.herokuapp.com/");
        statesSelect = new Select(driver.findElement(By.name("state")));
    }

    public HomePage enterQuantity(int quantity, String name) {
        driver.findElement(By.id("line_item_quantity_" + name)).sendKeys(Integer.toString(quantity));
        return this;
    }

    public HomePage selectState(String state) {
        if (Pattern.matches("\\A[A-Z]{2}\\z", state))
            statesSelect.selectByValue(state);
        else
            statesSelect.selectByVisibleText(state);
        return this;
    }

    public ConfirmPage checkout() {
        driver.findElement(By.name("commit")).click();
        return new ConfirmPage(driver);
    }

    public ConfirmPage buyAndShip(HashMap<String,Integer> orders, String state) {
        for (String sock: orders.keySet()) {
            int quantity = orders.get(sock);
            enterQuantity(quantity, sock);
        }
        selectState(state);
        return checkout();
    }

    public Sock[] getSocks() {
        List<WebElement> lineItems = driver.findElements(By.cssSelector(".line_item"));
        ArrayList<Sock> socks = new ArrayList<Sock>();
        for (WebElement lineItem: lineItems) {
            String name  = lineItem.findElement(By.cssSelector("td:nth-child(1)")).getText();
            String price = lineItem.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String stock = lineItem.findElement(By.cssSelector("td:nth-child(3)")).getText();
            socks.add(new Sock(name, Double.parseDouble(price), Integer.parseInt(stock)));
        }
        return socks.toArray(new Sock[socks.size()]);
    }

    public String[] getStates() {
        if (availableStates.size() == 0) {
            List<WebElement> states = statesSelect.getOptions();
            for (WebElement state: states) {
                String stateValue = state.getAttribute("value");
                if (stateValue != "")
                    availableStates.add(stateValue);
            }
        }
        return availableStates.toArray(new String[availableStates.size()]);
    }

}
