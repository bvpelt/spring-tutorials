package bsoft.com;

import bsoft.com.report.NumberExtractorReport;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class NumberExtractorReportTest extends TestCase {

    public void testNumberExtractor() {
        try {
            NumberExtractorReport numberExtractorReport = new NumberExtractorReport();
            ReportResult reportResult = numberExtractorReport.prepareAndSendReport("src/data.txt");

            Assert.assertEquals("Matching lines is incorrect, 4 expected " + reportResult.getLines() + " retrieved", 4, reportResult.getLines());
        } catch (Exception e) {
            log.error("Error: {}", e);
        }
    }
}
