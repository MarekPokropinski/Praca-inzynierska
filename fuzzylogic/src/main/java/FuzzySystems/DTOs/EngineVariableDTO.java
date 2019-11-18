package FuzzySystems.DTOs;

public class EngineVariableDTO {
    private String name;
    private double value;

    public EngineVariableDTO(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public EngineVariableDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
