package io.emen.demo.io.emen.demo.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public enum DriverType implements DriverSetup {
    FIREFOX {
        public WebDriver getWebDriverObject() {
            return new FirefoxDriver(DesiredCapabilities.firefox());
        }
    },
    CHROME {
        public WebDriver getWebDriverObject() {
            return new ChromeDriver(DesiredCapabilities.chrome());
        }
    }
}
