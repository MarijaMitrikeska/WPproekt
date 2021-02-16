package wp.com.demo.model;


import lombok.Data;

@Data
public class User {

    private Long Id;
    private String name;
    private String surname;
    private String username;
    private String password;

    public User(Long id, String name, String surname, String username, String password) {
        Id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }
}
