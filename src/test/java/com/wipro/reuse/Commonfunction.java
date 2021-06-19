package com.wipro.reuse;

import org.testng.Assert;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.wipro.ObjectRepsitory.AccountPage;
import com.wipro.ObjectRepsitory.AddressPage;
import com.wipro.ObjectRepsitory.HomePage;
import com.wipro.ObjectRepsitory.LandingPage;
import com.wipro.ObjectRepsitory.LoginPage;
import com.wipro.ObjectRepsitory.NewAddressPage;
import com.wipro.config.StartBrowser;
import com.wipro.webdriverCommands.ActionDriver;

public class Commonfunction 
{
	public ActionDriver aDriver;
	
	public Commonfunction()
	{
		aDriver = new ActionDriver();
	}
	
	public void login() throws FilloException
	{
		StartBrowser.childTest = StartBrowser.parentTest.createNode("Login to the application ");
		aDriver.navigateToApp("https://amazon.in");
		aDriver.click(LandingPage.sign_in, "Sign in link");
		Fillo f = new Fillo();
		Connection conn = f.getConnection("TestData/Data.xlsx");
		Recordset rs = conn.executeQuery("Select * from TS01");
		while(rs.next())
		{
			String uName = rs.getField("UserName");
			aDriver.type(LoginPage.txtEmail,uName, "Email or phone text box");
			aDriver.click(LoginPage.btnContinue, "Continue button");
			String pass = rs.getField("Password");
			aDriver.type(LoginPage.txtPassword,pass, "Password text box");
			aDriver.click(LoginPage.btnSignin, "Sigin button");
		}
		
	}
	
	public void signout()
	{
		StartBrowser.childTest = StartBrowser.parentTest.createNode("Sign out to the application");
		aDriver.mouseHover(HomePage.linkHelloUser, "Hello user link");
		aDriver.click(HomePage.linkSignout, "Sign out link");
		
	}
	
	public void changeLanguage()
	{
		StartBrowser.childTest = StartBrowser.parentTest.createNode("Changing the language");
		aDriver.mouseHover(HomePage.linkCountryRegion,"Country/Region link");
		aDriver.click(HomePage.linkHindi,"Hindi language link");
	}
	
	public void Search()
	{
		StartBrowser.childTest = StartBrowser.parentTest.createNode("Search");
		aDriver.type(HomePage.txtSearchBox,"mobile","Search box");
		aDriver.click(HomePage.btnSearch,"Search button");
	}
	
	public void addNewAddress() throws FilloException
	{
		StartBrowser.childTest = StartBrowser.parentTest.createNode("Adding new Address");
		aDriver.click(HomePage.linkHelloUser,"Hello User Link");
		aDriver.click(AccountPage.lnkYourAddress, "Your address link");
		aDriver.click(AddressPage.lnkAddAddress, "Add Address link");
		aDriver.click(NewAddressPage.cbCountry, "Country Region combo box");
		aDriver.click(NewAddressPage.listItemIndia,"India list item");
		aDriver.click(NewAddressPage.cbState, "State combo box");
		aDriver.click(NewAddressPage.listItemJharkhand,"Jharkhand list item");
		aDriver.click(NewAddressPage.cbAddressType,"Address Type combo box");
		aDriver.click(NewAddressPage.listItemHomeAddress,"Home address list item");
		
		Fillo f = new Fillo();
		Connection conn = f.getConnection("TestData/Data.xlsx");
		Recordset rs = conn.executeQuery("Select * from TS04");
		
		while (rs.next())
		{
			aDriver.type(NewAddressPage.txtName,rs.getField("Name"),"Name text box");
			aDriver.type(NewAddressPage.txtMobileNo,rs.getField("MobileNo"),"Mobile No text box");
			aDriver.type(NewAddressPage.txtPinCode,rs.getField("PinCode"),"Pin code text box");
			aDriver.type(NewAddressPage.txtAddressLine1,rs.getField("AddressLine1"),"Address Line 1 Text box");
			aDriver.type(NewAddressPage.txtAddressLine2,rs.getField("AddressLine2"), "Address Line 2 Text box");
			aDriver.type(NewAddressPage.txtLandmark,rs.getField("Landmark"), "Landmark text box");
			//aDriver.type(NewAddressPage.txtTown,rs.getField("Town"),"Town Text box");
		}
		
		aDriver.click(NewAddressPage.btnAddAddress,"Add Address button");
		String message = aDriver.extractText(AddressPage.alertBox, "Alert box");
		Assert.assertEquals(message,"Address saved");
		
		
	}
	
	public void removeAddress() throws InterruptedException
	{
		StartBrowser.childTest = StartBrowser.parentTest.createNode("Remove Address");
		aDriver.click(AddressPage.btnRemove,"Remove Button");
		Thread.sleep(10000);
		aDriver.click(AddressPage.btnYes,"Yes Button");
		String message = aDriver.extractText(AddressPage.alertBox,"Alert Box");
		Assert.assertEquals(message,"Address deleted");
	}
}
