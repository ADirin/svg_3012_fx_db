package app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TravelCalculatorTest {

    // ---------- timeCal ----------

    @Test
    public void testTimeCal_normalValues() {
        assertEquals(2.0, TravelCalculator.timeCal(50, 100), 0.0001);
    }

    @Test
    public void testTimeCal_speedZero_returnsZero() {
        assertEquals(0, TravelCalculator.timeCal(0, 100), 0.0001);
    }

    @Test
    public void testTimeCal_distanceZero_returnsZero() {
        assertEquals(0, TravelCalculator.timeCal(50, 0), 0.0001);
    }

    @Test
    public void testTimeCal_negativeDistance_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> TravelCalculator.timeCal(50, -10));
    }

    @Test
    public void testTimeCal_negativeSpeed_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> TravelCalculator.timeCal(-50, 100));
    }

    // ---------- validateInputs ----------

    @Test
    public void testValidateInputs_validValues_noException() {
        assertDoesNotThrow(() -> TravelCalculator.validateInputs(50, 100));
    }

    @Test
    public void testValidateInputs_negativeSpeed_throwsException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> TravelCalculator.validateInputs(-10, 100));
        assertTrue(ex.getMessage().contains("Speed cannot be negative"));
    }

    @Test
    public void testValidateInputs_negativeDistance_throwsException() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> TravelCalculator.validateInputs(50, -10));
        assertTrue(ex.getMessage().contains("Distance cannot be negative"));
    }
}