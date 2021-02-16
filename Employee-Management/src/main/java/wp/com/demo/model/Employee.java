package wp.com.demo.model;


import lombok.Data;

@Data
public class Employee {
    private String employee_name;
    private String employee_surname;
    private Long employee_Embg;

    public Employee(String employee_name, String employee_surname, Long employee_Embg) {
        this.employee_name = employee_name;
        this.employee_surname = employee_surname;
        this.employee_Embg = employee_Embg;
    }
}
