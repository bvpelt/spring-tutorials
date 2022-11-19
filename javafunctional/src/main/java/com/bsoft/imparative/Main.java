package com.bsoft.imparative;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Main {

    public void doImparative(List<Person> people) {
        // Imparative approch
        log.info("Imparative approch");
        List<Person> females = new ArrayList<>();

        for (Person person: people) {
            if (Gender.FEMALE.equals(person.getGender())) {
                females.add(person);
            }
        }

        for (Person female: females) {
            log.info("female: {}", female);
        }

    }

    public void doDeclarative(List<Person> people) {
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
        females2.forEach(person -> log.info("female: {}", person));
    }
    public void dotest() {
        List<Person> people = List.of(
                new Person("John", Gender.MALE),
                new Person("Maria", Gender.FEMALE),
                new Person("Aisha", Gender.FEMALE),
                new Person("Alex", Gender.MALE),
                new Person("Alice", Gender.FEMALE)
        );

        doImparative(people);

        doDeclarative(people);

    }

    public static void main(String[] args) {
        Main main = new Main();

        main.dotest();
    }

}
