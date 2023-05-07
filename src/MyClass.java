import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyClass {

	DesiredCapabilities caps = new DesiredCapabilities();

	@BeforeTest 
	public void beforeTest() {
		
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "farah");
//		to open app 
		File app = new File("src/myApps/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP,app.getAbsolutePath() );
//		to open chrome 
//		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
//		caps.setCapability("chromedriverExecutable","C:\\chromewebdriver\\chromedriver_win32 (1)\\chromedriver.exe"); // version of chrome
		

	}
	
	@Test 
	public void clickOnAllNumbers() throws MalformedURLException  {
	AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
		
	List <WebElement> myButtons = driver.findElements(By.className("android.widget.ImageButton"));
	System.out.println(myButtons.size());
	
	for (int i =0 ; i<myButtons.size();i++) {
		String id = myButtons.get(i).getAttribute("resource-id");
		
		if(id.contains("digit")) {
			myButtons.get(i).click();
			
		}		
	}	
	WebElement result = driver.findElement(By.className("android.widget.EditText"));
	String resultActual = result.getText();
	String resultExpected = "7894561230";
	
//	hard assert 
	Assertion assertt = new Assertion();
	assertt.assertEquals(resultExpected, resultActual);
		
	}
	
	@Test() 
	public void clickOnEvenNum() throws MalformedURLException   {
		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
		
		List <WebElement> myButtons = driver.findElements(By.className("android.widget.ImageButton"));
		System.out.println(myButtons.size());
		for (int i =0 ; i<myButtons.size();i++) {
			String id = myButtons.get(i).getAttribute("resource-id");
			String[] evenNum = {"2","4","6","8"};

			for(int k =0 ; k<evenNum.length ; k++) {
				
				if(id.contains("digit_" + evenNum[k] )) {
					myButtons.get(i).click();
					
				}
			}			
		}
		WebElement result = 	driver.findElement(By.className("android.widget.EditText"));
		String resultActual = result.getText();
		String resultExpected = "8462";
		
//		hard assert 
		Assertion assertt = new Assertion();
		assertt.assertEquals(resultExpected, resultActual);

	}
	
	
	//	@Test 
	//	public void openTheBrowser() throws MalformedURLException  {
	//	AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);	
	//	driver.get("https://www.google.com");	
	//	}
	
	@AfterTest 
	public void afterTest() {
		
		
	}
	
	
}
