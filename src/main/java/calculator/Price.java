package calculator;

//TODO: разделить метод на меньшие части

import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Price {
    int priceWeekDay = 20;
    int priceWeekNight = 15;
    int priceWeekend = 10;

    //Округления
    public LocalDateTime roundFloor(LocalDateTime fromDate) {
        //Round down to hours
        LocalDateTime floorFromDate = fromDate.withMinute(0).withSecond(0).withNano(0);
        System.out.println("Rounded down start date: " + floorFromDate);

        return floorFromDate;
    }

    public LocalDateTime roundCeil(LocalDateTime toDate) {
        //Round up to hours
        LocalDateTime ceilToDate = toDate.withMinute(0).withSecond(0).withNano(0).plusHours(1);
        System.out.println("Rounded up end date: " + ceilToDate);

        return ceilToDate;
    }

    //Избавляемся от секунд и миллисекуд, округляем до часов и минут
    public LocalDateTime cutSeconds(LocalDateTime dateTime) {
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
        System.out.println("Передано fromDate: " + fromDate);
        System.out.println("Передано toDate: " + toDate);
        //Округлим даты и обрежем
        LocalDateTime start = cutSeconds(roundFloor(fromDate));
        System.out.println("start is now: " + start);
        LocalDateTime end = cutSeconds(roundCeil(toDate));
        System.out.println("end is now: " + end);


        //Интервал дней:
        Interval interval = new Interval(DateTime.parse(start.toString()), DateTime.parse(end.toString()));



        //Тест - выводим все часы по одному прибавляя, до момента завершения времени
        int count = 0;
        int countWeekDays = 0;
        int countWeekNights = 0;
        int countWeekends = 0;
        for (LocalDateTime i = start; i.isBefore(end); i = i.plusHours(1) ) {
            count++;
            System.out.println(count + " time " + i);
            if (i.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) == 0) {
                countWeekends++;
                System.out.println("Выходной");
                System.out.println("Часы по прайсу выходного дня: " + countWeekends);
            } else if (i.getHour() >= 8 & i.getHour() < 18) {
                countWeekDays++;
                System.out.println("Часы по прайсу буднего дня с 8 до 18: " + countWeekDays);
            } else {
                countWeekNights++;
                System.out.println("Часы по прайсу буднего дня с 18 до 8: " + countWeekNights);
            }
        }

        //Найти время начала уикенда
        System.out.println(start.getDayOfWeek()); //день недели, когда начался отсчет


        //Вычисляем количество часов между датами
        long hours = ChronoUnit.HOURS.between(start, end);
        System.out.println("Chrono.HOURS: " + hours);

        //Зададим крайние точки выходных

//        //Переводим даты в формат joda
//        DateTimeZone timeZone = DateTimeZone.forID("GMT");
//        DateTime start = DateTime.parse(fromDate.toString());
//        DateTime end = DateTime.parse(toDate.toString());
//        System.out.println("joda: " + start);
//
//        DateTime saturdayBegins = start.withDayOfWeek(6).withTimeAtStartOfDay();
//        DateTime sundayEnds = saturdayBegins.plusDays(2).minus(1);
//        System.out.println("weekend starts: " + saturdayBegins +"___" + saturdayBegins.getDayOfWeek());
//        System.out.println("weekend ends: " + sundayEnds +"___" + sundayEnds.getDayOfWeek());
//
//        Hours weekdayHours = Hours.hoursBetween(start, end);
//        System.out.println("Часы по фулл-прайс: " + weekdayHours.toString());




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
