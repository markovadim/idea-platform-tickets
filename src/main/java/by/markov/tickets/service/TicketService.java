package by.markov.tickets.service;

import by.markov.tickets.model.TicketsData;
import by.markov.tickets.util.DataUtil;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class TicketService {

    private TicketsData ticketsData;

    public void getResultData() {
        readJsonFile();
        getAverageTimeOfFly();
        getPercentile();
    }

    public String readJsonFile() {
        StringBuilder stringBuilder = new StringBuilder("");

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(DataUtil.DEFAULT_JSON_FILE);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public void getAverageTimeOfFly() {
        Gson gson = new Gson();
        String jsonString = readJsonFile();

        ticketsData = gson.fromJson(jsonString, TicketsData.class);

        double average = ticketsData
                .getTickets()
                .stream()
                .filter(p -> p.getOrigin().equals(DataUtil.VLADIVOSTOK_ORIGIN) && p.getDestination().equals(DataUtil.TEL_AVIV_ORIGIN))
                .mapToLong(DataUtil.diffTimeLambdaFunction)
                .average()
                .getAsDouble();
        System.out.println("Average time of fly: " + LocalTime.ofSecondOfDay((long) (average * 60)));
    }

    public void getPercentile() {
        List<Long> sortedFlyTimeValuesInMinutes = ticketsData
                .getTickets()
                .stream()
                .mapToLong(DataUtil.diffTimeLambdaFunction)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        long resultPercentile = sortedFlyTimeValuesInMinutes.get(getIndex(DataUtil.REQUEST_PERCENTILE, sortedFlyTimeValuesInMinutes.size()));
        System.out.println("90th percentile is : " + resultPercentile);
    }


    public static int getIndex(int percentile, int size) {
        return (int) ((percentile / 100.0) * size) - 1;
    }
}
