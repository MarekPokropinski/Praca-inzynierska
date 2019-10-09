package FuzzySystems.FuzzySets.MembershipFunctions;

import javax.persistence.*;
import java.util.List;

@Entity
public class MembershipFunction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @OneToMany(targetEntity=FunctionFragment.class)
    private List<FunctionFragment> fragments;

    public MembershipFunction(List<FunctionFragment> fragments) {
        this.fragments = fragments;
    }
}
