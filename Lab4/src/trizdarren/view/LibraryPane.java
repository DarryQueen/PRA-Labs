package trizdarren.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LibraryPane extends JFrame {
    public static final String MOVIE_DROPDOWN_NAME = "Movie";
    public static final String MUSIC_DROPDOWN_NAME = "Music";

    public static final String SORT_TITLE = "Title";
    public static final String SORT_YEAR = "Release Year";
    public static final String SORT_QUALITY = "Quality";
    public static final String SORT_TRACKNAME = "Track Name";
    public static final String SORT_ARTIST = "Artist";

    private JPanel jpMoviePanel;
    private JPanel jpMusicPanel;
    private JPanel jpOtherPanel;

    private String[] movieSortOptions = {SORT_TITLE, SORT_YEAR, SORT_QUALITY};
    private String[] musicSortOptions = {SORT_TRACKNAME, SORT_ARTIST};
    private JComboBox<String> jcbMovieSort;
    private JComboBox<String> jcbMusicSort;

    private void setUI() {
        JPanel topPanel = new JPanel(); topPanel.setLayout(new BorderLayout());
        JPanel topHeader = new JPanel(); topHeader.setLayout(new BorderLayout());
        topHeader.add(new JLabel("Films"), BorderLayout.WEST);
        topHeader.add(jcbMovieSort, BorderLayout.EAST);
        topPanel.add(topHeader, BorderLayout.NORTH);
        topPanel.add(new JScrollPane(jpMoviePanel), BorderLayout.CENTER);

        JPanel middlePanel = new JPanel(); middlePanel.setLayout(new BorderLayout());
        JPanel middleHeader = new JPanel(); middleHeader.setLayout(new BorderLayout());
        middleHeader.add(new JLabel("Music"), BorderLayout.WEST);
        middleHeader.add(jcbMusicSort, BorderLayout.EAST);
        middlePanel.add(middleHeader, BorderLayout.NORTH);
        middlePanel.add(new JScrollPane(jpMusicPanel), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(); bottomPanel.setLayout(new BorderLayout());
        JPanel bottomHeader = new JPanel(); bottomHeader.setLayout(new BorderLayout());
        bottomHeader.add(new JLabel("Unclassified"), BorderLayout.WEST);
        bottomPanel.add(bottomHeader, BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(jpOtherPanel), BorderLayout.CENTER);

        this.add(topPanel);
        this.add(middlePanel);
        this.add(bottomPanel);
    }

    public LibraryPane(ActionListener al) {
        super("Media Library");

        this.setSize(500, 800);
        this.setLayout(new GridLayout(0, 1));

        jpMoviePanel = new JPanel();
        jpMusicPanel = new JPanel();
        jpOtherPanel = new JPanel();

        jcbMovieSort = new JComboBox<String>(movieSortOptions);
        jcbMovieSort.setName(MOVIE_DROPDOWN_NAME);
        jcbMovieSort.addActionListener(al);
        jcbMusicSort = new JComboBox<String>(musicSortOptions);
        jcbMusicSort.setName(MUSIC_DROPDOWN_NAME);
        jcbMusicSort.addActionListener(al);

        setUI();
    }

    private void setPane(JPanel pane, List<MediaPane.Item> itemList) {
        for (MediaPane.Item item : itemList) {
            pane.add(MediaPane.paneFromItem(item));
        }
        repaint(); revalidate();
    }
    public void setMovies(List<MediaPane.Item> movieList) {
        setPane(jpMoviePanel, movieList);
    }
    public void clearMovies() {
        jpMoviePanel.removeAll();
        repaint(); revalidate();
    }
    public void setMusic(List<MediaPane.Item> musicList) {
        setPane(jpMusicPanel, musicList);
    }
    public void clearMusic() {
        jpMusicPanel.removeAll();
        repaint(); revalidate();
    }
    public void setOther(List<MediaPane.Item> otherList) {
        setPane(jpOtherPanel, otherList);
    }
    public void clearOther() {
        jpOtherPanel.removeAll();
        repaint(); revalidate();
    }
}
