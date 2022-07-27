import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

public class HippodromeTest {

    @Test
    public void whenNameNULL_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    public void whenParamNULL_thenExceptionMessageIs_HorsesCannotBeNull() {
        String message = null;
        try {
            new Hippodrome(null);
        }
        catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Horses cannot be null.", message);
    }

    @Test
    public void whenParamEmptyList_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void whenParamEmptyList_thenExceptionMessageIs_HorsesCannotBeEmpty() {
        String message = null;
        try {
            new Hippodrome(new ArrayList<>());
        }
        catch (Exception e) {
            message = e.getMessage();
        }
        assertEquals("Horses cannot be empty.", message);
    }

    @Test
    public void returnsSameList_AsPassesInConstructorParameter() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse_" + i, i));
        }
        assertEquals(horses, new Hippodrome(horses).getHorses());
    }

    @Test
    public void given_MoveMethod_should_runGivenTimes() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    public void returnHorseWithHighestDistance() {
        Horse mock = Mockito.mock(Horse.class);
        List<Horse> horses = Collections.nCopies(5, mock);
        Mockito.when(horses.get(1).getDistance()).thenReturn(14D);
        Mockito.when(horses.get(2).getDistance()).thenReturn(22D);
        Mockito.when(horses.get(3).getDistance()).thenReturn(2D);
        Mockito.when(horses.get(4).getDistance()).thenReturn(0D);
        Horse expectedHorse = horses.get(2);

        assertEquals(expectedHorse, new Hippodrome(horses).getWinner());
    }

}
