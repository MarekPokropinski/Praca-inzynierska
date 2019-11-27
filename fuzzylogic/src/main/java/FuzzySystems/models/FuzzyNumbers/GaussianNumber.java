package FuzzySystems.models.FuzzyNumbers;

import FuzzySystems.models.LinguisticValue;
import com.fuzzylite.term.Gaussian;
import com.fuzzylite.term.Term;
import org.springframework.data.util.Pair;

import javax.persistence.Entity;

@Entity
public class GaussianNumber extends FuzzyNumber {
    private double mean;
    private double stddev;

    public GaussianNumber(LinguisticValue linguisticValue, double mean, double stddev) {
        super(linguisticValue);
        this.mean = mean;
        this.stddev = stddev;
    }

    public GaussianNumber() {
    }

    @Override
    public String getType() {
        return "gaussian";
    }


    @Override
    public Term getTerm(String name) {
        return new Gaussian(name, mean, stddev);
    }

    @Override
    public Pair<Double, Double> getRange() {
        return Pair.of(mean - 3 * stddev, mean + 3 * stddev);
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStddev() {
        return stddev;
    }

    public void setStddev(double stddev) {
        this.stddev = stddev;
    }
}
