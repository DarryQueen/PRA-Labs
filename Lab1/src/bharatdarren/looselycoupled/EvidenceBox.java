package bharatdarren.looselycoupled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvidenceBox {
    private List<Evidence> evidenceList;

    private String caseName;
    private int caseNumber;

    public EvidenceBox(String cname, int cnum) {
        this.evidenceList = new ArrayList<Evidence>();

        this.caseName = cname;
        this.caseNumber = cnum;
    }

    public String getCaseName() {
        return this.caseName;
    }
    public int getCaseNumber() {
        return this.caseNumber;
    }

    public void add(Evidence e) {
        this.evidenceList.add(e);
    }

    /**
     * Grabs evidence from this box by specified type.
     *
     * @param type string type of evidence to search for
     * @return list of evidence that matches type
     */
    public List<Evidence> getEvidenceByType(String type) {
        List<Evidence> evidenceList = new ArrayList<Evidence>();
        for (Evidence e : this.extract()) {
            if (type.equals(e.getType())) {
                evidenceList.add(e);
            }
        }
        return evidenceList;
    }

    /**
     * Returns a list of the evidence in the box.
     */
    private List<Evidence> extract() {
        return evidenceList;
    }

    /**
     * Returns a string representation of the contents in this box.
     * Formatted as count, evidence type.
     */
    @Override
    public String toString() {
        String s = "";

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Evidence e : evidenceList) {
            Integer count = map.get(e.getType());
            if (count == null) {
                count = new Integer(0);
            }
            count = new Integer(count.intValue() + 1);
            map.put(e.getType(), count);
        }

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

        // Iterate over entries.
        for (Map.Entry<String, Integer> e : entrySet) {
            s += e.getValue() + "x " + e.getKey() + "\n";
        }

        return s.trim();
    }
}
