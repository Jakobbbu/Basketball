package webPro.webProject;

import java.io.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {

	private final StringBuilder SEARCH_URL;
	
	private String target_URL;
	
	private Document doc;
	
	private LinkedHashMap<String, String> data;
	
	App() {
		
		SEARCH_URL =  new StringBuilder("https://www.basketball-reference.com/search/search.fcgi?search=");
		
	}
	
	private App setTarget(String name) throws IOException {
		
		SEARCH_URL.append(name.toLowerCase().trim().replaceAll("\\s+", "+"));
		
			Document doc = Jsoup.connect(SEARCH_URL.toString()).get();
			
			Element result = doc.selectFirst("div.search-item-url");
			
			target_URL = "https://www.basketball-reference.com" + result.text();
			
			this.doc = Jsoup.connect(target_URL).get();	
			
			return this;
			
	}
	
	private void extractData() {
		
		data = new LinkedHashMap<String, String>();
		
		Elements rows = doc.selectFirst("table#per_game").select("tr[id^=per_game]");	
		
		Collections.reverse(rows);
			
		rows.forEach((row) -> {
			data.put(row.select("th > a").text(), row.select("td[data-stat=fg3_per_g]").text());
		});
		
	}
	
	public LinkedHashMap<String, String> getData(String name) throws IOException {
		
		setTarget(name).extractData();
		
		return this.data;
		
	}
		
	public static void printData(String key, String value) {
		System.out.println(key + " " + value);
	}	
	
  public static void main(String[] args) {
	  
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	String name = "";	
	
	System.out.println("Enter name: ");
	
	App searcher = new App();
	
	try {
		name = br.readLine();
		searcher.getData(name).forEach(App::printData);
	}catch(Exception e) {
		System.out.print("Couldn't find player " + name);
	}
	 
  }
}