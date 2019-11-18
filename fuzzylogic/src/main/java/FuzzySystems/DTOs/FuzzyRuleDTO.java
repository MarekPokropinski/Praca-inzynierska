package FuzzySystems.DTOs;

import java.util.List;

public class FuzzyRuleDTO {
    private List<Long> premises;
    private List<Long> conclusions;

    public FuzzyRuleDTO(List<Long> premises, List<Long> conclusions) {
        this.premises = premises;
        this.conclusions = conclusions;
    }

    public FuzzyRuleDTO() {
    }

    public List<Long> getPremises() {
        return premises;
    }

    public void setPremises(List<Long> premises) {
        this.premises = premises;
    }

    public List<Long> getConclusions() {
        return conclusions;
    }

    public void setConclusions(List<Long> conclusions) {
        this.conclusions = conclusions;
    }
}
