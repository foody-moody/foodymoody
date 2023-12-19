package com.foodymoody.be.sse.service;

import com.foodymoody.be.common.util.ids.MemberId;
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

    private final Map<MemberId, SseEmitter> emitters = new ConcurrentHashMap<>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final SseAsyncService sseAsyncService;

    public void sendSseEvents(MemberId memberId) {
        executorService.submit(() -> sseAsyncService.sendSseEvents(memberId, emitters));
    }

    public void add(MemberId memberId, SseEmitter emitter) {
        emitters.put(memberId, emitter);
    }

    @PreDestroy
    public void shutDown() {
        for (SseEmitter emitter : emitters.values()) {
            emitter.complete();
        }
        executorService.shutdownNow();
    }
}