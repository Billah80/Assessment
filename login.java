package practiceClass;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.json.simple.JSONObject;

public class login {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, InterruptedException {
		// TODO Auto-generated method stub
		
		//Driver Initialization
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//Window & Link
		
		driver.get("https://www.xampro.org/");
		
		//Creating Json Parser Object
		
		JSONParser jsonParser = new JSONParser();
		
		//Parsing the content of json file
		
		JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("json\\read.json")) ; 
		
		//Reading the data
		
		String fullname = (String) jsonObject.get("Full Name");
		String emailid = (String) jsonObject.get("Email Id");
		String number = (String) jsonObject.get("Phone Number");
		String password = (String) jsonObject.get("Password");
		String conpassword = (String) jsonObject.get("Confirm Password");
		
		WebElement clickbtn = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/button[1]"));
		clickbtn.click();
		WebElement clickbtn1 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/nav[1]/div[1]/div[1]/div[1]/div[4]/div[2]/a[2]/div[1]/span[1]"));
		clickbtn1.click();
		
		//Registering Using Json Data
		
		driver.findElement(By.id("name")).sendKeys(fullname);
		driver.findElement(By.id("email")).sendKeys(emailid);
		driver.findElement(By.id("phoneNumber")).sendKeys(number);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmPassword")).sendKeys(conpassword);
		
		WebElement clickbtn2 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[3]/div[1]/div[2]/form[1]/div[6]/div[1]/button[1]/div[1]"));
		clickbtn2.click();
		Thread.sleep(3000);
		
		//Login to the profile 
		
		driver.navigate().back();
		WebElement clickbtn3 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/nav[1]/div[1]/div[1]/div[1]/div[4]/div[2]/a[1]/div[1]/span[1]"));
		clickbtn3.click();
		
		driver.findElement(By.id("email")).sendKeys(emailid);
		driver.findElement(By.id("password")).sendKeys(password);
		Thread.sleep(3000);
		
		WebElement clickbtn4 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/form[1]/div[4]/div[1]/button[1]/div[1]"));
		clickbtn4.click();
		Thread.sleep(3000);
		
		//Update Profile
		
		driver.get("https://www.xampro.org/profile");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//Add Picture
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		WebElement fileInput = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/*[name()='svg'][1]"));
//		js.executeScript("arguments[0].click();", fileInput);
//		fileInput.sendKeys("file:///C:/Users/Tamim/Desktop/Tamim.jpg");
		
		//adding date of birth
		WebElement dobField = driver.findElement(By.id("dob"));
		dobField.sendKeys("06-01-1998");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		
		//Gender Select
		WebElement GenderMale = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[5]/div[1]/fieldset[1]/div[1]/div[1]/input[1]"));
		GenderMale.click();
		
		//Education
		WebElement Education = driver.findElement(By.id("education"));
		Select selectEducation = new Select(Education);
        selectEducation.selectByVisibleText("BSC");
        
        //University
        
        WebElement universityInput = driver.findElement(By.id("react-select-3-input"));
        universityInput.sendKeys("University of Dhaka");
        List<WebElement> universityOptions = driver.findElements(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]"));
        for (WebElement option : universityOptions) {
            if (option.getText().equalsIgnoreCase("University of Dhaka")) {
                option.click();
                break;
            }
        }
        
        universityInput.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        
        //Update & Quit
        
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[8]/div[1]/button[1]")).click();
        Thread.sleep(3000);
        driver.quit();
        

		
		

		
	}

}
