package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WindowHandling {
    @Test
    public  void sampleTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        //   driver.get("https://omayo.blogspot.com/");
        //   driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(500));

        driver.get("https://the-internet.herokuapp.com/windows");
        driver.manage().window().maximize();

        //switch to new tab and switch back to parent tab
        String parentWindowId = driver.getWindowHandle();
        driver.findElement(By.linkText("Click Here")).click();
        for(String windowId : driver.getWindowHandles()) {
            if(!parentWindowId.equals(windowId))
                driver.switchTo().window(windowId);
        }
        String childWindowText = driver.findElement(By.xpath("//*[@class='example']/h3")).getText();
        System.out.println("child window tet: "+childWindowText);
        String childWindowTitle = driver.getTitle();
        System.out.println("child window title: "+childWindowTitle);
        driver.switchTo().window(parentWindowId);
        //open new window and switch to newly opened window or open new tab and switch to newly opened tab using newWindow() from selenium 4
        driver.switchTo().newWindow(WindowType.WINDOW);
        Thread.sleep(5000);
        driver.close();
        driver.switchTo().window(parentWindowId);
        Thread.sleep(8000);
        System.out.println("parent window title: "+driver.getTitle());
        driver.quit();
    }

}

