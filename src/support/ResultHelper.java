package support;

import java.util.Arrays;
import java.util.List;

public class ResultHelper {
    static List<String> printableClasses = Arrays.asList("String", "Long", "Integer");

    public static <T> void printOut(T result) {
        String className = result.getClass().getName();
        boolean isPrimitive = result.getClass().isPrimitive();
        if (isPrimitive && printableClasses.contains(className)) {
            System.out.println("Result of the question is " + result);
        }
    }
}
