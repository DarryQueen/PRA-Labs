package bharatdarren.looselycoupled;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Policeman {
	private String name;
	private Set<EvidenceBox> evidenceBoxes;
	
	public Policeman(String n) {
		this.name = n;
		this.evidenceBoxes = new HashSet<EvidenceBox>();
	}
	
	public void takeBox(EvidenceBox eb) {
		this.evidenceBoxes.add(eb);
	}
	
	/**
	 * Grabs relevant evidence from the specified boxes that this policeman possesses.
	 * 
	 * @param cnum the case number of the box to look in
	 * @param type string type of evidence to search for
	 * @return list of evidence that matches query
	 */
	public List<Evidence> getEvidence(int cnum, String type) {
		List<Evidence> evidenceList = new ArrayList<Evidence>();
		
		for (EvidenceBox eb : evidenceBoxes) {
			if (cnum != eb.getCaseNumber()) {
				continue;
			}
			evidenceList.addAll(eb.getEvidenceByType(type));
		}
		return evidenceList;
	}
	
	public String toString() {
		return this.name;
	}
}
