package FuzzySystems.services;

import FuzzySystems.Exceptions.NotFoundException;
import FuzzySystems.FuzzySets.FuzzySystem;
import FuzzySystems.repositories.FuzzySystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuzzySystemService {
    private final static String defaultConjunction = "Minimum";
    private final static String defaultImplication = "Minimum";
    private final static String defaultAggregation = "Maximum";
    private final static String defaultDefuzzifier = "Centroid";

    @Autowired
    FuzzySystemRepository fuzzySystemRepository;

    public List<FuzzySystem> getSystems() {
        return fuzzySystemRepository.findAll();
    }

    public void createSystem(String name) {
        var system = new FuzzySystem(name, defaultConjunction, defaultImplication, defaultAggregation, defaultDefuzzifier);
        fuzzySystemRepository.save(system);
    }

    public Optional<FuzzySystem> getSystem(long id) {
        return fuzzySystemRepository.findById(id);
    }

    public FuzzySystem updateSystem(long id, FuzzySystem fuzzySystem) throws NotFoundException {
        var system = fuzzySystemRepository.findById(id).orElseThrow(NotFoundException::new);
        String name = fuzzySystem.getName() == null ? system.getName() : fuzzySystem.getName();
        String conjunction = fuzzySystem.getConjunction() == null ? system.getConjunction() : fuzzySystem.getConjunction();
        String implication = fuzzySystem.getImplication() == null ? system.getImplication() : fuzzySystem.getImplication();
        String aggregation = fuzzySystem.getAggregation() == null ? system.getAggregation() : fuzzySystem.getAggregation();
        String defuzzifier = fuzzySystem.getDefuzzifier() == null ? system.getDefuzzifier() : fuzzySystem.getDefuzzifier();

        system = new FuzzySystem(name, conjunction, implication, aggregation, defuzzifier);
        system.setId(id);
        return fuzzySystemRepository.save(system);
    }
}
