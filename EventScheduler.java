package project;
import java.util.*;

class EventS {
    int start, end;
    public EventS(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class EventScheduler {
    public static void scheduleEvents(EventS[] events) {
        Arrays.sort(events, Comparator.comparingInt(e -> e.end));

        List<EventS> venue1 = new ArrayList<>();
        List<EventS> venue2 = new ArrayList<>();

        int endTime1 = 0;
        int endTime2 = 0;

        for (int i = 0; i < events.length; i++) {
            if (events[i].start >= endTime1) {
                venue1.add(events[i]);
                endTime1 = events[i].end;
            } else if (events[i].start >= endTime2) {
                venue2.add(events[i]);
                endTime2 = events[i].end;
            } else {
                System.out.println("Unable to schedule event (" + events[i].start + ", " + events[i].end + ") in any venue.");
            }
        }

        System.out.println("\nEvent Schedule for Venue 1:");
        for (int i = 0; i < venue1.size(); i++) {
            System.out.println("(" + venue1.get(i).start + ", " + venue1.get(i).end + ")");
        }

        System.out.println("\nEvent Schedule for Venue 2:");
        for (int i = 0; i < venue2.size(); i++) {
            System.out.println("(" + venue2.get(i).start + ", " + venue2.get(i).end + ")");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of events: ");
        int n = scanner.nextInt();
        EventS[] events = new EventS[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter start and end time for event " + (i + 1) + ": ");
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            events[i] = new EventS(start, end);
        }

        scanner.close();
        scheduleEvents(events);
    }
}
