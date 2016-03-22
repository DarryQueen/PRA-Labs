package trizdarren.model;

import generator.Media;

public class Unknown extends MediaItem {
    private String name;

    public Unknown(Media m) {
        super(m);

        name = getFilename(m);
    }

    @Override
    public Type getType() {
        return Type.UNKNOWN;
    }

    @Override
    public String getName() {
        return name;
    }
}
