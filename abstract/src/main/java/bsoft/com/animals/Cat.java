package bsoft.com.animals;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Builder
@Getter
@Setter
@ToString
@Slf4j
public class Cat extends Animal {
    private String name;

    @Override
    public String makeSound() {
        String sound = "Miaoo";
        log.info("sound: {}", sound);
        return sound;
    }
}
