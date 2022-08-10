package xyz.terrific.testing.expectations;

import xyz.terrific.testing.Assertions;

import java.util.regex.Pattern;

public class ExpectNotEqual extends Assertions {

    public <T> ExpectNotEqual(T value_1, T value_2) {
        total++;
        assertions_index[total] = total;


        String caller_class  = Pattern.compile(".*\\.").matcher(getCaller(0).getClassName()).replaceAll("");
        String caller_method = getCaller(0).getMethodName();

        if (value_1.equals(value_2)) {
            System.out.println("\033[91m`" + caller_class + "/" + caller_method + "/" + this.getClass().getSimpleName() + "` Failed:\n \tDid not expect: \n\t\t" + value_1 + " \n\tGot: \n\t\t" + value_2 + "\n\033[0m");
            assertions_failed.add("Failure in `" + caller_class + "/" + caller_method + "/" + this.getClass().getSimpleName() + "`: Did not expect: " + value_1 + " \tGot: " + value_2);
            fails++;
        } else {
            System.out.println("\033[92m`" + caller_class + "/" + caller_method + "/" + this.getClass().getSimpleName() + "` Passed\033[0m");
            passes++;
        }
    }

}