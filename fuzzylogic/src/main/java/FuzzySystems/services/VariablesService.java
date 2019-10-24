package FuzzySystems.services;

import FuzzySystems.DTOs.PlotDTO;
import FuzzySystems.DTOs.VariableDTO;
import FuzzySystems.DTOs.VariableDetailsDTO;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.FuzzySets.LinguisticVariable;
import FuzzySystems.repositories.VariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VariablesService {
    @Autowired
    public VariableRepository variableRepository;

    public List<VariableDTO> getVariables() {
        return variableRepository.findAll().stream().map(VariableDTO::fromEntity).collect(Collectors.toList());
    }

    public VariableDTO createVariable(String variableName) {
        LinguisticVariable linguisticVariable = new LinguisticVariable(variableName);
        linguisticVariable = variableRepository.save(linguisticVariable);
        return VariableDTO.fromEntity(linguisticVariable);
    }

    public VariableDetailsDTO getVariableDetails(long variableId) throws NotFoundException {
        LinguisticVariable linguisticVariable = variableRepository.findById(variableId).orElseThrow(NotFoundException::new);
        return VariableDetailsDTO.fromEntity(linguisticVariable);
    }

    public Optional<LinguisticVariable> getEntityById(long id) {
        return variableRepository.findById(id);
    }

    public List<PlotDTO> getPlot(long variableId) throws NotFoundException {
        LinguisticVariable linguisticVariable = variableRepository.findById(variableId).orElseThrow(NotFoundException::new);
        return linguisticVariable
                .getValues()
                .stream()
                .filter(value -> Objects.nonNull(value.getNumber()))
                .map(value -> value.getNumber().getPlot(value.getName()))
                .collect(Collectors.toList());
    }


}
