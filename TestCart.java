package Cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TestCart {
    //1- new object
    WebDriver driver = new ChromeDriver();
    @BeforeTest
    public void setUp() throws InterruptedException {
        //2- navigate to website and maximize screen and sleep 3 seconds
        driver.navigate().to("https://www.saucedemo.com/");

        driver.manage().window().maximize();
        Thread.sleep(1500);

        //3- Enter username and password
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }
   @Test
    public void testItem() throws InterruptedException {
       // 4-add item in cart
       String itemName= "Sauce Labs Fleece Jacket";
       String button="//div[text()='%s']/parent::a/parent::div/following-sibling::div/button";
       String itemButton=String.format(button,itemName);
       driver.findElement(By.xpath(itemButton)).click();
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

       driver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]")).click();
       driver.findElement(By.xpath("//div[text()='Sauce Labs Fleece Jacket']"));
       //List<WebElement> items = driver.findElements(By.xpath("//div[text()='Sauce Labs Fleece Jacket']"));
       Thread.sleep(3000);
       String namelocator="//div[text()='%s']";
       String nameformat=String.format(namelocator,itemName);
       List<WebElement> itemList = driver.findElements(By.xpath(nameformat));
       //5- check item in the cart or not
       if (!itemList.isEmpty()) {
           System.out.println("The item exists in the cart.");
       } else {
           System.out.println("The item doesn't exist in the cart.");
       }
       //6- close driver
       driver.quit();
   }
}
