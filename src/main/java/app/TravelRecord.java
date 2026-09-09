package app;

import java.time.LocalDateTime;

public class TravelRecord {
    private int id;
    private double speed;
    private double distance;
    private double timeTaken;
    private int travelTypeId;
    private LocalDateTime createdAt;

    public TravelRecord(double speed, double distance, double timeTaken, int travelTypeId) {
        this.speed = speed;
        this.distance = distance;
        this.timeTaken = timeTaken;
        this.travelTypeId = travelTypeId;
    }

    public TravelRecord(int id, double speed, double distance, double timeTaken,
                        int travelTypeId, LocalDateTime createdAt) {
        this.id = id;
        this.speed = speed;
        this.distance = distance;
        this.timeTaken = timeTaken;
        this.travelTypeId = travelTypeId;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public double getSpeed() { return speed; }
    public double getDistance() { return distance; }
    public double getTimeTaken() { return timeTaken; }
    public int getTravelTypeId() { return travelTypeId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}