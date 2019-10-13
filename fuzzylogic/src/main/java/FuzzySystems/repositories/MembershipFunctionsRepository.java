package FuzzySystems.repositories;

import FuzzySystems.FuzzySets.MembershipFunctions.MembershipFunction;
import org.springframework.data.repository.CrudRepository;

public interface MembershipFunctionsRepository extends CrudRepository<MembershipFunction, Long> {
}
