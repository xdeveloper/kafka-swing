package ua.com.abakumov.kafkaswing;

public class TopicEnteredEvent {
    private final String name;

    public TopicEnteredEvent(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
