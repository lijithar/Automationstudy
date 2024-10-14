package POC;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Application {
		
	WebDriver driver;
	
	@BeforeMethod()
	public void start()
	{
		driver = new ChromeDriver();		
		driver.get("https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC");
		driver.manage().window().maximize();
	}
	
	@Test(priority=1)
	public void registeruser() throws InterruptedException
	{
		 driver.findElement(By.linkText("Register")).click();
		 driver.findElement(By.id("customer.firstName")).sendKeys("Lijitha");
		 driver.findElement(By.id("customer.lastName")).sendKeys("Raju");
		 driver.findElement(By.id("customer.address.street")).sendKeys("KK house");
		 driver.findElement(By.id("customer.address.city")).sendKeys("Kozhikode");
		 driver.findElement(By.id("customer.address.state")).sendKeys("Kerala");
		 driver.findElement(By.id("customer.address.zipCode")).sendKeys("234432");
		 driver.findElement(By.id("customer.phoneNumber")).sendKeys("9287348987");
		 driver.findElement(By.id("customer.ssn")).sendKeys("777");
		 driver.findElement(By.id("customer.username")).sendKeys("kavya");
		 driver.findElement(By.id("customer.password")).sendKeys("lijitha@123");
		 driver.findElement(By.id("repeatedPassword")).sendKeys("lijitha@123");
		 driver.findElement(By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input")).click();
		 Thread.sleep(3000);
		 String message=driver.findElement(By.xpath("//h1[@class='title']")).getText();
		 String actualmsg="Welcome"; 
		 Assert.assertTrue(message.contains(actualmsg),"Exepcted and actual texts are not same");
	}	
	

	@Test(priority=2)
	public void Login() throws InterruptedException 
	{ 
	    driver.findElement(By.name("username")).sendKeys("lijitha");
	    driver.findElement(By.name("password")).sendKeys("lijitha@123");
	    driver.findElement(By.xpath("//input[@value='Log In']")).click();
	    Thread.sleep(3000);
	 
	}
	
	@Test(priority=3)
	public void accountsoverview() throws InterruptedException
	{
		Login();
		WebElement table = driver.findElement(By.id("accountTable"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for (WebElement row : rows) 
		{
          List<WebElement> cells = row.findElements(By.tagName("td")); 
          for (WebElement cell : cells) 
          {
                System.out.println(cell.getText());
          }
        }
		driver.findElement(By.linkText("28440")).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(3000);
	}

	@AfterMethod()
	public void close()
	{
		driver.close();
	}
}
