package FuzzySystems.services;

import FuzzySystems.DTOs.EngineOutputDTO;
import FuzzySystems.DTOs.EngineVariableDTO;
import FuzzySystems.DTOs.PlotDTO;
import FuzzySystems.exceptions.NotFoundException;
import FuzzySystems.models.FuzzyNumbers.FuzzyNumber;
import FuzzySystems.models.FuzzyRule;
import FuzzySystems.models.FuzzySystem;
import FuzzySystems.models.LinguisticValue;
import FuzzySystems.models.LinguisticVariable;
import com.fuzzylite.Engine;
import com.fuzzylite.rule.Rule;
import com.fuzzylite.rule.RuleBlock;
import com.fuzzylite.term.Constant;
import com.fuzzylite.variable.InputVariable;
import com.fuzzylite.variable.OutputVariable;
import com.fuzzylite.variable.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FuzzyEngineService {
    private RulesService rulesService;
    private VariablesService variablesService;
    private FuzzySystemService fuzzySystemService;

    @Autowired
    public FuzzyEngineService(VariablesService variablesService, RulesService rulesService, FuzzySystemService fuzzySystemService) {
        this.rulesService = rulesService;
        this.variablesService = variablesService;
        this.fuzzySystemService = fuzzySystemService;
    }

    public Engine buildEngine(long systemId) throws NotFoundException {
        FuzzySystem fuzzySystem = fuzzySystemService.getSystem(systemId).orElseThrow(NotFoundException::new);
        Engine engine = engine = new Engine(fuzzySystem.getName());
        List<LinguisticVariable> variables = variablesService.getVariablesEntities(systemId);
        try {
            Variable variable;
            for (LinguisticVariable linguisticVariable : variables) {
                if (linguisticVariable.isInput()) {
                    variable = new InputVariable(linguisticVariable.getName());
                } else {
                    variable = new OutputVariable(linguisticVariable.getName());
                }

                variable.setEnabled(true);
                for (LinguisticValue value : linguisticVariable.getValues()) {
                    FuzzyNumber number = value.getNumber();
                    if (number != null) {
                        variable.addTerm(number.getTerm(value.getName()));
                    } else {
                        variable.addTerm(new Constant(value.getName(), 0));
                    }
                }
                var range = linguisticVariable.getRange();
                variable.setRange(range.getFirst(), range.getSecond());


                if (linguisticVariable.isInput()) {
                    engine.addInputVariable((InputVariable) variable);
                } else {
                    OutputVariable outputVariable = (OutputVariable) variable;
//                    outputVariable.setAggregation(new Maximum());
//                    outputVariable.setDefuzzifier(new Centroid());
                    engine.addOutputVariable(outputVariable);
                }

            }
            RuleBlock ruleBlock = new RuleBlock();
            ruleBlock.setName("mamdani");
            List<String> rulesText = new ArrayList<>();
            for (FuzzyRule fuzzyRule : rulesService.getRulesEntities(systemId)) {
                Function<LinguisticValue, CharSequence> premiseToSequence = premise -> new StringBuffer(String.format("%s is %s", premise.getLinguisticVariable().getName(), premise.getName()));

                Stream<CharSequence> s = fuzzyRule.getPremises()
                        .stream()
                        .map(premiseToSequence);

                String premisesText = String.join(" and ", s::iterator);

                s = fuzzyRule.getConclusions().stream()
                        .map(premiseToSequence);

                String conclusionsText = String.join(" and ", s::iterator);
                rulesText.add("if " + premisesText + " then " + conclusionsText);
                ruleBlock.addRule(Rule.parse("if " + premisesText + " then " + conclusionsText, engine));
            }

            engine.addRuleBlock(ruleBlock);
            engine.configure(fuzzySystem.getConjunction(), "", fuzzySystem.getImplication(), fuzzySystem.getAggregation(), fuzzySystem.getDefuzzifier(), "General");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Hello! Finished building engine!");
        }

        StringBuilder status = new StringBuilder();
        if (!engine.isReady(status)) {
            System.out.println("[engine error] engine is not ready:n" + status);
            return null;
        }
        return engine;
    }

    public PlotDTO getFuzzyOutputPlot(OutputVariable outputVariable, String name) {
        var fuzzyOutput = outputVariable.fuzzyOutput();
        long steps = 128;
        double res = fuzzyOutput.range() / steps;
        List<Float> xs = new ArrayList<>();
        List<Float> ys = new ArrayList<>();
        double x = fuzzyOutput.getMinimum();
        for (int i = 0; i < steps; i++, x += res) {//double x = fuzzyOutput.getMinimum();x<=fuzzyOutput.getMaximum();x+=res) {
            xs.add((float) x);
            ys.add((float) fuzzyOutput.membership(x));
        }
        x = fuzzyOutput.getMaximum();
        xs.add((float) x);
        ys.add((float) fuzzyOutput.membership(x));

        return new PlotDTO(xs, ys, name, "linear");
    }

    public EngineOutputDTO process(long systemId, List<EngineVariableDTO> inputs) throws NotFoundException {
        Engine engine = buildEngine(systemId);
        for (EngineVariableDTO input : inputs) {
            engine.setInputValue(input.getName(), input.getValue());
        }
        engine.process();

        EngineOutputDTO engineOutputDTO = new EngineOutputDTO();

        List<LinguisticVariable> variables = variablesService.getVariablesEntities(systemId);
        for (var linguisticVariable : variables) {
            if (linguisticVariable.isInput()) {
                Double value = inputs.stream()
                        .filter(input -> input.getName().equals(linguisticVariable.getName()))
                        .map(input -> input.getValue())
                        .findAny()
                        .orElse(null);
                if (value == null) {
                    continue;
                }
                var inputVariable = engine.getInputVariable(linguisticVariable.getName());
                engineOutputDTO.addInputVariable(
                        linguisticVariable.getName(),
                        variablesService.getPlot(linguisticVariable.getId()),
                        inputVariable.fuzzyInputValue(),
                        inputVariable.getTerms().stream().map(term -> term.membership(value)).collect(Collectors.toList()),
                        linguisticVariable.getRange(),
                        value
                );
            } else {
                OutputVariable outputVariable = engine.getOutputVariable(linguisticVariable.getName());
                engineOutputDTO.addOutputVariable(
                        linguisticVariable.getName(),
                        variablesService.getPlot(linguisticVariable.getId()),
                        outputVariable.fuzzyOutputValue(),
                        linguisticVariable.getRange(),
                        outputVariable.getValue(),
                        getFuzzyOutputPlot(outputVariable, "")
                );
            }
        }

        for (var rule : engine.getRuleBlock(0).getRules()) {
            engineOutputDTO.addRule(rule.getText(), rule.getActivationDegree());
        }

        return engineOutputDTO;
    }

}
