package FuzzySystems.controllers;

import FuzzySystems.DTOs.VariableDTO;
import FuzzySystems.DTOs.VariableDetailsDTO;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.FuzzySets.LinguisticVariable;
import FuzzySystems.repositories.VariableRepository;
import org.graalvm.compiler.lir.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("variables")
public class VariablesController {
    @Autowired
    public VariableRepository variableRepository;

    @GetMapping("/")
    public List<VariableDTO> getVariables() {
        return variableRepository.findAll().stream().map(VariableDTO::fromEntity).collect(Collectors.toList());
    }

    @PostMapping("/")
    public VariableDTO createVariable(@RequestBody String variableName){
        LinguisticVariable linguisticVariable = new LinguisticVariable(variableName);
        linguisticVariable = variableRepository.save(linguisticVariable);
        return VariableDTO.fromEntity(linguisticVariable);
    }

    @GetMapping("/{variableId}")
    public VariableDetailsDTO getVariableDetails(@PathVariable long variableId) throws NotFoundException {
        LinguisticVariable linguisticVariable = variableRepository.findById(variableId).orElseThrow(NotFoundException::new);
        return VariableDetailsDTO.fromEntity(linguisticVariable);
    }
}
