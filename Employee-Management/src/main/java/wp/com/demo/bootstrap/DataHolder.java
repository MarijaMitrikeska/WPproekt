package wp.com.demo.bootstrap;

import lombok.Getter;
import org.springframework.stereotype.Component;
import wp.com.demo.model.*;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Employee> employees=new ArrayList<>();
    public static List<User>users=new ArrayList<>();
    public static List<Intern>interns=new ArrayList<>();
    public static List<Company>companies=new ArrayList<>();
    public static List<Contract>contracts=new ArrayList<>();


}
