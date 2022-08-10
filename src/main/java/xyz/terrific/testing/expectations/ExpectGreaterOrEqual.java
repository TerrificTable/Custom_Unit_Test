package xyz.terrific.testing.expectations;

import xyz.terrific.testing.Assertions;

import java.util.regex.Pattern;

public class ExpectGreaterOrEqual extends Assertions {

    public <T> ExpectGreaterOrEqual(T expected, T actual) {
        total++;
        assertions_index[total] = total;


        String caller_class  = Pattern.compile(".*\\.").matcher(getCaller(0).getClassName()).replaceAll("");
        String caller_method = getCaller(0).getMethodName();


        if (!((int) expected >= (int) actual)) {
            System.out.println("\033[91m`" + caller_class + "/" + caller_method + "/" + this.getClass().getSimpleName() + "` Failed:\n \tExpected greater than or equal to: \n\t\t" + expected + " \n\tGot: \n\t\t" + actual + "\n\033[0m");
            assertions_failed.add("Failure in `" + caller_class + "/" + caller_method + "/" + this.getClass().getSimpleName() + "`: Expected greater than or equal to: " + expected + " \tGot: " + actual);
            fails++;
        } else {
            System.out.println("\033[92m`" + caller_class + "/" + caller_method + "/" + this.getClass().getSimpleName() + "` Passed\033[0m");
            passes++;
        }
    }

}
