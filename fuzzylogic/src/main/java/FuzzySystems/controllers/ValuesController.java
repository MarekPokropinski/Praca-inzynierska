package FuzzySystems.controllers;

import FuzzySystems.DTOs.ValueDTO;
import FuzzySystems.DTOs.ValueToCreateDTO;
import FuzzySystems.Exceptions.BadRequestException;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.FuzzySets.FuzzyNumbers.FuzzyNumber;
import FuzzySystems.FuzzySets.LinguisticValue;
import FuzzySystems.services.ValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        return valuesService.getValueDetails(valueId).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/{valueId}")
    public LinguisticValue updateValue(@PathVariable long valueId, @RequestBody String name)throws NotFoundException {
        return valuesService.updateValue(valueId, name);
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
            case "gaussian":
                return valuesService.putGaussianNumber(valueId, params.get(0), params.get(1));
            case "bell":
                return valuesService.putBellNumber(valueId, params.get(0), params.get(1), params.get(2));
            case "pi":
                return valuesService.putPiShapeNumber(valueId,params.get(0), params.get(1), params.get(2), params.get(3));
            case "spike":
                return valuesService.putSpikeNumber(valueId,params.get(0), params.get(1));
            default:
                throw new BadRequestException();
        }
    }
    @DeleteMapping("/{valueId}")
    public void deleteValue(@PathVariable long valueId) {
        valuesService.deleteValue(valueId);
    }
}
