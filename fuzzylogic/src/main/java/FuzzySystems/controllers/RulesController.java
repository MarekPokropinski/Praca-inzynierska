package FuzzySystems.controllers;

import FuzzySystems.DTOs.FuzzyRuleDTO;
import FuzzySystems.DTOs.FuzzyRuleDescriptionDTO;
import FuzzySystems.DTOs.FuzzyRuleDetaisDTO;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.services.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RulesController {
    @Autowired
    private RulesService rulesService;

    @GetMapping("systems/{systemId}/rules")
    public List<FuzzyRuleDescriptionDTO> getRules(@PathVariable long systemId) {
        return rulesService.getRules(systemId);
    }

    @PostMapping("systems/{systemId}/rules")
    public void createRule(@PathVariable long systemId, @RequestBody FuzzyRuleDTO fuzzyRuleDTO) throws NotFoundException {
        rulesService.createRule(systemId, fuzzyRuleDTO.getPremises(), fuzzyRuleDTO.getConclusions());
    }

    @GetMapping("rules/{ruleId}")
    public FuzzyRuleDetaisDTO getRuleDetails(@PathVariable long ruleId) throws NotFoundException {
        return rulesService.getRuleDetails(ruleId);
    }

    @PutMapping("rules/{ruleId}")
    public void updateRule(@PathVariable long ruleId, @RequestBody FuzzyRuleDTO fuzzyRuleDTO) throws NotFoundException {
        rulesService.updateRule(ruleId, fuzzyRuleDTO);
    }

}
