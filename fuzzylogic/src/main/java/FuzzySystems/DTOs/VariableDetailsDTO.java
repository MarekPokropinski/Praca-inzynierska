package FuzzySystems.DTOs;

import FuzzySystems.FuzzySets.LinguisticValue;
import FuzzySystems.FuzzySets.LinguisticVariable;

import java.util.List;

public class VariableDetailsDTO {
    private String name;
    List<LinguisticValue> values;

    public VariableDetailsDTO(String name, List<LinguisticValue> values) {
        this.name = name;
        this.values = values;
    }

    public static VariableDetailsDTO fromEntity(LinguisticVariable variable){
        return new VariableDetailsDTO(variable.getName(), variable.getValues());
    }

    public String getName() {
        return name;
    }

    public List<LinguisticValue> getValues() {
        return values;
    }
}
