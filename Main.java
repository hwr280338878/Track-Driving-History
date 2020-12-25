import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if(args.length > 0) {
            try {
                File myObj = new File(args[0]);
                if(!myObj.exists()) {
                    System.out.println("File doesn't exit!");
                }
                Scanner myReader = new Scanner(myObj);
                History history = new History();
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] s = data.split(" ");
                    if(s.length == 2 && s[0].equals("Driver")) {
                        history.addDriver(s[1]);
                    }
                    if(s.length == 5 && s[0].equals("Trip")) {
                        history.addTrip(s[1], s[2], s[3], Float.parseFloat(s[4]));
                    }
                }
                myReader.close();
                history.result();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}

class History{
    Map<String, Trip> map;

    public History() {
        map = new HashMap<>();
    }

    public void addDriver(String name) {
        if(!map.containsKey(name)) {
            map.put(name, new Trip());
        }
    }

    public void addTrip(String name, String startTime, String stopTime, float totalMiles) {
        map.putIfAbsent(name, new Trip());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        LocalTime t1 = LocalTime.parse(startTime);
        LocalTime t2 = LocalTime.parse(stopTime);
        long timeSlot = Duration.between(t1, t2).toMinutes();
        float mph = getMPH((int) timeSlot, totalMiles);
        if(mph < 5f || mph > 100f) {
            return;
        }
        Trip trip = map.get(name);
        trip.setMiles(totalMiles);
        trip.setTime(startTime, stopTime);
        map.put(name, trip);
    }

    public float getMPH(int time, float totalMiles){
        return totalMiles / (time / 60f);
    }

    public void result(){
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> (int) (map.get(b).getMiles() - map.get(a).getMiles()));
        pq.addAll(map.keySet());
        while(!pq.isEmpty()) {
            String driver = pq.poll();
            int totalTime = map.get(driver).getTime();
            float totalMiles = map.get(driver).getMiles();
            float mph = getMPH(totalTime, totalMiles);
            if(totalTime == 0) {
                System.out.println(driver + " : 0 miles");
            } else {
                System.out.println(driver + ": " + Math.round(totalMiles) + " miles " + " @ " + Math.round(mph) + " mph");
            }
        }
    }
}

class Driver {
    String name;

    public Driver(String name) {
        this.name = name;
    }
}

class Trip {
    private int time;
    private float miles;

    public Trip() {

    }

    public int getTime() {
        return time;
    }
    public void setTime(String start, String stop) {
        LocalTime t1 = LocalTime.parse(start);
        LocalTime t2 = LocalTime.parse(stop);
        long timeSlot = Duration.between(t1, t2).toMinutes();
        this.time += timeSlot;
    }

    public float getMiles() {
        return miles;
    }

    public void setMiles(float miles) {
        this.miles += miles;
    }
}
