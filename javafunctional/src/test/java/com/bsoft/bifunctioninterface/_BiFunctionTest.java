package com.bsoft.bifunctioninterface;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.util.function.BiFunction;

@Slf4j
public class _BiFunctionTest extends TestCase {

    private final BiFunction<Integer, Integer, Integer> incrementByOneAndMultiplyByFunction = (numberToIncrementByOne, numberToMultiplyBy) -> (numberToIncrementByOne + 1) * numberToMultiplyBy;

    private int incrementByOneAndMultiply(int number, int numToMultiply) {

        return (number + 1) * numToMultiply;
    }

    public void testIncrementByOneAndMultiply() {
        int number = 0;

        log.info("Before - number: {}", number);
        number = incrementByOneAndMultiply(number, 10);
        log.info("After  - number: {}", number);
        Assert.assertEquals(10, number);
    }

    public void testIncrementByOneAndMultiplyByFunction() {
        int number = 1;

        log.info("Before - number: {}", number);
        number = incrementByOneAndMultiplyByFunction.apply(number, 10);
        log.info("After  - number: {}", number);
        Assert.assertEquals(20, number);
    }

}
