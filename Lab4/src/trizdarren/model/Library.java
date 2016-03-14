package trizdarren.model;

import java.util.LinkedList;
import java.util.List;

import generator.Media;
import generator.MediaGenerator;

public class Library {
    private List<MediaItem> movieList, musicList, unknownList;

    public Library() {
        movieList = new LinkedList<MediaItem>();
        musicList = new LinkedList<MediaItem>();
        unknownList = new LinkedList<MediaItem>();

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
                unknownList.add(item);
                break;
            }
        }
    }

    public List<MediaItem> getMovies() {
        return movieList;
    }
    public List<MediaItem> getMusic() {
        return musicList;
    }
    public List<MediaItem> getUnknown() {
        return unknownList;
    }
}
