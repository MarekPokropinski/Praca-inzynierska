package FuzzySystems.FuzzySets.FuzzyNumbers;

import FuzzySystems.DTOs.PlotDTO;
import FuzzySystems.FuzzySets.MembershipFunctions.FunctionFragment;
import FuzzySystems.FuzzySets.MembershipFunctions.LinearFragment;
import FuzzySystems.FuzzySets.MembershipFunctions.MembershipFunction;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
public class TriangularNumber extends FuzzyNumber {
//    private static MembershipFunction buildMembershipFunction(float start, float middle, float end){
//        ArrayList<FunctionFragment> fragments = new ArrayList<FunctionFragment>();
//        float a = 1 / (middle - start);
//        float b = -a * start;
//        fragments.add(new LinearFragment(start, middle, a, b));
//
//        a = 1 / (middle - end);
//        b = -a * end;
//        fragments.add(new LinearFragment(middle, end, a, b));
//
//        return new MembershipFunction(fragments);
//    }

    private float a, b, c;


    public TriangularNumber() {
    }

    public TriangularNumber(float start, float middle, float end) {
//        super(TriangularNumber.buildMembershipFunction(start, middle, end));
        this.a = start;
        this.b = middle;
        this.c = end;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getC() {
        return c;
    }

    public void setC(float c) {
        this.c = c;
    }

    @Override
    public String getType() {
        return "triangular";
    }

    @Override
    public PlotDTO getPlot(String name) {
        return new PlotDTO(Arrays.asList(this.a,this.b,this.c),Arrays.asList(0.0f,1.0f,0.0f),name, "linear");
    }
}
