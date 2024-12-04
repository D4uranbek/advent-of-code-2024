package uz.dauranbek.days;

import java.util.Arrays;
import java.util.List;

/**
 * @author d4uranbek
 * @since 02.12.2024
 */
public class Day02 implements Day {

    @Override
    public String part1(List<String> input) {
        long total = 0;
        for (String line : input) {
            long[] levels = Arrays.stream(line.split(" ")).sequential().mapToLong(Long::parseLong).toArray();
            boolean safe = isSafe(levels);
            if (safe) {
                total++;
            }

        }

        return Long.toString(total);
    }

    boolean isSafe(long[] levels) {
        boolean isIncreasing;
        if (levels[0] < levels[1]) {
            isIncreasing = true;
        } else if (levels[0] > levels[1]) {
            isIncreasing = false;
        } else {
            return false;
        }

        for (int i = 0; i < levels.length - 1; i++) {
            if (isIncreasing) {
                if (levels[i + 1] - levels[i] > 3 || levels[i + 1] - levels[i] < 1) {
                    return false;
                }
            } else {
                if (levels[i] - levels[i + 1] > 3 || levels[i] - levels[i + 1] < 1) {
                    return false;
                }
            }

        }

        return true;
    }

    @Override
    public String part2(List<String> input) {
        long total = 0;
        for (String line : input) {
            long[] levels = Arrays.stream(line.split(" ")).sequential().mapToLong(Long::parseLong).toArray();

            boolean safe = false;
            for (int i = 0; i < levels.length; i++) {
                long[] removed = remove(levels, i);
                safe = isSafe(removed);
                if (safe) {
                    total++;
                    break;
                }
            }
        }

        return Long.toString(total);
    }

    long[] remove(long[] source, int index) {
        long[] result = new long[source.length - 1];
        System.arraycopy(source, 0, result, 0, index);
        if (source.length != index) {
            System.arraycopy(source, index + 1, result, index, source.length - index - 1);
        }
        return result;
    }

}
