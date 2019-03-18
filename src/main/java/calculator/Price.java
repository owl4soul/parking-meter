package calculator;

//TODO: разделить метод на меньшие части

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class Price {
    final int PRICE_OF_WEEK_DAY = 20;
    final int PRICE_OF_WEEK_NIGHT = 15;
    final int PRICE_OF_WEEKEND = 10;

    public Map<String, Integer> priceMap = new LinkedHashMap<>();
    {
        priceMap.put("Цена парковки по будням с 8 до 18: ", PRICE_OF_WEEK_DAY);
        priceMap.put("Цена парковки по будням с 18 до 8: ", PRICE_OF_WEEK_NIGHT);
        priceMap.put("Цена парковки по выходным: ", PRICE_OF_WEEK_DAY);

    }


    //Округления
    public LocalDateTime roundFloor(LocalDateTime fromDate) {
        //Round down to hours
        LocalDateTime floorFromDate = fromDate.withMinute(0).withSecond(0).withNano(0);

        return floorFromDate;
    }

    public LocalDateTime roundCeil(LocalDateTime toDate) {
        //Round up to hours
        LocalDateTime ceilToDate = toDate.withMinute(0).withSecond(0).withNano(0).plusHours(1);

        return ceilToDate;
    }

    //Избавляемся от секунд и миллисекуд, округляем до часов и минут
    public LocalDateTime cutSeconds(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);

        LocalDateTime roundToMin = dateTime.truncatedTo(ChronoUnit.MINUTES);

        return roundToMin;
    }

    //Расчет цен в зависимости от дня недели и времени
    public int countCostWeekend(int hours) {
        int costWeekend = hours * PRICE_OF_WEEKEND;
        return costWeekend;
    }
    public int countCostWeekDay(int hours) {
        int costWeekDay = hours * PRICE_OF_WEEK_DAY;
        return costWeekDay;
    }
    public int countCostWeekNight(int hours) {
        int costWeekNight = hours * PRICE_OF_WEEK_NIGHT;
        return costWeekNight;
    }

    //Проверка дней
    public int countPrice(LocalDateTime fromDate, LocalDateTime toDate) {
        //Округлим даты и обрежем
        LocalDateTime start = cutSeconds(roundFloor(fromDate));
        LocalDateTime end = cutSeconds(roundCeil(toDate));

        int countWeekDays = 0;
        int countWeekNights = 0;
        int countWeekends = 0;

        for (LocalDateTime i = start; i.isBefore(end); i = i.plusHours(1) ) {
            if (i.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) == 0
                    | i.getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0) {
                countWeekends++;
            } else if (i.getHour() >= 8 & i.getHour() < 18) {
                countWeekDays++;

            } else {
                countWeekNights++;
            }
        }
        System.out.println("Расчёт стоимости парковки...");
        System.out.println(countWeekDays + " часов по 20 у.е  = " + countCostWeekDay(countWeekDays) + "у.е.");
        System.out.println(countWeekNights + " часов по 15 у.е  = " + countCostWeekNight(countWeekNights) + "у.е.");
        System.out.println(countWeekends + " часов по 10 у.е  = " + countCostWeekend(countWeekends) + "у.е.");
        int fullPrice = countCostWeekDay(countWeekDays)
                + countCostWeekNight(countWeekNights)
                + countCostWeekend(countWeekends);
        System.out.println("Общая сумма:  " + fullPrice + " у.е.");
        return fullPrice;
    }
}
