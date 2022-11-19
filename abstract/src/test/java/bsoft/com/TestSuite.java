package bsoft.com;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        NumberExtractorReportTest.class,
        CatsAndDogsTest.class,
        AbstractNumberReportTest.class
})
public class TestSuite {

}
