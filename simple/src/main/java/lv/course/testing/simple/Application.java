package lv.course.testing.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    private static final CalculationService calculationService = new CalculationService();

    public static void main(String[] args) throws IOException {
        String stop = "n";
        while (!stop.equalsIgnoreCase("y")) {
            System.out.println("Provide inputs for duplication.");
            System.out.println("First argument: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            long input1 = Long.parseLong(br.readLine());
            System.out.println("Second argument: ");
            long input2 = Long.parseLong(br.readLine());
            double result = calculationService.doCalculations(input1, input2);
            System.out.printf("%d / %d = %f%n", input1, input2, result);
            System.out.println("Stop execution (y / n)?");
            stop = br.readLine();
        }
    }
}
