import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {

    @Test
    void getTime() {
        Trip trip = new Trip();
        assertEquals(0, trip.getTime());
    }

    @Test
    void setTime() {
        Trip trip = new Trip();
        trip.setTime("07:10", "08:00");
        assertEquals(50, trip.getTime());
        trip.setTime("08:50", "09:00");
        assertEquals(60, trip.getTime());
        trip.setTime("08:50", "09:10");
        assertEquals(80, trip.getTime());
        trip.setTime("15:50", "16:00");
        assertEquals(90, trip.getTime());
    }

    @Test
    void getMiles() {
        Trip trip = new Trip();
        assertEquals(0, trip.getMiles());
    }

    @Test
    void setMiles() {
        Trip trip = new Trip();
        trip.setMiles(654.151f);
        assertEquals(654.151f, trip.getMiles());
        trip.setMiles(0.121f);
        assertEquals(654.272f, trip.getMiles());
        trip.setMiles(0.111f);
        assertEquals(654.383f , trip.getMiles());
    }
}