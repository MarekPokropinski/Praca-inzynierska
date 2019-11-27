package FuzzySystems.repositories;

import FuzzySystems.models.FuzzySystem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FuzzySystemRepository extends CrudRepository<FuzzySystem, Long> {
    List<FuzzySystem> findAll();
}
