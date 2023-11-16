package Catalog;

public class Employee {
    private String name;
    private Integer id;
    private Double experience;
    private String phone;

    public Employee(String name, Integer id, Double experience, String phone) {
        this.name = name;
        this.id = id;
        this.experience = experience;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", experience=" + experience +
                ", phone='" + phone + '\'' +
                '}';
    }
}
