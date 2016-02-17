package narmeendarren;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSlider;

/**
 * This class represents the camera abstraction.
 *
 * @author Darren, Narmeen
 */
public class Camera extends JSlider {
    /** Amount of time to sleep between rotations, in milliseconds. */
    private final static int SLEEP_TIME = 1000;

    /** Lazily loaded label map. */
    private static Dictionary<Integer, JComponent> mLabelMap = null;

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
     * @param labelMap dictionary of labels for each integer value.
     */
    public Camera() {
        super(0, 15, 0);
        this.setLabelTable(getLabelMap());
        this.setPaintLabels(true);
    }

    /**
     * Move the camera to point to the given positions.
     * Between each rotation, there is a delay of {@value #SLEEP_TIME} milliseconds.
     * @param positions array of ints to rotate towards.
     */
    public void rotate(int[] positions) {
        for (int i : positions) {
            if (i < 16) {
                this.setValue(i);
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch(Exception e) {}
            }
        }
    }
}
