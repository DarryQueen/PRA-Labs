package trizdarren.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;

import generator.Media;

public abstract class MediaItem {
    private static final String FILENAME_PATTERN = "(.+?)\\.[^\\.]*$";
    private static final String MOVIE_PATTERN = "\\.(flv|gif|mkv|mpeg|mpg|mov)$";
    private static final String MUSIC_PATTERN = "\\.(aiff|aac|aax|oog|wav|wma)$";

    public enum Type {
        MOVIE, MUSIC, UNKNOWN
    }

    private JLabel image;

    public MediaItem(Media m) {
        this.image = m.getImage();
    }

    public abstract Type getType();
    public abstract String getName();
    public JLabel getImage() {
        return image;
    }

    protected String getFilename(Media m) {
        Matcher filenameMatcher = Pattern.compile(FILENAME_PATTERN).matcher(m.getName());
        filenameMatcher.find();
        return filenameMatcher.group(1);
    }

    public static MediaItem createMediaItem(Media m) {
        Matcher movieMatcher = Pattern.compile(MOVIE_PATTERN).matcher(m.getName());
        Matcher musicMatcher = Pattern.compile(MUSIC_PATTERN).matcher(m.getName());

        MediaItem mediaItem = null;

        if (movieMatcher.find()) {
            mediaItem = new Movie(m);
        } else if (musicMatcher.find()) {
            mediaItem = new Music(m);
        } else {
            mediaItem = new Unknown(m);
        }

        return mediaItem;
    }
}
