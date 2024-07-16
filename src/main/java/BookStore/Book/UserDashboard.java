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

public class UserDashboard {

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

//*********** 3. USER DASHBOARD PAGE***********************		
        // Verify user profile data display(valid input)
        WebElement usernameElement = driver.findElement(By.id("username"));
        String usernameText = usernameElement.getText();
        //User asserEquals to compare Actual and Expected Text
        Assert.assertEquals("Ram", usernameText);
        System.out.println("Username displayed on dashboard: " + usernameText);
        
        //Navigate to a specific section (e.g., My Orders)
        WebElement myOrdersLink = driver.findElement(By.linkText("My Orders"));
        myOrdersLink.click();
        
        
        //Refreshing the My Orders page
        driver.navigate().refresh();
        
        // Navigate back to the Dashboard
        driver.navigate().back();
        
        //Verify data display in My Profile section
        WebElement profileHeader = driver.findElement(By.tagName("h2"));
        if (profileHeader.getText().equals("My Profile")) {
            System.out.println("Navigated to My Profile section successfully.");
            // Additional verification logic for profile data can be added here
        } else {
            System.out.println("Failed to navigate to My Profile section.");
        }

        // Close the browser/all windows
        driver.quit();


	}

}
