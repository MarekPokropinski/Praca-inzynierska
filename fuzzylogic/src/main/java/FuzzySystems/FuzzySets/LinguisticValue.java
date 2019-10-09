package FuzzySystems.FuzzySets;

import FuzzySystems.FuzzySets.FuzzyNumbers.FuzzyNumber;

public class LinguisticValue {
    private String name;
    private FuzzyNumber number;

    public LinguisticValue(String name, FuzzyNumber number) {
        this.name = name;
        this.number = number;
    }
}
