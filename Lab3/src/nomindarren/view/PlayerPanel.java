package nomindarren.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nomindarren.controller.Controller;

/**
 * This class represents the player's profile panel.
 * It includes his name and profile image.
 *
 * @author Darren, Nomin
 */
public class PlayerPanel extends JPanel {
    /** Behind-the-scenes arbitrary names for name and image change actions. */
    public static final String NAME_CHANGE = "name";
    public static final String IMAGE_CHANGE = "image";

    private int id;

    private JTextField jtfName;
    private JButton jbImage;
    private ActionListener alListener;

    /**
     * Constructor for this panel.
     * @param n name of the player.
     * @param i ID of the player.
     * @param p path of the player's mugshot.
     * @param al the ActionListener to register our events.
     */
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

    /**
     * Change the player's name on the view.
     * @param n name of the player.
     */
    public void setPlayerName(String n) {
        jtfName.setText(n);
    }
    /**
     * Change the player's profile picture.
     * @param p path of the player's mugshot.
     */
    public void setPlayerPath(String p) {
        jbImage.setIcon(new ImageIcon(p));
        jbImage.setText(null);
        repaint(); revalidate();
    }
}
