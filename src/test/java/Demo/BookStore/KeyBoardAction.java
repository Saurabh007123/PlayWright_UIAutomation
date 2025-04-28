package Demo.BookStore;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.JSHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class KeyBoardAction {

	public static void main(String[] args) 
	{
		try(Playwright playwright = Playwright.create())
		{
			// To get the child frame
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos/")));
			// Make sure to close, so that videos are saved.


			Page page = browserContext.newPage();
			page.navigate("https://vwo.com/free-trial/");
			page.click("#page-v1-step1-email");
			page.keyboard().type("Saurabh");
			page.keyboard().press("Enter");
			page.pause();
	     
		    // Mouse action
			
			page.mouse().down();
			page.mouse().move(100, 200);
			// right click (press down)
			page.mouse().down();
			// right click (release)
			page.mouse().up();
			
			// java script
			
			JSHandle windowHandle = page.evaluateHandle("Windows.scrollToView[0,100]");
			JSHandle windowHandle1 = page.evaluateHandle("Windows.scrollTo(0,document.body.scrollHeight)");
			
			// screenshot
			
			page.screenshot(new Page.ScreenshotOptions()
			.setPath(Paths.get("screenshot.png")));
			
			// screenshot full page
			
			page.screenshot(new Page.ScreenshotOptions()
					  .setPath(Paths.get("screenshot.png"))
					  .setFullPage(true));
		}
	}
}


