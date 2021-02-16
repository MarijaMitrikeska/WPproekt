package wp.com.demo.model;

import lombok.Data;

@Data
public class Contract {

    private Long code;
    private Long datum;

    public Contract(Long code, Long datum) {
        this.code = code;
        this.datum=datum;
    }
}
