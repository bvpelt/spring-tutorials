package bsoft.com;

import bsoft.com.abstractreport.EmailReport;
import bsoft.com.abstractreport.NumberReport;
import bsoft.com.animals.Animal;
import bsoft.com.animals.Cat;
import bsoft.com.animals.Dog;
import bsoft.com.report.NumberExtractorReport;
import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        App app = new App();

        app.catsAndDogs();
    }

    private void catsAndDogs() {
        Animal dog = Dog.builder()
                .name("Jip")
                .build();
        Animal cat = Cat.builder()
                .name("Quiten")
                .build();

        dog.makeSound();
        cat.makeSound();

        try {
            NumberExtractorReport numberExtractorReport = new NumberExtractorReport();
            numberExtractorReport.prepareAndSendReport("src/data.txt");
        } catch (Exception e) {
            log.error("Error: {}", e);
        }

        try {
            NumberReport numberReport = new NumberReport();
            numberReport.prepareAndSendReport("src/data.txt");
        } catch (Exception e) {
            log.error("Error: {}", e);
        }

        try {
            EmailReport emailReport = new EmailReport();
            emailReport.prepareAndSendReport("src/data.txt");
        } catch (Exception e) {
            log.error("Error: {}", e);
        }
    }
}
