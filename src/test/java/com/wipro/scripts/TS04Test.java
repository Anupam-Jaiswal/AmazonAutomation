package com.wipro.scripts;

import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.wipro.config.StartBrowser;
import com.wipro.reuse.Commonfunction;

public class TS04Test extends StartBrowser
{
	@Test
	public void add_Address_and_remove_address() throws FilloException, InterruptedException
	{
		Commonfunction cfs = new Commonfunction();
		cfs.login();
		cfs.addNewAddress();
		cfs.removeAddress();
		//final email checking
	}
}
