package techcourse.fp.mission;

import java.util.List;

public class Calculator {


    @FunctionalInterface
    public interface Conditional {
        boolean test(Integer number);
    }


    public static int sumAll(List<Integer> numbers, Conditional conditional) {
        int total = 0;
        for (int number : numbers) {
            if (conditional.test(number)) {
                total += number;
            }
        }
        return total;
    }
}