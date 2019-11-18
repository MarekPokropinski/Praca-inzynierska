package FuzzySystems.services;

import FuzzySystems.DTOs.PlotDTO;
import FuzzySystems.DTOs.VariableDTO;
import FuzzySystems.DTOs.VariableDetailsDTO;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.FuzzySets.FuzzySystem;
import FuzzySystems.FuzzySets.LinguisticValue;
import FuzzySystems.FuzzySets.LinguisticVariable;
import FuzzySystems.repositories.VariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VariablesService {
    @Autowired
    public VariableRepository variableRepository;

    @Autowired
    public FuzzySystemService fuzzySystemService;

    public List<VariableDTO> getVariables(long systemId) {
        return variableRepository.findByFuzzySystemId(systemId).stream().map(VariableDTO::fromEntity).collect(Collectors.toList());
    }

    public List<LinguisticVariable> getVariablesEntities(long systemId) {
        return variableRepository.findByFuzzySystemId(systemId);
    }

    public VariableDTO createVariable(long fuzzySystemId, String variableName, boolean isInput) throws NotFoundException {
        FuzzySystem fuzzySystem = fuzzySystemService.getSystem(fuzzySystemId).orElseThrow(NotFoundException::new);
        LinguisticVariable linguisticVariable = new LinguisticVariable(fuzzySystem, variableName, isInput);
        linguisticVariable = variableRepository.save(linguisticVariable);
        return VariableDTO.fromEntity(linguisticVariable);
    }

    public VariableDetailsDTO getVariableDetails(long variableId) throws NotFoundException {
        LinguisticVariable linguisticVariable = variableRepository.findById(variableId).orElseThrow(NotFoundException::new);
        return VariableDetailsDTO.fromEntity(linguisticVariable);
    }

    public void updateVariable(long fuzzySystemId, long variableId, String variableName, boolean isInput) throws NotFoundException {
        variableRepository.findById(variableId).orElseThrow(NotFoundException::new);
        FuzzySystem fuzzySystem = fuzzySystemService.getSystem(fuzzySystemId).orElseThrow(NotFoundException::new);
        LinguisticVariable linguisticVariable = new LinguisticVariable(fuzzySystem, variableId, variableName, isInput);
        variableRepository.save(linguisticVariable);
    }

    public Optional<LinguisticVariable> getEntityById(long id) {
        return variableRepository.findById(id);
    }

    public List<PlotDTO> getPlot2(long variableId) throws NotFoundException {
        LinguisticVariable linguisticVariable = variableRepository.findById(variableId).orElseThrow(NotFoundException::new);
        return linguisticVariable
                .getValues()
                .stream()
                .filter(value -> Objects.nonNull(value.getNumber()))
                .map(value -> value.getNumber().getPlot(value.getName()))
                .collect(Collectors.toList());
    }

    private PlotDTO createPlot(LinguisticValue linguisticValue) {
        var range = linguisticValue.getNumber().getRange();
        var term = linguisticValue.getNumber().getTerm(linguisticValue.getName());

        double res = (range.getSecond()-range.getFirst())/128;
        List<Float> xs = new ArrayList<>();
        List<Float> ys = new ArrayList<>();

        for(double x = range.getFirst();x<=range.getSecond();x+=res) {
            xs.add((float)x);
            ys.add((float)term.membership(x));
        }
        double x = range.getSecond();
        xs.add((float)x);
        ys.add((float)term.membership(x));

        return new PlotDTO(xs,ys,linguisticValue.getName(),"linear");
    }

    public List<PlotDTO> getPlot(long variableId) throws NotFoundException {
        LinguisticVariable linguisticVariable = variableRepository.findById(variableId).orElseThrow(NotFoundException::new);
        return linguisticVariable
                .getValues()
                .stream()
                .filter(value -> Objects.nonNull(value.getNumber()))
                .map(this::createPlot)
                .collect(Collectors.toList());
    }

}
