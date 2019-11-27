package FuzzySystems.models.FuzzyNumbers;

import FuzzySystems.models.LinguisticValue;
import com.fuzzylite.term.PiShape;
import com.fuzzylite.term.Term;
import org.springframework.data.util.Pair;

import javax.persistence.Entity;

@Entity
public class PiShapeNumber extends FuzzyNumber {
    private double bottomLeft;
    private double topLeft;
    private double topRight;
    private double bottomRight;

    public PiShapeNumber() {
    }

    public PiShapeNumber(LinguisticValue linguisticValue, double bottomLeft, double topLeft, double topRight, double bottomRight) {
        super(linguisticValue);
        this.bottomLeft = bottomLeft;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomRight = bottomRight;
    }

    @Override
    public String getType() {
        return "pi";
    }

    @Override
    public Term getTerm(String name) {
        return new PiShape(name, bottomLeft, topLeft, topRight, bottomRight);
    }

    @Override
    public Pair<Double, Double> getRange() {
        return Pair.of(bottomLeft, bottomRight);
    }

    public double getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(double bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public double getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(double topLeft) {
        this.topLeft = topLeft;
    }

    public double getTopRight() {
        return topRight;
    }

    public void setTopRight(double topRight) {
        this.topRight = topRight;
    }

    public double getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(double bottomRight) {
        this.bottomRight = bottomRight;
    }
}

