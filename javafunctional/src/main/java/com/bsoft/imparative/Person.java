package com.bsoft.imparative;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Person {
    private String name;
    private Gender gender;
}
