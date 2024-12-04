package uz.dauranbek;

import uz.dauranbek.days.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author d4uranbek
 * @since 01.12.2024
 */
public class AocApplication {

    private static final Map<Integer, Day> DAYS;

    static {
        DAYS = new HashMap<>();
        DAYS.put(4, new Day04());
    }

    public static void main(String[] args) {
        int day = 4;
        if (args.length != 0) {
            day = Integer.parseInt(args[0]);
        }

        int part = 2;
        if (args.length > 1) {
            part = Integer.parseInt(args[1]);
        }

        List<String> input = loadInput(day);

        String result;
        if (part == 1) {
            result = DAYS.get(day).part1(input);
        } else {
            result = DAYS.get(day).part2(input);
        }

        System.out.println(result);
    }

    private static List<String> loadInput(int day) {
        String paddedDay = String.valueOf(day);
        if (day < 10) {
            paddedDay = "0" + day;
        }
        String fileName = "src/main/resources/day" + paddedDay + ".txt";
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
