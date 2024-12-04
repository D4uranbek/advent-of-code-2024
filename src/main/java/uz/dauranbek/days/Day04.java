package uz.dauranbek.days;

import java.util.List;

/**
 * @author d4uranbek
 * @since 04.12.2024
 */
public class Day04 implements Day {

    char[][] matrix;
    long count = 0;
    String word = "MAS";

    @Override
    public String part1(List<String> input) {
        matrix = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            matrix[i] = input.get(i).toCharArray();
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'X') {
                    count(i, j);
                }
            }
        }

        return String.valueOf(count);
    }


    void count(int i, int j) {
        if (horizontal(i, j)) count++;
        if (horizontalBackward(i, j)) count++;
        if (vertical(i, j)) count++;
        if (verticalBackward(i, j)) count++;
        if (diagonalL(i, j)) count++;
        if (diagonalLBackward(i, j)) count++;
        if (diagonalR(i, j)) count++;
        if (diagonalRBackward(i, j)) count++;
    }

    boolean horizontal(int i, int j) {
        try {
            String check = "" + matrix[i][j + 1] + matrix[i][j + 2] + matrix[i][j + 3];
            return check.equals(word);
        } catch (Exception e) {
            return false;
        }
    }

    boolean horizontalBackward(int i, int j) {
        try {
            String check = "" + matrix[i][j - 1] + matrix[i][j - 2] + matrix[i][j - 3];
            return check.equals(word);
        } catch (Exception e) {
            return false;
        }
    }

    boolean vertical(int i, int j) {
        try {
            String check = "" + matrix[i + 1][j] + matrix[i + 2][j] + matrix[i + 3][j];
            return check.equals(word);
        } catch (Exception e) {
            return false;
        }
    }

    boolean verticalBackward(int i, int j) {
        try {
            String check = "" + matrix[i - 1][j] + matrix[i - 2][j] + matrix[i - 3][j];
            return check.equals(word);
        } catch (Exception e) {
            return false;
        }
    }

    boolean diagonalL(int i, int j) {
        try {
            String check = "" + matrix[i + 1][j + 1] + matrix[i + 2][j + 2] + matrix[i + 3][j + 3];
            return check.equals(word);
        } catch (Exception e) {
            return false;
        }
    }


    boolean diagonalLBackward(int i, int j) {
        try {
            String check = "" + matrix[i - 1][j - 1] + matrix[i - 2][j - 2] + matrix[i - 3][j - 3];
            return check.equals(word);
        } catch (Exception e) {
            return false;
        }
    }

    boolean diagonalR(int i, int j) {
        try {
            String check = "" + matrix[i + 1][j - 1] + matrix[i + 2][j - 2] + matrix[i + 3][j - 3];
            return check.equals(word);
        } catch (Exception e) {
            return false;
        }
    }

    boolean diagonalRBackward(int i, int j) {
        try {
            String check = "" + matrix[i - 1][j + 1] + matrix[i - 2][j + 2] + matrix[i - 3][j + 3];
            return check.equals(word);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String part2(List<String> input) {
        matrix = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            matrix[i] = input.get(i).toCharArray();
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'A') {
                    count2(i, j);
                }
            }
        }

        return String.valueOf(count);
    }

    void count2(int i, int j) {
        try {
            boolean horizontal = matrix[i - 1][j - 1] == 'M' && matrix[i + 1][j + 1] == 'S' ||
                                 matrix[i - 1][j - 1] == 'S' && matrix[i + 1][j + 1] == 'M';
            boolean vertical = matrix[i + 1][j - 1] == 'M' && matrix[i - 1][j + 1] == 'S' ||
                               matrix[i + 1][j - 1] == 'S' && matrix[i - 1][j + 1] == 'M';

            if (horizontal && vertical) {
                count++;
            }
        } catch (Exception ignored) {
        }
    }

}
