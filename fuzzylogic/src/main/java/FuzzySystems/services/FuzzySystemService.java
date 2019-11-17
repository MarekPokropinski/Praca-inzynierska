package FuzzySystems.services;

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
}
