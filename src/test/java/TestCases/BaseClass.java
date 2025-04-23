package TestCases;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;


public class BaseClass {
	
	protected Playwright playwright;
	protected Browser browser;
	protected Page page;

	@BeforeSuite
    public void setup()
    {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		page = browser.newPage();
		System.out.println("<----New Browser is about to launch---->\n");
    }
	
	@BeforeClass
    public void setupPage() 
	{
		
		page.navigate("https://demoqa.com/");        
        page.locator(".category-cards div:nth-child(6)").click();
        
		// click the 'login' side menu option
        page.locator("//*[text()='Login']").click();
        
		// fill in the UserName
		page.getByPlaceholder("UserName").fill("qa_king@yahoo.com");
		
		// fill in the Password
		page.getByPlaceholder("Password").fill("Marketplace@2015");
		
		// click the login button
		page.locator("//*[@id='login']").click();
		
		// Wait for the page get loaded
		page.waitForLoadState();
		System.out.println("<----Login Successfull---->\n");
	}
	
	@AfterClass
	public void closePage()
	{
		page.close();
		page=null;
		
	}
	
	@AfterSuite
	public void TearDown()
	{
		browser.close();
		browser = null;
		playwright.close();
		playwright = null;
		
	}

}
