package uz.dauranbek.days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author d4uranbek
 * @since 01.12.2024
 */
public class Day01 implements Day {

    long[] firstColumn;
    long[] secondColumn;

    @Override
    public String part1(List<String> input) {
        long sum = 0L;

        int rows = input.size();
        firstColumn = new long[rows];
        secondColumn = new long[rows];

        for (int i = 0; i < rows; i++) {
            String row = input.get(i);
            String[] s = row.split("   ");
            firstColumn[i] = Long.parseLong(s[0]);
            secondColumn[i] = Long.parseLong(s[1]);
        }

        Arrays.sort(firstColumn);
        Arrays.sort(secondColumn);

        for (int i = 0; i < rows; i++) {
            sum += Math.abs(firstColumn[i] - secondColumn[i]);
        }

        return String.valueOf(sum);
    }

    @Override
    public String part2(List<String> input) {
        long sum = 0L;

        int rows = input.size();
        firstColumn = new long[rows];
        secondColumn = new long[rows];

        for (int i = 0; i < rows; i++) {
            String row = input.get(i);
            String[] s = row.split("   ");
            firstColumn[i] = Long.parseLong(s[0]);
            secondColumn[i] = Long.parseLong(s[1]);
        }

        HashMap<Long, Long> map = new HashMap<>();
        for (long current : secondColumn) {
            map.put(current, map.getOrDefault(current, 0L) + 1);
        }

        for (long current : firstColumn) {
            sum += current * map.getOrDefault(current, 0L);
        }

        return String.valueOf(sum);
    }

}
