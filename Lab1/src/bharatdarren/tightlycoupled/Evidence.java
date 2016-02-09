package bharatdarren.tightlycoupled;

/**
 * This class represents the evidence object.
 *
 * @author Darren, Bharat
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
