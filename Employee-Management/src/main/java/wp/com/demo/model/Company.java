package wp.com.demo.model;

import lombok.Data;

@Data
public class Company {
    private String company_name;
    private String owner;
    private Integer employee_num;
    private Integer intern_num;

    public Company(String company_name, String owner, Integer employee_num, Integer intern_num) {
        this.company_name= company_name;
        this.owner = owner;
        this.employee_num = employee_num;
        this.intern_num = intern_num;
    }
}
