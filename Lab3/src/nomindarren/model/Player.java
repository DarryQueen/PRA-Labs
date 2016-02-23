package nomindarren.model;

import nomindarren.controller.Controller;

public class Player {
    private static int numPlayers = 0;

    protected String name;
    private int id;
    private String path = Controller.NULL_PATH;

    public Player() {
        this.id = numPlayers;

        numPlayers++;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String n) {
        this.name = n;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int i) {
        this.id = i;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String p) {
        if (p == null) {
            this.path = Controller.NULL_PATH;
        }
        this.path = p;
    }

    @Override
    public String toString() {
        return this.name + " " + this.id;
    }
}
