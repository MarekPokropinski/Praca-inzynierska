package FuzzySystems.DTOs;

public class ValueToCreateDTO {
    private String name;
    private long variableId;

    public ValueToCreateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVariableId() {
        return variableId;
    }

    public void setVariableId(long variableId) {
        this.variableId = variableId;
    }
}
