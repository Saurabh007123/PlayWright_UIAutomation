package TestCases;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitUntilState;


public class BaseClass {
	
	protected Browser browser;
	protected Playwright playwright;
	protected Page page;
	protected BrowserContext browserContext;

	@BeforeSuite
    public void setup()
    {
		try(Playwright playwright = Playwright.create())
		{
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		browserContext = browser.newContext();
		page = browserContext.newPage();
		System.out.println("<----New Browser is about to launch---->\n");
		}
    }
	
	@BeforeClass
    public void setupPage() 
	{
		/*This is a good way to ensure that most of the resources on the page
		 *  have been loaded, including images, scripts, and other assets.
		 */
		page.navigate("https://demoqa.com/", new Page.NavigateOptions()
				.setWaitUntil(WaitUntilState.NETWORKIDLE));  
		page.waitForSelector(".category-cards div:nth-child(6)");
        page.locator(".category-cards div:nth-child(6)").click();
        
        /* below syntax is to wait for element which has the locator
         * page.locator(".category-cards div:nth-child(6)").waitFor();
         * click option will auto wait for element.
         */
        
		// click the 'login' side menu option
        page.locator("//*[text()='Login']").click();
        
        /* page.setDefaultTimeout(milliseconds), you are setting 
         * a global timeout value for all subsequent operations performed on that specific
         * */
        page.setDefaultTimeout(30000);
        
        /* to get the default time out implemented by Playwright
         * double defaultTimeOut = page.getdefaultTimeout()
         * */
        
		// fill in the UserName
		page.getByPlaceholder("UserName").fill("qa_king@yahoo.com");
		
		// fill in the Password
		page.getByPlaceholder("Password").fill("Marketplace@2015");
		
		// click the login button
		page.locator("//*[@id='login']").click();
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
