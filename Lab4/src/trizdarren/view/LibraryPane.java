package trizdarren.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LibraryPane extends JFrame {
    private JPanel jpMoviePanel;
    private JPanel jpMusicPanel;
    private JPanel jpOtherPanel;

    public LibraryPane(ActionListener al) {
        super("Media Library");

        this.setSize(500, 800);
        this.setLayout(new GridLayout(0, 1));

        jpMoviePanel = new JPanel(); this.add(new JScrollPane(jpMoviePanel));
        jpMusicPanel = new JPanel(); this.add(new JScrollPane(jpMusicPanel));
        jpOtherPanel = new JPanel(); this.add(new JScrollPane(jpOtherPanel));
    }

    private void setPane(JPanel pane, List<MediaPane.Item> itemList) {
        for (MediaPane.Item item : itemList) {
            pane.add(MediaPane.paneFromItem(item));
        }
    }
    public void setMovies(List<MediaPane.Item> movieList) {
        setPane(jpMoviePanel, movieList);
    }
    public void setMusic(List<MediaPane.Item> musicList) {
        setPane(jpMusicPanel, musicList);
    }
    public void setOther(List<MediaPane.Item> otherList) {
        setPane(jpOtherPanel, otherList);
    }
}
