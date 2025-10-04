package Dropdowns;

import commonMethods.Wait;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


public class AutoSuggestiveDropdown {

    @Test
    public  void sampleTest() throws InterruptedException, IOException {
        ChromeDriver driver = new ChromeDriver();

        By makeMyTripLogoLocator = By.xpath("//*[@alt='Make My Trip']");
        By fromCityIDLocator = By.id("fromCity");
        By fromCitySearchLocator = By.xpath("//input[@placeholder='From']");
        By autoSuggestiveDrpdwnListLocator = By.xpath("//*[contains(@class,'hsw_autocomplePopup')]//li");

        driver.get("https://www.makemytrip.global/?cc=ca");
        driver.manage().window().maximize();

        //webdriver click is not working
//        driver.findElement(makeMyTripLogoLocator).click();

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(makeMyTripLogoLocator)).click().build().perform();

        driver.findElement(fromCityIDLocator).click();
        Wait.waitForElementToBeVisible(driver, 5, fromCitySearchLocator);
        driver.findElement(fromCitySearchLocator).sendKeys("canad");
        Thread.sleep(3000);

        Wait.waitForListOfElementsToBeVisible(driver, 5, autoSuggestiveDrpdwnListLocator);
        for (int i = 0; i < 4; i++) {
            driver.findElement(fromCitySearchLocator).sendKeys(Keys.ARROW_DOWN); //Keys.ARROW_DOWN works only if you click the element first and then perform arrow down
         //   Thread.sleep(2000);
        }

        driver.findElement(fromCitySearchLocator).sendKeys(Keys.ENTER);
    }
}
