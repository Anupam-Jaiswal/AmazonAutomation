package com.wipro.scripts;

import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.wipro.config.StartBrowser;
import com.wipro.reuse.Commonfunction;

public class TS03Test extends StartBrowser
{
	@Test
	public void change_language_Amazon() throws FilloException
	{
		Commonfunction cfs = new Commonfunction();
		cfs.login();
		cfs.changeLanguage();
	}
}
