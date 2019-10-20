package FuzzySystems.services;

import FuzzySystems.DTOs.ValueDTO;
import FuzzySystems.DTOs.ValueToCreateDTO;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.FuzzySets.FuzzyNumbers.FuzzyNumber;
import FuzzySystems.FuzzySets.FuzzyNumbers.TrapezoidalNumber;
import FuzzySystems.FuzzySets.FuzzyNumbers.TriangularNumber;
import FuzzySystems.FuzzySets.LinguisticValue;
import FuzzySystems.FuzzySets.LinguisticVariable;
import FuzzySystems.repositories.FuzzyNumberRepository;
import FuzzySystems.repositories.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ValuesService {

    @Autowired
    ValueRepository valueRepository;

    @Autowired
    public VariablesService variablesService;

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

    public LinguisticValue getValueDetails(long valueId) throws NotFoundException {
        return valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
    }

    public FuzzyNumber getFuzzyNumber(long valueId) throws NotFoundException {
        FuzzyNumber number = valueRepository.findById(valueId).orElseThrow(NotFoundException::new).getNumber();
        return valueRepository.findById(valueId).orElseThrow(NotFoundException::new).getNumber();
    }

    public TriangularNumber putTriangularNumber(long valueId, float a, float b, float c) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        TriangularNumber fuzzyNumber;

        validateNumberType(value,"triangular");


        fuzzyNumber = Objects.isNull(value.getNumber()) ? new TriangularNumber() : (TriangularNumber) value.getNumber();

        fuzzyNumber.setA(a);
        fuzzyNumber.setB(b);
        fuzzyNumber.setC(c);

        fuzzyNumber = fuzzyNumberRepository.save(fuzzyNumber);
        value.setNumber(fuzzyNumber);
        valueRepository.save(value);

        return fuzzyNumber;
    }

    public TrapezoidalNumber putTrapezoidalNumber(long valueId, float a, float b, float c, float d) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        TrapezoidalNumber fuzzyNumber;

        value = validateNumberType(value, "trapezoidal");

        fuzzyNumber = Objects.isNull(value.getNumber()) ? new TrapezoidalNumber() : (TrapezoidalNumber) value.getNumber();

        fuzzyNumber.setA(a);
        fuzzyNumber.setB(b);
        fuzzyNumber.setC(c);
        fuzzyNumber.setD(d);

        fuzzyNumber = fuzzyNumberRepository.save(fuzzyNumber);
        value.setNumber(fuzzyNumber);
        valueRepository.save(value);

        return fuzzyNumber;
    }

    public LinguisticValue validateNumberType(LinguisticValue value, String type) {
        FuzzyNumber fuzzyNumber = value.getNumber();
        if(fuzzyNumber!=null && !fuzzyNumber.getType().equals(type)){
            value.setNumber(null);
            value = valueRepository.save(value);
            fuzzyNumberRepository.delete(fuzzyNumber);
        }
        return value;
    }
}
