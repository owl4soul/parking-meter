package calculator;

import com.sun.scenario.effect.impl.sw.java.JSWPhongLighting_DISTANTPeer;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Price {
    public void countPrice(LocalDateTime fromDate, LocalDateTime toDate) {
        //Round down to hours
        LocalDateTime floorFromDate = fromDate.withMinute(0).withSecond(0).withNano(0);
        System.out.println("Rounded down start date: " + floorFromDate);

        //Round up to hours
        LocalDateTime ceilToDate = toDate.plusHours(1).withMinute(0).withSecond(0).withNano(0);
        System.out.println("Rounded up end date: " + ceilToDate);
    }



    public void applyPrice(LocalDateTime fromDate, LocalDateTime toDate) {
        System.out.println("from " + fromDate);
        System.out.println("to " + toDate);

        System.out.println();
        //Round down to hours
        LocalDateTime floorFromDate = fromDate.withMinute(0).withSecond(0).withNano(0);
        System.out.println("Rounded down start date: " + floorFromDate);
        //Round up to hours
        LocalDateTime ceilToDate = toDate.plusHours(1).withMinute(0).withSecond(0).withNano(0);
        System.out.println("Rounded up end date: " + ceilToDate);
        System.out.println()
        ;
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd'T'HH:mm")
                .withZone(ZoneId.of("GMT"));
//        System.out.println(ZoneId.getAvailableZoneIds());

        Instant instantStart = Instant.from(formatter.parse(floorFromDate.toString()));
        System.out.println("Зафиксированное время начала парковки: "+ instantStart.toString());
        Instant instantEnd = Instant.from(formatter.parse(ceilToDate.toString()));
        System.out.println("Зафиксированное время окончания парковки: "+ instantEnd.toString());


//        LocalDateTime floorFromDate = LocalDateTime.
//
//
//        Instant instantStart = Instant.from(fromDate);
//        Instant instantEnd = Instant.from(floorFromDate);
//        System.out.println(instantStart);
//        System.out.println(instantEnd);
    }
}
