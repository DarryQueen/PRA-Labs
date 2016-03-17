package trizdarren.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import trizdarren.model.Library;
import trizdarren.model.MediaItem;
import trizdarren.model.Movie;
import trizdarren.model.Music;
import trizdarren.model.Unknown;
import trizdarren.view.LibraryPane;
import trizdarren.view.MediaPane;

public class Controller implements ActionListener {
    private Library library;
    private LibraryPane libraryPane;

    /**
     * Construct a list of MediaPane.Item objects from the list of MediaItem.
     * The purpose of this method is to translate model objects to subviews.
     * @param items List of MediaItem objects to translate.
     * @return List of MediaPane.Item objects corresponding to given MediaItem objects.
     */
    private List<MediaPane.Item> getPaneList(List<MediaItem> items) {
        List<MediaPane.Item> paneList = new LinkedList<MediaPane.Item>();
        for (MediaItem item : items) {
            String bt = null, it = null;
            JLabel i = null;

            switch (item.getType()) {
            case MOVIE:
                Movie movie = (Movie) item;
                bt = movie.getName();
                it = movie.getYear() + " - " + movie.getQuality();
                i = movie.getImage();
                break;
            case MUSIC:
                Music music = (Music) item;
                bt = music.getName();
                it = music.getArtist();
                i = music.getImage();
                break;
            case UNKNOWN:
                Unknown unknown = (Unknown) item;
                bt = unknown.getName();
                it = MediaPane.DEFAULT_INFOTEXT;
                i = unknown.getImage();
                break;
            }

            paneList.add(new MediaPane.Item(bt, it, i));
        }
        return paneList;
    }

    /**
     * Standard constructor. Starts up the program; make sure to construct in main.
     */
    public Controller() {
        library = new Library();

        libraryPane = new LibraryPane(this);
        libraryPane.setMovies(getPaneList(library.getMovies()));
        libraryPane.setMusic(getPaneList(library.getMusic()));
        libraryPane.setOther(getPaneList(library.getUnknown()));

        libraryPane.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component component = (Component) e.getSource();

        if (component.getName() == LibraryPane.MOVIE_DROPDOWN_NAME) {
            JComboBox<String> dropdown = (JComboBox<String>) component;
            String sort = dropdown.getSelectedItem() + "";
            List<MediaItem> movieList = library.getMovies();
            Comparator comparator = null;

            switch (sort) {
            case LibraryPane.SORT_TITLE:
                comparator = new Comparator<MediaItem>() {
                    @Override
                    public int compare(MediaItem o1, MediaItem o2) {
                        Movie m1 = (Movie) o1; Movie m2 = (Movie) o2;
                        return m1.getSortName().compareTo(m2.getSortName());
                    }
                };
                break;
            case LibraryPane.SORT_YEAR:
                comparator = new Comparator<MediaItem>() {
                    @Override
                    public int compare(MediaItem o1, MediaItem o2) {
                        Movie m1 = (Movie) o1; Movie m2 = (Movie) o2;
                        return -1 * Integer.compare(m1.getYear(), m2.getYear());
                    }
                };
                break;
            case LibraryPane.SORT_QUALITY:
                comparator = new Comparator<MediaItem>() {
                    @Override
                    public int compare(MediaItem o1, MediaItem o2) {
                        Movie m1 = (Movie) o1; Movie m2 = (Movie) o2;
                        return -1 * Integer.compare(m1.getPixels(), m2.getPixels());
                    }
                };
                break;
            }

            Collections.sort(movieList, comparator);
            libraryPane.clearMovies();
            libraryPane.setMovies(getPaneList(movieList));
            return;
        }

        if (component.getName() == LibraryPane.MUSIC_DROPDOWN_NAME) {
            JComboBox<String> dropdown = (JComboBox<String>) component;
            String sort = dropdown.getSelectedItem() + "";
            List<MediaItem> musicList = library.getMusic();
            Comparator comparator = null;

            switch (sort) {
            case LibraryPane.SORT_TRACKNAME:
                comparator = new Comparator<MediaItem>() {
                    @Override
                    public int compare(MediaItem o1, MediaItem o2) {
                        Music m1 = (Music) o1; Music m2 = (Music) o2;
                        return m1.getSortName().compareTo(m2.getSortName());
                    }
                };
                break;
            case LibraryPane.SORT_ARTIST:
                comparator = new Comparator<MediaItem>() {
                    @Override
                    public int compare(MediaItem o1, MediaItem o2) {
                        Music m1 = (Music) o1; Music m2 = (Music) o2;
                        return m1.getSortArtist().compareTo(m2.getSortArtist());
                    }
                };
                break;
            }

            Collections.sort(musicList, comparator);
            libraryPane.clearMusic();
            libraryPane.setMusic(getPaneList(musicList));
            return;
        }
    }

    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
