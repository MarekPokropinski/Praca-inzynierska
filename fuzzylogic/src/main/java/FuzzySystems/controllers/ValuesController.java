package FuzzySystems.controllers;

import FuzzySystems.DTOs.VariableDTO;
import FuzzySystems.FuzzySets.LinguisticVariable;
import FuzzySystems.repositories.ValueRepository;
import FuzzySystems.repositories.VariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("values")
public class ValuesController {
    @Autowired
    public ValueRepository valueRepository;

//    @GetMapping("/")
//    public List<VariableDTO> getVariables() {
//        return valueRepository.findAll().stream().map(VariableDTO::fromEntity).collect(Collectors.toList());
//    }

//    @PostMapping("/")
//    public VariableDTO createValue(@RequestBody String valueName){
//        LinguisticVariable linguisticVariable = new LinguisticVariable(variableName);
//        linguisticVariable = variableRepository.save(linguisticVariable);
//        return VariableDTO.fromEntity(linguisticVariable);
//    }
}
