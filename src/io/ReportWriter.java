package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReportWriter {

    public void writeReport(String outputPath, String content) throws IOException {
        Files.writeString(Path.of(outputPath), content);
        System.out.println("Report successfully written to: " + outputPath);
    }
}