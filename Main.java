public class Main {

  static final String INPUT_SMALL = "5000 BT Records.csv";
  static final String INPUT_LARGE = "5000000 BT Records.csv";
  static final double CORE = 4;

  public static void main(String[] args) {
    runQ1();
    // runQ2();
  }

  public static void runQ1() {
    double ts = 0;
    double tp = 0;
    double speedup = 0;
    double efficiency = 0;
    System.out.println("===================== QUESTION 1 ("+INPUT_SMALL+") =====================");
    System.out.println("================================= SEQUENCE =================================");
    ts = Q1.run(INPUT_SMALL, Type.SEQUENCE);
    System.out.println("================================= PARALLEL =================================");
    tp = Q1.run(INPUT_SMALL, Type.PARALLEL);
    System.out.println("Sequence: " + ts + " ms");
    System.out.println("Parallel: " + tp + " ms");
    speedup = ts/tp;
    efficiency = (speedup*100)/CORE;
    System.out.println("Speed Up: " + speedup + " times faster");
    System.out.println("Efficiency: " + efficiency + " %");
    System.out.println("============================================================================");
    System.out.println("===================== QUESTION 1 ("+INPUT_LARGE+") ==================");
    System.out.println("================================= SEQUENCE =================================");
    ts = Q1.run(INPUT_LARGE, Type.SEQUENCE);
    System.out.println("================================= PARALLEL =================================");
    tp = Q1.run(INPUT_LARGE, Type.PARALLEL);
    System.out.println("Sequence: " + ts + " ms");
    System.out.println("Parallel: " + tp + " ms");
    speedup = ts/tp;
    efficiency = (speedup*100)/CORE;
    System.out.println("Speed Up: " + speedup + " times faster");
    System.out.println("Efficiency: " + efficiency + " %");
    System.out.println("============================================================================");
  }

  public static void runQ2() {
    double ts = 0;
    double tp = 0;
    double speedup = 0;
    double efficiency = 0;
    System.out.println("===================== QUESTION 2 ("+INPUT_SMALL+") =====================");
    System.out.println("================================= SEQUENCE =================================");
    ts = Q2.run(INPUT_SMALL, Type.SEQUENCE);
    System.out.println("================================= PARALLEL =================================");
    tp = Q2.run(INPUT_SMALL, Type.PARALLEL);
    System.out.println("Sequence: " + ts + " ms");
    System.out.println("Parallel: " + tp + " ms");
    speedup = ts/tp;
    efficiency = (speedup*100)/CORE;
    System.out.println("Speed Up: " + speedup + " times faster");
    System.out.println("Efficiency: " + efficiency + " %");
    System.out.println("============================================================================");
    System.out.println("===================== QUESTION 2 ("+INPUT_LARGE+") ==================");
    System.out.println("================================= SEQUENCE =================================");
    ts = Q2.run(INPUT_LARGE, Type.SEQUENCE);
    System.out.println("================================= PARALLEL =================================");
    tp = Q2.run(INPUT_LARGE, Type.PARALLEL);
    System.out.println("Sequence: " + ts + " ms");
    System.out.println("Parallel: " + tp + " ms");
    speedup = ts/tp;
    efficiency = (speedup*100)/CORE;
    System.out.println("Speed Up: " + speedup + " times faster");
    System.out.println("Efficiency: " + efficiency + " %");
    System.out.println("============================================================================");
  }
}
