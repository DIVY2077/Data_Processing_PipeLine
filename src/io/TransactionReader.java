package io;

import model.Transaction;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionReader {

    public List<Transaction> readTransactions(String filePath) throws IOException {
        try (var lines = Files.lines(Path.of(filePath))) {
            return lines
                    .skip(1)
                    .filter(line -> !line.isBlank())
                    .map(this::parseLine)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }

    private Optional<Transaction> parseLine(String line) {
        try {
            String[] fields = line.split(",");

            int id = Integer.parseInt(fields[0].trim());
            LocalDate date = LocalDate.parse(fields[1].trim());
            String customer = fields[2].trim();
            String category = fields[3].trim();
            double amount = Double.parseDouble(fields[4].trim());
            String status = fields[5].trim();

            return Optional.of(new Transaction(id, date, customer, category, amount, status));

        } catch (Exception e) {
            System.err.println("Skipping invalid line: \"" + line + "\" — reason: " + e.getMessage());
            return Optional.empty();
        }
    }
}