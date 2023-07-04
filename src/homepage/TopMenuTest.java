package homepage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {


    String baseUrl = " https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    @Test
    public void verifyPageNavigation() {
        selectMenu("Computers");
        String expectedTitle = "Computers";
        String actualTitle = driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(actualTitle, expectedTitle);


    }


}