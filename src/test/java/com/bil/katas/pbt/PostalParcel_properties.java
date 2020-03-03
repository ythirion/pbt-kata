package com.bil.katas.pbt;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class PostalParcel_properties {
    @Property
    public void delivery_costs_should_be_max_when_weight_is_greater_than_20(
            String uuid,
            @InRange(minInt = 21) int weight) {
        PostalParcel postalParcel = new PostalParcel(uuid, weight);
        Assert.assertEquals(PostalParcel.MAX_DELIVERY_COSTS, postalParcel.calculateDeliveryCosts(), 0);
    }

    @Property(trials = 20)
    public void delivery_costs_should_be_min_when_weight_is_less_than_or_equal_to_20(
            String uuid,
            @InRange(minInt = 1, maxInt = 20) int weight) {
        PostalParcel postalParcel = new PostalParcel(uuid, weight);
        Assert.assertEquals(PostalParcel.MIN_DELIVERY_COSTS, postalParcel.calculateDeliveryCosts(), 0);
    }

    @Property
    public void should_throw_illegal_argument_exception_when_weight_is_below_one(
            String uuid,
            @InRange(maxInt = 0) int weight) {

        IllegalArgumentException illegalArgumentException = null;

        try {
            PostalParcel postalParcel = new PostalParcel(uuid, weight);
        } catch (IllegalArgumentException e) {
            illegalArgumentException = e;
        }
        Assert.assertNotNull(illegalArgumentException);
    }
}