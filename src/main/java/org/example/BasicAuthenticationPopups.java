package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BasicAuthenticationPopups {

    @Test
    public  void sampleTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        //   driver.get("https://omayo.blogspot.com/");
        //   driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(500));

        //https://username:password@url
        driver.get("https://the-internet.herokuapp.com/basic_auth");
        Thread.sleep(3000);
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.quit();

    }
}
