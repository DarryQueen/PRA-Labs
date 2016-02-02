package bharatdarren;

import java.util.List;

import bharatdarren.looselycoupled.Evidence;
import bharatdarren.looselycoupled.EvidenceBox;
import bharatdarren.looselycoupled.Policeman;

public class Main {
	public static void main(String[] args) {
		Evidence handwrittenDocument = new Evidence("handwritten document");
		Evidence footprint = new Evidence("footprint");
		EvidenceBox evidenceBox = new EvidenceBox("S. Avery", 2005000381);
		Policeman jl = new Policeman("J. Lenk");
		Policeman ac = new Policeman("A. Colborn");
		
		evidenceBox.add(handwrittenDocument);
		evidenceBox.add(footprint);
		jl.takeBox(evidenceBox);
		
		System.out.println(handwrittenDocument);
		System.out.println(footprint);
		System.out.println(evidenceBox);
		
		int cnum = 2005000381;
		String type = "footprint";
		List<Evidence> evidenceList = jl.getEvidence(cnum, type);
		System.out.println("Pieces of " + type + " Evidence in Box number " + cnum + " found by " + jl + ":");
		System.out.println(evidenceList);
	}
}
