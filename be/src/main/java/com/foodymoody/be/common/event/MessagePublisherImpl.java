package com.foodymoody.be.common.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MessagePublisherImpl implements MessagePublisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(Event event) {
        publisher.publishEvent(event);
    }

}

