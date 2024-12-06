package com.example.websocket.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class YahooStockScraper {
	
	public static Map<String, String> getPrice(String symbol) {
		String url = "https://tw.stock.yahoo.com/quote/" + symbol;
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
		return priceMap;
	}
}



