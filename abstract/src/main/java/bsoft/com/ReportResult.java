package bsoft.com;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReportResult {
    private String report;
    private int lines;
}