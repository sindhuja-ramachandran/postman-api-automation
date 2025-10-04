package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SeleniumScreenshot {
    @Test
    public  void sampleTest() throws InterruptedException, IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.justdial.com/");
        driver.manage().window().maximize();
//driver.navigate().refresh();
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(file, new File("./Screenshots"));

        By alertButtonElement = By.id("alert1");
        By button2Element = By.id("but2");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click",driver.findElement(alertButtonElement));

        js.executeScript("arguments[0].scrollIntoView(true)",driver.findElement(button2Element));
        driver.quit();
    }
}

/*🟩 Line 1: Taking the Screenshot
File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

Part	Explanation
        (TakesScreenshot) driver	You're casting the driver object to TakesScreenshot, because WebDriver alone doesn't expose the getScreenshotAs() method. But ChromeDriver, FirefoxDriver, etc., implement TakesScreenshot.
        .getScreenshotAs(OutputType.FILE)	This method takes a screenshot and returns it as a temporary file.
File file = ...	Stores the screenshot in a Java File object. This is a temporary location, typically in a system temp folder (e.g., C:/Users/User/AppData/Local/Temp/...).
        🟨 Line 2: Saving the Screenshot to Your Folder
FileHandler.copy(file, new File("./Screenshots/abc.png"));

Part	Explanation
FileHandler.copy(...)	This copies a file from one location to another. It's a utility provided by Selenium (org.openqa.selenium.io.FileHandler).
file	The source file — your screenshot taken in line 1.
        new File("./Screenshots/abc.png")	The destination file — this creates a new file named abc.png inside the Screenshots folder relative to your project directory.
        ./Screenshots/abc.png	./ means "current working directory", so the image will be saved in a subfolder called Screenshots.

⚠️ If the folder Screenshots doesn't exist, this line will throw an IOException. You should create it manually or add code to create it automatically.

        ✅ Required Imports
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import java.io.File;

💡 Optional: Automatically Create Folder
new File("./Screenshots").mkdirs(); // Creates the folder if it doesn't exist


Add before FileHandler.copy(...) to prevent errors.

        ✅ Final Code Snippet with Folder Handling
TakesScreenshot ts = (TakesScreenshot) driver;
File src = ts.getScreenshotAs(OutputType.FILE);

// Create folder if not exists
new File("./Screenshots").mkdirs();

FileHandler.copy(src, new File("./Screenshots/abc.png"));

        📌 Summary
Function	What it Does
getScreenshotAs()	Takes a screenshot and returns a File
FileHandler.copy()	Moves the screenshot to your desired folder
./Screenshots/abc.png	Local folder path + filename for the screenshot */

