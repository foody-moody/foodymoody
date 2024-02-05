package com.foodymoody.be.sse.application.service;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.notification.application.service.NotificationReadService;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequiredArgsConstructor
@Component
public class SseAsyncService {

    public static final int MIN_COUNT = 0;
    public static final int DELAY_SECONDS = 1;
    public static final int INITIAL_DELAY_SECONDS = 0;
    public static final String NOTIFICATION_EVENT_NAME = "notification";
    private final NotificationReadService service;
    private final ScheduledExecutorService scheduledExecutorService;

    @Async
    public void sendSseEvents(MemberId memberId, Map<MemberId, SseEmitter> emitters) {
        SseEmitter emitter = emitters.get(memberId);
        if (emitter == null) {
            return;
        }
        final ScheduledFuture<?> scheduledTask = sendEventWitchSchedule(memberId, emitters, emitter);
        clearCompletedEmitter(emitter, scheduledTask);
    }

    private ScheduledFuture<?> sendEventWitchSchedule(
            MemberId memberId,
            Map<MemberId, SseEmitter> emitters,
            SseEmitter emitter
    ) {
        return scheduledExecutorService.scheduleWithFixedDelay(() -> {
            if (!emitters.containsKey(memberId)) {
                return;
            }
            try {
                long count = service.fetchCountNotReadNotification(memberId);
                if (count > MIN_COUNT) {
                    sendCount(memberId, count, emitter);
                }
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        }, INITIAL_DELAY_SECONDS, DELAY_SECONDS, TimeUnit.SECONDS);
    }

    private static void clearCompletedEmitter(SseEmitter emitter, ScheduledFuture<?> scheduledTask) {
        emitter.onCompletion(() -> scheduledTask.cancel(true));
        emitter.onTimeout(() -> scheduledTask.cancel(true));
        emitter.onError(e -> scheduledTask.cancel(true));
    }

    private static void sendCount(MemberId memberId, long count, SseEmitter emitter) throws IOException {
        SseResponse sseResponse = new SseResponse(count);
        emitter.send(SseEmitter.event()
                             .name(NOTIFICATION_EVENT_NAME)
                             .id(memberId.getValue())
                             .data(sseResponse));
    }
}
