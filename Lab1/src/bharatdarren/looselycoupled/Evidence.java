package bharatdarren.looselycoupled;

/**
 * @author Darren, Bharat
 *
 * This class represents the evidence object.
 */
public class Evidence {
    private String type;

    public Evidence(String t) {
        this.type = t;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.getType();
    }
}
