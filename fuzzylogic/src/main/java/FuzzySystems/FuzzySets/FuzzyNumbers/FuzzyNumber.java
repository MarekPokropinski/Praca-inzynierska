package FuzzySystems.FuzzySets.FuzzyNumbers;

import FuzzySystems.DTOs.PlotDTO;

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
}
