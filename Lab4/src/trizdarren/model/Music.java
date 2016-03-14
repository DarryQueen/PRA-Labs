package trizdarren.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import generator.Media;

public class Music extends MediaItem {
    private static final String PATTERN = "(.*) - (.*)";

    private String name;
    private String artist;

    private void setAttributes(String filename) {
        Matcher matcher = Pattern.compile(PATTERN).matcher(filename);
        matcher.find();
        name = matcher.group(1);
        artist = matcher.group(2);
    }

    public Music(Media m) {
        super(m);

        setAttributes(getFilename(m));
    }

    @Override
    public Type getType() {
        return Type.MUSIC;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getSortArtist() {
        Matcher matcher = Pattern.compile(SORTNAME_PATTERN).matcher(artist);
        matcher.find();
        return matcher.group(1);
    }
}
