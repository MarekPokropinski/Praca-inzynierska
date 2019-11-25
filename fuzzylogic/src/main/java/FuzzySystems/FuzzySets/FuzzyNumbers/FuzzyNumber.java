package FuzzySystems.FuzzySets.FuzzyNumbers;

import FuzzySystems.DTOs.PlotDTO;
import FuzzySystems.FuzzySets.LinguisticValue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fuzzylite.term.Term;
import org.springframework.data.util.Pair;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class FuzzyNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    public abstract String getType();

    @JsonIgnore
    @OneToOne
    private LinguisticValue linguisticValue;

    public FuzzyNumber(LinguisticValue linguisticValue) {
        this.linguisticValue = linguisticValue;
    }

    public FuzzyNumber() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    public abstract Term getTerm(String name);

    @JsonIgnore
    public abstract Pair<Double,Double> getRange();

//    public void setLinguisticValue(LinguisticValue linguisticValue) {
//        this.linguisticValue = linguisticValue;
//    }
}
