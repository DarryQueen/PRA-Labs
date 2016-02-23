package nomindarren.model;

public class Squad {
    public static final int NUM_GOALKEEPERS = 2;
    public static final int NUM_DEFENDERS = 5;
    public static final int NUM_MIDFIELDERS = 5;
    public static final int NUM_STRIKERS = 3;

    private Goalkeeper[] goalkeepers;
    private Defender[] defenders;
    private Midfielder[] midfielders;
    private Striker[] strikers;

    public Squad() {
        this.goalkeepers = new Goalkeeper[NUM_GOALKEEPERS];
        this.defenders = new Defender[NUM_DEFENDERS];
        this.midfielders = new Midfielder[NUM_MIDFIELDERS];
        this.strikers = new Striker[NUM_STRIKERS];

        for (int i = 0; i < this.goalkeepers.length; i++) {
            this.goalkeepers[i] = new Goalkeeper();
        }
        for (int i = 0; i < this.defenders.length; i++) {
            this.defenders[i] = new Defender();
        }
        for (int i = 0; i < this.midfielders.length; i++) {
            this.midfielders[i] = new Midfielder();
        }
        for (int i = 0; i < this.strikers.length; i++) {
            this.strikers[i] = new Striker();
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
}
