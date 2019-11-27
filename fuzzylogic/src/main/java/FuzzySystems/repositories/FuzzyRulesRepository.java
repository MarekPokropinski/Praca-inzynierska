package FuzzySystems.repositories;

import FuzzySystems.models.FuzzyRule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FuzzyRulesRepository extends CrudRepository<FuzzyRule, Long> {
    List<FuzzyRule> findAll();
    List<FuzzyRule> findByFuzzySystemId(long id);
}
