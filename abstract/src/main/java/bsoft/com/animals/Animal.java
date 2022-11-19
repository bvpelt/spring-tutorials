package bsoft.com.animals;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
abstract public class Animal {
    private String name;

    public abstract String makeSound();
}
