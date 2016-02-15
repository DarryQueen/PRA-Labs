package narmeendarren;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextArea;

/**
 * This class represents the camera communication GUI from the Martian.
 *
 * @author Darren, Narmeen
 */
public class Communication {
    /** Amount of time to sleep between camera rotations, in milliseconds. */
    private final static int SLEEP_TIME = 1000;
    /** Name of the ASCII table file. */
    private final static String ASCII_FILENAME = "ascii_table.csv";

    /** Store of contents of ASCII table. */
    private static Dictionary<String, String> mHexMap = null;

    /** JFrame title. */
    private final static String TITLE_DEFAULT = "Rover Camera";
    private final static String TITLE_SENDING = "Sending...";

    private static Dictionary<Integer, JComponent> mLabelMap = null;
    private static JFrame jfFrame;
    private static JSlider jsCamera;
    private static JTextArea jtaMessage;
    private static JButton jbSend;

    /**
     * Create the ActionListener for the sending JButton.
     * Must run any graphics-related updates in a new thread.
     */
    private static ActionListener jbalSend = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    // Change title, clear text:
                    jfFrame.setTitle(TITLE_SENDING);
                    String message = jtaMessage.getText();
                    jtaMessage.setText(null);

                    int[] positions = stringToHex(message);
                    moveCamera(positions);

                    // Set the title back:
                    jfFrame.setTitle(TITLE_DEFAULT);
                }
            };

            new Thread(r).start();
        }
    };

    /**
     * Grabs the ASCII table store. If it is not initialized, do so lazily.
     * @return Dictionary mapping of character to ASCII hex value, in String format.
     */
    private static Dictionary<String, String> getAsciiTable() {
        if (mHexMap != null) {
            return mHexMap;
        }
        mHexMap = new Hashtable<String, String>();

        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new FileReader(ASCII_FILENAME));

            // We don't care about the header.
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] cells = line.split(",");
                mHexMap.put(cells[4], cells[2]);
            }
        } catch(Exception e) {}

        return mHexMap;
    }

    /**
     * Grabs the label mapping for the camera JSlider. If it is not initialized, do so lazily.
     * @return Dictionary mapping of int (between 0 and 15 inclusive) to its hex value.
     */
    private static Dictionary<Integer, JComponent> getLabelMap() {
        if (mLabelMap != null) {
            return mLabelMap;
        }

        mLabelMap = new Hashtable<Integer, JComponent>();
        for (int i = 0; i < 10; i++) {
            mLabelMap.put(i, new JLabel(i + ""));
        }
        mLabelMap.put(10, new JLabel("A"));
        mLabelMap.put(11, new JLabel("B"));
        mLabelMap.put(12, new JLabel("C"));
        mLabelMap.put(13, new JLabel("D"));
        mLabelMap.put(14, new JLabel("E"));
        mLabelMap.put(15, new JLabel("F"));

        return mLabelMap;
    }

    /**
     * @param h char to convert.
     * @return int representation of h.
     */
    private static int hexToInt(char h) {
        switch(h) {
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case 'A': return 10;
            case 'B': return 11;
            case 'C': return 12;
            case 'D': return 13;
            case 'E': return 14;
            case 'F': return 15;
            default: return -1;
        }
    }

    /**
     * Convert a String to an array of ints representing their hex values.
     * @param s String to convert.
     * @return array of ints, coupled together to form the hex value of the chars of the String.
     */
    private static int[] stringToHex(String s) {
        int[] positions = new int[s.length() * 2];
        for (int i = 0; i < s.length(); i++) {
            String c = s.charAt(i) + "";
            String h = getAsciiTable().get(c);

            // Prepend a 0 if it the hex is less than 16:
            if (h.length() < 2) {
                h = "0" + h;
            }

            for (int j = 0; j < h.length(); j++) {
                char ch = h.charAt(j);
                positions[2 * i + j] = hexToInt(ch);
            }
        }

        return positions;
    }

    /**
     * Move the camera to point to the given positions.
     * Between each rotation, there is a delay of {@value #SLEEP_TIME} milliseconds.
     * @param positions array of ints to rotate towards.
     */
    public static void moveCamera(int[] positions) {
        for (int i : positions) {
            if (i < 16) {
                jsCamera.setValue(i);
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch(Exception e) {}
            }
        }
    }

    public static void main(String args[]) {
        getAsciiTable();

        // JFrame setup:
        jfFrame = new JFrame(TITLE_DEFAULT);
        jfFrame.setSize(600, 200);
        jfFrame.setLayout(new BorderLayout());

        jsCamera = new JSlider(0, 15);
        jsCamera.setLabelTable(getLabelMap());
        jsCamera.setPaintLabels(true);
        jfFrame.add(jsCamera, BorderLayout.NORTH);

        jtaMessage = new JTextArea();
        jfFrame.add(jtaMessage);

        jbSend = new JButton("Send");
        jbSend.addActionListener(jbalSend);
        jfFrame.add(jbSend, BorderLayout.SOUTH);

        jfFrame.setVisible(true);
        jfFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
