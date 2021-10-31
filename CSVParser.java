public class CSVParser {

  public static String[] parse(String content) {
    String[] data = content.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
    return data;
  }
}
