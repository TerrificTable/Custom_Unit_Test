package xyz.terrific.testing;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Assertions {

    public static int fails                         = 0;
    public static int passes                        = 0;
    public static int total                         = 0;
    public static long start                        = 0;
    public static long end                          = 0;
    public static List<String> assertions_failed    = new ArrayList<>();
    public static int[] assertions_index            = new int[512];


    private static StackTraceElement[] getCallers() {
        return Thread.currentThread().getStackTrace();
    }
    protected static StackTraceElement getCaller(int index) {
        return getCallers()[index + 4];
    }


    public static void test(Object object) {
        if (Objects.isNull(object)) {
            throw new RuntimeException("The object is null");
        }

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(TestingClass.class))
            throw new RuntimeException("The class is not annotated with @TestingClass");


        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void start() {
        start = System.currentTimeMillis();
    }
    public static Object[] getResults() {
        end = System.currentTimeMillis();
        return new Object[] { passes, fails, total, end - start };
    }
    public static void print_results() {
        Object[] results = Assertions.getResults();
        String res_string = "==  "
                + "\033[92mPassed: \033[0m" + results[0]
                + "  |  \033[91mFailed: \033[0m" + results[1]
                + "  |  Total: " + results[2]
                + "  |  Pass Rate: " + Float.parseFloat(results[0].toString()) / Float.parseFloat(results[2].toString()) * 100 + "%"
                + "  |  Took: " + results[3] + "ms "
                + "  ==";


        StringBuilder lines = new StringBuilder();
        lines.append("=".repeat(res_string.length() - "\033[92m\033[0m\033[91m\033[0m".length()));

        System.out.println("\n" + lines);
        System.out.println(res_string);
        System.out.println(lines);

        if (!Assertions.assertions_failed.isEmpty() || Assertions.fails > 0) {
            System.out.println("Failed Assertions: ");
            for (String assertion : Assertions.assertions_failed)
                System.out.println("\t- " + assertion /* + "\t Assertion index: [ " + index + " ]" */);
        } else
            System.out.println("Everything Passed!");

    }

}
