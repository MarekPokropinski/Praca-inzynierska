package FuzzySystems.DTOs;

import java.util.List;

public class FuzzyRuleDetaisDTO {
    private long ruleId;
    private List<PremiseDTO> premises;
    private List<PremiseDTO> conclusions;


    public FuzzyRuleDetaisDTO(long ruleId, List<PremiseDTO> premises, List<PremiseDTO> conclusions) {
        this.premises = premises;
        this.conclusions = conclusions;
        this.ruleId = ruleId;
    }

    public List<PremiseDTO> getPremises() {
        return premises;
    }

    public List<PremiseDTO> getConclusions() {
        return conclusions;
    }

    public long getRuleId() {
        return ruleId;
    }
}
