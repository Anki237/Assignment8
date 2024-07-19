package test5;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Mutipletestng {
	WebDriver driver;
	@BeforeTest
	public void bt() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	 @Test(priority = 1, groups = {"checkbox"})
	    public void testSingleCheckbox() {
	        WebElement checkbox1 = driver.findElement(By.id("checkBoxOption1"));
	        if (!checkbox1.isSelected()) {
	            checkbox1.click();
	        }
	    }

	    @Test(priority = 2, groups = {"checkbox"}, dependsOnMethods = {"testSingleCheckbox"})
	    public void testMultipleCheckboxes() {
	        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
	        for (WebElement checkbox : checkboxes) {
	            if (!checkbox.isSelected()) {
	                checkbox.click();
	            }
	        }
	    }

	    @Test(priority = 3, groups = {"radio"})
	    public void testSingleRadioButton() {
	        WebElement radioButton1 = driver.findElement(By.cssSelector("input[value='radio1']"));
	        if (!radioButton1.isSelected()) {
	            radioButton1.click();
	        }
	    }

	    @Test(priority = 4, groups = {"radio"}, dependsOnMethods = {"testSingleRadioButton"})
	    public void testMultipleRadioButtons() {
	        List<WebElement> radioButtons = driver.findElements(By.name("radioButton"));
	        for (WebElement radioButton : radioButtons) {
	            if (!radioButton.isSelected()) {
	                radioButton.click();
	                break;  // Select only the first one
	            }
	        }
	    }

	    @AfterMethod
		@AfterTest
	    public void tearDown() {
	        driver.close();
	    }
}
