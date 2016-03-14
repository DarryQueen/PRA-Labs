package trizdarren.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import generator.Media;

public class Movie extends MediaItem {
    private static final String NAME_PATTERN = "(?:\\) (.*?) \\()|(?:^([^\\(]*) \\()|(?:\\) ([^\\)]*)$)";
    private static final String PAREN_PATTERN = "\\((.*?)\\)";
    private static final String YEAR_PATTERN = "^\\d{4}$";
    private static final String QUALITY_PATTERN = "([A-Z][A-Z]), ([0-9]*)p";

    private String name;
    private String quality;
    private int year, pixels;

    private void setAttributes(String filename) {
        // Grab movie name.
        Matcher nameMatcher = Pattern.compile(NAME_PATTERN).matcher(filename);
        if (nameMatcher.find()) {
            if (nameMatcher.group(1) != null) {
                name = nameMatcher.group(1);
            } else if (nameMatcher.group(2) != null) {
                name = nameMatcher.group(2);
            } else if (nameMatcher.group(3) != null) {
                name = nameMatcher.group(3);
            }
        }

        // Grab parentheses content.
        Matcher parenMatcher = Pattern.compile(PAREN_PATTERN).matcher(filename);

        while(parenMatcher.find()) {
            String content = parenMatcher.group(1);

            Matcher yearMatcher = Pattern.compile(YEAR_PATTERN).matcher(content);
            Matcher qualityMatcher = Pattern.compile(QUALITY_PATTERN).matcher(content);
            if (yearMatcher.find()) {
                year = Integer.parseInt(content);
            } else if (qualityMatcher.find()) {
                quality = qualityMatcher.group(1);
                pixels = Integer.parseInt(qualityMatcher.group(2));
            }
        }
    }

    public Movie(Media m) {
        super(m);

        setAttributes(getFilename(m));
    }

    @Override
    public Type getType() {
        return Type.MOVIE;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getQuality() {
        return quality;
    }

    public int getPixels() {
        return pixels;
    }
}
