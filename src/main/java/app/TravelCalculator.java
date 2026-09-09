package app;

public class TravelCalculator {

    public static void validateInputs(double speed, double distance) {
        if (speed < 0) {
            throw new IllegalArgumentException("Speed cannot be negative: " + speed);
        }
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative: " + distance);
        }
    }

    public static double timeCal(double speed, double distance) {
        validateInputs(speed, distance);
        if (distance == 0 || speed == 0) {
            return 0;
        }
        return distance / speed;
    }
}