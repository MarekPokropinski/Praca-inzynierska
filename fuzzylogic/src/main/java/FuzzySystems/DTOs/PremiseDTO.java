package FuzzySystems.DTOs;

public class PremiseDTO {
    private long variableId;
    private String variableName;
    private long valueId;
    private String valueName;

    public PremiseDTO(long variableId, String variableName, long valueId, String valueName) {
        this.variableId = variableId;
        this.variableName = variableName;
        this.valueId = valueId;
        this.valueName = valueName;
    }

    public long getVariableId() {
        return variableId;
    }

    public String getVariableName() {
        return variableName;
    }

    public long getValueId() {
        return valueId;
    }

    public String getValueName() {
        return valueName;
    }
}
