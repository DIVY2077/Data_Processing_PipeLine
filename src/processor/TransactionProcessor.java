package processor;
import model.Transaction;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionProcessor {
    public List<Transaction> filterByStatus(List<Transaction> txns, String status) {
        return txns.stream()
                .filter(t -> t.status().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
    public double totalRevenue(List<Transaction> txns) {
        return txns.stream()
                .filter(t -> t.status().equalsIgnoreCase("SUCCESS"))
                .mapToDouble(Transaction::amount)
                .sum();
    }
    public Map<String, Double> revenueByCategory(List<Transaction> txns) {
        return txns.stream()
                .filter(t -> t.status().equalsIgnoreCase("SUCCESS"))
                .collect(Collectors.groupingBy(
                        Transaction::category,
                        Collectors.summingDouble(Transaction::amount)
                ));
    }

    public Map<String, Long> transactionCountByCustomer(List<Transaction> txns) {
        return txns.stream()
                .collect(Collectors.groupingBy(
                        Transaction::customer,
                        Collectors.counting()
                ));
    }

    public Optional<Transaction> highestTransaction(List<Transaction> txns) {
        return txns.stream()
                .max(Comparator.comparingDouble(Transaction::amount));
    }

    public double averageTransactionAmount(List<Transaction> txns) {
        return txns.stream()
                .filter(t -> t.status().equalsIgnoreCase("SUCCESS"))
                .mapToDouble(Transaction::amount)
                .average()
                .orElse(0.0);
    }

    public long failedTransactionCount(List<Transaction> txns) {
        return txns.stream()
                .filter(t -> t.status().equalsIgnoreCase("FAILED"))
                .count();
    }
}
