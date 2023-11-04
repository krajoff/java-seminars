import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    public int id;
    final Person person;
    private String topic;

    enum Priority {High, Medium, Low}

    private Duration duration;
    private LocalDateTime startTask;
    private LocalDateTime deadline;
    public LocalDateTime creationDateTime;

    enum Notification {On, Off}

    private Priority priority;
    private Notification notification;
    final DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    static int cnt = 1;

    public Task(String topic, Person person, LocalDateTime startTask, LocalDateTime deadline) {
        this.id = cnt;
        this.topic = topic;
        this.person = person;
        this.startTask = startTask;
        this.deadline = deadline;
        this.duration = Duration.between(startTask, deadline);
        this.creationDateTime = LocalDateTime.now();
        this.priority = Priority.Medium;
        this.notification = Notification.Off;
        cnt++;
    }

    public void setDuration(LocalDateTime startTask) {
        this.startTask = startTask;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Duration getDuration() {
        return duration;
    }

    public String toStringDuration() {
        if (String.format("%d", duration.toHours()).equals("0")) {
            return String.format("%d мин.", duration.toMinutes());
        } else {
            return String.format("%d ч. %d мин.",
                    duration.toHours(),
                    duration.toMinutesPart());
        }
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public LocalDateTime getStartTask() {
        return startTask;
    }

    public String getTopic() {
        return topic;
    }

    public Person getPerson() {
        return person;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getCreationDateTime() {
        return creationDateTime.format(formatDateTime);
    }

    public Notification getNotification() {
        return notification;
    }

    @Override
    public String toString() {
        return "Задача{" +
                "номер='" + id + '\'' +
                " " + person + '\'' +
                ", тема='" + topic + '\'' +
                ", начало=" + startTask.format(formatDateTime) +
                ", дэдлайн=" + deadline.format(formatDateTime) +
                ", длительность=" + toStringDuration() +
                ", дата создания записи=" + creationDateTime.format(formatDateTime) +
                ", приоритет=" + priority +
                ", нотификация=" + notification +
                '}' + '\n';
    }
}
