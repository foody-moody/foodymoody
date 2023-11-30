package com.foodymoody.be.sse.service;

import com.foodymoody.be.notification.application.NotificationReadService;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RequiredArgsConstructor
@Component
public class SseAsyncService {

    private final NotificationReadService notificationReadService;

    @Async
    public void sendSseEvents(String memberId, Map<String, SseEmitter> emitters) {
        SseEmitter emitter = emitters.get(memberId);
        if (emitter == null) {
            log.error("Emitter for member {} not found", memberId);
            return;
        }

        try {
            configureEmitter(emitter, memberId, emitters);
            while (emitters.containsKey(memberId)) {
                long count = notificationReadService.countByMemberId(memberId);
                emitter.send(SseEmitter.event().name("notification").id(memberId).data(count));
                Thread.sleep(1000); // 1초 지연
            }
        } catch (IOException e) {
            log.error("Error sending SSE for member {}", memberId, e);
            emitter.completeWithError(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Interrupted while sending SSE for member {}", memberId, e);
        } finally {
            emitter.complete();
            emitters.remove(memberId);
        }
    }

    private void configureEmitter(SseEmitter emitter, String memberId, Map<String, SseEmitter> emitters) {
        emitter.onCompletion(() -> {
            log.info("SSE completed for member {}", memberId);
            emitters.remove(memberId);
        });
        emitter.onTimeout(() -> {
            log.info("SSE timeout for member {}", memberId);
            emitters.remove(memberId);
        });
        emitter.onError(e -> {
            log.error("SSE error for member {}: {}", memberId, e.getMessage());
            emitters.remove(memberId);
        });
    }
}
