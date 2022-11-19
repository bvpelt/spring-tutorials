package bsoft.com;

import bsoft.com.abstractreport.EmailReport;
import bsoft.com.abstractreport.NumberReport;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class AbstractNumberReportTest extends TestCase {

    public void testNumberReport() {

        try {
            NumberReport numberReport = new NumberReport();
            ReportResult reportResult = numberReport.prepareAndSendReport("src/data.txt");
            Assert.assertEquals("Matching lines is incorrect, 4 expected " + reportResult.getLines() + " retrieved", 4, reportResult.getLines());
        } catch (Exception e) {
            log.error("Error: {}", e);
        }


    }

    public void testEmailReport() {
        try {
            EmailReport emailReport = new EmailReport();
            ReportResult reportResult = emailReport.prepareAndSendReport("src/data.txt");
            Assert.assertEquals("Matching lines is incorrect, 3 expected " + reportResult.getLines() + " retrieved", 3, reportResult.getLines());
        } catch (Exception e) {
            log.error("Error: {}", e);
        }
    }
}
