import java.util.Scanner;
public class Main {

  static final String INPUT_SMALL = "5000 BT Records.csv";
  static final String INPUT_LARGE = "5000000 BT Records.csv";
  static final double CORE = 4;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the question (Press 1 for Q1, Press 2 for Q2): ");
    int question = sc.nextInt();
    if(question == 1) {
      runQ1();
    } else if(question == 2) {
      System.out.println("Enter the task (Press 1 for Task 2.1, Press 2 for Task 2.2): ");
      int task = sc.nextInt();
      if(task == 1) {
        runQ2_1();
      } else if(task == 2) {
        runQ2_2();
      }
    } 
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

  public static void runQ2_1() {
    double ts = 0;
    double tp = 0;
    double speedup = 0;
    double efficiency = 0;
    System.out.println("================================== TASK 1 ==================================");
    System.out.println("===================== QUESTION 2 ("+INPUT_SMALL+") =====================");
    System.out.println("================================= SEQUENCE =================================");
    ts = Q2.runT1(INPUT_SMALL, Type.SEQUENCE);
    System.out.println("================================= PARALLEL =================================");
    tp = Q2.runT1(INPUT_SMALL, Type.PARALLEL);
    System.out.println("Sequence: " + ts + " ms");
    System.out.println("Parallel: " + tp + " ms");
    speedup = ts/tp;
    efficiency = (speedup*100)/CORE;
    System.out.println("Speed Up: " + speedup + " times faster");
    System.out.println("Efficiency: " + efficiency + " %");
    System.out.println("============================================================================");
    System.out.println("===================== QUESTION 2 ("+INPUT_LARGE+") ==================");
    System.out.println("================================= SEQUENCE =================================");
    ts = Q2.runT1(INPUT_LARGE, Type.SEQUENCE);
    System.out.println("================================= PARALLEL =================================");
    tp = Q2.runT1(INPUT_LARGE, Type.PARALLEL);
    System.out.println("Sequence: " + ts + " ms");
    System.out.println("Parallel: " + tp + " ms");
    speedup = ts/tp;
    efficiency = (speedup*100)/CORE;
    System.out.println("Speed Up: " + speedup + " times faster");
    System.out.println("Efficiency: " + efficiency + " %");
    System.out.println("============================================================================");
  }

  public static void runQ2_2() {
    double ts = 0;
    double tp = 0;
    double speedup = 0;
    double efficiency = 0;
    System.out.println("================================== TASK 2 ==================================");
    System.out.println("===================== QUESTION 2 ("+INPUT_SMALL+") =====================");
    System.out.println("================================= SEQUENCE =================================");
    ts = Q2.runT2(INPUT_SMALL, Type.SEQUENCE);
    System.out.println("================================= PARALLEL =================================");
    tp = Q2.runT2(INPUT_SMALL, Type.PARALLEL);
    System.out.println("Sequence: " + ts + " ms");
    System.out.println("Parallel: " + tp + " ms");
    speedup = ts/tp;
    efficiency = (speedup*100)/CORE;
    System.out.println("Speed Up: " + speedup + " times faster");
    System.out.println("Efficiency: " + efficiency + " %");
    System.out.println("============================================================================");
    System.out.println("===================== QUESTION 2 ("+INPUT_LARGE+") ==================");
    System.out.println("================================= SEQUENCE =================================");
    ts = Q2.runT2(INPUT_LARGE, Type.SEQUENCE);
    System.out.println("================================= PARALLEL =================================");
    tp = Q2.runT2(INPUT_LARGE, Type.PARALLEL);
    System.out.println("Sequence: " + ts + " ms");
    System.out.println("Parallel: " + tp + " ms");
    speedup = ts/tp;
    efficiency = (speedup*100)/CORE;
    System.out.println("Speed Up: " + speedup + " times faster");
    System.out.println("Efficiency: " + efficiency + " %");
    System.out.println("============================================================================");
  }
}
