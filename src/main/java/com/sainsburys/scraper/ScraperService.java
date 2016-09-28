package com.sainsburys.scraper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import javax.xml.xpath.XPath;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sainsburys.consoleapp.model.Item;
import com.sainsburys.consoleapp.model.Output;;

/**
 * This is the main class which performs reading the html page, gets the relevant
 *  data and generates a JSON from this data.
 * 
 */
public class ScraperService {
	
	private static final Logger log = Logger.getLogger(ScraperService.class);
	
	private static Gson gson = new GsonBuilder().serializeNulls().create();
	
    /**
     * Computes/pulls the required data from the html document object object using JSoup.
     * 
     * @param htmlDocument document containing required product data
     * @return Item item/product data
     */
    public Item getItem(Document htmlDocument) {

    	// Get the title from h1 tag
    	Elements titleElement = 
    			readElement(htmlDocument, "div.productTitleDescriptionContainer", "h1");
    	String title = titleElement.first().text();
    	log.debug("Title retrieved from html doc: "+title);
    	
    	// Get the size of the document
        String strSize = String.valueOf(htmlDocument.toString().length() / 1024) + "kb";
    	log.debug("Size of the html doc: "+strSize);
        
        // Get the unit price
    	Elements priceElement = 
    			readElement(htmlDocument, "p.pricePerUnit", null);            
    	String priceString = priceElement.first().text();
    	String strPrice = priceString.substring(1, priceString.length()-5);
    	log.debug("Price of the item: "+strPrice);
    	BigDecimal price = new BigDecimal(strPrice);

        // Get the description from productText
    	Elements descElement = 
    			readElement(htmlDocument, "div.productText", null);             
    	String description = descElement.first().text();
    	log.debug("Desription of the item: "+description);
    	
    	// we need a constructor here as all data together make an item. added constructor to Item class.
    	return new Item(title, strSize, price, description);
    }

    /**
     * Generic method to get the required element from the document object using selector 
     * and optional actual element.
     * 
     * @param doc Html document object.
     * @param selector selector string on document.
     * @param optional actual element on derived selector element.
     * @return Elements elements of objects.
     */
    public Elements readElement(Document doc, String selector, String actualElement){
        Element htmlElement = doc.select(selector).first();
        Elements htmlElements = new Elements();
    	Optional<Element> optionalElement = Optional.ofNullable(htmlElement);
    	Optional<String> optionalActElement = Optional.ofNullable(actualElement);

    	if(optionalElement.isPresent() && optionalActElement.isPresent()){
    		log.debug("Inner element present for selector: "+selector);
            htmlElements = htmlElement.getElementsByTag(actualElement);
    	}else{
    		log.debug("No Inner element present for selector: "+selector);
    		htmlElements.add(htmlElement);
    	}
    		
    	return htmlElements;
    }
    
    /**
     * Works on the actual document, pulls the required fields, calculates total price, 
     * converts to JSON.
     * 
     * @param doc Html document object.
     * @return String JSON string.
     */
    public String traverseDocument(Document htmlDocument) throws IOException, Exception{
        //output jaxb class.
    	Output output = new Output();
        BigDecimal total = new BigDecimal("0.0");
        
        try {
        	Elements liElements = readElement(htmlDocument, "ul.productLister", "li");

        	// now iterate through all elements and each item and add to result. compute total.
            for (Element element: liElements) {
                Element productInfo = element.select("div.productInfo").first();
                String hrefUrl =  productInfo.getElementsByTag("a").first().attr("href");
                Document hrefDocument = Jsoup.connect(hrefUrl).get();
                Item itemData = getItem(hrefDocument);
                output.getResults().add(itemData);
                total = total.add(itemData.getUnitPrice());
            }

            log.info("Total unit price of all items: "+total);
            output.setTotal(total);
            // using gson to convert jaxb object to string json.
            String outputJson = gson.toJson(output, Output.class);
            return outputJson;
        } catch (IOException ex) {
        	log.error(ex);
        	throw ex;
        }
    }
}