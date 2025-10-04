package frames;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

//switch to frame by index
public class FrameIndex {
    @Test
    public  void sampleTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://docs.oracle.com/javase/8/docs/api/");
        driver.manage().window().maximize();

        By frameset = By.xpath("//frameset//frame");
        By title = By.xpath("//h1[@class='title']");


        int index = -1;
        int frameSize = driver.findElements(frameset).size();
        System.out.println(frameSize);
        for(int i=0; i<frameSize; i++) {
            driver.switchTo().defaultContent();
            driver.switchTo().frame(i);
            if(driver.findElements(title).size() > 0) {
                index = i;
                break;
            }
        }
        System.out.println(index);

    }
}
