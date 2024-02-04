import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Text Box");

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create Text field for text input
        JTextField textField = new JTextField();
        textField.setSize(500, 50);


        // Create menu with menu items
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        // Create date time menu item
        final JMenuItem dateTimeMenuItem = new JMenuItem("Print Date and Time");
        dateTimeMenuItem.addActionListener((event) -> {
            var dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            var now = LocalDateTime.now();

            var currentText = textField.getText();
            textField.setText(dtf.format(now));
        });

        // Create save menu item
        final JMenuItem saveMenuItem = new JMenuItem("Save Text Log");
        saveMenuItem.addActionListener((event) -> {
            var filePath = "C:\\temp\\log.txt";
            try {
                // Write to file
                var file = new File(filePath);
                file.createNewFile();

                var fileWriter = new FileWriter(filePath);
                var printWriter = new PrintWriter(fileWriter);

                printWriter.println(textField.getText());

                printWriter.close();
                fileWriter.close();

                JOptionPane.showMessageDialog(frame, "Saved to file.");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to save to file.");
            }
        });

        // Create randomize color menu item
        final JMenuItem backgroundColorMenuItem = new JMenuItem("Randomize");
        backgroundColorMenuItem.addActionListener((event) -> {
            var color = new Color(
                    (int) (Math.random() * 128),
                    255,
                    (int) (Math.random() * 128)
            );
            frame.getContentPane().setBackground(color);
            backgroundColorMenuItem.setText(
                    "Randomize: r"
                            + color.getRed() + " g"
                            + color.getGreen() + " b"
                            + color.getBlue()
            );
        });

        // Create quit menu item
        final JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener((event) -> System.exit(0));

        // Add menu items to menu
        menu.add(dateTimeMenuItem);
        menu.add(saveMenuItem);
        menu.add(backgroundColorMenuItem);
        menu.add(quitMenuItem);

        // Add menu bar to frame
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        // Add other components
        frame.add(textField);

        // Make the frame visable
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }
}