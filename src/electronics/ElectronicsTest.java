package electronics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class ElectronicsTest extends Utility {
    String baseUrl = " https://demo.nopcommerce.com/";

    //Open the Browser
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() throws InterruptedException {
        //1.1 Mouse Hover on “Electronics” Tab
        WebElement ele = driver.findElement(By.xpath("//a[text()='Electronics ']"));
        WebElement cell = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        // Using Action Class to perform the actions
        Actions actions = new Actions(driver);
        actions.moveToElement(ele).moveToElement(cell).click().build().perform();
        //1.2 Mouse Hover on “Cell phones” and click
        // Expected Text for Cell
        String expectedCellPhoneText = "Cell phones";
        //ActualText for Cell
        String actualCellPhoneText = driver.findElement(By.xpath("//h1[contains(text(),'Cell phones')]")).getText();
        //Comparing Expected and actual cell phone text
        Assert.assertEquals(expectedCellPhoneText, actualCellPhoneText);

        //2.4 Clicking on List View Tab
        driver.findElement(By.xpath(" //a[contains(text(),'List')]")).click();
        Thread.sleep(2000);
        //2.5 Clicking on product name “Nokia Lumia 1020” link
        driver.findElement(By.xpath("//a[contains(text(),'Nokia Lumia 1020')]")).click();
        // 2.6 Verifying the text “Nokia Lumia 1020”
        Thread.sleep(2000);
        //Expected Text result
        String expectedNokiaText = "Nokia Lumia 1020\n";
        // After verifying Actual text result
        String actualNokiaText = driver.findElement(By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]")).getText();
        //Comparing the result
        //Assert.assertEquals(expectedNokiaText,actualNokiaText);
        //2.7 Verifying the price “$349.00
        String expectedPrice = "$349.00";
        //Actual Price
        String actualPrice = driver.findElement(By.xpath(" //span[@id='price-value-20']")).getText();
        //Comparing the result
        Assert.assertEquals(expectedPrice, actualPrice);
        //2.8 Change quantity to 2

        driver.findElement(By.xpath("//input[@id='product_enteredQuantity_20']\n")).clear();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@id='product_enteredQuantity_20']")).sendKeys("2");
        //2.9 Click on “ADD TO CART” tab
        driver.findElement(By.id("add-to-cart-button-20")).click();
        //2.10 Verify the Message "The product has been added to your shopping cart" on Top  green Bar
        verifyTextElement("The product has been added to your shopping cart", By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"));
        Thread.sleep(2000);

        // After that close the bar clicking on the cross button.
        driver.findElement(By.xpath("//span[@class='close']")).click();
        // 2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button

        mouseHoverAndClickOnElement(By.xpath("//span[contains(text(),'Shopping cart')]"));

        driver.findElement(By.xpath("//button[contains(text(),'Go to cart')]")).click();

        //2.12 Verify the message "Shopping cart"

        verifyTextElement("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"));

        // 2.13 Verify the quantity is 2
        //verifyTextElement("2",By.xpath("//input[@id='itemquantity11274']"));

        //2.14 Verify the Total $698.00
        verifyTextElement("$698.00", By.xpath("//tbody/tr[1]/td[6]/span[1]"));

        //2.15 click on checkbox “I agree with the terms of service”
        driver.findElement(By.id("termsofservice")).click();

        //2.16 Click on “CHECKOUT”
        driver.findElement(By.id("checkout")).click();

        //2.17 Verify the Text “Welcome, Please Sign In!”

        //2.18 Click on “REGISTER” tab

        driver.findElement(By.xpath(" //button[contains(text(),'Register')]")).click();
        //2.19 Verify the text “Register”
        verifyTextElement("Register", By.xpath("//h1[contains(text(),'Register')]"));
        //2.20 Fill the mandatory fields
        driver.findElement(By.id("gender-male")).click();
        //entering First name
        driver.findElement(By.id("FirstName")).sendKeys("HareKrishna");
        //Entering Last Name
        driver.findElement(By.id("LastName")).sendKeys("RadheKishan");
        //Entering Email Id
        driver.findElement(By.xpath(" //input[@id='Email']")).sendKeys("HareKrishna@gmails.com");
        //Entering Password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("HARehares@123");
        //Reentering Password
        driver.findElement(By.xpath(" //input[@id='ConfirmPassword']")).sendKeys("HARehares@123");
        //2.21 Click on “REGISTER”Button
        driver.findElement(By.id("register-button")).click();
        //2.22 Verify the message “Your registration completed”
        verifyTextElement("Your registration completed", By.xpath("//div[@class='result']"));
        //2.23 Click on “CONTINUE” tab
        driver.findElement(By.xpath("//a[text()='Continue']")).click();
        //2.24 Verify the text “Shopping card”
        verifyTextElement("Shopping cart", By.xpath("//span[contains(text(),'Shopping cart')]"));
        //2.25 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

        //2.26 2.26 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        //2.27 Fill the Mandatory fields
        selectByVisibleTextFromDropDown(By.id("BillingNewAddress_CountryId"), "United Kingdom");
        sendTextToElement(By.id("BillingNewAddress_City"), "London");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "123 Downing Street");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "EC157YN");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "0135879213");

        ////2.28 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        //2.29 Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.id("shippingoption_2"));

        //2.30 2.30 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //2.31 Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));

        //2.32 Select “Visa” From Select credit card dropdown
        selectByVisibleTextFromDropDown(By.id("CreditCardType"), "Visa");

        //2.33 Fill all the details
        sendTextToElement(By.id("CardholderName"), "Mr Prime Patel");
        sendTextToElement(By.id("CardNumber"), "5356 6548 1418 5420");
        selectByVisibleTextFromDropDown(By.id("ExpireMonth"), "12");
        selectByVisibleTextFromDropDown(By.id("ExpireYear"), "2026");
        sendTextToElement(By.id("CardCode"), "456");

        //2.34 Click on CONTINUE
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        //2.35 Verify “Payment Method” is “Credit Card”
        verifyTextElement("Payment Method:", By.xpath("//span[contains(text(),'Payment Method:')]"));

        //2.36 Verify “Shipping Method” is “2nd Day Air”
        verifyTextElement("Credit Card", By.xpath("//span[contains(text(),'Credit Card')]"));
        verifyTextElement("Shipping Method:", By.xpath("//span[contains(text(),'Shipping Method:')]"));
        verifyTextElement("2nd Day Air", By.xpath("//span[contains(.,'2nd Day Air')]"));

        //2.37 Verify Total is “$698.00”
        verifyTextElement("$698.00", By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]"));

        //2.38 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        //2.39  Verify the Text “Thank You”
        verifyTextElement("Thank you", By.xpath("//h1[contains(text(),'Thank you')]"));

        //2.40 Verify the message “Your order has been successfully processed!”
        verifyTextElement("Your order has been successfully processed!", By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));

        //2.41 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        //2.42 Verify the text “Welcome to our store”
        verifyTextElement("Welcome to our store", By.xpath("//h2[contains(text(),'Welcome to our store')]"));

        //2.43 Click on “Logout” link
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));

        //2.44 Verify the URL is “https://demo.nopcommerce.com/”
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://demo.nopcommerce.com/");
    }

    public void tearDown() {
        driver.close();
    }


}













