import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SwingDigitalClock extends JFrame {
    private JLabel clockLabel;
    private Calendar calendar;
    private String timeFormat;

    public SwingDigitalClock(String timeFormat) {
        this.timeFormat = timeFormat;
        calendar = Calendar.getInstance();

        // Set up the frame
        setTitle("Swing Digital Clock");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Clock display panel
        JPanel clockPanel = new JPanel();
        clockLabel = new JLabel();
        clockLabel.setFont(new Font("Arial", Font.BOLD, 40));
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateClock();
        clockPanel.add(clockLabel);

        // Button panel for user interaction
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addHourButton = new JButton("Add Hour");
        JButton addMinuteButton = new JButton("Add Minute");
        JButton addSecondButton = new JButton("Add Second");

        addHourButton.addActionListener(e -> adjustTime(Calendar.HOUR_OF_DAY, 1));
        addMinuteButton.addActionListener(e -> adjustTime(Calendar.MINUTE, 1));
        addSecondButton.addActionListener(e -> adjustTime(Calendar.SECOND, 1));

        buttonPanel.add(addHourButton);
        buttonPanel.add(addMinuteButton);
        buttonPanel.add(addSecondButton);

        // Format selection dropdown
        String[] formatOptions = {"12-hour format", "24-hour format"};
        JComboBox<String> formatBox = new JComboBox<>(formatOptions);
        formatBox.addActionListener(e -> updateTimeFormat(formatBox.getSelectedIndex()));
        buttonPanel.add(formatBox);

        // Add panels to the frame
        add(clockPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Start the clock
        new Timer(1000, e -> tick()).start();
    }

    private void tick() {
        calendar.add(Calendar.SECOND, 1);
        updateClock();
    }

    private void updateClock() {
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
        clockLabel.setText(formatter.format(calendar.getTime()));
    }

    private void adjustTime(int field, int amount) {
        calendar.add(field, amount);
        updateClock();
    }

    private void updateTimeFormat(int selectedIndex) {
        timeFormat = selectedIndex == 0 ? "hh:mm:ss a" : "HH:mm:ss";
        updateClock();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SwingDigitalClock clock = new SwingDigitalClock("HH:mm:ss");
            clock.setVisible(true);
        });
    }
}