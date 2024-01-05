package com.foodymoody.be.common.event;

public interface MessagePublisher {

    void publish(Event event);
}
