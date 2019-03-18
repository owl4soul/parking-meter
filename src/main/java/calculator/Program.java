package calculator;

public class Program {
    public static void main(String[] args) {
        //TODO: тесты убрать
        Meter met = new Meter();

        Price p = new Price();
        p.getInstantsOfPeriod(met.dateTime1, met.dateTime2);
        System.out.println();
        System.out.println(met.dateTime1);
        System.out.println();
        p.getInstantsOfPeriod(met.dateTime1, met.dateTime2);

        System.out.println("__________________________________");
        p.testWeek(met.dateTime1, met.dateTime2);

    }
}
