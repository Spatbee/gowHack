package com.spatbee.gowhack;

public class ColorAverage {

    private double r, g, b, totalWeight;

    public ColorAverage() {
        r = 0;
        g = 0;
        b = 0;
        totalWeight = 0;
    }

    public double getR() {
        return r;
    }
    
    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public void addWeightedColorDatum(int r, int g, int b, double weight) {
        if(weight > 0) {
            this.r = (this.r * totalWeight + r * weight) / (totalWeight + weight);
            this.g = (this.g * totalWeight + g * weight) / (totalWeight + weight);
            this.b = (this.b * totalWeight + b * weight) / (totalWeight + weight);
            totalWeight += weight;
        }
    }

    public String toString() {
        return String.format("[%.2f,%.2f,%.2f]", r, g, b);
    }

    public double getDistanceTo(ColorAverage other) {
        return Math.sqrt(Math.pow(r - other.getR(), 2) + Math.pow(g - other.getG(), 2) + Math.pow(b - other.getB(), 2));
    }

}
