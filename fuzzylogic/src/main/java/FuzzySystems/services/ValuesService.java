package FuzzySystems.services;

import FuzzySystems.DTOs.ValueDTO;
import FuzzySystems.DTOs.ValueToCreateDTO;
import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.FuzzySets.FuzzyNumbers.*;
import FuzzySystems.FuzzySets.LinguisticValue;
import FuzzySystems.FuzzySets.LinguisticVariable;
import FuzzySystems.repositories.FuzzyNumberRepository;
import FuzzySystems.repositories.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ValuesService {

    @Autowired
    public VariablesService variablesService;
    @Autowired
    ValueRepository valueRepository;
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

    public TriangularNumber putTriangularNumber(long valueId, float a, float b, float c) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        TriangularNumber fuzzyNumber;

        validateNumberType(value, "triangular");


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

    public GaussianNumber putGaussianNumber(long valueId, float mean, float stddev) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        GaussianNumber fuzzyNumber;

        value = validateNumberType(value, "gaussian");

        fuzzyNumber = Objects.isNull(value.getNumber()) ? new GaussianNumber() : (GaussianNumber) value.getNumber();

        fuzzyNumber.setMean(mean);
        fuzzyNumber.setStddev(stddev);

        fuzzyNumber = fuzzyNumberRepository.save(fuzzyNumber);
        value.setNumber(fuzzyNumber);
        valueRepository.save(value);

        return fuzzyNumber;
    }

    public BellNumber putBellNumber(long valueId, double center, double width, double slope) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        BellNumber fuzzyNumber;

        value = validateNumberType(value, "bell");

        fuzzyNumber = Objects.isNull(value.getNumber()) ? new BellNumber() : (BellNumber) value.getNumber();

        fuzzyNumber.setCenter(center);
        fuzzyNumber.setSlope(slope);
        fuzzyNumber.setWidth(width);

        fuzzyNumber = fuzzyNumberRepository.save(fuzzyNumber);
        value.setNumber(fuzzyNumber);
        valueRepository.save(value);

        return fuzzyNumber;
    }

    public PiShapeNumber putPiShapeNumber(long valueId, double bottomLeft, double topLeft, double topRight, double bottomRight) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        PiShapeNumber fuzzyNumber;

        value = validateNumberType(value, "pi");

        fuzzyNumber = Objects.isNull(value.getNumber()) ? new PiShapeNumber() : (PiShapeNumber) value.getNumber();

        fuzzyNumber.setBottomLeft(bottomLeft);
        fuzzyNumber.setBottomRight(bottomRight);
        fuzzyNumber.setTopLeft(topLeft);
        fuzzyNumber.setTopRight(topRight);

        fuzzyNumber = fuzzyNumberRepository.save(fuzzyNumber);
        value.setNumber(fuzzyNumber);
        valueRepository.save(value);

        return fuzzyNumber;
    }

    public SpikeNumber putSpikeNumber(long valueId, double center, double width) throws NotFoundException {
        LinguisticValue value = valueRepository.findById(valueId).orElseThrow(NotFoundException::new);
        SpikeNumber fuzzyNumber;

        value = validateNumberType(value, "spike");

        fuzzyNumber = Objects.isNull(value.getNumber()) ? new SpikeNumber() : (SpikeNumber) value.getNumber();

        fuzzyNumber.setCenter(center);
        fuzzyNumber.setWidth(width);

        fuzzyNumber = fuzzyNumberRepository.save(fuzzyNumber);
        value.setNumber(fuzzyNumber);
        valueRepository.save(value);

        return fuzzyNumber;
    }

    public LinguisticValue validateNumberType(LinguisticValue value, String type) {
        FuzzyNumber fuzzyNumber = value.getNumber();
        if (fuzzyNumber != null && !fuzzyNumber.getType().equals(type)) {
            value.setNumber(null);
            value = valueRepository.save(value);
            fuzzyNumberRepository.delete(fuzzyNumber);
        }
        return value;
    }
}
