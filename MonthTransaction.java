import java.util.Calendar;

public class MonthTransaction {

  private Calendar monthYear;
  private double amount;

  public MonthTransaction(String monthYear, double amount) {
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
