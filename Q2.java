import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.stream.Stream;
import java.util.concurrent.ForkJoinPool;

public class Q2 {

  public static double run(String filename, Type type) {
    if (type == Type.SEQUENCE) {
      return sequence(filename);
    } else if (type == Type.PARALLEL) {
      return parallel(filename);
    }
    return -1;
  }

  public static double sequence(String filename) {
    try {
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
      Stream<String> files = Files.lines(Paths.get(filename));
      files
        .skip(1)
        .map(CSVParser::parse)
        .map(
          line ->
            new Transaction(
              line[0].split("-"),
              line[1],
              line[2],
              line[3],
              line[4]
            )
        )
        .collect(groupingBy(Transaction::getMonthYear))
        .entrySet()
        .stream()
        .map(
          entry -> {
            Transaction firstTransaction = entry.getValue().get(0);
            BigDecimal initialBalance = firstTransaction.getEntryBalance();
            BigDecimal totalTransaction = entry
              .getValue()
              .stream()
              .map(d -> d.getTransactionAmount())
              .reduce(BigDecimal.ZERO, BigDecimal::add);
            double monthlyBalance = initialBalance
              .add(totalTransaction)
              .setScale(2, RoundingMode.HALF_UP)
              .doubleValue();
            return new MonthTransaction(entry.getKey(), monthlyBalance);
          }
        )
        .sorted(
          (t1, t2) -> {
            int result = t1.getMonthYear().compareTo(t2.getMonthYear());
            return result;
          }
        )
        .collect(toList())
        .forEach(System.out::println);
      Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());
      double time = (endTimestamp.getTime() - timestamp.getTime());
      System.out.println("Time taken: " + time + " ms");
      return time;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("File not found");
    }
    return -1;
  }

  public static double parallel(String filename) {
    try {
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
      Stream<String> files = Files.lines(Paths.get(filename));
      ForkJoinPool forkJoinPool = new ForkJoinPool(4);
      forkJoinPool.submit(()->files
        .parallel()
        .skip(1)
        .map(CSVParser::parse)
        .map(
          line ->
            new Transaction(
              line[0].split("-"),
              line[1],
              line[2],
              line[3],
              line[4]
            )
        )
        .collect(groupingBy(Transaction::getMonthYear))
        .entrySet()
        .parallelStream()
        .map(
          entry -> {
            Transaction firstTransaction = entry.getValue().get(0);
            BigDecimal initialBalance = firstTransaction.getEntryBalance();
            BigDecimal totalTransaction = entry
              .getValue()
              .parallelStream()
              .map(d -> d.getTransactionAmount())
              .reduce(BigDecimal.ZERO, BigDecimal::add);
            double monthlyBalance = initialBalance
              .add(totalTransaction)
              .setScale(2, RoundingMode.HALF_UP)
              .doubleValue();
            return new MonthTransaction(entry.getKey(), monthlyBalance);
          }
        )
        .sorted(
          (t1, t2) -> {
            int result = t1.getMonthYear().compareTo(t2.getMonthYear());
            return result;
          }
        )
        .collect(toList())
        ).get().forEach(System.out::println);
      Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());
      double time = (endTimestamp.getTime() - timestamp.getTime());
      System.out.println("Time taken: " + time + " ms");
      return time;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("File not found");
    }
    return -1;
  }
}
