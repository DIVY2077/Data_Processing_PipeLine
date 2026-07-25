package report;

import model.Transaction;

import java.util.Map;

public record SummaryReport(
        double totalRevenue,
        Map<String, Double> revenueByCategory,
        Map<String, Long> countByCustomer,
        Transaction topTransaction,
        double averageAmount,
        long failedCount
) {

    public String toFormattedString() {
        StringBuilder sb = new StringBuilder();

        sb.append("========== TRANSACTION SUMMARY REPORT ==========\n\n");

        sb.append(String.format("Total Revenue (SUCCESS only): ₹%.2f%n", totalRevenue));
        sb.append(String.format("Average Transaction Amount:   ₹%.2f%n", averageAmount));
        sb.append(String.format("Failed Transactions:          %d%n", failedCount));

        sb.append("\n--- Revenue By Category ---\n");
        revenueByCategory.forEach((category, revenue) ->
                sb.append(String.format("%-15s ₹%.2f%n", category, revenue))
        );

        sb.append("\n--- Transaction Count By Customer ---\n");
        countByCustomer.forEach((customer, count) ->
                sb.append(String.format("%-15s %d%n", customer, count))
        );

        sb.append("\n--- Highest Transaction ---\n");
        if (topTransaction != null) {
            sb.append(String.format("ID: %d | Customer: %s | Category: %s | Amount: ₹%.2f%n",
                    topTransaction.id(), topTransaction.customer(),
                    topTransaction.category(), topTransaction.amount()));
        }

        sb.append("\n==================================================\n");

        return sb.toString();
    }
}