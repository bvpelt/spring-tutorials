package bsoft.com;

import bsoft.com.animals.Animal;
import bsoft.com.animals.Cat;
import bsoft.com.animals.Dog;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class CatsAndDogsTest extends TestCase {

    public void testCatsAndDogs() {
        Animal dog = Dog.builder()
                .name("Jip")
                .build();
        Animal cat = Cat.builder()
                .name("Quiten")
                .build();

        String sound = "";
        sound = dog.makeSound();
        Assert.assertEquals("retrieved sound {} expected Woff Woff", "Woff Woff", sound);

        sound = cat.makeSound();
        Assert.assertEquals("retrieved sound {} expected Miaoo", "Miaoo", sound);
    }
}
