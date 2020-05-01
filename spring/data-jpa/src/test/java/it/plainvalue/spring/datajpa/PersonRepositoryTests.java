package it.plainvalue.spring.datajpa;

import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.substringBefore;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class PersonRepositoryTests
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository customers;

    @Test
    public void shouldReturnPersonsHavingLastNameBaggins()
    {
        persistTheLordOfTheRingsCharacters();

        Iterable<Person> findByLastName = customers.findByLastName("Baggins");
        assertThat(findByLastName).extracting(Person::getLastName).containsOnly("Baggins");
    }

    private void persistTheLordOfTheRingsCharacters()
    {
        Arrays.stream(new String[]
        { "Bilbo Baggins", "Frodo Baggins", "Tom Bombadil", "Merry Brandybuck", "Samwise Gamgee", "Gríma Wormtongue",
                "Pippin Took" }).map(fullName ->
                {
                    Person person = new Person();
                    person.setFirstName(substringBefore(fullName, SPACE));
                    person.setLastName(substringAfter(fullName, SPACE));
                    return person;
                }).forEach(person -> entityManager.persist(person));
    }
}