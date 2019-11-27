package FuzzySystems.services;

import FuzzySystems.DTOs.ValueDTO;
import FuzzySystems.DTOs.ValueToCreateDTO;
import FuzzySystems.exceptions.NotFoundException;
import FuzzySystems.models.FuzzyNumbers.*;
import FuzzySystems.models.LinguisticValue;
import FuzzySystems.models.LinguisticVariable;
import FuzzySystems.repositories.FuzzyNumberRepository;
import FuzzySystems.repositories.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ValuesService {

    @Autowired
    private VariablesService variablesService;
    @Autowired
    private ValueRepository valueRepository;
    @Autowired
    private FuzzyNumberRepository fuzzyNumberRepository;

    public List<ValueDTO> getValues(long variableId) {
        List<LinguisticValue> values = valueRepository.findByVariableId(variableId);
        return values.stream().map(ValueDTO::fromEntity).collect(Collectors.toList());
    }

    public ValueDTO createValue(ValueToCreateDTO valueToCreate) throws NotFoundException {
        LinguisticVariable variable = variablesService.getEntityById(valueToCreate.getVariableId()).orElseThrow(NotFoundException::new);
        LinguisticValue value = new LinguisticValue(valueToCreate.getName(), variable);
        value = valueRepository.save(value);
        return ValueDTO.fromEntity(value);
    }

    public Optional<LinguisticValue> getValueDetails(long valueId) {
        return valueRepository.findById(valueId);
    }

    public FuzzyNumber getFuzzyNumber(long valueId) throws NotFoundException {
        return valueRepository.findById(valueId).orElseThrow(NotFoundException::new).getNumber();
    }

    private FuzzyNumber putNumber(LinguisticValue linguisticValue, FuzzyNumber fuzzyNumber) {
        if (linguisticValue.getNumber() != null) {
            fuzzyNumber.setId(linguisticValue.getNumber().getId());
        }

        fuzzyNumber = fuzzyNumberRepository.save(fuzzyNumber);
        return fuzzyNumber;
    }

    public FuzzyNumber putTriangularNumber(long valueId, float a, float b, float c) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        validateNumberType(value, "triangular");
        TriangularNumber fuzzyNumber = new TriangularNumber(value, a, b, c);
        return putNumber(value, fuzzyNumber);
    }

    public FuzzyNumber putTrapezoidalNumber(long valueId, float a, float b, float c, float d) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        value = validateNumberType(value, "trapezoidal");
        TrapezoidalNumber fuzzyNumber = new TrapezoidalNumber(value, a, b, c, d);
        return putNumber(value, fuzzyNumber);
    }


    public FuzzyNumber putGaussianNumber(long valueId, float mean, float stddev) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        value = validateNumberType(value, "gaussian");
        GaussianNumber fuzzyNumber = new GaussianNumber(value, mean, stddev);
        return putNumber(value, fuzzyNumber);
    }

    public FuzzyNumber putBellNumber(long valueId, double center, double width, double slope) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        value = validateNumberType(value, "bell");
        BellNumber fuzzyNumber = new BellNumber(value, center, width, slope);
        return putNumber(value, fuzzyNumber);
    }

    public FuzzyNumber putPiShapeNumber(long valueId, double bottomLeft, double topLeft, double topRight, double bottomRight) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        value = validateNumberType(value, "pi");
        PiShapeNumber fuzzyNumber = new PiShapeNumber(value, bottomLeft, topLeft, topRight, bottomRight);
        return putNumber(value, fuzzyNumber);
    }

    public FuzzyNumber putSpikeNumber(long valueId, double center, double width) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        value = validateNumberType(value, "spike");
        SpikeNumber fuzzyNumber = new SpikeNumber(value, center, width);
        return putNumber(value, fuzzyNumber);
    }

    public LinguisticValue validateNumberType(LinguisticValue value, String type) {
        FuzzyNumber fuzzyNumber = value.getNumber();
        if (fuzzyNumber != null && !fuzzyNumber.getType().equals(type)) {
            value.setNumber(null);
            valueRepository.save(value);
            fuzzyNumberRepository.delete(fuzzyNumber);
        }
        return value;
    }

    public LinguisticValue createValue(LinguisticVariable variable, String name, float a, float b, float c) {
        LinguisticValue linguisticValue = valueRepository.save(new LinguisticValue(name, variable));
        FuzzyNumber fuzzyNumber = fuzzyNumberRepository.save(new TriangularNumber(linguisticValue, a, b, c));
        linguisticValue.setNumber(fuzzyNumber);
        return linguisticValue;
    }

    public void deleteValue(long valueId) {
        valueRepository.deleteById(valueId);
    }

    public LinguisticValue updateValue(long valueId, String name) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        value.setName(name);
        return valueRepository.save(value);
    }
}
