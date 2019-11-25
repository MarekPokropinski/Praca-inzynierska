package FuzzySystems.FuzzySets.FuzzyNumbers;

import FuzzySystems.DTOs.PlotDTO;
import FuzzySystems.FuzzySets.LinguisticValue;
import com.fuzzylite.term.Term;
import com.fuzzylite.term.Triangle;
import org.springframework.data.util.Pair;

import javax.persistence.Entity;
import java.util.Arrays;

@Entity
public class TriangularNumber extends FuzzyNumber {
    private float a, b, c;

    public TriangularNumber() {
    }

    public TriangularNumber(LinguisticValue linguisticValue) {
        super(linguisticValue);
    }

    public TriangularNumber(LinguisticValue linguisticValue, float a, float b, float c) {
        super(linguisticValue);
        this.a = a;
        this.b = b;
        this.c = c;
    }

//    public TriangularNumber(float start, float middle, float end) {
//        this.a = start;
//        this.b = middle;
//        this.c = end;
//    }

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
    public Term getTerm(String name) {
        return new Triangle(name,a,b,c);
    }

    @Override
    public Pair<Double, Double> getRange() {
        return Pair.of((double)a,(double)c);
    }

}
