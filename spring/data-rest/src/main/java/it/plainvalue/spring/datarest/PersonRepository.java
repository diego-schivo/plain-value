package it.plainvalue.spring.datarest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "people", collectionResourceRel = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	Iterable<Person> findByLastName(@Param("name") String name);
}