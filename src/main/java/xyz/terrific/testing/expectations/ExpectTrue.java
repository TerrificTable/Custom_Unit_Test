package xyz.terrific.testing.expectations;

import xyz.terrific.testing.Assertions;

import java.util.regex.Pattern;

public class ExpectTrue extends Assertions {

    public <T> ExpectTrue(boolean value) {
        total++;
        assertions_index[total] = total;


        String caller_class  = Pattern.compile(".*\\.").matcher(getCaller(0).getClassName()).replaceAll("");
        String caller_method = getCaller(0).getMethodName();

        if (!value) {
            System.out.println("\033[91m`" + caller_class + "/" + caller_method + "/" + this.getClass().getSimpleName() + "` Failed:\n \tExpected True Got: \n\t\t" + value + "\n\033[0m");
            assertions_failed.add("Failure in `" + caller_class + "/" + caller_method + "/" + this.getClass().getSimpleName() + "`: Expected True Got: " + value);
            fails++;
        } else {
            System.out.println("\033[92m`" + caller_class + "/" + caller_method + "/" + this.getClass().getSimpleName() + "` Passed\033[0m");
            passes++;
        }
    }

}
