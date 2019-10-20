package FuzzySystems.controllers;

import FuzzySystems.DTOs.*;
import FuzzySystems.Exceptions.BadRequestException;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.FuzzySets.FuzzyNumbers.FuzzyNumber;
import FuzzySystems.FuzzySets.FuzzyNumbers.TriangularNumber;
import FuzzySystems.FuzzySets.LinguisticValue;
import FuzzySystems.FuzzySets.LinguisticVariable;
import FuzzySystems.repositories.FuzzyNumberRepository;
import FuzzySystems.repositories.ValueRepository;
import FuzzySystems.repositories.VariableRepository;
import FuzzySystems.services.ValuesService;
import FuzzySystems.services.VariablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("values")
public class ValuesController {
    @Autowired
    public ValuesService valuesService;


    @GetMapping("/")
    public List<ValueDTO> getValues(@RequestParam long variableId) {
        return valuesService.getValues(variableId);
    }

    @PostMapping("/")
    public ValueDTO createValue(@RequestBody ValueToCreateDTO valueToCreate) throws NotFoundException {
        return valuesService.createValue(valueToCreate);
    }

    @GetMapping("/{valueId}")
    public LinguisticValue getValueDetails(@PathVariable long valueId) throws NotFoundException {
        return valuesService.getValueDetails(valueId);
    }

    @GetMapping("/{valueId}/number")
    public FuzzyNumber getFuzzyNumber(@PathVariable long valueId) throws NotFoundException {
        return valuesService.getFuzzyNumber(valueId);
    }

    @PutMapping("/{valueId}/number")
    public FuzzyNumber createOrUpdateNumber(@PathVariable long valueId, @RequestParam String type, @RequestBody List<Float> params) throws NotFoundException, BadRequestException {
        switch (type) {
            case "triangular":
                return valuesService.putTriangularNumber(valueId, params.get(0), params.get(1), params.get(2));
            case "trapezoidal":
                return valuesService.putTrapezoidalNumber(valueId, params.get(0), params.get(1), params.get(2), params.get(3));
            default:
                throw new BadRequestException();
        }
    }
}
