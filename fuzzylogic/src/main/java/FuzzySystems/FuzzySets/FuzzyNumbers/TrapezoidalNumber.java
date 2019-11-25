package FuzzySystems.FuzzySets.FuzzyNumbers;

import FuzzySystems.DTOs.PlotDTO;
import FuzzySystems.FuzzySets.LinguisticValue;
import com.fuzzylite.term.Term;
import com.fuzzylite.term.Trapezoid;
import org.springframework.data.util.Pair;

import javax.persistence.Entity;
import java.util.Arrays;

@Entity
public class TrapezoidalNumber extends FuzzyNumber {

    private float a, b, c, d;


    public TrapezoidalNumber() {
    }

    public TrapezoidalNumber(LinguisticValue linguisticValue, float a, float b, float c, float d) {
        super(linguisticValue);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
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

    public float getD() {
        return d;
    }

    public void setD(float d) {
        this.d = d;
    }

    @Override
    public String getType() {
        return "trapezoidal";
    }


    @Override
    public Term getTerm(String name) {
        return new Trapezoid(name,a,b,c,d);
    }

    @Override
    public Pair<Double, Double> getRange() {
        return Pair.of((double)a,(double)d);
    }
}
