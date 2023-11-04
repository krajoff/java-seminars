import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalendarTask {
    private List<Task> tasks = new ArrayList<>();
    final DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    public CalendarTask(List<Task> tasks) { this.tasks = tasks; }
    public CalendarTask() { new CalendarTask(tasks); }
    public void add(Task task) {
        sameTimeAndPerson(task);
        tasks.add(task);
    }
    public void toSave(String nameFile) throws IOException {
        File csvFile = new File(nameFile);
        FileWriter fileWriter = new FileWriter(csvFile);
        //Header
        fileWriter.write("id, " +
                "Дата создания, " +
                "Тема, " +
                "Начало, " +
                "Дэдлайн, " +
                "Длительность, " +
                "Имя, " +
                "Фамилия, " +
                "Отчество, " +
                "Нотификация, " +
                "Приоритет\n");
        for (Task task : tasks) {
            StringBuilder line = new StringBuilder();
            String[] data = {Integer.toString(task.id),
                    task.getCreationDateTime(),
                    task.getTopic(),
                    task.getStartTask().format(formatDateTime),
                    task.getDeadline().format(formatDateTime),
                    task.toStringDuration(),
                    task.person.getName(),
                    task.person.getSurname(),
                    task.person.getPatronymic(),
                    String.valueOf(task.getNotification()),
                    String.valueOf(task.getPriority()),
            };
            for (int i = 0; i < data.length; i++) {
                line.append("\"");
                line.append(data[i].replaceAll("\"", "\"\""));
                line.append("\"");
                if (i != data.length - 1) {
                    line.append(',');
                }
            }
            line.append("\n");
            fileWriter.write(line.toString());
        }
        fileWriter.close();
        System.out.printf("Запись файла %s выполена", nameFile);
    }


    public void sameTimeAndPerson(Task task) {
        for (Task someTask : tasks) {
            if (task.getStartTask().isBefore(someTask.getDeadline())
                    && someTask.getStartTask().isBefore(task.getDeadline())
                    && someTask.getPerson().equals(task.getPerson())) {
                System.out.println();
                System.out.printf("Для %s задача %s пересекается с задачей %s",
                        task.getPerson().getSurname(),
                        someTask.getTopic(),
                        task.getTopic());
                System.out.println();
            }
        }
    }

    @Override
    public String toString() {
        return "Календарь{" +
                tasks +
                '}';
    }
}
