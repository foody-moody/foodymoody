package com.foodymoody.be.sse.service;

import java.io.IOException;
import java.util.Map;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
public class SseAsyncService {

    @Async
    public void sendSseEvents(String memberId, Map<String, SseEmitter> emitters) {
        SseEmitter emitter = emitters.get(memberId);
        while (true) {
            emitter.onCompletion(() -> emitters.remove(memberId));
            emitter.onTimeout(() -> emitters.remove(memberId));
            emitter.onError(e -> emitters.remove(memberId));
            if (!emitters.containsValue(emitter)) {
                break;
            }
            try {
                emitter.send(SseEmitter.event().name("notification")
                        .id(memberId)
                        .data("1"));
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
            try {
                Thread.sleep(1000); // 1초
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
