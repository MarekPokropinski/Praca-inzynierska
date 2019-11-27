package FuzzySystems.DTOs;

import FuzzySystems.models.LinguisticValue;

public class ValueDTO {
    private String name;
    private long id;

    public ValueDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ValueDTO fromEntity(LinguisticValue linguisticValue) {
        return new ValueDTO(linguisticValue.getId(), linguisticValue.getName());
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
