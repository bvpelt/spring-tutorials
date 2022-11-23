package com.bsoft.functionalinterface;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.util.function.Function;

@Slf4j
public class _FunctionTest extends TestCase {

    private final Function<Integer, Integer> incrementByOneFunction = number -> number + 1;
    private final Function<Integer, Integer> multiplyByTenFunction = number -> number * 10;

    private final Function<Integer, Integer> addByOneAndThenMultiplyByTen = incrementByOneFunction.andThen(multiplyByTenFunction);

    private int increment(int number) {
        return number + 1;
    }

    public void testNormalIncrement() {
        int number = 0;

        log.info("Before - number: {}", number);
        number = increment(number);
        log.info("After  - number: {}", number);
        Assert.assertEquals(1, number);
    }

    public void testIncFunction() {
        int number = 1;

        log.info("Before - number: {}", number);
        number = incrementByOneFunction.apply(number);
        log.info("After  - number: {}", number);
        Assert.assertEquals(2, number);
    }

    public void testIncMultFunction() {
        int number = 2;

        log.info("Before - number: {}", number);
        number = incrementByOneFunction.apply(number);
        log.info("After  - number: {}", number);
        Assert.assertEquals(3, number);

        log.info("Before - number: {}", number);
        number = multiplyByTenFunction.apply(number);
        log.info("After  - number: {}", number);
        Assert.assertEquals(30, number);
    }

    public void testIncAndMultFunction() {
        int number = 4;

        log.info("Before - number: {}", number);
        number = addByOneAndThenMultiplyByTen.apply(number);
        log.info("After  - number: {}", number);
        Assert.assertEquals(50, number);
    }

}
