import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;

public class HorseTest {

    @Test
    public void whenNameNULL_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 11));
    }

    @Test
    public void whenNameNULL_thenExceptionMessageIs_NameCannotBeNull() {
        String message = null;
        try {
            new Horse(null, 11);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        assertEquals("Name cannot be null.", message);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void whenNameSpaceOrEmpty_thenThrowIllegalArgumentException(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 11));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void whenNameisSpaceOrEmpty_thenExceptionMessageIs_NameCannotBeBlank(String name) {
        String message = null;
        try {
            new Horse(name, 11);
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Name cannot be blank.", message);
    }

    @Test
    public void whenSpeedIsNegative_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", -11));
    }

    @Test
    public void whenSpeedIsNegative_thenExceptionMessageIs_SpeedCannotBeNegative() {
        String message = null;
        try {
            new Horse("Horse", -11);
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Speed cannot be negative.", message);
    }

    @Test
    public void whenDistanceIsNegative_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", 11, -8));
    }

    @Test
    public void whenDistanceIsNegative_thenExceptionMessageIs_DistanceCannotBeNegative() {
        String message = null;
        try {
            new Horse("Horse", 11, -8);
        } catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Distance cannot be negative.", message);
    }

    @Test
    public void returnsName() {
        String expected = "Horse Name";
        Horse horse = new Horse(expected, 11);
        assertEquals(expected, horse.getName());
    }

    @Test
    public void returnsSpeed() {
        int expected = 11;
        Horse horse = new Horse("Horse", expected);
        assertEquals(expected, horse.getSpeed());
    }

    @Test
    public void returnsDistance() {
        int expected = 22;
        Horse horse = new Horse("Horse", 11, expected);
        assertEquals(expected, horse.getDistance());
    }

    @Test
    public void returnsZeroForDistance_WhenCreatedObjectWithTwoParameters() {
        Horse horse = new Horse("Horse", 11);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void checkIfMethodMoveInvokes_getRandomDoublemethod() {
        try (MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class)) {
            new Horse("Horse Name", 11).move();
            mockStatic.verify(() -> Horse.getRandomDouble(anyDouble(), anyDouble()));
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"2, 22, 0.2",
                        "3, 12, 12",
                        "3.3, 0, 0",
                        "0, 0, 0",
                        "5, 5, 5",})
    public void given(double speed, double distance, double result) {
        try (MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class)) {
            mockStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(result);
            Horse horse = new Horse("Horse Name", speed, distance);
            double expected = distance + speed * Horse.getRandomDouble(0.2, 0.9);
            horse.move();
            assertEquals(expected, horse.getDistance());
        }
    }
}
