package FuzzySystems.DTOs;

import FuzzySystems.models.LinguisticVariable;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class VariableDTO {
    private long id;
    private String name;
    private List<ValueDTO> values;
    private boolean isInput;
    private long systemId;
    private Pair<Double, Double> range;

    public VariableDTO(){}

    public VariableDTO(long systemId, long id, String name, List<ValueDTO> values, boolean isInput, Pair<Double, Double> range) {
        this(systemId, id, name, values, isInput);
        this.range = range;
    }

    public VariableDTO(long systemId, long id, String name, List<ValueDTO> values, boolean isInput) {
        this.systemId = systemId;
        this.id = id;
        this.name = name;
        this.values = values;
        this.isInput = isInput;
    }

    public static VariableDTO fromEntity(LinguisticVariable variable) {
        List<ValueDTO> values = variable.getValues().stream().map(linguisticValue -> ValueDTO.fromEntity(linguisticValue)).collect(Collectors.toList());
        return new VariableDTO(
                variable.getFuzzySystem().getId(),
                variable.getId(),
                variable.getName(),
                values,
                variable.isInput(),
                variable.getRange());
    }

    public long getSystemId() {
        return systemId;
    }

    public void setSystemId(long systemId) {
        this.systemId = systemId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ValueDTO> getValues() {
        return values;
    }

    public boolean isInput() {
        return isInput;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInput(boolean input) {
        isInput = input;
    }

    public Pair<Double, Double> getRange() {
        return range;
    }
}
