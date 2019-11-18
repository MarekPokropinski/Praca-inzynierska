package FuzzySystems.repositories;

import FuzzySystems.FuzzySets.FuzzyNumbers.FuzzyNumber;
import org.springframework.data.repository.CrudRepository;

public interface FuzzyNumberRepository extends CrudRepository<FuzzyNumber, Long> {
}
