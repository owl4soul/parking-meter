package calculator;

public class Program {
    public static void main(String[] args) {
        //TODO: тесты убрать
        Meter met = new Meter();

        Price p = new Price();
        p.countPrice(met.dateTime1, met.dateTime2);

    }
}