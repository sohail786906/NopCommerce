package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Utilities.WaitHelper;

public class SearchCustomerPage {
public WebDriver ldriver;


WaitHelper waithelper;
public SearchCustomerPage(WebDriver ldriver) {
	PageFactory.initElements(ldriver, this);
	waithelper = new WaitHelper(ldriver);
}
@FindBy(how=How.ID, using = "SearchEmail")
@CacheLookup
WebElement txtEmail;

@FindBy(how=How.ID, using = "SearchFirstName")
@CacheLookup
WebElement txtFirstName;
@FindBy(how=How.ID, using = "SearchLastName")
@CacheLookup
WebElement txtLastName;

/*@FindBy(how=How.XPATH, using = "//select[@id='SearchMonthOfBirth']")
@CacheLookup
WebElement drpdbMonth;

@FindBy(how=How.XPATH, using = "//select[@id='SearchDayOfBirth']")
@CacheLookup
WebElement drpdbDay; */
@FindBy(how=How.ID, using = "search-customers")
@CacheLookup
WebElement btnSearch;
@FindBy(how=How.XPATH, using = "//table[@role='grid']")
WebElement table;
@FindBy(how=How.XPATH, using = "//table[@role='grid']//tbody/tr")
List<WebElement> tableRows;
@FindBy(how=How.XPATH, using = "//table[@role='grid']//tbody/tr/td")
List<WebElement> tableColoumns;

public void setEmail(String email) {
	waithelper.WaitForElement(txtEmail, 30);
	txtEmail.clear();
	txtEmail.sendKeys(email);
}
public void setFirstName(String fname) {
	waithelper.WaitForElement(txtFirstName, 30);
	txtFirstName.clear();
	txtFirstName.sendKeys(fname);
}
public void setLastName(String lname) {
	waithelper.WaitForElement(txtLastName, 30);
	txtLastName.clear();
	txtLastName.sendKeys(lname);
}
public void clickSearch() {
	btnSearch.click();
	waithelper.WaitForElement(btnSearch, 30);
}
public int getNoOfRows() {
	return(tableRows.size());
}
public int getNoOfColumns() {
	return(tableColoumns.size());
}
public boolean searchCustomerByEmail(String email) {
	boolean flag = false;
	for(int i=1; i<=getNoOfRows();i++) {
		String emailid= table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
		System.out.println(emailid);
		if(emailid.equals("victoria_victoria@nopCommerce.com")) {
			flag=true;
		}
	}
	return flag;
}
}
