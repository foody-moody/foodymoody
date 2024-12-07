package com.foodymoody.be.sse.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.sse.application.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequiredArgsConstructor
@Controller
public class SseController {

    private final SseService sseService;

    @GetMapping(value = "/api/sse", produces = "text/event-stream;charset=UTF-8")
    public SseEmitter stream(@CurrentMemberId MemberId memberId) {
        return sseService.stream(memberId);
    }

}
