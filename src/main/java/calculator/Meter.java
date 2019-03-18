package calculator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Meter {
    //Принимаем ввод строки, парсим в ней даты
    String input = "calc 2016-02-19 12:22:19.000 to 2016-02-20 14:01:00.000"; //TODO заменить на считывание

    //Split input line into separated words
    String[] substrings = input.split(" ");

    String tmp1 = "2016-02-19 12:22:19.000"; //TODO: заменить на значение из массива сабстрингов
    String tmp2 = "2016-02-20 14:01:00.000";

    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.SSS");
    LocalDateTime fromDate1 = LocalDateTime.parse(tmp1, format);
    LocalDateTime toDate = LocalDateTime.parse(tmp2, format);





}
