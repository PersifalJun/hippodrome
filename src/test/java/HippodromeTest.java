import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HippodromeTest {


    @Test
    public void hippodromeTestConstructorThrowsException(){
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->new Hippodrome(null));
        assertEquals("Horses cannot be null.",exception.getMessage());

    }
    @Test
    public void hippodromeEmptyList(){

        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()-> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.",exception.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i <=30; i++) {
            horses.add(new Horse("Pony number "+i,i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i <=50; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome(horses).move();

        for (Horse horse : horses){
            Mockito.verify(horse).move();
        }

    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i <=30; i++) {
            horses.add(new Horse("Pony number "+i,i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses.get(30),hippodrome.getWinner());
    }
}