package FuzzySystems.FuzzySets.MembershipFunctions;

import javax.persistence.Entity;

@Entity
public class LinearFragment extends FunctionFragment {
    // Line parameters y = ax + b
    private float a;
    private float b;

    public LinearFragment(float start, float end, float a, float b) {
        super(start, end);
        this.a = a;
        this.b = b;
    }
}
