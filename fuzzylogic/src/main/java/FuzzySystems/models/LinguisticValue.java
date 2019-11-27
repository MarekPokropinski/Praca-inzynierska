package FuzzySystems.models;

import FuzzySystems.models.FuzzyNumbers.FuzzyNumber;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class LinguisticValue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "linguisticValue")
    private FuzzyNumber number;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private LinguisticVariable linguisticVariable;

    public LinguisticValue() {
    }

    public LinguisticValue(String name, LinguisticVariable linguisticVariable) {
        this.name = name;
        this.linguisticVariable = linguisticVariable;
    }

    public LinguisticValue(String name, FuzzyNumber number) {
        this.name = name;
        this.number = number;
    }

    public LinguisticValue(String name, FuzzyNumber number, LinguisticVariable linguisticVariable) {
        this.name = name;
        this.number = number;
        this.linguisticVariable = linguisticVariable;
    }

    public LinguisticVariable getLinguisticVariable() {
        return linguisticVariable;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FuzzyNumber getNumber() {
        return number;
    }

    public void setNumber(FuzzyNumber number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }
}
