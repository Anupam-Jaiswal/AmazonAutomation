package com.wipro.scripts;

import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.wipro.config.StartBrowser;
import com.wipro.reuse.Commonfunction;

public class TS01Test extends StartBrowser
{
	@Test
	public void Login_Logout_Amazon() throws FilloException
	{
		Commonfunction cfs  =new Commonfunction();
	
		cfs.login();
		cfs.signout();
	}
	
}
