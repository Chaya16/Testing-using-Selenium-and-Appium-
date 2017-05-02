package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Testcase1 {

	private final static String LINK1_URL ="https://www.groupon.com/checkout/cart";
	private final static String LINK2_URL = "https://www.groupon.com/cart";
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:/Users/Chaya/Desktop/chromedriver_win32 (1)/chromedriver.exe");
		 //ChromeOptions options = new ChromeOptions();
		 //options.addArguments("--kiosk");
		 WebDriver driver = new ChromeDriver(); 
		 driver.manage().window().maximize();
		 driver.get("https://www.groupon.com/");
		 Thread.sleep(1000);
		 try
		 {
			 driver.findElement(By.id("nothx")).click();
		 }
		 catch(NoSuchElementException e){
		      System.out.println("Sign up pop Up not shown");
		 }
		 Thread.sleep(1000); 
		 driver.findElement(By.id("ls-user-signin")).click();
		 //driver.findElement(By.xpath("//*[@id='ls-user-signin']")).click();
		 driver.findElement(By.id("email-input")).sendKeys("chaya.malik89@gmail.com");
		 driver.findElement(By.id("password-input")).sendKeys("*****");
		 Thread.sleep(1000); 
		 driver.findElement(By.id("signin-button")).click();
		 Thread.sleep(1000); 
		 driver.findElement(By.id("search")).sendKeys("Umbrella");
		 Thread.sleep(1000); 
		 driver.findElement(By.id("ls-header-search-button")).click();
		 Thread.sleep(1000); 
		 try
		 {
			 Select dropdown = new Select(driver.findElement(By.id("grpn-sorts-select"))); 
			 dropdown.selectByValue("price:asc");
		 }
		 catch(NoSuchElementException e){
		        System.out.println("Sort element not found");
		 }
		 Thread.sleep(2000);    
		 driver.findElement(By.className("cui-view-deal")).click();
		 Thread.sleep(2000); 
		 try
		 {
			 Select colordropdown = new Select(driver.findElement(By.xpath("//*[@id=\"trait-0\"]")));
			 colordropdown.selectByIndex(2);
		 }
		 
		 catch(NoSuchElementException e){
		     System.out.println("Colour element not found");
		 }
		 Thread.sleep(1000); 
		 driver.findElement(By.id("buy-link")).click();
		 Thread.sleep(1000); 
		 try
		 {
			 Select qtydropdown = new Select(driver.findElement(By.cssSelector("#items-list > div > div > div.info-container.columns.nine > div > div.columns.twelve > select")));
			 qtydropdown.selectByIndex(2);
		 }
		 catch(NoSuchElementException e){
		     System.out.println("Quantity element not found");
		 }
		 Thread.sleep(1000); 
		 driver.findElement(By.id("top-continue-shopping")).click();
		 Thread.sleep(1000); 
		 driver.findElement(By.id("search")).sendKeys("Rain Coat");
		 Thread.sleep(1000); 
		 driver.findElement(By.id("ls-header-search-button")).click();
		 Thread.sleep(1000); 
		 driver.findElement(By.id("ls-cart-link")).click();
		 if(driver.getCurrentUrl().equals(LINK2_URL)) {
			 System.out.println("Cart link matched");
			 } else {
			 System.out.println("Cart link did not match");
			 driver.close();
			 }
		 driver.findElement(By.id("bottom-proceed-to-checkout")).click();
		 Thread.sleep(1000);
		 if(driver.getCurrentUrl().equals(LINK1_URL)) {
			 System.out.println("Checkout Link matched");
			 } else {
			 System.out.println("Checkout link did not match");
			 driver.close();
			 }
		 driver.findElement(By.xpath("//*[@id=\"mod-overview\"]/div/div[2]/div/button")).click();		
		 //System.out.println(i);
		 Thread.sleep(2000);  
		 driver.close();
	}

}