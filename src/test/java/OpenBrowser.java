import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Message;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
public class OpenBrowser {
WebDriver driver = null;
LoginPage login;
    @BeforeTest
    public void openBrowser() throws InterruptedException{
    // 1- Bridge between test Scripts and Browsers
    String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
    System.out.println(chromePath);
    System.setProperty("webdriver.chrome.driver", chromePath);
    // 2- New object for WebDriver
    driver = new ChromeDriver();
    // 3- Navigate to Google Website and Maximize Screen and Sleep 3 Seconds
    driver.navigate().to( "https://the-internet.herokuapp.com/login");
    driver.manage().window().maximize();
    Thread.sleep(3000);
    // 4- Create New Objects
    login = new LoginPage();
    }

    @Test
    public void ValidData() throws InterruptedException {
    driver.navigate().to( "https://the-internet.herokuapp.com/login");
    login.LoginSteps(driver,"tomsmith", "SuperSecretPassword!");

    Thread.sleep(3000);
    String expectedResult = "You logged into a secure area!";
    String actualResult = driver.findElement(login.flashPOM()).getText();
    //  Hard Assertion
    // First Assertion
    System.out.println("First Assertion");
    Assert.assertEquals(actualResult.contains(expectedResult), true);
    assertTrue(actualResult.contains(expectedResult));
    // Second Assertion
    System.out.println("Second Assertion");
    Assert.assertTrue(driver.findElement(login.logoutPOM()).isDisplayed());
    // Third Assertion
    System.out.println("Third Assertion");
    System.out.println(driver.getCurrentUrl());
    Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure");
    }
    @Test
    public void invalidData() {
    driver.navigate().to( "https://the-internet.herokuapp.com/login");
    login.LoginSteps(driver,"tom", "Super");
    String actualResult = driver.findElement(login.flashPOM()).getText();
    System.out.println("actualResult" + actualResult);
    String expectedResult = "Your username is invalid!";
    Assert.assertTrue(actualResult.contains(expectedResult), "Error Message: Text is Wrong");
}
    @AfterTest
    public void closedriver (){

}
}
