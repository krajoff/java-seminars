public class Person {
    private String name;
    private String surname;
    private String patronymic;

    Person(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setName(String name) { this.name = name; }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public String toString() {
        return "Автор{" +
                "имя='" + name + '\'' +
                ", фамилия='" + surname + '\'' +
                ", отчество='" + patronymic + '\'' +
                '}';
    }
}
