package FuzzySystems.FuzzySets;

import FuzzySystems.FuzzySets.FuzzyNumbers.FuzzyNumber;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class LinguisticValue {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    @OneToOne
    @JoinColumn
    private FuzzyNumber number;

    @ManyToOne
    @JoinColumn
    private LinguisticVariable linguisticVariable;

    public LinguisticValue() {}

    public LinguisticValue(String name, LinguisticVariable linguisticVariable) {
        this.name = name;
        this.linguisticVariable = linguisticVariable;
    }

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

    public void setNumber(FuzzyNumber number) {
        this.number = number;
    }
}
