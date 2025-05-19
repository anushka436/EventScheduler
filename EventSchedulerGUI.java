package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

class Event {
    int start, end;

    public Event(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class EventSchedulerGUI extends JFrame {
    private JTextField numEventsField;
    private JPanel inputPanel;
    private JButton submitButton, scheduleButton;
    private JTextArea resultArea;
    private Event[] events;

    public EventSchedulerGUI() {
        setTitle("Event Scheduler (2 Venues)");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Enter number of events:"));
        numEventsField = new JTextField(5);
        topPanel.add(numEventsField);
        submitButton = new JButton("Submit");
        topPanel.add(submitButton);
        add(topPanel, BorderLayout.NORTH);

        // Input panel
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 5, 5));
        add(new JScrollPane(inputPanel), BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        scheduleButton = new JButton("Schedule Events");
        scheduleButton.setEnabled(false);
        bottomPanel.add(scheduleButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Result area
        resultArea = new JTextArea(20, 25);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.EAST);

        // Action for Submit
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int n = Integer.parseInt(numEventsField.getText());
                    if (n <= 0) {
                        JOptionPane.showMessageDialog(null, "Enter a valid number of events.");
                        return;
                    }
                    events = new Event[n];
                    inputPanel.removeAll();

                    for (int i = 0; i < n; i++) {
                        inputPanel.add(new JLabel("Start " + (i + 1) + ":"));
                        inputPanel.add(new JTextField(5));
                        inputPanel.add(new JLabel("End " + (i + 1) + ":"));
                        inputPanel.add(new JTextField(5));
                    }

                    inputPanel.revalidate();
                    inputPanel.repaint();
                    scheduleButton.setEnabled(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Enter a valid number.");
                }
            }
        });

        // Action for Schedule
        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component[] components = inputPanel.getComponents();
                int index = 0;
                boolean valid = true;

                for (int i = 0; i < events.length; i++) {
                    try {
                        JTextField startField = (JTextField) components[index + 1];
                        JTextField endField = (JTextField) components[index + 3];
                        int start = Integer.parseInt(startField.getText());
                        int end = Integer.parseInt(endField.getText());

                        if (start >= end) {
                            JOptionPane.showMessageDialog(null, "Start must be less than end for event " + (i + 1));
                            valid = false;
                            break;
                        }

                        events[i] = new Event(start, end);
                        index += 4;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Enter valid numbers.");
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    String output = scheduleEventsTwoVenues(events);
                    resultArea.setText(output);
                }
            }
        });

        setVisible(true);
    }

    public static String scheduleEventsTwoVenues(Event[] events) {
        // Sort by end time
        Arrays.sort(events, Comparator.comparingInt(e -> e.end));

        StringBuilder result = new StringBuilder("Event Schedule for 2 venues:\n");

        int lastEndVenue1 = -1;
        int lastEndVenue2 = -1;

        // Normal for loop to iterate through events
        for (int i = 0; i < events.length; i++) {
            Event e = events[i];
            if (e.start >= lastEndVenue1) {
                result.append("Venue 1: (").append(e.start).append(", ").append(e.end).append(")\n");
                lastEndVenue1 = e.end;
            } else if (e.start >= lastEndVenue2) {
                result.append("Venue 2: (").append(e.start).append(", ").append(e.end).append(")\n");
                lastEndVenue2 = e.end;
            } else {
                result.append("Skipped: (").append(e.start).append(", ").append(e.end).append(")\n");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventSchedulerGUI());
    }
}
