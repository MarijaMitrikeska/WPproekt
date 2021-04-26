package wp.com.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
public class Intern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String surname;
    private String Embg;
    private String email;
    private String street;
    private String city;
    private String country;
    private String intership_section;
    private String department;


    private LocalDate intership_start;
    private String intership_duration;

    private String phone;
    private Integer projects;
    private Integer intership_salary;
    private Integer experience;
    private String imageSource;

    @ManyToOne
    private Company companyId;

    public Intern() {
    }

    public Intern(String name, String surname, String embg, String email, String street, String city, String country,
                  String intership_section, String department, LocalDate intership_start, String intership_duration, String phone, Integer projects, Integer intership_salary,
                  Integer experience, String imageSource, Company companyId) {
        this.name = name;
        this.surname = surname;
        Embg = embg;
        this.email = email;
        this.street = street;
        this.city = city;
        this.country = country;
        this.intership_section = intership_section;
        this.department = department;
        this.intership_start = intership_start;
        this.intership_duration = intership_duration;
        this.phone = phone;
        this.projects = projects;
        this.intership_salary = intership_salary;
        this.experience = experience;
        this.imageSource = imageSource;
        this.companyId = companyId;
    }
}
