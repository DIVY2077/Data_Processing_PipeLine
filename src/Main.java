import model.Transaction;
import io.TransactionReader;
import io.ReportWriter;
import processor.TransactionProcessor;
import report.SummaryReport;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        String inputPath = "C:\\Users\\Divya Sharma\\IdeaProjects\\Data_Processing_PipeLine\\resources\\transactions.csv";
        String outputPath = "C:\\Users\\Divya Sharma\\IdeaProjects\\Data_Processing_PipeLine\\summary_report.txt";

        TransactionReader reader = new TransactionReader();
        TransactionProcessor processor = new TransactionProcessor();
        ReportWriter writer = new ReportWriter();

        try {
            List<Transaction> transactions = reader.readTransactions(inputPath);
            System.out.println("Total transactions loaded: " + transactions.size());

            double totalRevenue = processor.totalRevenue(transactions);
            Map<String, Double> revenueByCategory = processor.revenueByCategory(transactions);
            Map<String, Long> countByCustomer = processor.transactionCountByCustomer(transactions);
            Optional<Transaction> highest = processor.highestTransaction(transactions);
            double avgAmount = processor.averageTransactionAmount(transactions);
            long failedCount = processor.failedTransactionCount(transactions);

            SummaryReport report = new SummaryReport(
                    totalRevenue,
                    revenueByCategory,
                    countByCustomer,
                    highest.orElse(null),
                    avgAmount,
                    failedCount
            );

            String formattedReport = report.toFormattedString();

            System.out.println(formattedReport);

            writer.writeReport(outputPath, formattedReport);

        } catch (IOException e) {
            System.err.println("Error reading/writing file: " + e.getMessage());
            e.printStackTrace();   // ye line add karo — poora stack trace dikhega
        }
    }
}