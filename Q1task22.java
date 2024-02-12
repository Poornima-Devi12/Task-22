package task22;
import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Q1task22 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Launch the chrome crowser
		WebDriver driver = new ChromeDriver();

		// Launching the URL using get method
		driver.get("https://phptravels.com/demo/");

		// Using pageLoadTimeout for the web page to load
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

		// Using implicit wait for all the elements in the web page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Maximise the browser view
		driver.manage().window().maximize();

		// Filling all the mandatory field in form
		driver.findElement(By.name("first_name")).sendKeys("Testuser");
		driver.findElement(By.name("last_name")).sendKeys("Test");
		driver.findElement(By.name("business_name")).sendKeys("Travel guide");
		driver.findElement(By.xpath("(//input[@name='email'])[1]")).sendKeys("Testuser002@gmail.com");

		// Adding the two numbers for validation before submission
		String num1 = driver.findElement(By.xpath("//span[@id='numb1']")).getText();
		String num2 = driver.findElement(By.xpath("//span[@id='numb2']")).getText();

		// Converting String to Int for calculation
		int result = Integer.parseInt(num1) + Integer.parseInt(num2);

		driver.findElement(By.xpath("//input[@id='number']")).sendKeys(String.valueOf(result));

		// Clicking submit button
		driver.findElement(By.xpath("//button[text()='Submit']")).click();

		// Using explicit wait until the success message and thank you text is displayed
		WebElement thankYouText = driver.findElement(By.xpath("//strong[text()=' Thank you!']"));
		WebElement successMessage = driver.findElement(By.xpath("//p[@class='text-center cw']"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(thankYouText));
		wait.until(ExpectedConditions.visibilityOf(successMessage));

		String thankYou = driver.findElement(By.xpath("//strong[text()=' Thank you!']")).getText();
		String success = driver.findElement(By.xpath("//p[@class='text-center cw']")).getText();

		// Validating whether the success message and thank you message matches
		if (thankYou.equals("Thank you!") && success.equals(
				"We have sent your demo credentials to your email please check your email to test demo website. if message not found inbox please check spam folder")) {
			System.out.println("The form submission was successful");
		} else {
			System.out.println("The form submission was unsuccessful");
		}

		// Converting web driver object to take screenshot
		TakesScreenshot screenshot = ((TakesScreenshot) driver);

		// Using getScreenshotAs method for creating a file
		File source = screenshot.getScreenshotAs(OutputType.FILE);

		// Mentioning the destination file
		File destination = new File("C:\\Users\\Prabu\\OneDrive\\Desktop\\Poornima Devi JAT15WD\\Excelsheetop\\src\\main\\java\\task22\\Screenshot");

		// Using try catch in cases of file exception during the file copying process
		try {
			// copying the image from source to destination
			FileUtils.copyFile(source, destination);
		} catch (Exception e) {
			System.out.println("The exception occured is :" + e.getMessage());
		}

		// Printing the success message after taking a screenshot
		System.out.println("-------------------------------------------------------");
		System.out.println("The screensnot was saved at location " + destination + " successfully");

	}

}
