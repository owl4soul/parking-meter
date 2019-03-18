package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Program {
    public static void main(String[] args) throws IOException {

        Price price = new Price();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите команду: ");
        try {
            String input = reader.readLine();
            if (input.equals("price")) {
                for (Map.Entry<String, Integer> entry : price.priceMap.entrySet()) {
                    System.out.println(entry.toString().replace('=', ' ') + " у.е в час");
                }
            } else {
                Meter meter = new Meter(input);
                price.countPrice(meter.dateTime1, meter.dateTime2);
            }
        } catch (IOException e) {
            e.toString();
        } finally {
            reader.close();
        }
    }
}
