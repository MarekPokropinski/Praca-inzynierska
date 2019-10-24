package FuzzySystems.FuzzySets.Rules;

import FuzzySystems.FuzzySets.LinguisticValue;

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

    public FuzzyRule() {
    }

    public FuzzyRule(List<LinguisticValue> premises, List<LinguisticValue> conclusions) {
        this.premises = premises;
        this.conclusions = conclusions;
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
