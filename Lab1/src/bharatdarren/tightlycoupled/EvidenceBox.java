package bharatdarren.tightlycoupled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Darren, Bharat
 *
 * This class represents a box of evidence.
 */
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
	
	/**
	 * Adds a piece of evidence into the box.
	 */
	public void add(Evidence e) {
		this.evidenceList.add(e);
	}
	
	/**
	 * Returns a list of the evidence in the box.
	 */
	public List<Evidence> extract() {
		return evidenceList;
	}
	
	/**
	 * Returns a string representation of the contents in this box.
	 * Formatted as count, evidence type.
	 */
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
