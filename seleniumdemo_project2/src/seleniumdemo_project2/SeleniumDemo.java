package seleniumdemo_project2;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

//import seleniumdemo.String;

public class SeleniumDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		 System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		 WebDriver driver = new ChromeDriver();
		// Maximize browser
		 driver.manage().window().maximize();
		 driver.get("https://www.groupon.com/");
		 String i = driver.getCurrentUrl();
		 System.out.println(i);
		 driver.findElement(By.id("nothx")).click();
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("ls-user-signin")).click();
		 
		 driver.findElement(By.id("email-input")).sendKeys("abc@gmail.com");
		 driver.findElement(By.id("password-input")).sendKeys("abc123");
		 driver.findElement(By.id("signin-button")).click();
		 
		 if(driver.getCurrentUrl().equals("https://www.groupon.com/")) {
			 System.out.println("Link matched");
			 } else {
			 System.out.println("Link did not match");
			 return ;
			 }
		 Thread.sleep(1000);
		 driver.findElement(By.id("ls-footer-giftcards")).click();
		 
		 if(driver.getCurrentUrl().equals("https://www.groupon.com/giftcards")) {
			 System.out.println("Link matched");
			 } else {
			 System.out.println("Link did not match");
			 return ;
			 }
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("send_ecard")).click();
		 
		 ArrayList<String> tabs3 = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs3.get(1));
		    
	        
		 Thread.sleep(1000);
		
		 driver.findElement(By.id("message")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("message")).sendKeys("Happy birthday!! Enjoy your gift!");
		 Thread.sleep(2000);
		 driver.findElement(By.id("design-cat2")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.id("img_DD8CB4JYD")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("amount")).click();
		 driver.findElement(By.id("amount-25")).click();
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("name")).click();
		 driver.findElement(By.id("name")).sendKeys("Arpita");
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("email")).click();
		 driver.findElement(By.id("email")).sendKeys("arpita.dixit@sjsu.edu");
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("from_name")).click();
		 driver.findElement(By.id("from_name")).sendKeys("Chaya");
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("order-check-out")).click();
		 
		 if(driver.getCurrentUrl().equals("https://groupon.cashstar.com/gift-card/payment/")) {
			 System.out.println("Link matched");
			 } else {
			 System.out.println("Link did not match");
			 return ;
			 }
		 Thread.sleep(1000);
		 
		 driver.findElement(By.id("id_email")).click();
		 driver.findElement(By.id("id_email")).sendKeys("chaya.malik89@gmail.com");
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("id_credit_card_number")).click();
		 driver.findElement(By.id("id_credit_card_number")).sendKeys("1234 1234 1234 1234");
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("id_first_name")).click();
		 driver.findElement(By.id("id_first_name")).sendKeys("Chaya");
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("id_last_name")).click();
		 driver.findElement(By.id("id_last_name")).sendKeys("Malik");
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("id_street")).click();
		 driver.findElement(By.id("id_street")).sendKeys("SJSU");
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("id_city")).click();
		 driver.findElement(By.id("id_city")).sendKeys("San Jose");
		 
		 Thread.sleep(1000);
		 Select dropdown = new Select(driver.findElement(By.id("id_state")));
		 dropdown.selectByVisibleText("California");
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("id_zip_code")).click();
		 driver.findElement(By.id("id_zip_code")).sendKeys("95112");
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("id_phone_number")).click();
		 driver.findElement(By.id("id_phone_number")).sendKeys("1023456789");
		 
		 Thread.sleep(1000);
		 driver.findElement(By.id("order-submit")).click();
		 
		 Thread.sleep(1000);
		 driver.close();
		 driver.switchTo().window(tabs3.get(0));
		 Thread.sleep(1500);
		 driver.close();
		 

	}

}
