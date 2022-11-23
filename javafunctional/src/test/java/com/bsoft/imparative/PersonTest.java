package com.bsoft.imparative;


import com.bsoft.AppTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class PersonTest extends TestCase {
    private final List<Person> people = List.of(
            new Person("John", Gender.MALE),
            new Person("Maria", Gender.FEMALE),
            new Person("Aisha", Gender.FEMALE),
            new Person("Alex", Gender.MALE),
            new Person("Alice", Gender.FEMALE)
    );

    public PersonTest(String testName) {
        super(testName);
    }

    public static Test suite() {

        return new TestSuite(PersonTest.class);
    }

    public void testImparative() {
        // Imparative approch
        log.info("Imparative approch");
        List<Person> females = new ArrayList<>();

        Assert.assertEquals("List not emptye", 0, females.size());
        for (Person person : people) {
            if (Gender.FEMALE.equals(person.getGender())) {
                females.add(person);
            }
        }
        Assert.assertEquals("List not emptye", 3, females.size());

        for (Person female : females) {
            log.info("female: {}", female);
        }
    }

    private void showPersonList(List<Person> persons) {
        persons.forEach(person -> log.info("person: {}", person));
    }

    public void testDeclarative() {
        // Declarative approach
        log.info("Declarative approch");
        people.stream()
                .filter(person -> Gender.FEMALE.equals(person.getGender()))
                //            .collect(Collectors.toList())                            // not really needed
                .forEach(person -> log.info("female: {}", person));

        log.info("Splitup collect and logmessage");
        List<Person> females2 = people.stream()
                .filter(person -> Gender.FEMALE.equals(person.getGender()))
                .collect(Collectors.toList());

        Assert.assertEquals("List not emptye", 3, females2.size());
        showPersonList(females2);
    }

    public void testDeclarativeFunctional() {
        // Declarative approach
        log.info("Declarative functional approch");
        people.stream()
                .filter(person -> Gender.FEMALE.equals(person.getGender()))
                //            .collect(Collectors.toList())                            // not really needed
                .forEach(person -> log.info("female: {}", person));

        log.info("Splitup collect and logmessage");
        Predicate<Person> personPredicate = person -> Gender.FEMALE.equals(person.getGender());

        List<Person> females2 = people.stream()
                .filter(personPredicate)
                .collect(Collectors.toList());
        Assert.assertEquals("List not emptye", 3, females2.size());
        showPersonList(females2);
    }
}
