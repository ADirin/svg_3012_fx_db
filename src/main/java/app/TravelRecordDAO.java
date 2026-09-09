package app;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TravelRecordDAO {

    public void save(TravelRecord record) throws SQLException {
        String sql = "INSERT INTO travel_record (speed, distance, time_taken, travel_type_id) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, record.getSpeed());
            ps.setDouble(2, record.getDistance());
            ps.setDouble(3, record.getTimeTaken());
            ps.setInt(4, record.getTravelTypeId());
            ps.executeUpdate();
        }
    }

    public List<TravelRecord> getAllRecords() throws SQLException {
        List<TravelRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM travel_record ORDER BY created_at DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                records.add(new TravelRecord(
                        rs.getInt("id"),
                        rs.getDouble("speed"),
                        rs.getDouble("distance"),
                        rs.getDouble("time_taken"),
                        rs.getInt("travel_type_id"),
                        createdAt
                ));
            }
        }
        return records;
    }
}