import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;
import java.util.stream.Stream;

class Helper {

  public static int monthToInt(String month) {
    switch (month) {
      case "Jan":
        return 0;
      case "Feb":
        return 1;
      case "Mar":
        return 2;
      case "Apr":
        return 3;
      case "May":
        return 4;
      case "Jun":
        return 5;
      case "Jul":
        return 6;
      case "Aug":
        return 7;
      case "Sep":
        return 8;
      case "Oct":
        return 9;
      case "Nov":
        return 10;
      case "Dec":
        return 11;
      default:
        return -1;
    }
  }
}

class MonthTransaction {

  private Calendar monthYear;
  private double amount;

  public MonthTransaction(String monthYear, double amount) {
    this.monthYear = Calendar.getInstance();
    this.monthYear.set(
        Calendar.MONTH,
        Integer.parseInt(monthYear.split("/")[0])
      );
    this.monthYear.set(
        Calendar.YEAR,
        Integer.parseInt(monthYear.split("/")[1])
      );
    this.monthYear.set(Calendar.DAY_OF_MONTH, 1);
    this.monthYear.set(Calendar.HOUR_OF_DAY, 0);
    this.monthYear.set(Calendar.MINUTE, 0);
    this.monthYear.set(Calendar.SECOND, 0);
    this.monthYear.set(Calendar.MILLISECOND, 0);
    this.amount = amount;
  }

  public Calendar getMonthYear() {
    return monthYear;
  }

  public double getAmount() {
    return amount;
  }

  public String toString() {
    return (
      (monthYear.get(Calendar.MONTH) + 1) +
      "/" +
      monthYear.get(Calendar.YEAR) +
      ": " +
      amount
    );
  }
}

class Transaction {

  private Calendar date;
  private String description;
  private String deposite;
  private String withdraw;
  private String balance;

  public Transaction(
    String[] date,
    String description,
    String deposite,
    String withdraw,
    String balance
  ) {
    this.date = Calendar.getInstance();
    this.date.set(Calendar.YEAR, Integer.parseInt(date[2]));
    this.date.set(Calendar.MONTH, Helper.monthToInt(date[1]));
    this.date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[0]));
    this.date.set(Calendar.HOUR_OF_DAY, 0);
    this.date.set(Calendar.MINUTE, 0);
    this.date.set(Calendar.SECOND, 0);
    this.date.set(Calendar.MILLISECOND, 0);
    this.description = description;
    this.deposite = deposite.replace("\"", "");
    this.withdraw = withdraw.replace("\"", "");
    this.balance = balance.replace("\"", "");
  }

  public boolean isClearBalance() {
    return balance.equals("00.00");
  }

  public String toString() {
    return (
      date.get(Calendar.DAY_OF_MONTH) +
      "/" +
      (date.get(Calendar.MONTH) + 1) +
      "/" +
      date.get(Calendar.YEAR) +
      " " +
      description +
      " " +
      deposite +
      " " +
      withdraw +
      " " +
      balance
    );
  }

  public String getMonthYear() {
    return ((date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR));
  }

  public Calendar getDate() {
    return date;
  }

  public String getDescription() {
    return description;
  }

  public double getTransactionAmount() {
    return (
      Double.parseDouble(deposite.replace(",", "")) -
      Double.parseDouble(withdraw.replace(",", ""))
    );
  }
}

class CSVParser {

  public static String[] parse(String content) {
    String[] data = content.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    return data;
  }
}

public class Main {

  public static void runQ1Sequence(String filename) {
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
        .filter(Transaction::isClearBalance)
        .sorted((t1, t2) -> t1.getDate().compareTo(t2.getDate()))
        .collect(
          groupingBy(
            Transaction::getDescription,
            collectingAndThen(toList(), t -> t.stream().findFirst().get())
          )
        )
        .entrySet()
        .stream()
        .map(entry -> entry.getValue())
        .sorted(
          (t1, t2) -> {
            int dateCompare = t1.getDate().compareTo(t2.getDate());
            if (dateCompare == 0) {
              return t1.getDescription().compareTo(t2.getDescription());
            }
            return dateCompare;
          }
        )
        .collect(toList())
        .forEach(System.out::println);
      Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());
      System.out.println(
        "Time taken: " + (endTimestamp.getTime() - timestamp.getTime()) + " ms"
      );
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("File not found");
    }
  }

  public static void runQ1Parallel(String filename) {
    try {
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
      Stream<String> files = Files.lines(Paths.get("5000000 BT Records.csv"));
      files
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
        .filter(Transaction::isClearBalance)
        .sorted((t1, t2) -> t1.getDate().compareTo(t2.getDate()))
        .collect(
          groupingBy(
            Transaction::getDescription,
            collectingAndThen(toList(), t -> t.stream().findFirst().get())
          )
        )
        .entrySet()
        .parallelStream()
        .map(entry -> entry.getValue())
        .sorted(
          (t1, t2) -> {
            int dateCompare = t1.getDate().compareTo(t2.getDate());
            if (dateCompare == 0) {
              return t1.getDescription().compareTo(t2.getDescription());
            }
            return dateCompare;
          }
        )
        .collect(toList())
        .forEach(System.out::println);
      Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());
      System.out.println(
        "Time taken: " + (endTimestamp.getTime() - timestamp.getTime()) + " ms"
      );
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("File not found");
    }
  }

  public static void runQ2Sequence(String filename) {
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
            double total = entry
              .getValue()
              .stream()
              .mapToDouble(Transaction::getTransactionAmount)
              .sum();
            return new MonthTransaction(entry.getKey(), round(total, 2));
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
      System.out.println(
        "Time taken: " + (endTimestamp.getTime() - timestamp.getTime()) + " ms"
      );
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("File not found");
    }
  }

  public static void runQ2Parallel(String filename) {
    try {
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
      Stream<String> files = Files.lines(Paths.get(filename));
      files
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
            double total = entry
              .getValue()
              .parallelStream()
              .mapToDouble(Transaction::getTransactionAmount)
              .sum();
            return new MonthTransaction(entry.getKey(), round(total, 2));
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
      System.out.println(
        "Time taken: " + (endTimestamp.getTime() - timestamp.getTime()) + " ms"
      );
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("File not found");
    }
  }

  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  public static void main(String[] args) {
    // Sequence Approach
    // runQ1Sequence("5000 BT Records.csv");
    // Parallel Approach
    // runQ1Parallel("5000 BT Records.csv");
    // Sequence Approach
    // runQ2Sequence("5000 BT Records.csv");
    // Parallel Approach
    // runQ2Parallel("5000 BT Records.csv");
  }
}
