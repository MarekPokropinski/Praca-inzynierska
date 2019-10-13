package FuzzySystems.DTOs;

public class ValueToCreateDTO {
    private String name;
    private long variableId;
    public ValueToCreateDTO() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setVariableId(long variableId) {
        this.variableId = variableId;
    }

    public String getName() {
        return name;
    }

    public long getVariableId() {
        return variableId;
    }
}
