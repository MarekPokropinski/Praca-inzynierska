package FuzzySystems.FuzzySets.FuzzyNumbers;

import FuzzySystems.DTOs.PlotDTO;
import FuzzySystems.FuzzySets.MembershipFunctions.MembershipFunction;
import FuzzySystems.FuzzySets.FuzzySet;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class FuzzyNumber {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
//    public FuzzyNumber(MembershipFunction membershipFunction) {
//        super(membershipFunction);
//        // check if valid membership function
//    }
    public abstract String getType();
    public abstract PlotDTO getPlot(String name);
}
