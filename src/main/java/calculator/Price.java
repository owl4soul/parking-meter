package calculator;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Price {
    public void countPrice(LocalDateTime fromDate, LocalDateTime toDate) {
        //Round down to hours
        LocalDateTime floorFromDate = fromDate.withMinute(0).withSecond(0).withNano(0);
        System.out.println("Rounded down start date: " + floorFromDate);

        //Round up to hours
        LocalDateTime ceilToDate = toDate.plusHours(1).withMinute(0).withSecond(0).withNano(0);
        System.out.println("Rounded up end date: " + ceilToDate);
    }
}
