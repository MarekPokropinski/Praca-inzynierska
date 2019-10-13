package FuzzySystems.FuzzySets;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LinguisticVariable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @OneToMany(mappedBy = "linguisticVariable", cascade = CascadeType.ALL)
    private List<LinguisticValue> values;

    public LinguisticVariable(){
        values = new ArrayList<>();
    }

    public LinguisticVariable(String name) {
        this();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<LinguisticValue> getValues() {
        return values;
    }
}
