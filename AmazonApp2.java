package webAppTest;

import java.io.File;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AmazonApp2 {

	WebDriver driver;

	//******************************  Launch Amazon Website  ****************************************//
	
	@BeforeSuite
	public void LaunchBrowser() {
		 
		driver= new ChromeDriver();
	}
	
	
	@Test(priority = 1)
	public void LaunchAmazon() throws Exception {

		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		Thread.sleep(2000);

	}

	//*******************************  Click Sign Up Page  ****************************************//
	@Test(priority = 2)
	public void SignupPage() throws Exception {

		WebElement signupPage = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		signupPage.click();
		Thread.sleep(2000);

	}

	//*************   Enter the Email   *****************// 
	@Test(priority = 3)
	@Parameters("Email")
	public void enterEmail(String Email) throws Exception {

		WebElement email = driver.findElement(By.id("ap_email"));
		email.sendKeys(Email+Keys.ENTER);
		Thread.sleep(2000);

	}

	//*************    Enter the Password   ***************//
	@Test(priority = 4)
	@Parameters("Password")
	public void password(String Password) throws Exception{

		WebElement password = driver.findElement(By.id("ap_password"));
		password.sendKeys(Password+Keys.ENTER);
		Thread.sleep(2000);
	}	

	//***********    Select  the  Categories  *************//

	@Test(priority = 5)
	public void categories() throws Exception {

		WebElement search = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
		search.sendKeys("E"+Keys.ENTER);
		Thread.sleep(2000);
	}

	//************  Search the Product  *****************//
	@Test(priority = 6)
	@Parameters("Search2")
	public void searchBox(String Search2) throws Exception {

		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys(Search2 +Keys.ENTER);
		Thread.sleep(2000);
	}

	//*************   Click The  Product  *************//
	@Test(priority = 7)
	public void clickTheProduct() throws Exception {

		WebElement clickProduct = driver.findElement(By.xpath("//span[text()='4,999']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", clickProduct);
		Thread.sleep(2000);
		clickProduct.click();

	}


	//**************  Switch the Window and add the product to the cart *******************//
	@Test(priority = 8)
	public void addToCart() throws Exception {

		Set<String> handles = driver.getWindowHandles();

		for (String newWindow : handles) {

			driver.switchTo().window(newWindow);

		}

		WebElement addToCart = driver.findElement(By.id("add-to-cart-button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", addToCart);
		Thread.sleep(2000);
		addToCart.click();
		Thread.sleep(2000);

	}

	//*************************  Click the Cart  **************************//
	@Test(priority = 9)	
	public void clickCart() throws Exception{

		WebElement clickCart = driver.findElement(By.id("nav-cart-count"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", clickCart);
		clickCart.click();


	}


	//***************************  Take Screenshot *************************//
	@Test(priority = 10)
	public void screenShot() throws Exception   {

		TakesScreenshot screenshot = ((TakesScreenshot )driver);
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File("C:\\Users\\lenovo\\Pictures\\amazonCart2.png");
		FileHandler.copy(sourceFile,destinationFile);

	}

	//************************ Verify the product in the cart  ************************//
	@Test(priority = 11)
	public void verify() {

		WebElement watch = driver.findElement(By.xpath("//span[text()='4,999.00']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", watch);
		if (watch.isDisplayed()) {
			System.out.println("Product is added to cart");
		}

	}

	//************************* close the browser *********************************//
	@AfterSuite
	public void Close() {

		driver.quit();
	}

}
