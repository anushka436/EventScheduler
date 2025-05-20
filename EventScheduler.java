package project;

import java.util.*;

class EventS 
{
    int start, end;

    public EventS(int start, int end) 
    {
        this.start = start;
        this.end = end;
    }
}

public class EventScheduler 
{

    public static void scheduleEvents(EventS[] events) 
    {
        Arrays.sort(events, Comparator.comparingInt(e -> e.end));

        List<EventS> venue1 = new ArrayList<>();
        List<EventS> venue2 = new ArrayList<>();

        int endTime1 = 0;
        int endTime2 = 0;

        for (EventS event : events)
        {
            if (event.start >= endTime1)
            {
                venue1.add(event);
                endTime1 = event.end;
            } 
            else if (event.start >= endTime2) 
            {
                venue2.add(event);
                endTime2 = event.end;
            } 
            else {
                System.out.println("Unable to schedule event (" + event.start + ", " + event.end + ") in any venue.");
            }
        }

        System.out.println("\nEvent Schedule for Venue 1:");
        for (EventS e : venue1) 
        {
            System.out.println("(" + e.start + ", " + e.end + ")");
        }

        System.out.println("\nEvent Schedule for Venue 2:");
        for (EventS e : venue2) 
        {
            System.out.println("(" + e.start + ", " + e.end + ")");
        }
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of events: ");
        int n = scanner.nextInt();
        if (n <= 0) 
        {
            System.out.println("Number of events must be positive.");
            return;
        }

        EventS[] events = new EventS[n];
        for (int i = 0; i < n; i++) 
        {
            System.out.print("Enter start and end time for event " + (i + 1) + ": ");
            int start = scanner.nextInt();
            int end = scanner.nextInt();

            if (start < 0 || end < 0 || end < start) 
            {
                System.out.println("Invalid input! Start time must be <= end time and both must be non-negative.");
                return;
            }

            events[i] = new EventS(start, end);
        }

        scanner.close();
        scheduleEvents(events);
    }
}

