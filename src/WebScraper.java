import java.util.LinkedHashMap;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class WebScraper {

	public static void main(String[] args) {
		
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		
		try {
			String searchUrl = "https://ca.finance.yahoo.com/quote/TSLA?p=TSLA";
			HtmlPage page = client.getPage(searchUrl);
			List<HtmlElement> dataTable = page.getByXPath("//table[@data-reactid = '38']//span");
			
			LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
			for (int i = 0; i < dataTable.size(); i += 2) {
				String key = dataTable.get(i).getVisibleText();
				String value = dataTable.get(i+1).getVisibleText();
				data.put(key, value);
			}
			
			for (String node : data.keySet()) {
				System.out.println(node + ": " + data.get(node));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}

		client.close();
	}

}
