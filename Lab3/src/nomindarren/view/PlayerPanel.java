package nomindarren.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nomindarren.controller.Controller;

public class PlayerPanel extends JPanel {
    public static final String NAME_CHANGE = "name";
    public static final String IMAGE_CHANGE = "image";

    private int id;

    private JTextField jtfName;
    private JButton jbImage;
    private ActionListener alListener;

    public PlayerPanel(String n, int i, String p, ActionListener al) {
        super();
        this.id = i;
        this.alListener = al;
        setLayout(new BorderLayout());

        jtfName = new JTextField(n);
        jtfName.addActionListener(this.alListener);
        jtfName.setName(NAME_CHANGE + " " + this.id);

        jbImage = new JButton();
        jbImage.setName(IMAGE_CHANGE + " " + this.id);
        jbImage.addActionListener(this.alListener);
        if (p == Controller.NULL_PATH) {
            jbImage.setText("+");
        } else {
            jbImage.setIcon(new ImageIcon(p));
        }

        this.add(jbImage, BorderLayout.CENTER);
        this.add(jtfName, BorderLayout.SOUTH);
    }

    public void setPlayerName(String n) {
        jtfName.setText(n);
    }
    public void setPlayerPath(String p) {
        jbImage.setIcon(new ImageIcon(p));
        jbImage.setText(null);
        repaint(); revalidate();
    }
}
