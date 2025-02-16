import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class HorseTest {


    @Test
    void testConstructorThrowsExceptionOnFirstParam() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null,1) // Передаем null первым параметром
        );

        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            ", 30, 1,  3",
            "\n, 1, 3",
            "\t 1, 3",
            "\b 1, 3",
            "\f 1, 3",
            "\' 1, 3",
            "\" 1, 3",
            "\\ 1, 3",

    })
    void testConstructorThrowsExceptionOnParameters() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("",1) // Передаем null первым параметром
        );

        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void testConstructorThrowsExceptionOnSecondParam() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("s",-1.0)
        );

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    void testConstructorThrowsExceptionOnThirdParam() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                ()-> new Horse("s",1.0,-1)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    void getName() {
        Horse horse = new Horse("Pony", 1,3);
        assertEquals("Pony",horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("Pony", 1,3);
        assertEquals(1,horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("Pony", 1,3);
        assertEquals(3,horse.getDistance());

    }
    @Test
    void getZeroDistance() {
        Horse horse = new Horse("Pony", 1);
        assertEquals(0,horse.getDistance());

    }

    @Test
    void moveWithVerifyParameters() {
        try(MockedStatic<Horse> mockHorseStatic = Mockito.mockStatic(Horse.class)){
            new Horse("Pony",1).move();
            mockHorseStatic.verify(() -> Horse.getRandomDouble(2.0d,9.0d));

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    @Test
    void moveWithCheckingFormula(){
        try(MockedStatic<Horse> mockHorseStatic = Mockito.mockStatic(Horse.class)){

           Horse horse =  new Horse("Pony",1);
           when(Horse.getRandomDouble(0.2,0.9)).thenReturn(0.5);
           Assertions.assertEquals(0.5,Horse.getRandomDouble(0.2,0.9));



        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}