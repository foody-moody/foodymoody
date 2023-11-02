package com.foodymoody.be.common.event;

import org.springframework.context.ApplicationEventPublisher;


public class NotificationEvents {

    private static ApplicationEventPublisher publisher;

    public static void publish(NotificationEvent event) {
        publisher.publishEvent(event);
    }

    static void setPublisher(ApplicationEventPublisher publisher) {
        NotificationEvents.publisher = publisher;
    }
}
