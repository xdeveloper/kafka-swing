package ua.com.abakumov.kafkaswing;

import com.google.common.eventbus.EventBus;

public class EventBusSingleton {

    private static EventBus eventBus;

    public static EventBus getEventBus() {
        if (eventBus == null) {
            eventBus = new EventBus();
        }
        return eventBus;
    }
}
