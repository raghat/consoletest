package com.sainsburys.scraper;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.sainsburys.consoleapp.exception.ScraperException;

/**
 * This is the client class that calls the service class which does the work 
 * of html parsing and json creation.
 * 
 */
public class Scraper {

	private static final Logger log = Logger.getLogger(Scraper.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		PropertyConfigurator.configure("log4j.properties");
		
		String scraperUrl 
	    	= "http://hiring-tests.s3-website-eu-west-1" +
	    			".amazonaws.com/2015_Developer_Scrape/5_products.html";
	        
		ScraperService scraper = new ScraperService();
		try{
			// Get the document using Jsoup connecting to the url.
            Document htmlDocument = Jsoup.connect(scraperUrl).get();
            // call the below method to parse the document and format the JSON.
			String outputJson = scraper.traverseDocument(htmlDocument);
			log.info("Output JSON from console application: "+outputJson);
		}catch(IOException e){
			log.error("Failed calling the console application", e);
			System.exit(1);
		}catch(ScraperException e){
			log.error("Failed calling the console application", e);
			System.exit(1);
		}catch(Exception e){
			log.error("Failed calling the console application", e);
			System.exit(1);
		}
	}
}