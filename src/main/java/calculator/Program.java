package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program {
    public static void main(String[] args) throws IOException {
        Meter meter = new Meter();
        Price price = new Price();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите команду: ");
        try {
            String input = reader.readLine();
            if (input.equals("price")) {
                System.out.println("PRICE-----");
            } else {
                meter.getSubstrings(input);
            }
        } catch (IOException e) {
            e.toString();
        } finally {
            reader.close();
        }






        //TODO: тесты убрать
        Meter met = new Meter();

        Price p = new Price();
        p.getInstantsOfPeriod(met.dateTime1, met.dateTime2);
        System.out.println();
        System.out.println(met.dateTime1);
        System.out.println();
        p.getInstantsOfPeriod(met.dateTime1, met.dateTime2);

        System.out.println("__________________________________");
        p.countPrice(met.dateTime1, met.dateTime2);

        System.out.println("__________________________________");
        p.cutSeconds(met.dateTime1);

    }
}
