package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AlertPopups {
    @Test
    public  void sampleTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        //   driver.get("https://omayo.blogspot.com/");
        //   driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(500));

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println("info: "+alert.getText());
        alert.accept();

        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();
        System.out.println("confirm: "+alert.getText());
        alert.dismiss();
        driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();
        System.out.println("prompt: "+alert.getText());
        alert.sendKeys("sindhu");
        alert.accept();

        driver.quit();
    }
}
