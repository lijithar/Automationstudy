package POC;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Dredingapp {

	WebDriver driver;
	
	
	@BeforeTest()
	public void start()
	{
		driver = new ChromeDriver();
		driver.get("https://ui.kmb-dev.ults.build/login");
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider="logindata",dataProviderClass=Utilitycls.class,priority=1)
	public void Login(String UserName,String Password) throws InterruptedException 
	{ 
	    driver.findElement(By.id("userName")).sendKeys(UserName);
	    driver.findElement(By.id("password")).sendKeys(Password);
	    driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[2]/form/div[5]/button")).click();
	    Thread.sleep(3000);	 
	}
	
	@Test(priority=2)
	public void sandbooking()
	{
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/aside/div/nav/ul/div[4]/li/a/span[2]")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		
		WebElement dropdown =wait.until(ExpectedConditions.elementToBeClickable(By.name("port")));		
		Select dropdown1 = new Select(dropdown);
		dropdown1.selectByIndex(7);
		
		WebElement dropdown2 =wait.until(ExpectedConditions.elementToBeClickable(By.name("zone")));		
		Select dropdown3 = new Select(dropdown2);
		dropdown3.selectByIndex(4);
		
		driver.findElement(By.id("distance")).sendKeys("250");
		driver.findElement(By.id("unloadingPlace")).sendKeys("ummalathoor");
		
		WebElement dropdown4=wait.until(ExpectedConditions.elementToBeClickable(By.name("tonRequired")));
		Select dropdown5=new Select(dropdown4);
		dropdown5.selectByIndex(3);
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div/div/form/div[2]/button")).click();
		
		String msg= driver.findElement(By.xpath("/html/body/div[3]/div/div")).getText();
        //String warningMessage = popup.findElement(By.className("messageClass")).getText(); 
        System.out.println("Warning Message: " +msg);
		
        
        
		
	}
		
	                
	
	
	
	
	
	
	
	
	
	
	
}
