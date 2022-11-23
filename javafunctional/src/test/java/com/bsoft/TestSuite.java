package com.bsoft;

import com.bsoft.bifunctioninterface._BiFunctionTest;
import com.bsoft.functionalinterface._FunctionTest;
import com.bsoft.imparative.PersonTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        PersonTest.class,
        _FunctionTest.class,
        _BiFunctionTest.class
})
public class TestSuite {
}
