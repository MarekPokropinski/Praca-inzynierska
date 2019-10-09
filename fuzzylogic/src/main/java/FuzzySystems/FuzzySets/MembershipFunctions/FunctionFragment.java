package FuzzySystems.FuzzySets.MembershipFunctions;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class FunctionFragment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private float fragmentStart;
    private float fragmentEnd;

    public FunctionFragment(float start, float end) {
        fragmentStart = start;
        fragmentEnd = end;
    }
}
