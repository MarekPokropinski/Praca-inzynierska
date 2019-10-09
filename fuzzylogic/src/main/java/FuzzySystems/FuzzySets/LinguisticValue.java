package FuzzySystems.FuzzySets;

import FuzzySystems.FuzzySets.FuzzyNumbers.FuzzyNumber;

import javax.persistence.*;

@Entity
public class LinguisticValue {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    @OneToOne
    private FuzzyNumber number;

    public LinguisticValue() {}

    public LinguisticValue(String name, FuzzyNumber number) {
        this.name = name;
        this.number = number;
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
}
