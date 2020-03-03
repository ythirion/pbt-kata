package com.bil.katas.pbt;

public class PostalParcel {
    public static final double MAX_DELIVERY_COSTS = 4.99;
    public static final double MIN_DELIVERY_COSTS = 1.99;

    private int weight;
    private String uuid;

    public PostalParcel(String uuid, int weight) {
        if(weight < 0) {
            throw new IllegalArgumentException("Grams not acceptable, less than 0.");
        }
        this.uuid = uuid;
        this.weight = weight;
    }

    public double calculateDeliveryCosts() {
        if (weight > 20) {
            return MAX_DELIVERY_COSTS;
        }
        return MIN_DELIVERY_COSTS;
    }
}

