package calculator;

//TODO: разделить метод на меньшие части

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeZone;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Price {
    int priceWeekDay = 20;
    int priceWeekNight = 15;
    int priceWeekend = 10;


//    public void countPrice(LocalDateTime fromDate, LocalDateTime toDate) {
//        //Round down to hours
//        LocalDateTime floorFromDate = fromDate.withMinute(0).withSecond(0).withNano(0);
//        System.out.println("Rounded down start date: " + floorFromDate);
//
//        //Round up to hours
//        LocalDateTime ceilToDate = toDate.plusHours(1).withMinute(0).withSecond(0).withNano(0);
//        System.out.println("Rounded up end date: " + ceilToDate);
//    }

    public LocalDateTime roundDown(LocalDateTime fromDate) {
        //Round down to hours
        LocalDateTime floorFromDate = fromDate.withMinute(0).withSecond(0).withNano(0);
        System.out.println("Rounded down start date: " + floorFromDate);

        return floorFromDate;
    }

    public LocalDateTime roundUp(LocalDateTime fromDate) {
        //Round up to hours
        LocalDateTime ceilToDate = fromDate.withMinute(0).withSecond(0).withNano(0);
        System.out.println("Rounded up end date: " + ceilToDate);

        return ceilToDate;
    }

    //Избавляемся от секунд и миллисекуд, округляем до часов и минут
    public LocalDateTime truncateToMin(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
//        LocalDateTime res = LocalDateTime.parse(dateTime.toString(), formatter);
//        System.out.println(res);
        LocalDateTime truncated = dateTime.truncatedTo(ChronoUnit.MINUTES);
        System.out.println("Обрезаем до минут: " + truncated);
        return truncated;
    }


//TODO: дублирующийся код - теперь есть отдельные методы, применить их
    public Instant[] getInstantsOfPeriod(LocalDateTime fromDate, LocalDateTime toDate) {
        System.out.println("from " + fromDate);
        System.out.println("to " + toDate);

        System.out.println();
        //Round down to hours
        LocalDateTime floorFromDate = fromDate.withMinute(0).withSecond(0).withNano(0);
        System.out.println("Rounded down start date: " + floorFromDate);
        //Round up to hours
        LocalDateTime ceilToDate = toDate.plusHours(1).withMinute(0).withSecond(0).withNano(0);
        System.out.println("Rounded up end date: " + ceilToDate);
        System.out.println();

        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm")
                .withZone(ZoneId.of("GMT"));
//        System.out.println(ZoneId.getAvailableZoneIds());

        Instant instantStart = Instant.from(formatter.parse(floorFromDate.toString()));
        System.out.println("Зафиксированное время начала парковки: "+ instantStart.toString());
        Instant instantEnd = Instant.from(formatter.parse(ceilToDate.toString()));
        System.out.println("Зафиксированное время окончания парковки: "+ instantEnd.toString());

        Instant[] instants = new Instant[2];
        instants[0] = instantStart;
        instants[1] = instantEnd;

        return instants;
    }

    //Расчет цен в зависимости от дня недели и времени
    public int countCostWeekend(int hours) {
        int costWeekend = hours * priceWeekend;
        return costWeekend;
    }
    public int countCostWeekDay(int hours) {
        int costWeekDay = hours * priceWeekDay;
        return costWeekDay;
    }
    public int countCostWeekNight(int hours) {
        int costWeekNight = hours * priceWeekNight;
        return costWeekNight;
    }

    //Проверка дней
    public void testWeek(LocalDateTime fromDate, LocalDateTime toDate) {
        //Найти время начала уикенда Joda

        DateTimeZone timeZone = DateTimeZone.forID("GMT");
        DateTime date1 = DateTime.parse(fromDate.toString());
        System.out.println("joda: " + date1);



//        for (LocalDateTime i = fromDate; i.isBefore(toDate); ) {
//
//            if (DayOfWeek.from(i).compareTo(DayOfWeek.SATURDAY) == 0
//                    || DayOfWeek.from(i).compareTo(DayOfWeek.SUNDAY) == 0) {
////                LocalDateTime timeOfWeekendEnd = LocalDateTime.of(i, "yyyy-MM-dd'T'HH:mm");
////                Duration dur = Duration.between(i, i);
//            }
//        }
    }
}
