import java.math.BigDecimal;
import java.util.Calendar;

public class Transaction {

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

  public String getDescription() {
    return description;
  }

  public Calendar getDate() {
    return date;
  }

  public String getMonthYear() {
    return ((date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR));
  }

  public BigDecimal getTransactionAmount() {
    return (
      new BigDecimal(deposite.replace(",", ""))
      .subtract(new BigDecimal(withdraw.replace(",", "")))
    );
  }

  public BigDecimal getEntryBalance() {
    return new BigDecimal(balance.replace(",", ""))
    .subtract(getTransactionAmount());
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
}
