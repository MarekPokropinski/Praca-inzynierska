package FuzzySystems.FuzzySets;

import FuzzySystems.FuzzySets.MembershipFunctions.MembershipFunction;

import javax.persistence.*;

@MappedSuperclass
public class FuzzySet {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @OneToOne(targetEntity=MembershipFunction.class)
    private MembershipFunction membershipFunction;

    public FuzzySet(MembershipFunction membershipFunction) {
        this.membershipFunction = membershipFunction;
    }
}