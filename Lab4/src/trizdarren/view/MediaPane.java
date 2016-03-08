package trizdarren.view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MediaPane extends JPanel {
    private String boldText;
    private String infoText;
    private String imagePath;

    private JLabel jlBoldText;
    private JLabel jlInfoText;
    private JLabel jlImage;

    public MediaPane(String bt, String it, String ip) {
        super();
        this.setLayout(new BorderLayout());

        this.boldText = bt;
        this.infoText = it;
        this.imagePath = ip;

        jlBoldText = new JLabel(bt);
        jlInfoText = new JLabel(it);
        jlImage = new JLabel(new ImageIcon(ip));

        this.add(jlImage, BorderLayout.NORTH);
        this.add(jlBoldText, BorderLayout.CENTER);
        this.add(jlInfoText, BorderLayout.SOUTH);
    }
}
