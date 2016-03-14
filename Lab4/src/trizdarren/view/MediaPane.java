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

    public static MediaPane paneFromItem(Item item) {
        return new MediaPane(item.boldText, item.infoText, item.image);
    }

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
