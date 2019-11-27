package FuzzySystems.repositories;

import FuzzySystems.models.LinguisticValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ValueRepository extends CrudRepository<LinguisticValue, Long> {
    @Query("select value from LinguisticValue value where value.linguisticVariable.id = ?1")
    List<LinguisticValue> findByVariableId(long id);
}