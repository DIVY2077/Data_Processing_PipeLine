# Transaction Data Processing Pipeline

A Java-based data processing pipeline that reads transaction records from a CSV file, 
processes them using functional programming techniques (Stream API, lambdas, method 
references), and generates a formatted summary report.

## Overview

This project simulates a real-world backend data pipeline: ingesting raw transactional 
data, cleaning and validating it, computing business metrics, and producing a readable 
report — all using Java's functional programming features instead of traditional 
imperative loops.

## Features

- CSV file ingestion using Java NIO (`Files.lines`)
- Fault-tolerant parsing — invalid rows are skipped and logged instead of crashing the pipeline
- Functional-style data processing using Stream API:
  - Filtering (successful vs failed transactions)
  - Mapping and aggregation (revenue by category, transaction count by customer)
  - Reduction (total revenue, average transaction amount, highest transaction)
- Grouping and collecting with `Collectors.groupingBy`
- Summary report generation and export to a text file

## Tech / Concepts Used

- Java Records (immutable data modeling)
- Stream API (`filter`, `map`, `reduce`, `collect`)
- Lambda expressions & method references
- Collections Framework (`List`, `Map`)
- `Optional` for safe null handling
- Custom exception handling
- File I/O (NIO — `Files.lines`, `Files.writeString`)

## Project Structure

```
├── model/       → Transaction (record)
├── exception/   → Custom exception classes
├── io/          → CSV reader & report writer
├── processor/   → Core Stream-based business logic
├── report/      → Summary report model & formatting
└── Main.java    → Pipeline orchestration
```

## Sample Output

```
========== TRANSACTION SUMMARY REPORT ==========

Total Revenue (SUCCESS only): ₹XXXXX.XX
Average Transaction Amount:   ₹XXX.XX
Failed Transactions:          X

--- Revenue By Category ---
Electronics     ₹XXXX.XX
Groceries       ₹XXX.XX
...

--- Transaction Count By Customer ---
Rahul           X
Priya           X
...

--- Highest Transaction ---
ID: X | Customer: XXX | Category: XXX | Amount: ₹XXXX.XX
==================================================
```

## How to Run

1. Clone the repository
2. Place your transaction CSV in `resources/transactions.csv` (columns: `id,date,customer,category,amount,status`)
3. Run `Main.java`
4. Check console output and the generated `summary_report.txt`

## Author

Built as part of a structured Java learning path focused on backend development fundamentals — OOP, Collections, Exception Handling, File I/O, and Modern Java (Lambdas & Stream API).
