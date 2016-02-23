package nomindarren.model;

import java.util.Dictionary;
import java.util.Hashtable;

public class Squad {
    public static final int NUM_GOALKEEPERS = 2;
    public static final int NUM_DEFENDERS = 5;
    public static final int NUM_MIDFIELDERS = 5;
    public static final int NUM_STRIKERS = 3;

    private Goalkeeper[] goalkeepers;
    private Defender[] defenders;
    private Midfielder[] midfielders;
    private Striker[] strikers;
    private Dictionary<Integer, Player> players;

    public Squad() {
        this.goalkeepers = new Goalkeeper[NUM_GOALKEEPERS];
        this.defenders = new Defender[NUM_DEFENDERS];
        this.midfielders = new Midfielder[NUM_MIDFIELDERS];
        this.strikers = new Striker[NUM_STRIKERS];
        this.players = new Hashtable<Integer, Player>();

        for (int i = 0; i < this.goalkeepers.length; i++) {
            this.goalkeepers[i] = new Goalkeeper();
            players.put(this.goalkeepers[i].getId(), this.goalkeepers[i]);
        }
        for (int i = 0; i < this.defenders.length; i++) {
            this.defenders[i] = new Defender();
            players.put(this.defenders[i].getId(), this.defenders[i]);
        }
        for (int i = 0; i < this.midfielders.length; i++) {
            this.midfielders[i] = new Midfielder();
            players.put(this.midfielders[i].getId(), this.midfielders[i]);
        }
        for (int i = 0; i < this.strikers.length; i++) {
            this.strikers[i] = new Striker();
            players.put(this.strikers[i].getId(), this.strikers[i]);
        }
    }

    public Player[] getGoalkeepers() {
        return this.goalkeepers;
    }
    public Player[] getDefenders() {
        return this.defenders;
    }
    public Player[] getMidfielders() {
        return this.midfielders;
    }
    public Player[] getStrikers() {
        return this.strikers;
    }
    public Player getPlayerById(int id) {
        return this.players.get(id);
    }
}
