package com.foodymoody.be.common.event;

import org.springframework.context.ApplicationEventPublisher;


public class NotificationEvents {

    private static ApplicationEventPublisher publisher;

    private NotificationEvents() {
        throw new IllegalStateException("Utility class");
    }

    public static void publish(NotificationEvent event) {
        publisher.publishEvent(event);
    }

    public static void setPublisher(ApplicationEventPublisher publisher) {
        NotificationEvents.publisher = publisher;
    }
}
