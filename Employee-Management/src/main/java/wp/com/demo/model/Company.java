package wp.com.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Company {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String moto;

    private String owner;
    private Integer employee_num;
    private Integer intern_num;
    @OneToMany
    private List<Employee> employees;
//TODO: metod koj ke proveruva dali kopceto edit i add employee za soodvetnata kompanija(spored id) na drugite
    //moze samo da go gleda opisot bez nikakvo kopce
    /*@ManyToOne
    private User user;*/

    public Company() {
    }

    public Company( String name, String description, String moto, String owner, Integer employee_num, Integer intern_num) {

        this.name = name;
        this.description = description;
        this.moto = moto;
        this.owner = owner;
        this.employee_num = employee_num;
        this.intern_num = intern_num;
    }
}
