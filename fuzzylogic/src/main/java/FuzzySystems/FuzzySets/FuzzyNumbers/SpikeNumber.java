package FuzzySystems.FuzzySets.FuzzyNumbers;

import com.fuzzylite.term.Spike;
import com.fuzzylite.term.Term;
import org.springframework.data.util.Pair;

import javax.persistence.Entity;

@Entity
public class SpikeNumber extends FuzzyNumber {
    private double center;
    private double width;

    public SpikeNumber() {
    }

    public SpikeNumber(double center, double width) {
        this.center = center;
        this.width = width;
    }


    @Override
    public String getType() {
        return "spike";
    }

    @Override
    public Term getTerm(String name) {
        return new Spike(name, center, width);
    }

    @Override
    public Pair<Double, Double> getRange() {
        return Pair.of(center - width / 2, center + width / 2);
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
}
