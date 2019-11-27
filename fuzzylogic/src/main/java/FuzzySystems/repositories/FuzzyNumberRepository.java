package FuzzySystems.repositories;

import FuzzySystems.models.FuzzyNumbers.FuzzyNumber;
import org.springframework.data.repository.CrudRepository;

public interface FuzzyNumberRepository extends CrudRepository<FuzzyNumber, Long> {
}
