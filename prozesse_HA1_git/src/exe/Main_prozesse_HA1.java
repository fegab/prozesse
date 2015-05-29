package exe;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import model.Element;
import model.Menge;
import model.Sense;

public class Main_prozesse_HA1 {

	public static void main(String[] args) {

		Map<UUID, Element> elemente = new HashMap<>();
		Menge a = new Menge(UUID.randomUUID(), elemente);
		int helfer = 0;

		for (int i = 0; i < 10; i++) {
			Element newele = new Element(UUID.randomUUID(), "element" + i, null);
			elemente.put(newele.getId(), newele);
			// System.out.println(elemente.size());
			// System.out.println(newele.getId());
		}

		for (UUID id : elemente.keySet()) {
			Element e = elemente.get(id);
			System.out.println(e.getId());
			helfer += 1;
		}
		// System.out.println("Anzahl der Elemente:(mit helfer) " + helfer);
		// System.out
		// .println("Anzahl der Elemente:(mit .size) " + elemente.size());

		// neuer test
		System.out.println("Mengen-ID: " + a.getIdMenge());
		System.out.println("Anzahl der Elemente: " + a.getElemente().size());

	}

}
