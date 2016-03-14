package trizdarren.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

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
    }

    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
