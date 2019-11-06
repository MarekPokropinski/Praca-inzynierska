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
@RequestMapping("rules")
public class RulesController {
    @Autowired
    private RulesService rulesService;

    @GetMapping("/")
    public List<FuzzyRuleDescriptionDTO> getRules() {
        return rulesService.getRules();
    }

    @PostMapping("/")
    public void createRule(@RequestBody FuzzyRuleDTO fuzzyRuleDTO) throws NotFoundException {
        rulesService.createRule(fuzzyRuleDTO.getPremises(), fuzzyRuleDTO.getConclusions());
    }

    @GetMapping("/{ruleId}")
    public FuzzyRuleDetaisDTO getRuleDetails(@PathVariable long ruleId) throws NotFoundException {
        return rulesService.getRuleDetails(ruleId);
    }

    @PutMapping("/{ruleId}")
    public void updateRule(@PathVariable long ruleId, @RequestBody FuzzyRuleDTO fuzzyRuleDTO) throws NotFoundException {
        rulesService.updateRule(ruleId, fuzzyRuleDTO);
    }

}
