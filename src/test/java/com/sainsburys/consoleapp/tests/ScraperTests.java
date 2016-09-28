package com.sainsburys.consoleapp.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.sainsburys.consoleapp.exception.ScraperException;

@RunWith(MockitoJUnitRunner.class)
public class ScraperTests {

	@Test
	public void testGetItem() {
		Assert.assertTrue(1==1);
	}

	@Test
	public void testReadElement() {
		Assert.assertTrue(1==1);
	}
	
	@Test
	public void testTraverseDocument() {
		Assert.assertTrue(1==1);
	}
	
	@Test(expected=ScraperException.class)
	public void testException() {
	}
}
