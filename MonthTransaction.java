import java.util.Calendar;

public class MonthTransaction {

  private Calendar monthYear;
  private double deposit;
  private double withdraw;
  private double balance;

  public MonthTransaction(
    String monthYear,
    double deposit,
    double withdraw,
    double balance
  ) {
    this.monthYear = Calendar.getInstance();
    this.monthYear.set(
        Calendar.MONTH,
        Integer.parseInt(monthYear.split("/")[0]) - 1
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
    this.balance = balance;
    this.deposit = deposit;
    this.withdraw = withdraw;
  }

  public Calendar getMonthYear() {
    return monthYear;
  }

  public double getBalance() {
    return balance;
  }

  public String toString() {
    return (
      (monthYear.get(Calendar.MONTH) + 1) +
      "/" +
      monthYear.get(Calendar.YEAR) +
      ": " +
      String.format("%.2f", balance)
    );
  }

  public String toStringWithDetail() {
    return (
      (monthYear.get(Calendar.MONTH) + 1) +
      "/" +
      monthYear.get(Calendar.YEAR) +
      ": " +
      " Deposit: " +
      String.format("%.2f", deposit) +
      " Withdraw: " +
      String.format("%.2f", withdraw)
    );
  }
}
