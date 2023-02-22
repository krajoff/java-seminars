import java.time.LocalDateTime;

public class PhoneCalling extends Task {
    public PhoneCalling(String topic, Person person, LocalDateTime startTask, LocalDateTime deadline) {
        super(topic, person, startTask, deadline);
    }
}
