package it.plainvalue.spring.accessingdatajpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class CustomerRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CustomerRepository customers;

	@Test
	public void shouldReturnCustomersHavingLastNameBar() {
		Customer customer = new Customer("Foo", "Bar");
		entityManager.persist(customer);

		Iterable<Customer> findByLastName = customers.findByLastName(customer.getLastName());

		assertThat(findByLastName).extracting(Customer::getLastName).containsOnly("Bar");
	}
}