package StepDefinations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import io.cucumber.java.en.*;
import junit.framework.Assert;
import pageObject.AddcustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomerPage;

public class Steps extends BaseClass {
 
  
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//driver/chromedriver.exe");
		driver = new ChromeDriver();
		
	   lp=new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
	driver.get(url);
	driver.manage().window().maximize();
	}
	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) {
	   lp.setUserName(email);
	   lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() throws InterruptedException {
	   lp.clickLogin();
	   Thread.sleep(3000);
	   
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title) throws InterruptedException {
	    if(driver.getPageSource().contains("Login was unsuccessful.")) {
	    driver.close();
	    
	    Assert.assertTrue(false);
	    }
	    else {
	     Assert.assertEquals(title, driver.getTitle());
	    }
	    Thread.sleep(3000);
	    
	}

	@When("User click on Log out link")
	public void user_click_on_Log_out_link() throws InterruptedException {
	  lp.clickLogout();
	  Thread.sleep(3000);
	
	}

	@Then("close browser")
	public void close_browser() {
		  driver.quit();
	}

//Customer feature stepDefination...................
	
	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
	    addCust = new AddcustomerPage(driver);
	    Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
	   addCust.clickOnCustomerMenu();
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(3000);
	    addCust.clickOnCustmerMenuItem();
	}

	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		
		addCust.clickOnAddnew();
		Thread.sleep(2000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
	  Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
	   String email = randomestring()+"@gmail.com";
	   addCust.setEmail(email);
	   addCust.setPassword("test123");
                	   //Registered - default
	                     //The customer cannot be in both 'Guests' and 'Registered' customer roles
	             //Add the customer to 'Guests' or 'Registered' customer role
	   Thread.sleep(3000);
	   addCust.setFirstName("sohaa");
	   addCust.setLastName("mohammed");
	   addCust.setGender("Male");
	   addCust.setDob("09/05/1999");
	   
	   addCust.setCompanyName("Apple");
	   
	   
   //addCust.setCustomerRoles("Forum Moderators");
   
   
   addCust.setManagerOfVendor("Vendor 2");
   
  
   addCust.setAdminContent("This is for testing..... ");
	}

	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		addCust.ClickOnSave();
		Thread.sleep(2000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
	    Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
		
	}
	//Steps for searching a customer using Email ID....
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
	   searchCust = new SearchCustomerPage(driver);
	   searchCust.setEmail("victoria_victori23a@nopCommerece.com");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
	   searchCust.clickSearch();
	   Thread.sleep(3000);
	   
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
	  boolean status = searchCust.searchCustomerByEmail("victoria_victori23a@nopCommerece.com");
	Assert.assertEquals(false, status);


	}


}
