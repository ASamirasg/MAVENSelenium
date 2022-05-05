import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertTrue;


public class OpenBrowserSoft {

WebDriver driver = null;
    @BeforeTest
    public void openBrowser() throws InterruptedException{
        String chromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.out.println(chromePath);

        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();

        driver.navigate().to( "https://the-internet.herokuapp.com/login");
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }
    @Test
    public void ValidData(){
      driver.navigate().to( "https://the-internet.herokuapp.com/login");
      driver.findElement(By.id("username")).clear();
      driver.findElement(By.id("username")).sendKeys("tomsmith");
      driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
      driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
      String expectedResult = "You logged into a secure area!";
      String actualResult = driver.findElement(By.id("flash")).getText();
    // ** Soft Assertion
        SoftAssert soft = new SoftAssert();
    // First Assertion
      System.out.println("First Assertion");
      soft.assertEquals(actualResult.contains(expectedResult), true, "First Assertion Equals");
      soft.assertTrue(actualResult.contains(expectedResult), "First Assertion True");
    // Second Assertion
      System.out.println("Second Assertion");
      soft.assertTrue(driver.findElement(By.cssSelector("href=\"/logout\"")).isDisplayed(), "Second Assertion");
    // Third Assertion
       System.out.println("Third Assertion");
       System.out.println(driver.getCurrentUrl());
       soft.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure", "Third Assertion");
    // Assert All
        soft.assertAll();
    }
    @Test
    public void invalidData() {
        driver.navigate().to( "https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("Invalid");
        driver.findElement(By.name("password")).sendKeys("Super");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);

        String actualResult = driver.findElement(By.id("flash")).getText();
        System.out.println("actualResult" + actualResult);
        String expectedResult = "Your username is invalid!";
        Assert.assertTrue(actualResult.contains(expectedResult), "Error Message: Text is Wrong");




}
    @AfterTest
    public void closedriver (){

}
}
