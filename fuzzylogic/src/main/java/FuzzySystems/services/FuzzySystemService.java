package FuzzySystems.services;

import FuzzySystems.exceptions.NotFoundException;
import FuzzySystems.models.FuzzyRule;
import FuzzySystems.models.FuzzySystem;
import FuzzySystems.models.LinguisticValue;
import FuzzySystems.models.LinguisticVariable;
import FuzzySystems.repositories.FuzzySystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FuzzySystemService {
    private final static String defaultConjunction = "Minimum";
    private final static String defaultImplication = "Minimum";
    private final static String defaultAggregation = "Maximum";
    private final static String defaultDefuzzifier = "Centroid";

    @Autowired
    private FuzzySystemRepository fuzzySystemRepository;

    @Autowired
    private VariablesService variablesService;

    @Autowired
    private ValuesService valuesService;

    @Autowired
    private RulesService rulesService;

    public List<FuzzySystem> getSystems() {
        return fuzzySystemRepository.findAll();
    }

    public FuzzySystem createSystem(String name) {
        var system = new FuzzySystem(name, defaultConjunction, defaultImplication, defaultAggregation, defaultDefuzzifier);
        return fuzzySystemRepository.save(system);
    }

    public Optional<FuzzySystem> getSystem(long id) {
        return fuzzySystemRepository.findById(id);
    }

    public void deleteSystem(long id) {
        fuzzySystemRepository.deleteById(id);
    }

    public FuzzySystem updateSystem(long id, FuzzySystem fuzzySystem) throws NotFoundException {
        var system = fuzzySystemRepository.findById(id).orElseThrow(NotFoundException::new);
        String name = fuzzySystem.getName() == null ? system.getName() : fuzzySystem.getName();
        String conjunction = fuzzySystem.getConjunction() == null ? system.getConjunction() : fuzzySystem.getConjunction();
        String implication = fuzzySystem.getImplication() == null ? system.getImplication() : fuzzySystem.getImplication();
        String aggregation = fuzzySystem.getAggregation() == null ? system.getAggregation() : fuzzySystem.getAggregation();
        String defuzzifier = fuzzySystem.getDefuzzifier() == null ? system.getDefuzzifier() : fuzzySystem.getDefuzzifier();

        system = new FuzzySystem(name, conjunction, implication, aggregation, defuzzifier);
        system.setId(id);
        return fuzzySystemRepository.save(system);
    }

    private LinguisticValue valueWithLargestMembership(LinguisticVariable variable, double x) {
        String name = variable.getName();
        return variable
                .getValues()
                .stream()
                .reduce((value1, value2) -> value1.getNumber().getTerm(name).membership(x) > value2.getNumber().getTerm(name).membership(x) ? value1 : value2).orElse(null);
    }

    public FuzzySystem buildSystem(String name, List<String> variableNames, float[][] S, int f, int output) {
        var fuzzySystem = new FuzzySystem(name, defaultConjunction, defaultImplication, defaultAggregation, defaultDefuzzifier);
        fuzzySystem = fuzzySystemRepository.save(fuzzySystem);
        int current_depth = 0;
        int attributes_count = S[0].length;
        int examples_count = S.length;

        // Pokrycie przestrzeni cech zbiorami rozmytymi

        LinguisticVariable[] linguisticVariables = new LinguisticVariable[attributes_count];
        for (int i = 0; i < attributes_count; i++) {
            int attribute = i;
            linguisticVariables[attribute] = variablesService.createVariable(fuzzySystem, variableNames.get(attribute), attribute != output);

            Arrays.sort(S, (a, b) -> (int) Math.signum(a[attribute] - b[attribute]));
            float offset = examples_count / (f - 1);
            float x = offset - 1;
            float a = S[0][attribute];
            float b = S[(int) Math.floor(x)][attribute];
            float c;

            List<LinguisticValue> values = new ArrayList<>();
            values.add(valuesService.createValue(linguisticVariables[attribute], String.format("Value_%d", 1), a, a, b));

            for (int j = 1; j < f - 1; j++) {
                c = S[(int) Math.floor(x + offset)][attribute];
                values.add(valuesService.createValue(linguisticVariables[attribute], String.format("Value_%d", j+1), a, b, c));
                x += offset;
                a = b;
                b = c;
            }
            values.add(valuesService.createValue(linguisticVariables[attribute], String.format("Value_%d", f), a, b, b));
            linguisticVariables[attribute].setValues(values);
        }


        // Generowanie reguł

        // Przechowujemy reguły w słowniku aby uniknąć reguł o takim samym poprzedniku
        Hashtable<List<LinguisticValue>, Pair<FuzzyRule,Float>> rules = new Hashtable<>();

        for (int example = 0; example < examples_count; example++) {
            List<LinguisticValue> antecedent = new ArrayList<>();
            List<LinguisticValue> consequent = new ArrayList<>();

            // Dopasowanie reguły
            float wk = 1;

            for (int attribute = 0; attribute < attributes_count; attribute++) {
                float x = S[example][attribute];
                LinguisticVariable linguisticVariable = linguisticVariables[attribute];
                LinguisticValue value = valueWithLargestMembership(linguisticVariable, x);
                wk *= value.getNumber().getTerm(linguisticVariable.getName()).membership(x);
                if (attribute != output) {
                    antecedent.add(value);
                } else {
                    consequent.add(value);
                }
            }
            FuzzyRule rule = new FuzzyRule(fuzzySystem, antecedent, consequent);
            if(!rules.containsKey(antecedent)){
                rules.put(antecedent, Pair.of(rule,wk));
            } else {
                float previousFitness = rules.get(antecedent).getSecond();
                // Dodanie reguły o lepszym dopasowaniu
                if(wk>previousFitness) {
                    rules.put(antecedent, Pair.of(rule,wk));
                }
            }
        }
        for(var entry : rules.entrySet()){
            rulesService.createRule(entry.getValue().getFirst());
        }
        return fuzzySystem;
    }

}
