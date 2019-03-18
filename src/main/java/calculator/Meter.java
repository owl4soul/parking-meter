package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Meter {
    //Массив с датами и временем из двух ячеек хранящий start time и end time
    LocalDateTime[] datesTimes;

    //Поля, хранящие даты и время по-отдельности
    LocalDate date1;
    LocalTime time1;

    LocalDate date2;
    LocalTime time2;

    //Поля, хранящие объединенные дату+время
    LocalDateTime dateTime1;
    LocalDateTime dateTime2;

    //CONSTRUCT
    public Meter() {
        System.out.println("Введите дату в формате " + "\"calc 2016-02-19 12:22:19.000 to 2016-02-20 14:01:00.000\": ");
        String[] inputSubstrings = getSubstrings();
        this.datesTimes = getDatesTimes(inputSubstrings);

        this.date1 = getDate(inputSubstrings[0]);
        this.time1 = getTime(inputSubstrings[1]);
        this.date2 = getDate(inputSubstrings[2]);
        this.time2 = getTime(inputSubstrings[3]);

        this.dateTime1 = LocalDateTime.of(date1, time1);
        this.dateTime2 = LocalDateTime.of(date2, time2);
    }

    //Get user input and transform it to substrings array
    public String[] getSubstrings() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            //Split input line into separated words
            String[] inputStrings = reader.readLine().split(" ");

            //Будем работать с массивом как со списком для упрощения
            List<String> substringsList = new ArrayList<>(Arrays.asList(inputStrings));

            //Пройдемся в цикле, чтоб удалить из массива substrings лишние ячейки, содержащие слова
            for (int i = 0; i < substringsList.size(); i++) {
                if (isWord(substringsList.get(i))) {
                    substringsList.remove(i);
                } else {
                    continue;
                }

            }

            String[] onlyDatesTimes = substringsList.toArray(new String[0]);
            return onlyDatesTimes;

        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    //Проверка, является ли эта строка словом (состоит ли из букв)
    public boolean isWord(String testString) {
        Pattern pattern = Pattern.compile("[a-z|A-z]+");
        Matcher matcher = pattern.matcher(testString);
        return matcher.matches();
    }


    //Convert 4-cell array of substrings[] to LocalDateTime[] array of 2 cell
    public LocalDateTime[] getDatesTimes(String[] substrings) {

        LocalDateTime[] datesTimes = new LocalDateTime[2]; //First - start date and time, Second - end date and time
        for (int i = 0, j = 0; i < substrings.length - 1; i = i + 2, j++) {
            LocalDate date = getDate(substrings[i]);
            LocalTime time = getTime(substrings[i+1]);

            datesTimes[j] = LocalDateTime.of(date, time);
        }
        return datesTimes;
    }


    //Парсим даты и время из облегченного массива, содержащего только целевые строки
    public LocalDate getDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return date;
    }

    public LocalTime getTime(String timeString) {
        LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
        return time;
    }

//    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.SSS");

}
