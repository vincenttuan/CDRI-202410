package com.example.websocket.channel;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/channel/esschat") // 修改路由
public class ESSChatChannelController { // 修改控制器名稱

    // 儲存所有用戶的 SseEmitter
    private final Map<String, SseEmitter> clients = Collections.synchronizedMap(new HashMap<>());

    // 廣播消息給所有連接用戶
    private void broadcast(String sessionId, String message) {
        clients.forEach((id, emitter) -> {
            try {
                emitter.send("[ " + sessionId + " 說]: " + message);
            } catch (IOException e) {
                emitter.complete();
                clients.remove(id);
            }
        });
    }

    // 發送消息給指定用戶
    private void sendToSession(String targetId, String sessionId, String message) {
        SseEmitter targetEmitter = clients.get(targetId);
        if (targetEmitter != null) {
            try {
                targetEmitter.send("[ " + sessionId + " 私訊]: " + message);
            } catch (IOException e) {
                targetEmitter.complete();
                clients.remove(targetId);
            }
        }
    }

    // 建立連接
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect(@RequestParam String sessionId) {
        SseEmitter emitter = new SseEmitter(0L); // 無限超時
        clients.put(sessionId, emitter);

        // 初始訊息
        try {
            emitter.send("[ " + sessionId + " 已連線]");
            broadcast(sessionId, "已連線");
        } catch (IOException e) {
            emitter.complete();
        }

        // 監聽連接中斷
        emitter.onCompletion(() -> clients.remove(sessionId));
        emitter.onTimeout(() -> clients.remove(sessionId));
        emitter.onError(e -> clients.remove(sessionId));

        return emitter;
    }

    // 處理消息
    @PostMapping("/send")
    public void sendMessage(@RequestParam String sessionId, @RequestParam String target, @RequestParam String message) {
        if ("all".equals(target)) {
            broadcast(sessionId, message); // 廣播消息
        } else {
            sendToSession(target, sessionId, message); // 私訊
        }
    }
}
