import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public WebElement usernamePOM (WebDriver driver){
        By username = By.id("username");
        WebElement usernameEle = driver.findElement(username);
        return usernameEle;
    }
public WebElement passwordPOM (WebDriver driver)
{
        return driver.findElement(By.name("password"));
    }

    public By flashPOM ()
    {
        return By.id("flash");
    }
    public By logoutPOM ()
    {
    return By.cssSelector("href=\"/logout\"");
    }


    public void LoginSteps(WebDriver driver, String username, String password)
    {
    // Enter Username using POM
    usernamePOM(driver).clear();
    usernamePOM(driver).sendKeys(username);

    // Enter Password using POM
    passwordPOM(driver).sendKeys(password);
    passwordPOM(driver).sendKeys(Keys.ENTER);

    }
}
