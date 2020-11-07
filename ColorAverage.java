public class ColorAverage {

    private double r, b, g, totalWeight;

    public ColorAverage() {
        r = 0;
        b = 0;
        g = 0;
        totalWeight = 0;
    }

    public double getR() {
        return r;
    }
    
    public double getB() {
        return b;
    }

    public double getG() {
        return g;
    }

    public void addWeightedColorDatum(float r, float b, float g, float weight) {
        this.r = (this.r * totalWeight + r * weight) / (totalWeight + weight);
        this.b = (this.b * totalWeight + b * weight) / (totalWeight + weight);
        this.g = (this.g * totalWeight + g * weight) / (totalWeight + weight);
        totalWeight += weight;
    }

    public String toString() {
        return String.format("[%.2d,%.2d,%.2d]", r, b, g);
    }

    public double getDistanceTo(ColorAverage other) {
        return Math.sqrt(Math.pow(r - other.getR(), 2) + Math.pow(b - other.getB(), 2) + Math.pow(g - other.getG(), 2));
    }

}
