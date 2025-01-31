package ua.com.abakumov.kafkaswing;

public class TopicSelectedEvent {
    private final int id;
    private final String name;

    public TopicSelectedEvent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
