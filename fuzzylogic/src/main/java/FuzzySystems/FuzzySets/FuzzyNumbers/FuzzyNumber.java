package FuzzySystems.FuzzySets.FuzzyNumbers;

import FuzzySystems.DTOs.PlotDTO;
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

    //    public FuzzyNumber(MembershipFunction membershipFunction) {
//        super(membershipFunction);
//        // check if valid membership function
//    }
    public abstract String getType();

    public abstract PlotDTO getPlot(String name);

    @JsonIgnore
    public abstract Term getTerm(String name);

    @JsonIgnore
    public abstract Pair<Double,Double> getRange();
}
