package com.bil.katas.pbt;

import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import io.vavr.test.Arbitrary;
import io.vavr.test.Property;
import org.junit.Test;

public class Addition_properties_with_vavr_test {

    private final int size = 10_000, tries = 100;

    @Test
    public void commutative() {
        CheckedFunction2<Integer, Integer, Boolean> commutativity =
                (x, y) -> Calculator.add(x, y) == Calculator.add(y, x);

        Property.def("Addition is commutative")
                .forAll(Arbitrary.integer(), Arbitrary.integer())
                .suchThat(commutativity)
                .check(size, tries)
                .assertIsSatisfied();
    }

    @Test
    public void associative() {
        CheckedFunction1<Integer, Boolean> associativity = (x) -> {
            int result1 = Calculator.add(x, 1);
            result1 = Calculator.add(result1, 1);
            int result2 = Calculator.add(x, 2);

            return result1 == result2;
        };

        Property.def("Addition is associative")
                .forAll(Arbitrary.integer())
                .suchThat(associativity)
                .check(size, tries)
                .assertIsSatisfied();
    }

    @Test
    public void identity() {
        CheckedFunction1<Integer, Boolean> associativity =
                (x) -> Calculator.add(x, 0) == x;

        Property.def("0 is the identity")
                .forAll(Arbitrary.integer())
                .suchThat(associativity)
                .check(size, tries)
                .assertIsSatisfied();
    }
}


