package trizdarren.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Library extends JFrame {
    private JPanel jpMoviePanel;
    private JPanel jpMusicPanel;
    private JPanel jpOtherPanel;

    public Library(ActionListener al) {
        super("Media Library");

        this.setSize(500, 800);
        this.setLayout(new BorderLayout());

        jpMoviePanel = new JPanel(); this.add(jpMoviePanel, BorderLayout.NORTH);
        jpMusicPanel = new JPanel(); this.add(jpMusicPanel, BorderLayout.CENTER);
        jpOtherPanel = new JPanel(); this.add(jpOtherPanel, BorderLayout.SOUTH);
    }
}
