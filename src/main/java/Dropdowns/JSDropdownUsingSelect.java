package Dropdowns;

import commonMethods.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class JSDropdownUsingSelect {
    @Test
    public  void sampleTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        //   driver.get("https://omayo.blogspot.com/");
        //   driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(500));

        //https://username:password@url

        By drpDwnLocator = By.id("dropdown");
            driver.get("https://the-internet.herokuapp.com/dropdown");
        driver.manage().window().maximize();

        WebElement drpDwnElement = driver.findElement(drpDwnLocator);
        Select select = new Select(drpDwnElement);
        select.selectByValue("1");
        Wait.waitForElementToBeVisible(driver, 3, drpDwnLocator);
        select.selectByIndex(2);
        Wait.waitForElementToBeVisible(driver, 3, drpDwnLocator);
        select.selectByVisibleText("Option 1");
        Wait.waitForElementToBeVisible(driver, 3, drpDwnLocator);
        driver.quit();
    }
}
