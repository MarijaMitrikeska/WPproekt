package wp.com.demo.model;

import lombok.Data;

@Data
public class Intern {

    private String intern_name;
    private String intern_surname;
    private Long intern_embg;

    public Intern(String intern_name, String intern_surname, Long intern_embg) {
        this.intern_name = intern_name;
        this.intern_surname = intern_surname;
        this.intern_embg = intern_embg;
    }
}
