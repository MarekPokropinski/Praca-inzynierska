package FuzzySystems.controllers;

import FuzzySystems.DTOs.TriangleNumberDTO;
import FuzzySystems.DTOs.ValueDTO;
import FuzzySystems.DTOs.ValueToCreateDTO;
import FuzzySystems.DTOs.VariableDTO;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.FuzzySets.FuzzyNumbers.FuzzyNumber;
import FuzzySystems.FuzzySets.FuzzyNumbers.TriangleNumber;
import FuzzySystems.FuzzySets.LinguisticValue;
import FuzzySystems.FuzzySets.LinguisticVariable;
import FuzzySystems.repositories.FuzzyNumberRepository;
import FuzzySystems.repositories.ValueRepository;
import FuzzySystems.repositories.VariableRepository;
import FuzzySystems.services.VariablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("values")
public class ValuesController {
    @Autowired
    public ValueRepository valueRepository;

    @Autowired
    public VariablesService variablesService;

    @Autowired
    private FuzzyNumberRepository fuzzyNumberRepository;

    @GetMapping("/")
    public List<ValueDTO> getValues(@RequestParam long variableId) {
        List<LinguisticValue> values = valueRepository.findByVariableId(variableId);
        return values.stream().map(ValueDTO::fromEntity).collect(Collectors.toList());
    }

    @PostMapping("/")
    public ValueDTO createValue(@RequestBody ValueToCreateDTO valueToCreate) throws NotFoundException{
        LinguisticVariable variable = variablesService.getEntityById(valueToCreate.getVariableId()).orElseThrow(NotFoundException::new);
        LinguisticValue value = new LinguisticValue(valueToCreate.getName(), variable);
        value = valueRepository.save(value);
        return ValueDTO.fromEntity(value);
    }

    @GetMapping("/{valueId}")
    public LinguisticValue getValueDetails(@PathVariable long valueId) throws NotFoundException{
        return valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/{valueId}/")
    public LinguisticValue putTriangleNumber(@PathVariable long valueId, @RequestBody TriangleNumberDTO triangleNumber) throws NotFoundException{
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);

        FuzzyNumber fuzzyNumber = new TriangleNumber(triangleNumber.getA(),triangleNumber.getB(),triangleNumber.getC());

        fuzzyNumber = fuzzyNumberRepository.save(fuzzyNumber);
        value.setNumber(fuzzyNumber);

        System.out.println(value.getNumber().getClass());

        return valueRepository.save(value);
    }
}
