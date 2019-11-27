package FuzzySystems.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LinguisticVariable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @OneToMany(mappedBy = "linguisticVariable", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<LinguisticValue> values;

    private boolean isInput;

    @ManyToOne
    @JoinColumn
    private FuzzySystem fuzzySystem;

    public LinguisticVariable() {
        values = new ArrayList<>();
    }

    public LinguisticVariable(FuzzySystem fuzzySystem, String name, boolean isInput) {
        this();
        this.fuzzySystem = fuzzySystem;
        this.name = name;
        this.isInput=isInput;
    }

    public LinguisticVariable(FuzzySystem fuzzySystem, long id, String name, boolean isInput) {
        this(fuzzySystem, name, isInput);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setValues(List<LinguisticValue> values) {
        this.values = values;
    }

    public List<LinguisticValue> getValues() {
        return values;
    }

    public boolean isInput() {
        return isInput;
    }

    public FuzzySystem getFuzzySystem() {
        return fuzzySystem;
    }

    @JsonIgnore
    public Pair<Double,Double> getRange() {
        return values.stream()
                .map(value->value.getNumber()==null?Pair.of(0.0,0.0):value.getNumber().getRange())
                .reduce((range,acc)->Pair.of(Math.min(range.getFirst(),acc.getFirst()),Math.max(range.getSecond(),acc.getSecond())))
                .orElse(Pair.of(0.0,0.0));
    }
}
