package eventsc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

class EventS {
    int start, end;
    public EventS(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class EventSchedulerGUI extends JFrame {
    private final DefaultTableModel inputModel = new DefaultTableModel(new String[]{"Start", "End"}, 0);
    private final DefaultTableModel venue1Model = new DefaultTableModel(new String[]{"Start", "End"}, 0);
    private final DefaultTableModel venue2Model = new DefaultTableModel(new String[]{"Start", "End"}, 0);

    private final JTextField startField = new JTextField(5);
    private final JTextField endField = new JTextField(5);
    private final List<EventS> events = new ArrayList<>();

    public EventSchedulerGUI() {
        setTitle("🌸 Event Scheduler");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(255, 240, 245)); // light pink background
        setLayout(new BorderLayout(10, 10));

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(255, 228, 225));
        inputPanel.add(new JLabel("Start Time:"));
        inputPanel.add(startField);
        inputPanel.add(new JLabel("End Time:"));
        inputPanel.add(endField);

        JButton addButton = new JButton("➕ Add Event");
        addButton.addActionListener(e -> addEvent());
        inputPanel.add(addButton);

        JButton scheduleButton = new JButton("📅 Schedule Events");
        scheduleButton.addActionListener(e -> scheduleEvents());
        inputPanel.add(scheduleButton);

        add(inputPanel, BorderLayout.NORTH);

        // Tables
        JTable inputTable = new JTable(inputModel);
        JTable venue1Table = new JTable(venue1Model);
        JTable venue2Table = new JTable(venue2Model);

        JScrollPane inputScroll = new JScrollPane(inputTable);
        JScrollPane v1Scroll = new JScrollPane(venue1Table);
        JScrollPane v2Scroll = new JScrollPane(venue2Table);

        inputScroll.setBorder(BorderFactory.createTitledBorder("📝 Input Events"));
        v1Scroll.setBorder(BorderFactory.createTitledBorder("🏛 Venue 1"));
        v2Scroll.setBorder(BorderFactory.createTitledBorder("🏛 Venue 2"));

        JPanel tablesPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        tablesPanel.add(inputScroll);
        tablesPanel.add(v1Scroll);
        tablesPanel.add(v2Scroll);
        tablesPanel.setBackground(new Color(255, 240, 245));

        add(tablesPanel, BorderLayout.CENTER);
    }

    private void addEvent() {
        try {
            int start = Integer.parseInt(startField.getText());
            int end = Integer.parseInt(endField.getText());
            if (start >= end) {
                JOptionPane.showMessageDialog(this, "Start time must be less than end time!");
                return;
            }
            events.add(new EventS(start, end));
            inputModel.addRow(new Object[]{start, end});
            startField.setText("");
            endField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid integers for start and end times.");
        }
    }

    private void scheduleEvents() {
        venue1Model.setRowCount(0);
        venue2Model.setRowCount(0);

        List<EventS> sortedEvents = new ArrayList<>(events);
        sortedEvents.sort(Comparator.comparingInt(e -> e.end));

        List<EventS> venue1 = new ArrayList<>();
        List<EventS> venue2 = new ArrayList<>();

        int endTime1 = 0, endTime2 = 0;
        for (EventS e : sortedEvents) {
            if (e.start >= endTime1) {
                venue1.add(e);
                endTime1 = e.end;
                venue1Model.addRow(new Object[]{e.start, e.end});
            } else if (e.start >= endTime2) {
                venue2.add(e);
                endTime2 = e.end;
                venue2Model.addRow(new Object[]{e.start, e.end});
            } else {
                JOptionPane.showMessageDialog(this,
                    "Unable to schedule event (" + e.start + ", " + e.end + ") in any venue.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EventSchedulerGUI gui = new EventSchedulerGUI();
            gui.setVisible(true);
        });
    }
}

