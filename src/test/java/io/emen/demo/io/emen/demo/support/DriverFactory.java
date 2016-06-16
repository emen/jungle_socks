package io.emen.demo.io.emen.demo.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.emen.demo.io.emen.demo.support.DriverType.valueOf;
import static io.emen.demo.io.emen.demo.support.DriverType.FIREFOX;

public class DriverFactory {

    private static final String browser = System.getProperty("browser").toUpperCase();

    public static WebDriver getDriver() {
        DriverSetup driverType = valueOf(browser);
        return driverType.getWebDriverObject();
    }

}
