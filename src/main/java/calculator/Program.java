package calculator;

public class Program {
    public static void main(String[] args) {
        //TODO: тесты убрать
        Meter met = new Meter();
        for (String sub : met.substrings) {
            System.out.println(sub);
        }
        Price p = new Price();
        p.countPrice(met.fromDate1, met.toDate);

    }
}
