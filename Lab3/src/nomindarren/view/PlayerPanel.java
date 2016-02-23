package nomindarren.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nomindarren.controller.Controller;

public class PlayerPanel extends JPanel {
    public static final String NAME_CHANGE = "name";
    public static final String IMAGE_CHANGE = "image";

    private int id;

    private JTextField jtfName;
    private JLabel jlImage;
    private JButton jbAddImage;
    private ActionListener alListener;

    public PlayerPanel(String n, int i, String p, ActionListener al) {
        super();
        this.id = i;
        this.alListener = al;
        setLayout(new GridLayout(0, 1));

        jtfName = new JTextField(n);
        jtfName.addActionListener(this.alListener);
        jtfName.setName(NAME_CHANGE + " " + this.id);
        if (p == Controller.NULL_PATH) {
            jbAddImage = new JButton("+");
            jbAddImage.setName(this.id + "");
            this.add(jbAddImage);
        } else {
            jlImage = new JLabel(new ImageIcon(p));
            jlImage.setName(this.id + "");
            this.add(jlImage);
        }
        this.add(jtfName);
    }

    public void setPlayerName(String n) {
        jtfName.setText(n);
    }
    public void setPlayerPath(String p) {
        removeAll();
        jlImage.setIcon(new ImageIcon(p));

        this.add(jlImage);
        this.add(jtfName);
        repaint(); revalidate();
    }
}
