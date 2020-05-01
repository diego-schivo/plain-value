package it.plainvalue.spring.datajpa;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

	Iterable<Person> findByLastName(String name);
}