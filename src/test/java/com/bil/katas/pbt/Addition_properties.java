package com.bil.katas.pbt;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class Addition_properties {
    @Property
    public void commutative(int randomX, int randomY) {
        int result1 = Calculator.add(randomX, randomY);
        int result2 = Calculator.add(randomY, randomX);

        Assert.assertEquals(result1, result2);
    }

    @Property
    public void associative(int randomX) {
        int result1 = Calculator.add(randomX, 1);
        result1 = Calculator.add(result1, 1);
        int result2 = Calculator.add(randomX, 2);

        Assert.assertEquals(result1, result2);
    }

    @Property
    public void identity(int randomX) {
        int result1 = Calculator.add(randomX, 0);

        Assert.assertEquals(result1, randomX);
    }
}

