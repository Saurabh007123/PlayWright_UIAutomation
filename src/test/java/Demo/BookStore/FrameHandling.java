package Demo.BookStore;

import java.awt.JobAttributes.DialogType;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FrameHandling {

	public static void main(String[] args) 
	{
		try(Playwright playwright = Playwright.create())
		{
			// To get the child frame
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext browserContext = browser.newContext();
			Page page = browserContext.newPage();
			page.navigate("https://the-internet.herokapp.com/nested_frames");
			Frame frame = page.mainFrame();
			List<Frame> childfra = frame.childFrames();
			for(Frame child : childfra) {
				System.out.println(child);
			}
			
			// pop up handles
			
			page.onDialog(dialog -> {
                System.out.println("Dialog type: " + dialog.type());
                System.out.println("Dialog message: " + dialog.message());
                if (dialog.type() == DialogType.ALERT) {
                    dialog.accept(); // Click 'OK'
                } else if (dialog.type() == DialogType.CONFIRM) {
                    // dialog.accept(); // Click 'OK'
                    dialog.dismiss(); // Click 'Cancel'
                } else if (dialog.type() == DialogType.PROMPT) {
                    System.out.println("Dialog default value: " + dialog.defaultValue());
                    dialog.accept("Your Input"); // Click 'OK' and provide input
                    // dialog.dismiss(); // Click 'Cancel'
                }
            });
			
			browserContext.onPopup(newPage -> {
                System.out.println("New popup window opened: " + newPage.url());
                // You can now interact with the new page
                newPage.waitForLoadState();
                System.out.println("Title of the popup: " + newPage.title());
                newPage.close(); // Or perform other actions
            });

            // Trigger the action that opens the popup
            page.locator("#windowButton").click(); // Or #messageWindowButton, #tabButton

            // Continue with your test on the original page

            browser.close();
			
			// access the child frame
			
			
		}

	}

}
