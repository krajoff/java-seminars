package seminars.fifth.note;

import java.util.Objects;

public class Contact {
    private static int count = 0;
    int Id;
    String Name;
    String Surname;
    String Company;
    Integer Phone;

    public Contact(int id, String name, String surname, String company, Integer phone) {
        Id = id;
        Name = name;
        Surname = surname;
        Company = company;
        Phone = phone;
        count++;
    }

    public Contact() {
        new Contact(0,"", "", "", null);
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public Integer getPhone() {
        return Phone;
    }

    public void setPhone(Integer phone) {
        Phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + Id +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Company='" + Company + '\'' +
                ", Phone=" + Phone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(Name, contact.Name)
                && Objects.equals(Surname, contact.Surname)
                && Objects.equals(Company, contact.Company)
                && Objects.equals(Phone, contact.Phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, Surname, Company, Phone);
    }
}
