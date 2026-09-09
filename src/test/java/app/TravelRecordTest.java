package app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class TravelRecordTest {

    @Test
    public void testConstructor_withoutIdAndTimestamp() {
        TravelRecord record = new TravelRecord(50, 100, 2.0, 1);

        assertEquals(50, record.getSpeed());
        assertEquals(100, record.getDistance());
        assertEquals(2.0, record.getTimeTaken());
        assertEquals(1, record.getTravelTypeId());
        assertNull(record.getCreatedAt()); // not set via this constructor
    }

    @Test
    public void testConstructor_withIdAndTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        TravelRecord record = new TravelRecord(5, 60, 120, 2.0, 1, now);

        assertEquals(5, record.getId());
        assertEquals(60, record.getSpeed());
        assertEquals(120, record.getDistance());
        assertEquals(2.0, record.getTimeTaken());
        assertEquals(1, record.getTravelTypeId());
        assertEquals(now, record.getCreatedAt());
    }
}