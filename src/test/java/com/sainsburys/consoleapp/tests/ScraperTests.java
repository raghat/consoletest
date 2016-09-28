package com.sainsburys.consoleapp.tests;

import java.math.BigDecimal;

import javafx.beans.binding.When;

import org.jsoup.Connection;
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
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sainsburys.consoleapp.exception.ScraperException;
import com.sainsburys.consoleapp.model.Item;
import com.sainsburys.consoleapp.model.Output;
import com.sainsburys.scraper.ScraperService;

@RunWith(PowerMockRunner.class)
public class ScraperTests {

	private static final String item1Link = "http://hiring-tests.s3-website-eu-west-1." +
		     		"amazonaws.com/2015_Developer_Scrape/sainsburys-apricot-" +
		     		"ripe---ready-320g.html";
	
	private static final String item2Link = "http://hiring-tests.s3-website-eu-west-1." +
     		"amazonaws.com/2015_Developer_Scrape/sainsburys" +
     		"-avocado-xl-pinkerton-loose-300g.html";
	
	
	ScraperService service = new ScraperService();
	
	private static Gson gson = new GsonBuilder().serializeNulls().create();
	
	@Mock
	private static Connection conn1;
	
	@Mock
	private static Connection conn2;
	
	@Before
	public void init(){
		PowerMockito.mockStatic(Jsoup.class);

		MockitoAnnotations.initMocks(this);
		conn1 = Mockito.mock(Connection.class);
		conn2 = Mockito.mock(Connection.class);
		

		String html1 = "<html><head><title>Sainsburys site</title></head><body>" +
				"<p class=\"pricePerUnit\">£3.50/unit</p><div class=\"productText\">Great to eat</div>" +
				"<div class=\"productTitleDescriptionContainer\">" +
				"<h1>Sainsbury Avocado</h1></div>" +
				"<body></html><body></html>";

		String html2 = "<html><head><title>Sainsburys site</title></head><body>" +
				"<p class=\"pricePerUnit\">£3.50/unit</p><div class=\"productText\">Great to eat</div>" +
				"<div class=\"productTitleDescriptionContainer\">" +
				"<h1>Sainsbury Avocado</h1></div>" +
				"<body></html><body></html>";
		
		Document html1Doc = Jsoup.parse(html1);
		Document html2Doc = Jsoup.parse(html2);
		
		try{
		     Mockito.when(Jsoup.connect(item1Link))
		     .thenReturn(conn1);
		     Mockito.when(Jsoup.connect(item2Link))
		     		.thenReturn(conn2);
		     
		     Mockito.when(conn1.get()).thenReturn(html1Doc);
		     Mockito.when(conn2.get()).thenReturn(html2Doc);
		}catch(Exception e){
			//exception need not be catched as this is junit only
		}
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
		
		Document descDocument = Jsoup.parse(html);
		Elements elements = service.readElement(descDocument, "div.productText", null);
		Assert.assertTrue(elements.first().text().equals("Great to eat"));
	}
	
	@Test
	public void testReadElementForTitle() {
		String html = "<html><head><title>Sainsburys site</title></head><body>" +
				"<div class=\"productTitleDescriptionContainer\">" +
				"<h1>Sainsbury Avocado</h1></div><body></html>";
		
		Document titleDocument = Jsoup.parse(html);
		Elements elements = service.readElement(titleDocument, "div.productTitleDescriptionContainer", "h1");
		Assert.assertTrue(elements.first().text().equals("Sainsbury Avocado"));
	}

	@Test
	public void testGetItem() {
		String html = "<html><head><title>Sainsburys site</title></head><body>" +
				"<p class=\"pricePerUnit\">£3.50/unit</p><div class=\"productText\">Great to eat</div>" +
				"<div class=\"productTitleDescriptionContainer\">" +
				"<h1>Sainsbury Avocado</h1></div>" +
				"<body></html><body></html>";
		Document htmlDocument = Jsoup.parse(html);
		Item item = service.getItem(htmlDocument);
		Assert.assertTrue(item.getDescription().equals("Great to eat"));
		Assert.assertTrue(item.getTitle().equals("Sainsbury Avocado"));
		Assert.assertTrue(item.getUnitPrice().equals(new BigDecimal("3.50")));
	}

	@Test
	public void testTraverseDocument() {
		// donot send the link in href as this have to be mocked.
		String html = "<ul class=\"productLister listView\">"+
						"<li><div class=\"productInfo\"><h3><a href="+item1Link+"></h3></div></li>"+
						"<li><div class=\"productInfo\"><h3><a href="+item2Link+"></h3></div></li>"+
						"</ul>";
		Document htmlDocument = Jsoup.parse(html);
		
		String json = "";
		try{
			 PowerMockito.verifyStatic(Mockito.times(2));
		     json = service.traverseDocument(htmlDocument);
		}catch(Exception e){
			//log exception
		}
		
		Output out = gson.fromJson(json, Output.class);
	    Assert.assertTrue(out.getTotal().equals(new BigDecimal("5.00")));
	}
	
	@Test(expected=ScraperException.class)
	public void testException() {
	}
}