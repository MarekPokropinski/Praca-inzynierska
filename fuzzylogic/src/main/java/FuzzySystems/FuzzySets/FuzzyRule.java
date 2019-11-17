package FuzzySystems.FuzzySets;

import javax.persistence.*;
import java.util.List;

@Entity
public class FuzzyRule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToMany
    private List<LinguisticValue> premises;

    @ManyToMany
    private List<LinguisticValue> conclusions;

    @ManyToOne
    private FuzzySystem fuzzySystem;

    public FuzzyRule() {
    }

    public FuzzyRule(FuzzySystem fuzzySystem, List<LinguisticValue> premises, List<LinguisticValue> conclusions) {
        this.fuzzySystem = fuzzySystem;
        this.premises = premises;
        this.conclusions = conclusions;
    }

    public FuzzySystem getFuzzySystem() {
        return fuzzySystem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<LinguisticValue> getPremises() {
        return premises;
    }

    public void setPremises(List<LinguisticValue> premises) {
        this.premises = premises;
    }

    public List<LinguisticValue> getConclusions() {
        return conclusions;
    }

    public void setConclusions(List<LinguisticValue> conclusions) {
        this.conclusions = conclusions;
    }
}
