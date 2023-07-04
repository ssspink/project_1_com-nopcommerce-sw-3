package computer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {
    String baseUrl = " https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }


    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        //Selecting on Computer menu
        selectMenu("Computers");
        //clicking on Element using X path
        clickOnElement(By.xpath("//a[text()=' Desktops ']"));
        //

        WebElement dropDown = driver.findElement(By.id("products-orderby"));
        //Using select class for dropdown
        Select select = new Select(dropDown);
        //Receiving list Z to A
        select.selectByVisibleText("Name: Z to A");

        //1.4 Verify the Product will arrange in Descending order.
        List<WebElement> beforeFilterNameZtoAList = driver.findElements(By.xpath("item-grid"));
        List<Double> beforeFileNameZtoAList = new ArrayList<>();
        for (WebElement nameZtoA : beforeFilterNameZtoAList) {
            beforeFileNameZtoAList.add(Double.valueOf(nameZtoA.getText().replace("$", "")));
        }
        // selectByVisibleTextFromDropDown(By.xpath("//select[@id='products-orderby']"),"Name: Z to A");
        //Sel.selectByVisibleText("Name: Z to A");

        List<WebElement> afterFilterNameZtoAList = driver.findElements(By.xpath("item-grid"));
        List<Double> afterFileNameZtoAList = new ArrayList<>();
        for (WebElement nameZtoA : afterFilterNameZtoAList) {
            afterFileNameZtoAList.add(Double.valueOf(nameZtoA.getText().replace("$", "")));
        }

        Collections.sort(beforeFileNameZtoAList);
        Assert.assertEquals(beforeFilterNameZtoAList, afterFilterNameZtoAList);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //Clicking on computer Menu
        selectMenu("Computers");
        //Clicking on Dektops
        clickOnElement(By.xpath("//a[text()=' Desktops ']"));

        WebElement dropDown = driver.findElement(By.id("products-orderby"));
        //Using select class for dropdown
        Select select = new Select(dropDown);
        //Receiving list Name: A to Z
        select.selectByVisibleText("Name: A to Z");
        //Putting sleep to wait
        Thread.sleep(3000);
        //2.4Click on cart Button
        driver.findElement(By.xpath(("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//button[1]"))).click();

        //Verifying The text
        String expectedResult = "Build your own computer";
        String actualResult = driver.findElement(By.xpath("//h1[normalize-space()='Build your own computer']")).getText();
        Assert.assertEquals(expectedResult, actualResult);


        //2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class

        WebElement dropDown1 = driver.findElement(By.name("product_attribute_1"));
        //Using Select class
        Select select1 = new Select(dropDown1);
        //Selecting product using Select class
        select1.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");
        Thread.sleep(5000);
        //2.7 Selecting value using Select class
        //selectByVisibleTextFromDropDown(By.id("product_attribute_2"),"8GB [+$60.00]");
        WebElement selectRam = driver.findElement(By.name("product_attribute_2"));
        //Using select class
        Select select2 = new Select(selectRam);
        //Select the value
        select2.selectByVisibleText("8GB [+$60.00]");
        //2.8 Selecting HDD radio "400 GB [+$100.00]"
        clickOnElement(By.id("product_attribute_3_7"));
        //2.9 Selecting  OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.id("product_attribute_4_9"));
        //2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander //[+$5.00]"
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox' and contains(@name,'product_attribute_5')]"));

        for (WebElement chbox : checkboxes) {
            String checkbox = chbox.getAttribute("id");
            if (checkbox.equals("product_attribute_5_10") || checkbox.equals("product_attribute_5_12"))
                chbox.click();
        }


        //2.11 Verify the price "$1,470.00"
        verifyTextElement("$1,470.00", By.id("price-value-1"));

      /* driver.findElement(By.xpath("//span[@id='price-value-1']"));
       String expectedPrice= "$1,430.00";
       String actualPrice= driver.findElement(By.className("product-price")).getText();
       Assert.assertEquals(expectedPrice,actualPrice);*/


        //2.12 Click on "ADD TO CARD" Button.
        clickOnElement(By.id("add-to-cart-button-1"));

        //2.13 Verify the Message "The product has been added to your shopping cart" on Top green Bar

        String expectedMessage2 = "The product has been added to your shopping cart";
        String actualMessage2 = getTextFromElement(By.xpath("//p[@class='content']"));

        //checking actual and expected result
        //Thread.sleep(2000);
        Assert.assertEquals(expectedMessage2, actualMessage2);
        //close the bar clicking on the cross button
        clickOnElement(By.xpath("//span[@class='close']"));


        //2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button
        WebElement shoppingCart = driver.findElement(By.xpath("//span[@class='cart-label']"));
        WebElement goToCart = driver.findElement(By.xpath("//button[normalize-space()='Go to cart']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCart).build().perform();
        goToCart.click();

        //2.15 Verify the message "Shopping cart"
        String expectedText = "Shopping cart";
        String actualText = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        Assert.assertEquals("", expectedText, actualText);

        //  2.16	Change the Qty to "2" and Click on "Update shopping cart"

        driver.findElement(By.xpath("//input[@class='qty-input']")).clear();
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        clickOnElement(By.xpath("//button[@id='updatecart']"));


        // 2.17	Verify the Total"$2,950.00"
        String expectedValue = "$2,850.00";
        Thread.sleep(5000);
        String actualValue = getTextFromElement(By.xpath("//span[@class='value-summary']"));
        // Assert.assertEquals("", expectedValue, actualValue);

        //  2.18 Clicking on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));

        // 2.19	Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        //   2.20	Verify the Text “Welcome, Please Sign In!”
        String expectedText2 = "Welcome, Please Sign In!";
        String actualText2 = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        Assert.assertEquals("", expectedText2, actualText2);

        // 2.21Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));

        // 2.22 Fill the all mandatory field
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "pinkal");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "patel");
        sendTextToElement(By.xpath("//input[@type='email']"), "pinks1111@gmail.com");
        sendTextToElement(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "India");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "22, Baker Street");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "NW1 7jr");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "0987232323");

        //2.23	Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        // 2.24	Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.xpath("//input[@id='shippingoption_1']"));

        //2.25	Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));


        //2.26	Select Radio Button “Credit Card”
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));


        //2.27	Select “Master card” From Select credit card dropdown
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Master card");

        // 2.28	Fill all the details
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Mr John Smith");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5521573041475125");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireMonth']"), "05");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2025");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "123");

        // 2.29	Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        // 2.30 Verify “Payment Method” is “Credit Card”
        String expectedText4 = "Credit Card";
        String actualText4 = getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]"));
        Assert.assertEquals("", expectedText4, actualText4);

        // 2.32	Verify “Shipping Method” is “Next Day Air”
        String expectedText5 = "Next Day Air";
        String actualText5 = getTextFromElement(By.xpath("//span[normalize-space()='Next Day Air']"));
        Assert.assertEquals("", expectedText5, actualText5);


        // 2.33	Verify Total is “$2,950.00”
        String expectedValue1 = "$2,940.00";
        Thread.sleep(5000);
        String actualValue1 = getTextFromElement(By.xpath("//span[@class='value-summary']"));
        Assert.assertEquals("", expectedValue, actualValue);

        //2.34	Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        // 2.35	Verify the Text “Thank You”
        String expectedText6 = "Thank you";
        String actualText6 = getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]"));
        Assert.assertEquals("", expectedText6, actualText6);

        // 2.36	Verify the message “Your order has been successfully processed!”
        String expectedText7 = "Your order has been successfully processed!";
        String actualText7 = getTextFromElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));
        Assert.assertEquals("", expectedText7, actualText7);

        // 2.37	Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        // 2.37 Verify the text “Welcome to our store”
        String expectedText8 = "Welcome to our store";
        String actualText8 = getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]"));
        Assert.assertEquals("", expectedText8, actualText8);

    }

    public void tearDown() {
        driver.close();
    }
}


