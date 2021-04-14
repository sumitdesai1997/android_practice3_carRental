package com.example.android_practice3_carrental;

public class Model {
    private String modelName;
    private String brandName;
    private String image;
    private double dRate;
    private long cMillage;

    public Model(String modelName, String brandName, String image, double dRate, long cMillage) {
        this.modelName = modelName;
        this.brandName = brandName;
        this.image = image;
        this.dRate = dRate;
        this.cMillage = cMillage;
    }

    public String getModelName() {
        return modelName;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getImage() {
        return image;
    }

    public double getdRate() {
        return dRate;
    }

    public long getcMillage() {
        return cMillage;
    }
}
