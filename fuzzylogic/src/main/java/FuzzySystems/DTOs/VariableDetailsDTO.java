package FuzzySystems.DTOs;

import FuzzySystems.FuzzySets.LinguisticValue;
import FuzzySystems.FuzzySets.LinguisticVariable;

import java.util.List;

public class VariableDetailsDTO {
    private long id;
    private String name;
    private List<LinguisticValue> values;

    public VariableDetailsDTO(long id, String name, List<LinguisticValue> values) {
        this.id = id;
        this.name = name;
        this.values = values;
    }

    public static VariableDetailsDTO fromEntity(LinguisticVariable variable) {
        return new VariableDetailsDTO(variable.getId(), variable.getName(), variable.getValues());
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
}
