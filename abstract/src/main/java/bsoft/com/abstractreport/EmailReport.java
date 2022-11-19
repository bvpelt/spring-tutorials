package bsoft.com.abstractreport;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class EmailReport extends ExtractorReport {

    private static final Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public Pattern getPattern() {
        return pattern;
    }

    public String getReportName() {
        return "Emails";
    }

}
