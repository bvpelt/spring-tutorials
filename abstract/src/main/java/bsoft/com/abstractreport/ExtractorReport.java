package bsoft.com.abstractreport;

import bsoft.com.ReportResult;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
abstract public class ExtractorReport {

    public abstract Pattern getPattern();

    public abstract String getReportName();

    private ReportResult parse(String path) throws FileNotFoundException {

        String report = "";
        int lines = 0;
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        if (scanner.hasNext()) {              // skip header
            scanner.nextLine();
            lines++;
        } else {
            ReportResult reportResult = ReportResult.builder()
                    .report(report)
                    .lines(lines)
                    .build();
            return reportResult;
        }

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            Matcher matcher = getPattern().matcher(nextLine);

            boolean matches = matcher.matches();
            if (matches) {
                report += nextLine + "\n";
                lines++;
            }
        }
        ReportResult reportResult = ReportResult.builder()
                .report(report)
                .lines(lines)
                .build();
        return reportResult;
    }

    public ReportResult prepareAndSendReport(String path) throws FileNotFoundException {
        log.info("Starting report...{}", getReportName());
        ReportResult report = parse(path);
        log.info("Report with {} lines \n{}", report.getLines(), report.getReport());
        log.info("Send report: {}", getReportName());

        return report;
    }
}
