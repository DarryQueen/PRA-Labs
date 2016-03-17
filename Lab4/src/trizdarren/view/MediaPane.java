package trizdarren.view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MediaPane extends JPanel {
    public static final String DEFAULT_INFOTEXT = "Unclassified";

    private String boldText;
    private String infoText;

    private JLabel jlBoldText;
    private JLabel jlInfoText;
    private JLabel jlImage;

    /**
     * Constructs a single JPanel for the media item in context.
     * @param bt String bolded text to display.
     * @param it String subtext underneath bolded text to display.
     * @param i JLabel image representing the item.
     */
    public MediaPane(String bt, String it, JLabel i) {
        super();
        this.setLayout(new BorderLayout());

        this.boldText = bt;
        this.infoText = it;

        jlBoldText = new JLabel(bt);
        jlInfoText = new JLabel(it);
        jlImage = i;

        this.add(jlImage, BorderLayout.NORTH);
        this.add(jlBoldText, BorderLayout.CENTER);
        this.add(jlInfoText, BorderLayout.SOUTH);
    }

    /**
     * Construct a MediaPane from the given Item.
     * @param item Item representing contents of media.
     * @return MediaPane to display.
     */
    public static MediaPane paneFromItem(Item item) {
        return new MediaPane(item.boldText, item.infoText, item.image);
    }

    /**
     * Class representing contents of an item in the MediaPane.
     */
    public static class Item {
        public String boldText, infoText;
        public JLabel image;

        public Item(String bt, String it, JLabel i) {
            boldText = bt;
            infoText = it;
            image = i;
        }
    }
}
