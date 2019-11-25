package FuzzySystems.FuzzySets.FuzzyNumbers;

import FuzzySystems.FuzzySets.LinguisticValue;
import com.fuzzylite.term.Bell;
import com.fuzzylite.term.Term;
import org.springframework.data.util.Pair;

import javax.persistence.Entity;

@Entity
public class BellNumber extends FuzzyNumber {
    private double center;
    private double width;
    private double slope;

    public BellNumber() {
    }

    public BellNumber(LinguisticValue linguisticValue, double center, double width, double slope) {
        super(linguisticValue);
        this.center = center;
        this.width = width;
        this.slope = slope;
    }


    @Override
    public String getType() {
        return "bell";
    }

    @Override
    public Term getTerm(String name) {
        return new Bell(name, center, width, slope);
    }

    @Override
    public Pair<Double, Double> getRange() {
//        return Pair.of(center - width / 2 - slope, center + width / 2 + slope);
        double w = Math.pow(99,1/(2*slope))*width;
        return Pair.of(center - w, center + w);
    }

    public double getCenter() {
        return center;
    }

    public void setCenter(double center) {
        this.center = center;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }
}
