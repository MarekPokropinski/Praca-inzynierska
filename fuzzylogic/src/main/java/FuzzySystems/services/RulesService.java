package FuzzySystems.services;

import FuzzySystems.DTOs.FuzzyRuleDTO;
import FuzzySystems.DTOs.FuzzyRuleDescriptionDTO;
import FuzzySystems.DTOs.FuzzyRuleDetaisDTO;
import FuzzySystems.DTOs.PremiseDTO;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.FuzzySets.FuzzySystem;
import FuzzySystems.FuzzySets.LinguisticValue;
import FuzzySystems.FuzzySets.FuzzyRule;
import FuzzySystems.repositories.FuzzyRulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RulesService {

    @Autowired
    private ValuesService valuesService;

    @Autowired
    private FuzzySystemService fuzzySystemService;

    @Autowired
    private FuzzyRulesRepository fuzzyRulesRepository;

    private FuzzyRuleDescriptionDTO buildDescription(FuzzyRule fuzzyRule) {
        Function<List<LinguisticValue>, String> convertValuesToString =
                values -> values
                        .stream()
                        .map(linguisticValue -> String.format("%s=%s", linguisticValue.getLinguisticVariable().getName(), linguisticValue.getName()))
                        .reduce((acc, next) -> acc + " and " + next).orElse("");

        String premises = convertValuesToString.apply(fuzzyRule.getPremises());
        String conclusions = convertValuesToString.apply(fuzzyRule.getConclusions());

        String description = String.format("IF %s THEN %s", premises, conclusions);
        return new FuzzyRuleDescriptionDTO(fuzzyRule.getId(), description);
    }

    public FuzzyRuleDescriptionDTO createRule(long systemId,List<Long> premises, List<Long> conclusions) throws NotFoundException {
        List<LinguisticValue> premisesValues = premises
                .stream()
                .map(id -> valuesService.getValueDetails(id).orElse(null))
                .collect(Collectors.toList());

        List<LinguisticValue> conclusionValues = conclusions
                .stream()
                .map(id -> valuesService.getValueDetails(id).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        FuzzySystem fuzzySystem = fuzzySystemService.getSystem(systemId).orElseThrow(NotFoundException::new);
        FuzzyRule fuzzyRule = new FuzzyRule(fuzzySystem, premisesValues, conclusionValues);
        fuzzyRule = fuzzyRulesRepository.save(fuzzyRule);
        return buildDescription(fuzzyRule);
    }

    public FuzzyRuleDetaisDTO getRuleDetails(long id) throws NotFoundException {
        FuzzyRule fuzzyRule = fuzzyRulesRepository.findById(id).orElseThrow(NotFoundException::new);
        List<PremiseDTO> premises = fuzzyRule
                .getPremises()
                .stream()
                .map(linguisticValue -> new PremiseDTO(linguisticValue.getLinguisticVariable().getId(), linguisticValue.getLinguisticVariable().getName(), linguisticValue.getId(), linguisticValue.getName()))
                .collect(Collectors.toList());

        List<PremiseDTO> conclusions = fuzzyRule
                .getConclusions()
                .stream()
                .map(linguisticValue -> new PremiseDTO(linguisticValue.getLinguisticVariable().getId(), linguisticValue.getLinguisticVariable().getName(), linguisticValue.getId(), linguisticValue.getName()))
                .collect(Collectors.toList());

        return new FuzzyRuleDetaisDTO(fuzzyRule.getId(), premises, conclusions);
    }

    public List<FuzzyRule> getRulesEntities(long systemId) {
        return fuzzyRulesRepository.findByFuzzySystemId(systemId);
    }

    public void updateRule(long id, FuzzyRuleDTO fuzzyRuleDTO) throws NotFoundException {
        List<LinguisticValue> premisesValues = fuzzyRuleDTO.getPremises()
                .stream()
                .map(premiseId -> valuesService.getValueDetails(premiseId).orElse(null))
                .collect(Collectors.toList());

        List<LinguisticValue> conclusionValues = fuzzyRuleDTO.getConclusions()
                .stream()
                .map(conclusionId -> valuesService.getValueDetails(conclusionId).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        FuzzyRule fuzzyRule = fuzzyRulesRepository.findById(id).orElseThrow(NotFoundException::new);
        fuzzyRule.setPremises(premisesValues);
        fuzzyRule.setConclusions(conclusionValues);
        fuzzyRule = fuzzyRulesRepository.save(fuzzyRule);
    }

    public List<FuzzyRuleDescriptionDTO> getRules(long systemId) {
        return fuzzyRulesRepository.findByFuzzySystemId(systemId)
                .stream()
                .map(this::buildDescription)
                .collect(Collectors.toList());
    }

    public void createRule(FuzzyRule rule){
        fuzzyRulesRepository.save(rule);
    }

    public void deleteRule(long ruleId) {
        fuzzyRulesRepository.deleteById(ruleId);
    }
}
