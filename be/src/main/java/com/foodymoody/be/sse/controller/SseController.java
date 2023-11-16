package com.foodymoody.be.sse.controller;

import com.foodymoody.be.sse.service.SseService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequiredArgsConstructor
@Controller
public class SseController {

    private final SseService sseService;

    @GetMapping(value = "/api/sse/{memberId}", produces = "text/event-stream;charset=UTF-8")
    public SseEmitter streamSeeMvc(@PathVariable String memberId) throws IOException {
        SseEmitter emitter = new SseEmitter();
        emitter.send(SseEmitter.event()
                .name("connect")
                .data("connected!"));
        sseService.add(memberId, emitter);
        sseService.sendSseEvents(memberId);
        return emitter;
    }
}
