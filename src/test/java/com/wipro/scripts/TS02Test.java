package com.wipro.scripts;

import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.wipro.config.StartBrowser;
import com.wipro.reuse.Commonfunction;

public class TS02Test extends StartBrowser
{
	@Test
	public void Search_Product() throws FilloException
	{
		Commonfunction cfs = new Commonfunction();
		cfs.login();
		cfs.Search();
	}
	
}
