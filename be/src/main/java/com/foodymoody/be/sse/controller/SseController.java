package com.foodymoody.be.sse.controller;

import com.foodymoody.be.sse.service.SseService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SseController {

    private final SseService sseService;

    @GetMapping(value = "/api/sse/{memberId}", produces = "text/event-stream;charset=UTF-8")
    public SseEmitter streamSeeMvc(@PathVariable String memberId) throws IOException {
        SseEmitter emitter = new SseEmitter();
        try {
            emitter.send(SseEmitter.event().name("connect").data("connected!"));
            sseService.add(memberId, emitter);
            sseService.sendSseEvents(memberId);
        } catch (IOException e) {
            log.error("Error while sending initial SSE to member {}", memberId, e);
            emitter.completeWithError(e);
        }
        return emitter;
    }
}
