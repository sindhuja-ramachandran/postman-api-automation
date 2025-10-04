package frames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

//switching b/w parent, child and siblings frames
//frame take arg as 1. index; 2. String(name or id); 3. webelement
public class frame {
    @Test
    public  void sampleTest() throws InterruptedException {

    WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        driver.manage().window().maximize();

        By frameTop = By.xpath("//*[@src='/frame_top']");
        By frameleft = By.xpath("//*[@src='/frame_left']");
        By frameMiddle = By.xpath("//*[@src='/frame_middle']");
        By frameBottom = By.xpath("//*[@src='/frame_bottom']");

        By leftBodyTxt = By.xpath("//*[normalize-space(text())='LEFT']");
        By middleBodyTxt = By.xpath("//*[normalize-space(text())='MIDDLE']");
        By bottomBodyTxt = By.xpath("//*[normalize-space(text())='BOTTOM']");

        driver.switchTo().frame(driver.findElement(frameTop)); //switch to top frame - parent
        driver.switchTo().frame(driver.findElement(frameleft)); //switch to left frame - child
        System.out.println("left: "+driver.findElement(leftBodyTxt).getText());

        driver.switchTo().parentFrame();//goes one step back to parent frame - parentFrame() method from selenium4
        driver.switchTo().frame(driver.findElement(frameMiddle)); //switch to middle frame - child
        System.out.println("middle: "+driver.findElement(middleBodyTxt).getText());

     //   driver.switchTo().defaultContent();//goes back to webpage to go to another parent frame from webpage
        //OR
        driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();

        driver.switchTo().frame(driver.findElement(frameBottom)); //switch to bottom frame - another parent
        System.out.println("bottom: "+driver.findElement(bottomBodyTxt).getText());
        driver.switchTo().defaultContent(); //goes back to webpage

        driver.quit();



        }
}
