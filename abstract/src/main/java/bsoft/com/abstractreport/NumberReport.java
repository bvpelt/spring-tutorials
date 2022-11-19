package bsoft.com.abstractreport;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class NumberReport extends ExtractorReport {
    private static final Pattern pattern = Pattern.compile("^[0-9]*$");

    public Pattern getPattern() {
        return pattern;
    }

    public String getReportName() {
        return "Phone Numbers";
    }

}
