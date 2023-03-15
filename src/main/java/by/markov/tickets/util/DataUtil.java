package by.markov.tickets.util;

import by.markov.tickets.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.ToLongFunction;

public class DataUtil {

    public static String DEFAULT_JSON_FILE = "tickets.json";
    public static String VLADIVOSTOK_ORIGIN = "VVO";
    public static String TEL_AVIV_ORIGIN = "TLV";
    public static int REQUEST_PERCENTILE = 90;

    public static ToLongFunction<Ticket> diffTimeLambdaFunction = p -> Duration.between(
            LocalDateTime.parse(p.getDeparture_date() + p.getDeparture_time(), DataUtil.dateTimeFormatter()),
            LocalDateTime.parse(p.getArrival_date() + p.getArrival_time(), DataUtil.dateTimeFormatter())
    ).toMinutes();

    public static DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd.MM.yyH:m");
    }
}
