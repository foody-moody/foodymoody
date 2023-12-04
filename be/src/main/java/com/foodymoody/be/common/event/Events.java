package com.foodymoody.be.common.event;

import org.springframework.context.ApplicationEventPublisher;


public class Events {

    private static ApplicationEventPublisher publisher;

    private Events() {
        throw new IllegalStateException("Utility class");
    }

    public static void publish(Event event) {
        publisher.publishEvent(event);
    }

    public static void setPublisher(ApplicationEventPublisher publisher) {
        Events.publisher = publisher;
    }
}
