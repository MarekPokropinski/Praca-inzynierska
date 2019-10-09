package FuzzySystems.repositories;

import FuzzySystems.FuzzySets.LinguisticValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ValueRepository extends CrudRepository<LinguisticValue, Long> {

}