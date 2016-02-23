package nomindarren.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nomindarren.controller.Controller;

public class PlayerPanel extends JPanel {
    private int id;

    private JLabel jlName;
    private JLabel jlImage;
    private JButton jbAddImage;
    private ActionListener alListener;

    public PlayerPanel(String n, int i, String p, ActionListener al) {
        super();
        this.id = i;
        this.alListener = al;
        setLayout(new GridLayout(0, 1));

        jlName = new JLabel(n);
        if (p == Controller.NULL_PATH) {
            jbAddImage = new JButton("+");
            jbAddImage.setName(this.id + "");
            this.add(jbAddImage);
        } else {
            jlImage = new JLabel(new ImageIcon(p));
            jlImage.setName(this.id + "");
            this.add(jlImage);
        }
        this.add(jlName);
    }

    public void setPlayerName(String n) {
        jlName.setText(n);
    }
    public void setPlayerPath(String p) {
        removeAll();
        jlImage.setIcon(new ImageIcon(p));

        this.add(jlImage);
        this.add(jlName);
        repaint(); revalidate();
    }
}
