import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoryTest {

    @Test
    void addDriver() {
        History history = new History();
        history.addDriver("Dan");
        history.addDriver("Dan");
        history.addDriver("Dan");
        history.addDriver("Dan");
        history.addDriver("Dan");
        history.addDriver("Dan");
        history.addDriver("Dan");
        assertEquals(1, history.map.size());
        history.addDriver("Lauren");
        assertEquals(2, history.map.size());
    }

    @Test
    void addTrip() {
        History history = new History();
        history.addTrip("Dan", "07:15", "07:45", 17.3f);
        history.addTrip("Dan", "06:12", "06:32", 21.8f);
        assertEquals(50, history.map.get("Dan").getTime());
        history.addTrip("Dan", "06:12", "06:13", 200f);
        history.addTrip("Dan", "06:12", "06:13", 5f);
        assertEquals(39.1f, history.map.get("Dan").getMiles());
        history.addTrip("Dan", "13:12", "14:32", 21.8f);
        assertEquals(130, history.map.get("Dan").getTime());
        history.addTrip("Dan", "23:15", "23:25", 17.3f);
        assertEquals(130, history.map.get("Dan").getTime());
        history.addTrip("Dan", "22:15", "23:15", 100f);
        assertEquals(190, history.map.get("Dan").getTime());
    }

    @Test
    void result() {
        History history = new History();
        history.addTrip("Dan", "07:15", "07:45", 17.3f);
        history.addTrip("Dan", "06:12", "06:32", 21.8f);
        history.result();
        assertEquals(39, Math.round(history.map.get("Dan").getMiles()));
        assertEquals(47, Math.round(history.getMPH(history.map.get("Dan").getTime(), history.map.get("Dan").getMiles())));
        history.addDriver("Lauren");
        assertEquals(0, Math.round(history.getMPH(history.map.get("Lauren").getTime(), history.map.get("Lauren").getMiles())));
        history.result();
    }

    @Test
    void getMPH() {
        History history = new History();
        assertEquals(120, history.getMPH(30, 60));
        assertEquals(54, history.getMPH(50, 45));
        assertEquals(40.625, history.getMPH(96, 65));
        assertEquals(33.75, history.getMPH(32, 18));
    }
}