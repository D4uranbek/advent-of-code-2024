package uz.dauranbek.days;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author d4uranbek
 * @since 05.12.2024
 */
public class Day05 implements Day {

    List<String> sections = new ArrayList<>();
    List<Point> rules = new ArrayList<>();

    @Override
    public String part1(List<String> input) {
        long total = 0;

        for (String line : input) {
            if (line.isBlank()) {
                continue;
            }

            if (line.contains("|")) {
                String[] split = line.split("[|]");
                rules.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            }
            if (line.contains(",")) {
                sections.add(line);
            }
        }

        for (String section : sections) {
            boolean isCorrect = true;
            List<Integer> list = Arrays.stream(section.split(",")).map(Integer::parseInt).toList();

            GO:
            for (int i = 0; i < list.size(); i++) {
                int current = list.get(i);

                for (Point rule : rules) {
                    if (rule.x == current) {
                        if (!isCorrectLeft(list, rule.y, i)) {
                            isCorrect = false;
                            break GO;
                        }
                    }
                    if (rule.y == current) {
                        if (!isCorrectRight(list, rule.x, i)) {
                            isCorrect = false;
                            break GO;
                        }
                    }
                }
            }

            if (isCorrect) {
                total += list.get(list.size() / 2);
            }
        }

        return String.valueOf(total);
    }

    boolean isCorrectLeft(List<Integer> list, int number, int indexTo) {
        for (int i = 0; i < indexTo; i++) {
            if (list.get(i) == number) {
                return false;
            }
        }
        return true;
    }

    boolean isCorrectRight(List<Integer> list, int number, int indexFrom) {
        for (int i = indexFrom + 1; i < list.size(); i++) {
            if (list.get(i) == number) {
                return false;
            }
        }
        return true;
    }


    @Override
    public String part2(List<String> input) {
        long total = 0;

        for (String line : input) {
            if (line.isBlank()) {
                continue;
            }

            if (line.contains("|")) {
                String[] split = line.split("[|]");
                rules.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            }
            if (line.contains(",")) {
                sections.add(line);
            }
        }

        for (String section : sections) {
            List<Integer> list = Arrays.stream(section.split(",")).map(Integer::parseInt).toList();

            if (!isCorrect(list)) {
                System.out.println("section = " + section);
                List<Integer> corrected = list;
                while (!isCorrect(corrected)) {
                    corrected = correct(corrected);
                }
                System.out.println("corrected = " + corrected);
                total += corrected.get(corrected.size() / 2);
            }
        }

        return String.valueOf(total);
    }

    private boolean isCorrect(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);

            for (Point rule : rules) {
                if (rule.x == current) {
                    if (!isCorrectLeft(list, rule.y, i)) {
                        return false;
                    }
                }
                if (rule.y == current) {
                    if (!isCorrectRight(list, rule.x, i)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    List<Integer> correct(List<Integer> list) {
        Integer[] corrected = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            int current = list.get(i);
            int left = 0;

            for (Point rule : rules) {
                if (rule.y == current) {
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j) == rule.x) {
                            left++;
                        }
                    }
                }
            }

            corrected[left] = current;
        }
        return List.of(corrected);
    }
}
