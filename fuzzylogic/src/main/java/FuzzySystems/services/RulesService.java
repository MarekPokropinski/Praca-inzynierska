package FuzzySystems.services;

import FuzzySystems.DTOs.FuzzyRuleDescriptionDTO;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.FuzzySets.LinguisticValue;
import FuzzySystems.FuzzySets.Rules.FuzzyRule;
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
    public ValuesService valuesService;

    @Autowired
    public FuzzyRulesRepository fuzzyRulesRepository;

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

    public FuzzyRuleDescriptionDTO createRule(List<Long> premises, List<Long> conclusions) throws NotFoundException {
        List<LinguisticValue> premisesValues = premises
                .stream()
                .map(id -> valuesService.getValueDetails(id).orElse(null))
                .collect(Collectors.toList());

        List<LinguisticValue> conclusionValues = conclusions
                .stream()
                .map(id -> valuesService.getValueDetails(id).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        FuzzyRule fuzzyRule = new FuzzyRule(premisesValues, conclusionValues);
        fuzzyRule = fuzzyRulesRepository.save(fuzzyRule);
        return buildDescription(fuzzyRule);
    }

    public List<FuzzyRuleDescriptionDTO> getRules() {
        return fuzzyRulesRepository.findAll()
                .stream()
                .map(this::buildDescription)
                .collect(Collectors.toList());
    }
}
