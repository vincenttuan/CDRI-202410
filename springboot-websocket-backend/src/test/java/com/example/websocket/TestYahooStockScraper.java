package com.example.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

public class TestYahooStockScraper {
	
	@Test
	public void test() throws IOException {
		String url = "https://tw.stock.yahoo.com/quote/2330.TW";
		//String url = "https://tw.stock.yahoo.com/quote/^TWII";
		Document doc = Jsoup.connect(url).get();
		// 找到 <ul> 下的 <li class="price-detail-item"> 前面幾個字是 price-detail-item 的元素
		Elements elements = doc.select("ul > li.price-detail-item");
		// 過濾 html tag
		Map<String, String> priceMap = new HashMap<>();
		elements.forEach(e -> {
			// e 裡面有 2 個 <span> 分別印出 text
			Elements spans = e.select("span");
			String key = spans.get(0).text().replace(" ", "").replace("\n", "");
			String value = spans.get(1).text().replace("%", "").replace(",", "").replace(" ", "").replace("\n", "");
			priceMap.put(key, value);
		});
		System.out.println(priceMap);
	}
}



