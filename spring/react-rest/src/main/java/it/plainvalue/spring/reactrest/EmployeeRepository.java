package it.plainvalue.spring.reactrest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "employees", collectionResourceRel = "employees")
public interface EmployeeRepository extends CrudRepository<Employee, Long>
{
}