package wp.com.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Company {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    private String name;
    private String description;
    private String moto;

    private String owner;
    private Integer employee_num;
    private Integer intern_num;

//    @OneToOne
//    private User user;
    private  String companyUsername;

//    @Column(nullable = true, length = 64)
    private String imageSource;

    @OneToMany(mappedBy = "companyId")
    private List<Employee> employees;

//TODO: metod koj ke proveruva dali kopceto edit i add employee za soodvetnata kompanija(spored id) na drugite


    public Company() {
    }

    public Company( String companyUsername,String name, String description, String moto, String owner,  String imageSource,Integer employee_num, Integer intern_num) {

        this.name = name;
        this.description = description;
        this.moto = moto;
        this.owner = owner;
        this.employee_num = employee_num;
        this.intern_num = intern_num;
        this.imageSource=imageSource;
        this.companyUsername=companyUsername;

    }
}
