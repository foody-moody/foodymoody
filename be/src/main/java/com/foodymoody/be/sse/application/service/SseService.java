package com.foodymoody.be.sse.application.service;

import com.foodymoody.be.common.util.ids.MemberId;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class SseService {

    public static final String CONNECT_EVENT_NAME = "connect";
    public static final String CONNECTED_MESSAGE = "connected!";
    private final Map<MemberId, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final SseAsyncService sseAsyncService;

    public SseEmitter stream(MemberId memberId) {
        SseEmitter emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter.event().name(CONNECT_EVENT_NAME).data(CONNECTED_MESSAGE));
            add(memberId, emitter);
            sendSseEvents(memberId);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
        return emitter;
    }

    @PreDestroy
    public void shutDown() {
        for (SseEmitter emitter : emitters.values()) {
            emitter.complete();
        }
        executorService.shutdownNow();
    }

    private void add(MemberId memberId, SseEmitter emitter) {
        emitters.put(memberId, emitter);
    }

    private void sendSseEvents(MemberId memberId) {
        executorService.submit(() -> sseAsyncService.sendSseEvents(memberId, emitters));
    }

}
