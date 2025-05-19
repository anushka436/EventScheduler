🗓️ Event Scheduler (2 Venues)

This project is a Java-based **event scheduling system** that uses a **greedy algorithm** to allocate events to two venues in a way that avoids time conflicts. It comes with both a **console version** and a **graphical user interface (GUI)** version using Java Swing.

---

## 📌 Features

- Schedules events to **2 venues** without overlapping times.
- Skips events that can't fit in either venue.
- Two versions:
  - ✅ Console version (`EventScheduler.java`)
  - ✅ GUI version using Swing (`EventSchedulerGUI.java`)
- Input validation and real-time feedback in GUI.

---

## 🧠 How It Works

The program:
1. Sorts all events by their end time.
2. Iterates through events and assigns each one to the first available venue (venue 1 or 2) where it doesn't overlap with the last scheduled event.
3. Skips any event that can't be scheduled in either venue.

This follows the **activity selection greedy strategy**.
