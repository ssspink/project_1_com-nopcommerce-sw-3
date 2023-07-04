package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {
    /**
     * This Method will click on Log in links
     */

    public void clickOnElement(By by) {
        driver.findElement(by).click();

    }

    /**
     * This Method will get Text
     */
    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }


    /**
     * This Method will send text to send
     */

    public void sendTextToElement(By by, String text) {

        driver.findElement(by).sendKeys("text");

    }

    //This method will select the option by visible text

    public void selectByVisibleTextFromDropDown(By by, String text) {

        WebElement dropDown = driver.findElement(By.name("country"));
        Select select = new Select(dropDown);
        select.selectByVisibleText("text");

    }


    public void selectByValueFromDropDown(By by, String value) {
        new Select(driver.findElement(by)).selectByValue(value);

    }


    public void selectByIndexFromDropDown(By by, String index) {
        new Select(driver.findElement(by)).selectByValue(index);

    }

    //*******************Alert Methods*****************************************
    //*************************Select class Method***********************************
    //This mathod will switch
    public void switchToAlert() {
        driver.switchTo().alert();
    }

    //This method will accept element
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    //This Method will Dismissed the alert
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    //This Method will get text from Element
    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    //This Method will send the text
    public void sendKeysToAlert(String keys) {
        driver.switchTo().alert().sendKeys(keys);
    }


    public void selectMenu(String menu) {

        driver.findElement(By.xpath("//ul[@class='top-menu notmobile']/li/a[text() = '" + menu + " ']")).click();
    }

         //This  method will verify Text by Assert
        public void verifyTextElement(String Text, By by) {
            String expectedText = Text;
            String actualText = getTextFromElement(by);
            Assert.assertEquals(actualText, expectedText);

        }
           // 5. This method will mouse hover and click on element
            public void mouseHoverAndClickOnElement(By by){
                Actions actions = new Actions(driver);
                actions.moveToElement(driver.findElement(by)).click().build().perform();
            }

       /* //1. This method will find the element  and click on that element
        public void clickOnElement(By by) {
            driver.findElement(by).click();
        }

        // 2. This method will send text to the element
        public void sendTextFromElement(By by, String text) {
            driver.findElement(by).sendKeys(text);
        }

        // 3. This method will get the text from an element
        public String getTextFromElement(By by) {
            return driver.findElement(by).getText();

        }

        // 4. This method will mouse hover on the element
        public void mouseHoverOnElement(By by) {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(by)).build().perform();
            // To do in one line  1. and 2. Together
            // actions.moveToElement(computer).moveToElement(software).click().build().perform();
        }
        5. This method will mouse hover and click on element
        public void mouseHoverAndClickOnElement(By by){
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(by)).click().build().perform();
        }
        // 6. This method will select the option from the dropdown by visible Text
        public void selectFromVisibleTextFromDropdown(By by,String text){
            WebElement dropdown = driver.findElement(by);
            Select select = new Select(dropdown);
            select.selectByVisibleText(text);
        }
        //7. This method select the option from the dropdown by visible Value
        public void selectByValueFromDropDown(By by, String value){
            WebElement dropdown = driver.findElement(by);
            Select select = new Select(dropdown);
            select.selectByValue(value);
        }

        //8. This method select the option from the dropdown by visible Index
        public void selectByIndexFromDropDown(By by, String index) {
            WebElement dropdown = driver.findElement(by);
            Select select = new Select(dropdown);
            select.selectByValue(index);

        }
        // 9. This  method will verify Text by Assert
        public void verifyTextElement(String Text, By by){
            String expectedText = Text;
            String actualText = getTextFromElement(by);
            Assert.assertEquals(actualText,expectedText);
        }
        //10. This method will select Menu and click
        public void selectMenuAndClick1(String menu) {
            WebElement menu1= driver.findElement(By.linkText(menu));
            Actions actions = new Actions(driver);
            actions.moveToElement(menu1).click().build().perform();
        }

    */



    }


