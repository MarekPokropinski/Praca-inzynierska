package FuzzySystems.DTOs;

import java.util.List;

public class FuzzySystemToGenerateDTO {
    private String name;
    private List<String> variablesNames;
    private float[][] data;
    private int fuzzyNumbersPerVariable;
    private int output;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getVariablesNames() {
        return variablesNames;
    }

    public void setVariablesNames(List<String> variablesNames) {
        this.variablesNames = variablesNames;
    }

    public float[][] getData() {
        return data;
    }

    public void setData(float[][] data) {
        this.data = data;
    }

    public int getFuzzyNumbersPerVariable() {
        return fuzzyNumbersPerVariable;
    }

    public void setFuzzyNumbersPerVariable(int fuzzyNumbersPerVariable) {
        this.fuzzyNumbersPerVariable = fuzzyNumbersPerVariable;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }
}
