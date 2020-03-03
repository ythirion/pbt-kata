package com.bil.katas.pbt;

import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.util.Random;

public class Calculator_specs {
    @Test
    public void when_I_add_1_to_3_I_expect_4() {
        Assert.assertEquals(4, Calculator.add(1, 3));
    }

    @Test
    public void when_I_add_2_to_2_I_expect_4() {

        Assert.assertEquals(4, Calculator.add(2, 2));
    }

    @Test
    public void when_I_add_minus_1_to_2_I_expect_3() {

        Assert.assertEquals(2, Calculator.add(-1, 3));
    }

    @Test
    public void when_I_add_2_to_3_I_expect_5() {

        Assert.assertEquals(5, Calculator.add(2, 3));
    }

    @Test
    public void when_I_add_two_numbers_the_result_should_not_depend_on_parameter_order() {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int randomX = random.nextInt();
            int randomY = random.nextInt();

            int result1 = Calculator.add(randomX, randomY);
            int result2 = Calculator.add(randomY, randomX);

            Assert.assertEquals(result1, result2);
        }
    }

    @Test
    public void adding_1_twice_is_the_same_as_adding_2_once() {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int randomX = random.nextInt();

            int result1 = Calculator.add(randomX, 1);
            result1 = Calculator.add(result1, 1);

            int result2 = Calculator.add(randomX, 2);

            Assert.assertEquals(result1, result2);
        }
    }

    @Test
    public void adding_0_is_the_same_than_doing_nothing() {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int randomX = random.nextInt();

            int result1 = Calculator.add(randomX, 0);
            int result2 = randomX;

            Assert.assertEquals(result1, result2);
        }
    }
}