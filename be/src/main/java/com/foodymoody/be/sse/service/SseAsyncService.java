package com.foodymoody.be.sse.service;

import com.foodymoody.be.notification.application.NotificationReadService;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RequiredArgsConstructor
@Component
public class SseAsyncService {

    public static final int MIN_COUNT = 0;
    public static final int DELAY_SECONDS = 1;
    public static final int INITIAL_DELAY_SECONDS = 0;
    private final NotificationReadService notificationReadService;
    private final ScheduledExecutorService scheduledExecutorService;

    @Async
    public void sendSseEvents(String memberId, Map<String, SseEmitter> emitters) {
        SseEmitter emitter = emitters.get(memberId);
        if (emitter == null) {
            log.error("Emitter for member {} not found", memberId);
            return;
        }

        final ScheduledFuture<?> scheduledTask = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            if (!emitters.containsKey(memberId)) {
                return;
            }

            try {
                long count = notificationReadService.fetchCountNotReadNotification(memberId);
                if (count > MIN_COUNT) {
                    SseResponse sseResponse = new SseResponse(count);
                    emitter.send(SseEmitter.event().name("notification").id(memberId).data(sseResponse));
                }
            } catch (IOException e) {
                log.error("Error sending SSE for member {}", memberId, e);
                emitter.completeWithError(e);
            }
        }, INITIAL_DELAY_SECONDS, DELAY_SECONDS, TimeUnit.SECONDS);

        emitter.onCompletion(() -> scheduledTask.cancel(true));
        emitter.onTimeout(() -> scheduledTask.cancel(true));
        emitter.onError(e -> scheduledTask.cancel(true));
    }
}
