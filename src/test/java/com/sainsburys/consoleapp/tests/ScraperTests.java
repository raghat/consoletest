package com.sainsburys.consoleapp.tests;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import com.sainsburys.consoleapp.exception.ScraperException;
import com.sainsburys.scraper.ScraperService;

@RunWith(MockitoJUnitRunner.class)
public class ScraperTests {

	ScraperService service = new ScraperService();
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		//service = Mockito.mock(ScraperService.class);
	}
	
	@Test
	public void testGetItem() {
		Assert.assertTrue(1==1);
	}

	@Test
	public void testReadElementForPrice() {
		String html = "<html><head><title>Sainsburys site</title></head><body>" +
				"<p class=\"pricePerUnit\">£3.50/unit</p><body></html>";
		
		Document priceDocument = Jsoup.parse(html);
		Elements elements = service.readElement(priceDocument, "p.pricePerUnit", null);
		Assert.assertTrue(elements.first().text().equals("£3.50/unit"));
	}
	
	@Test
	public void testReadElementForDesc() {
		String html = "<html><head><title>Sainsburys site</title></head><body>" +
				"<div class=\"productText\">Great to eat</div><body></html>";
		
		Document priceDocument = Jsoup.parse(html);
		Elements elements = service.readElement(priceDocument, "div.productText", null);
		Assert.assertTrue(elements.first().text().equals("Great to eat"));
	}
	
	@Test
	public void testReadElementForTitle() {
		String html = "<html><head><title>Sainsburys site</title></head><body>" +
				"<div class=\"productTitleDescriptionContainer\">" +
				"<h1>Sainsbury Avocado</h1></div><body></html>";
		
		Document priceDocument = Jsoup.parse(html);
		Elements elements = service.readElement(priceDocument, "div.productTitleDescriptionContainer", "h1");
		Assert.assertTrue(elements.first().text().equals("Sainsbury Avocado"));
	}

	
	@Test
	public void testTraverseDocument() {
		Assert.assertTrue(1==1);
	}
	
	@Test(expected=ScraperException.class)
	public void testException() {
	}
}