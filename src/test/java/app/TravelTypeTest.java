package app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class TravelTypeTest {

    @Test
    public void testConstructorAndGetters() {
        TravelType type = new TravelType(1, "Car");
        assertEquals(1, type.getId());
        assertEquals("Car", type.getTypeName());
    }

    @Test
    public void testToString_returnsTypeName() {
        TravelType type = new TravelType(2, "Bicycle");
        assertEquals("Bicycle", type.toString());
    }
}