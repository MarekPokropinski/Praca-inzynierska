package FuzzySystems.DTOs;

import FuzzySystems.FuzzySets.LinguisticValue;
import FuzzySystems.FuzzySets.LinguisticVariable;

import java.util.List;

public class VariableDetailsDTO {
    private long id;
    private String name;
    private List<LinguisticValue> values;
    private boolean isInput;

    public VariableDetailsDTO(long id, String name, List<LinguisticValue> values, boolean isInput) {
        this.id = id;
        this.name = name;
        this.values = values;
        this.isInput = isInput;
    }

    public static VariableDetailsDTO fromEntity(LinguisticVariable variable) {
        return new VariableDetailsDTO(variable.getId(), variable.getName(), variable.getValues(), variable.isInput());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<LinguisticValue> getValues() {
        return values;
    }

    public boolean isInput() {
        return isInput;
    }
}
