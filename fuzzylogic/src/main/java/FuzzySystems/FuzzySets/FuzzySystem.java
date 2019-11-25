package FuzzySystems.FuzzySets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
public class FuzzySystem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "fuzzySystem")
    private List<LinguisticVariable> linguisticVariables;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "fuzzySystem")
    private List<FuzzyRule> fuzzyRules;

    @Column(unique = true)
    private String name;

    private String conjunction;
    //    private String disjunction;
    private String implication;
    private String aggregation;
    private String defuzzifier;


    public FuzzySystem() {
    }

    public FuzzySystem(String name, String conjunction, String implication, String aggregation, String defuzzifier) {
        this.name = name;
        this.conjunction = conjunction;
        this.implication = implication;
        this.aggregation = aggregation;
        this.defuzzifier = defuzzifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConjunction() {
        return conjunction;
    }

    public void setConjunction(String conjunction) {
        this.conjunction = conjunction;
    }

    public String getImplication() {
        return implication;
    }

    public void setImplication(String implication) {
        this.implication = implication;
    }

    public String getAggregation() {
        return aggregation;
    }

    public void setAggregation(String aggregation) {
        this.aggregation = aggregation;
    }

    public String getDefuzzifier() {
        return defuzzifier;
    }

    public void setDefuzzifier(String defuzzifier) {
        this.defuzzifier = defuzzifier;
    }
}
