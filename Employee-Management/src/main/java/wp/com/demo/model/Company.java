package wp.com.demo.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Company {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String company_name;

    private String owner;
    private Integer employee_num;
    private Integer intern_num;

    @ManyToOne
    private User user;

    public Company() {
    }

    public Company(String company_name, String owner, Integer employee_num, Integer intern_num) {
        this.company_name= company_name;
        this.owner = owner;
        this.employee_num = employee_num;
        this.intern_num = intern_num;
    }
}
