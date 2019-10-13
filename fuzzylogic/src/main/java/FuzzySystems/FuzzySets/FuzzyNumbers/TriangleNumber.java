package FuzzySystems.FuzzySets.FuzzyNumbers;

import FuzzySystems.FuzzySets.MembershipFunctions.FunctionFragment;
import FuzzySystems.FuzzySets.MembershipFunctions.LinearFragment;
import FuzzySystems.FuzzySets.MembershipFunctions.MembershipFunction;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class TriangleNumber extends FuzzyNumber {
    private static MembershipFunction buildMembershipFunction(float start, float middle, float end){
        ArrayList<FunctionFragment> fragments = new ArrayList<FunctionFragment>();
        float a = 1 / (middle - start);
        float b = -a * start;
        fragments.add(new LinearFragment(start, middle, a, b));

        a = 1 / (middle - end);
        b = -a * end;
        fragments.add(new LinearFragment(middle, end, a, b));

        return new MembershipFunction(fragments);
    }

    public TriangleNumber(float start, float middle, float end) {
        super(TriangleNumber.buildMembershipFunction(start, middle, end));
    }
}
