package Phase1Amazon;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SamsungSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver=new ChromeDriver();

		//Launch of Amazon
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

		// finding Search bar element and passing samsung word
		WebElement searchbox=driver.findElement(By.id("twotabsearchtextbox"));
		searchbox.sendKeys("samsung");

		//finding Go button element
		WebElement Srchbtn=driver.findElement(By.id("nav-search-submit-button"));
		Srchbtn.click();

		// List of All samsung product on 1st page
		List<WebElement> Product=driver.findElements(By.xpath("//div[@class='a-section']//span[starts-with(text(),'Samsung ')]"));

		//List of All samsung product price on 1st page
		List<WebElement> Price=driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));

		System.out.println("Total Products found " + Product.size());
		//Printing all samsung product and price in console
		for(int i=0;i<Product.size();i++) {
			System.out.println(Product.get(i).getText() + "  " + Price.get(i).getText());
		}

		//Get the unique id of parent window 
		String ParentWH=driver.getWindowHandle();
		System.out.println("Before clicking tab button "+ ParentWH);

		// Get the text of first product detail
		String linktext = Product.get(0).getText();
		System.out.println("first link text is " + linktext);

		//click on first product link
		Product.get(0).click();

		//Get the list of windows and switching to 2nd window
		
		Set<String> AllWindowHandler =	driver.getWindowHandles();
		
		for(String newwin : AllWindowHandler ) {
			System.out.println(newwin);

			if(!newwin.equals(ParentWH)) {
				driver.switchTo().window(newwin);
			}
		}
					
		// Get the text of first product on child window
       WebElement Childwindow=driver.findElement(By.id("productTitle"));
		String str = Childwindow.getText();

		//Validation on parent and child windows
		if(str.equals(linktext)) {
			System.out.println("TC passed : Title is matching");
		}else {
			System.out.println("TC Failed : Title is not matching");
		}
		driver.quit();
	}
}