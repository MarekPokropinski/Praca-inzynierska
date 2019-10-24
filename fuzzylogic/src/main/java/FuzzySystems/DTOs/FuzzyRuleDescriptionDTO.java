package FuzzySystems.DTOs;

public class FuzzyRuleDescriptionDTO {
    private long id;
    private String description;

    public FuzzyRuleDescriptionDTO(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
