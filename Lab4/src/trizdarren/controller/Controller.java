package trizdarren.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import generator.Media;
import generator.MediaGenerator;
import trizdarren.model.MediaItem;
import trizdarren.view.Library;

public class Controller implements ActionListener {
    private List<MediaItem> movieList, musicList, otherList;
    private Library library;

    public Controller() {
        movieList = new LinkedList<MediaItem>();
        musicList = new LinkedList<MediaItem>();
        otherList = new LinkedList<MediaItem>();

        for (Media m : MediaGenerator.getMedia()) {
            MediaItem item = MediaItem.createMediaItem(m);
            switch (item.getType()) {
            case MOVIE:
                movieList.add(item);
                break;
            case MUSIC:
                musicList.add(item);
                break;
            case UNKNOWN:
                otherList.add(item);
                break;
            }
        }

        library = new Library(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {
        // Controller c = new Controller();

        for (Media m : MediaGenerator.getMedia()) {
            MediaItem mi = MediaItem.createMediaItem(m);
            // if (mi != null) System.out.println(mi.getInfoText());
        }
    }
}
