package app.dao;

import app.db.DatabaseConnection;
import app.model.TravelType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TravelTypeDAO {

    public List<TravelType> getAllTypes() throws SQLException {
        List<TravelType> types = new ArrayList<>();
        String sql = "SELECT id, type_name FROM travel_type";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                types.add(new TravelType(rs.getInt("id"), rs.getString("type_name")));
            }
        }
        return types;
    }
}