package xyz.terrific.examples;

import xyz.terrific.testing.Assertions;
import xyz.terrific.testing.Test;
import xyz.terrific.testing.TestingClass;
import xyz.terrific.testing.expectations.*;

public class Example {

    public static void main(String[] args) {

        Assertions.start();

        Assertions.test(new SimpleTestExample());

        Assertions.print_results();

    }

}

@TestingClass
class SimpleTestExample {

    @Test
    public void exampleTest() {

        new ExpectEqual(1, 1);      // succeed
        new ExpectNotEqual(1, 2);                 // succeed

        new ExpectTrue(true);           // succeed
        /*  FAIL  */
        new ExpectFalse(true);          // fail

        new ExpectGreater(1, 3);            // succeed
        /*  FAIL  */
        new ExpectGreaterOrEqual(3, 1);     // fail

        /*  FAIL  */
        new ExpectSmaller(1, 3);               // fail
        new ExpectSmallerOrEqual(3, 1);        // succeed

    }

}
