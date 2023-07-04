package browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    //Defining Driver
    public static WebDriver driver;
    //Set Browser
    public void openBrowser(String baseUrl) {
        //Set up the browser
        driver = new ChromeDriver();
        //Getting Url
        driver.get(baseUrl);
        //Maximizing window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

    }
}