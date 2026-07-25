package model;
import  java.time.LocalDate;
public record  Transaction (
        int id,
        LocalDate date,
        String customer,
        String category,
        double amount,
        String status
) {
}