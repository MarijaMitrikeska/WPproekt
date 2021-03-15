package wp.com.demo.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //TODO: isklucok i metod za veke zacuvan vraboten
    //TODO: metod so koj koga ke se zacuva vraboten istiot da se zacuva i vo listata na vraboteni vo kompanijata
    private String company;
    private String name;
    private String surname;
    private String Embg;
    private String email;
    private String street;
    private String city;
    private String country;
    private String jobTitle;
    private String department;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date employmentDate;
    private String status;
    private String phone;
    private Integer projects;
    private Integer salary;
    private Integer experience;


    public Employee() {
    }

    public Employee( String name, String surname, String embg, String email, String street, String city, String country, String jobTitle, String department, Date employmentDate, String status, String phone, Integer projects, Integer salary, Integer experience) {

        this.name = name;
        this.surname = surname;
        Embg = embg;
        this.email = email;
        this.street = street;
        this.city = city;
        this.country = country;
        this.jobTitle = jobTitle;
        this.department = department;


        this.employmentDate = employmentDate;

        this.status = status;
        this.phone = phone;
        this.projects = projects;
        this.salary = salary;
        this.experience = experience;
    }
}
