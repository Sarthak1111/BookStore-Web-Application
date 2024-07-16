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

public class UserRegistration {

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
		
//*********** 1. USER REGISTRATION PAGE***********************		
		//WebElements for valid inputs
		driver.findElement(By.name("firstname")).sendKeys("Ram");
		driver.findElement(By.name("lastname")).sendKeys("Raj");
		driver.findElement(By.id("email")).sendKeys("ram@gmail.com");
		driver.findElement(By.id("password")).sendKeys("abcd@1234");
		
		//Selecting "India" country from AutoSuggestion dropDown used Actions class instance
		Actions actions=new Actions(driver);
		actions.sendKeys(driver.findElement(By.xpath("//div[@class=\"form-group\"]/input")), "ind").build().perform();
		
		// Locate the dropDown element by its ID or other suitable selector
        WebElement dropdownElement = driver.findElement(By.id("dropdown-id"));
		//Selecting "Karnataka" state from Static-Dropdown by using Select class instance
		Select staticDropdown=new Select(dropdownElement);
		staticDropdown.selectByValue("Karnataka");
		//Select user checkBox whether user is student or employee
		Assert.assertTrue(driver.findElement(By.cssSelector("input[id='Student']")).isSelected());
		driver.findElement(By.className("phoneNumber")).sendKeys("1234567890");
		driver.findElement(By.linkText("Register")).click();
		
		
		//WebElements for invalid inputs
		driver.findElement(By.name("firstname")).sendKeys("@#$%&^&YFTC"); //person name can't have special characters
		driver.findElement(By.name("lastname")).sendKeys("  ; jbjbj@"); //person name can't have special characters
		driver.findElement(By.id("email")).sendKeys("ram@.com"); //domain name is mandatory
		driver.findElement(By.id("password")).sendKeys("abcd"); //minimum password length 8 characters
		driver.findElement(By.className("phoneNumber")).sendKeys("1as2^3456"); //phone number should have only 10 numeric values
		driver.findElement(By.linkText("Register")).click();

		
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
