package Demo.BookStore;

import org.testng.annotations.Test;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import TestCases.BaseClass;

public class Demo_BookStore extends BaseClass{
	
	
	private String UserName;
	private String BookStore;
	private String BookTitle;
	private String BookAuthor;
	private String PublisherName;
	private String logOut;
	
	private Demo_BookStore()
	{
		this.UserName = "#userName-value";
		this.BookStore = "//*[text()='Book Store']";
		this.BookTitle ="//div[@class='rt-table']/div[2]/div[1]/div/div[2]/div/span";
		this.BookAuthor = "//div[@class='rt-table']/div[2]/div[1]/div/div[3]";
		this.PublisherName = "//div[@class='rt-table']/div[2]/div[1]/div/div[4]";
		this.logOut = "//*[text()='Log out']";
	}
	
	@Test
	public void TestCase_CheckUserName()
	{
		
		Locator ActualUserName = page.locator(UserName);
		PlaywrightAssertions.assertThat(ActualUserName).containsText("qa_king@yahoo.com");
		System.out.println("Validated the User Name");
	}
	@Test
	public void TestCase_SearchBookbyKeyWords()
	{
		// Land on Book Store Section
		page.locator(BookStore).click();
		page.getByPlaceholder("Type to search").fill("Learning JavaScript Design Patterns");
		String Title = page.locator(BookTitle).textContent();
		// Print Title
		System.out.println("Title of the Book :: "+ Title);
		String Author = page.locator(BookAuthor).textContent();
		// Print Author
		System.out.println("Author of the Book :: "+ Author);
		String Publisher = page.locator(PublisherName).textContent();
		// print publisher
		System.out.println("Publisher Name of the Book :: "+ Publisher);
	}
	
	@Test
	public void TestCase_ChecklogOutbtn()
	{
		String ExpectedBtnName = "Log out";
		Locator logOut_btn = page.locator(logOut);
		PlaywrightAssertions.assertThat(logOut_btn).containsText(ExpectedBtnName);
		System.out.println("Validated the LogOut Button");
		page.locator(logOut).click();
		System.out.println("LogOut successfully");
		
	}

	

}
