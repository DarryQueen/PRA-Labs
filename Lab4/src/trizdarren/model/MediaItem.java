package trizdarren.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;

import generator.Media;

public abstract class MediaItem {
    private static final String FILENAME_PATTERN = "(.+?)\\.[^\\.]*$";
    private static final String MOVIE_PATTERN = "\\.(flv|gif|mkv|mpeg|mpg|mov)$";
    private static final String MUSIC_PATTERN = "\\.(aiff|aac|aax|oog|wav|wma)$";

    protected static final String SORTNAME_PATTERN = "^(?:The )?(.*?)$";

    public enum Type {
        MOVIE, MUSIC, UNKNOWN
    }

    private JLabel image;

    public MediaItem(Media m) {
        this.image = m.getImage();
    }

    public abstract Type getType();
    public abstract String getName();

    /**
     * Grabs the sort name of the MediaItem. Removes a trailing "The " if it exists.
     * @return String sort name.
     */
    public String getSortName() {
        Matcher matcher = Pattern.compile(SORTNAME_PATTERN).matcher(getName());
        matcher.find();
        return matcher.group(1);
    }

    /**
     * This is a getter method for the JLabel image.
     * @return JLabel image representation.
     */
    public JLabel getImage() {
        return image;
    }

    /**
     * Grabs the name of the file sans extension.
     * @param m Media to inspect.
     * @return String name of file.
     */
    protected String getFilename(Media m) {
        Matcher filenameMatcher = Pattern.compile(FILENAME_PATTERN).matcher(m.getName());
        if (filenameMatcher.find()) {
            return filenameMatcher.group(1);
        }
        return m.getName();
    }

    /**
     * Creates a MediaItem model from the generated Media.
     * @param m Media from which to create model.
     * @return Instance of corresponding subclass MediaItem model.
     */
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
