package com.example.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class ChatController {

    private final ChatClient chatClient;
    
    private final Map<String, Integer> foodMap = new LinkedHashMap<>();

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
        // 初始化食物與價格
        foodMap.put("麥香魚", 58);
        foodMap.put("大麥克", 72);
        foodMap.put("薯餅", 29);
        foodMap.put("可樂", 20);
        foodMap.put("沙拉", 35);
        foodMap.put("雞塊", 45);
        foodMap.put("蘋果派", 30);
        foodMap.put("冰淇淋", 25);
        foodMap.put("咖啡", 40);
        foodMap.put("熱巧克力", 50);
        foodMap.put("雞腿堡", 65);
        foodMap.put("花生奶昔", 55);
        foodMap.put("玉米湯", 28);
        foodMap.put("炸雞", 85);
        foodMap.put("牛肉堡", 68);
        foodMap.put("海鮮湯", 60);
        foodMap.put("豬排堡", 62);
        foodMap.put("柳橙汁", 25);
        foodMap.put("奶昔", 50);
        foodMap.put("巧克力蛋糕", 45);
        foodMap.put("草莓派", 30);
        foodMap.put("炸薯條", 35);
        foodMap.put("雞肉卷", 55);
        foodMap.put("雞肉沙拉", 48);
        foodMap.put("飲料套餐", 75);
        foodMap.put("起司蛋堡", 70);
        foodMap.put("蜜汁雞翅", 90);

    }
    
    private String buildFoodList() {
        StringBuilder sb = new StringBuilder();
        foodMap.forEach((name, price) -> sb.append("品名:").append(name).append(" 價格:").append(price).append(", "));
        return sb.toString().trim(); // 刪除最後的逗號
    }
   
    @RequestMapping (value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter chat(@RequestParam String message) {
    	
    	String foods = buildFoodList();
    	String finalMessage = "一定要用繁體中文回答,麥當勞商品有:" + foods + "," + message + ",直接提供簡潔的答案";
    	
        SseEmitter emitter = new SseEmitter();

        // 使用 ChatClient 的 stream() 方法獲取串流回應
        Flux<String> responseFlux = chatClient.prompt()
                                              .user(finalMessage)
                                              .stream()
                                              .content();

        // 訂閱 Flux，逐字傳送給前端
        responseFlux.subscribe(
            content -> {
                try {
                    emitter.send(content);
                } catch (IOException e) {
                    emitter.completeWithError(e);
                }
            },
            error -> emitter.completeWithError(error),
            emitter::complete
        );

        return emitter;
    }
}