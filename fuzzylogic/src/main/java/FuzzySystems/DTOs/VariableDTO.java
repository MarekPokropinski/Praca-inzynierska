package FuzzySystems.DTOs;

import FuzzySystems.FuzzySets.LinguisticVariable;

import java.util.List;
import java.util.stream.Collectors;

public class VariableDTO {
    private long id;
    private String name;
    private List<String> values;

    public VariableDTO(long id, String name, List<String> values) {
        this.id = id;
        this.name = name;
        this.values = values;
    }

    public static VariableDTO fromEntity(LinguisticVariable variable) {
        List<String> values = variable.getValues().stream().map(linguisticValue -> linguisticValue.getName()).collect(Collectors.toList());
        return new VariableDTO(variable.getId(), variable.getName(), values);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getValues() {
        return values;
    }
}
