import java.time.LocalDateTime;

public class Meeting extends Task{
    public Meeting(String topic, Person person, LocalDateTime startTask, LocalDateTime deadline) {
        super(topic, person, startTask, deadline);
    }
}
