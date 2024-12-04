package uz.dauranbek.days;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author d4uranbek
 * @since 03.12.2024
 */
public class Day03 implements Day {

    @Override
    public String part1(List<String> input) {
        long total = 0;

        for (String memory : input) {
            String regex = "mul[(][0-9]{1,3}[,][0-9]{1,3}[)]";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(memory);

            List<String> matches = new ArrayList<>();

            while (matcher.find()) {
                matches.add(matcher.group());
            }

            for (String match : matches) {
                System.out.println(match);
                total += eval(match);
            }

        }

        return Long.toString(total);
    }

    long eval(String mulProgram) {
        String substring = mulProgram.substring("mul(".length(), mulProgram.length() - 1);
        String[] split = substring.split(",");
        return Long.parseLong(split[0]) * Long.parseLong(split[1]);
    }


    @Override
    public String part2(List<String> input) {
        AtomicLong total = new AtomicLong();

        AtomicBoolean enabled = new AtomicBoolean(true);
        StringBuilder memoryBuilder = new StringBuilder();
        for (String row : input) {
            memoryBuilder.append(row);
        }
        String memory = memoryBuilder.toString();

        String regex = "mul[(][0-9]{1,3}[,][0-9]{1,3}[)]";
        String doRegex = "do[(][)]";
        String dontRegex = "don't[(][)]";

        Matcher matcher = Pattern.compile(regex).matcher(memory);

        Map<Integer, String> matches = new HashMap<>();

        while (matcher.find()) {
            matches.put(matcher.start(), matcher.group());
        }

        Matcher doMatcher = Pattern.compile(doRegex).matcher(memory);
        while (doMatcher.find()) {
            matches.put(doMatcher.start(), doMatcher.group());
        }

        Matcher dontMatcher = Pattern.compile(dontRegex).matcher(memory);
        while (dontMatcher.find()) {
            matches.put(dontMatcher.start(), dontMatcher.group());
        }

        Map<Integer, String> sortedTreeMap = new TreeMap<>(matches);

        sortedTreeMap.forEach((k, v) -> {
            if (v.equals("do()")) enabled.set(true);
            else if (v.equals("don't()")) enabled.set(false);
            else {
                if (enabled.get()) total.addAndGet(eval(v));
            }
        });

        return Long.toString(total.get());
    }

}
