package BookStore.Book;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UserLogin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//added WebdriverManager dependency
		WebDriverManager.chromedriver().setup(); 
		//Initializing ChromeDriver instance
		WebDriver driver=new ChromeDriver();
		
		//navigating to book webApplication
		driver.get("http://bookstore-url/");
		
		//Maximizing the browser window
		driver.manage().window().maximize();

		
//*********** 2. USER lOGIN PAGE***********************		
		//WebElements for valid inputs
		
		driver.findElement(By.id("email")).sendKeys("ram@gmail.com");
		driver.findElement(By.id("password")).sendKeys("abcd@1234");
		driver.findElement(By.linkText("login")).click();
		
		//Sometimes application takes sometime in verifying data and then in navigation to homepage 
		// that's why added explicit wait for 5 seconds
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='About Us']")));
		driver.getTitle();
		
		//WebElements for invalid inputs
		driver.findElement(By.id("email")).sendKeys("ram@gmail");
		driver.findElement(By.id("password")).sendKeys("abcd");
		driver.findElement(By.linkText("login")).click();
		
		
		// Wait for the error message element to be visible
        WebElement errorMessage = driver.findElement(By.id("error-message"));
        String errorText = errorMessage.getText();

        // Verify that the error message is displayed and contains expected text
        if (errorText.contains("Incorrect password")) {
            System.out.println("Error message for incorrect password is displayed.");
        } else {
            System.out.println("Error message for incorrect password is not displayed.");
        }
        
        // Same above thing Verify that the error message is now done by SoftAssert
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(errorText.contains("Incorrect password"), "Error message for incorrect password is displayed."); 
        softAssert.assertAll(); 

        // Close the browser/all windows
        driver.quit();


	}

}
