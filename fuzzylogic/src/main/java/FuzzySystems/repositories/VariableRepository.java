package FuzzySystems.repositories;

import FuzzySystems.FuzzySets.LinguisticVariable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariableRepository extends CrudRepository<LinguisticVariable, Long> {
    List<LinguisticVariable> findAll();
    List<LinguisticVariable> findByFuzzySystemId(long id);
}
