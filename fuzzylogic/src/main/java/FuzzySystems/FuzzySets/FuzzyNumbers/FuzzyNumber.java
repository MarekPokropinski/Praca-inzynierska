package FuzzySystems.FuzzySets.FuzzyNumbers;

import FuzzySystems.FuzzySets.MembershipFunctions.MembershipFunction;
import FuzzySystems.FuzzySets.FuzzySet;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class FuzzyNumber extends FuzzySet {
    public FuzzyNumber(MembershipFunction membershipFunction) {
        super(membershipFunction);
        // check if valid membership function
    }
}
