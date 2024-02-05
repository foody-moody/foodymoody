package com.foodymoody.be.common.event;

import java.util.ArrayList;
import java.util.List;


public class EventManager {

    private static final ThreadLocal<List<Event>> threadLocalEvents = new ThreadLocal<>();

    private EventManager() {
        throw new AssertionError();
    }

    public static List<Event> getEvents() {
        if (threadLocalEvents.get() == null) {
            threadLocalEvents.set(new ArrayList<>());
        }
        return threadLocalEvents.get();
    }

    public static void clear() {
        threadLocalEvents.remove();
    }

    public static void raise(Event event) {
        if (event == null) {
            return;
        }
        getEvents().add(event);
    }
}
