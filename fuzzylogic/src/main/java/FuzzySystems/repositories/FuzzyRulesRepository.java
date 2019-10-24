package FuzzySystems.repositories;

import FuzzySystems.FuzzySets.Rules.FuzzyRule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FuzzyRulesRepository extends CrudRepository<FuzzyRule, Long> {
    List<FuzzyRule> findAll();
}
