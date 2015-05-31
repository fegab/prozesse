package exe;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import database.Database;
import model.Element;
import model.Menge;
import model.Sense;

public class Main_prozesse_HA1 {

	public static void main(String[] args) throws Exception {

		Map<UUID, Element> elemente = new HashMap<>();
		Sense testSense = new Sense("testSense");
		// Menge a = new Menge(UUID.randomUUID(), elemente, "test");
		int helfer = 0;

		for (int i = 0; i < 10; i++) {
			testSense.setName("testSense" + i);
			Element newele = new Element(UUID.randomUUID(), testSense);
			elemente.put(newele.getId(), newele);
			//System.out.println(testSense.getName());
			// System.out.println(elemente.size());
			// System.out.println(newele.getId());
		}

		for (UUID id : elemente.keySet()) {
			Element e = elemente.get(id);
			System.out.println(e.getId());
			System.out.println(e.getSense().getName());
			helfer += 1;
		}

		// ab hier Datenbank-shizzl

		String fileName = "./datenbank";
		Database db = new Database();
		db.connect(fileName, "fegab", "");
		db.setTransactionOn(true); // Schalte den Transaktionsmodus an

		db.clearTables();

		db.createTables();
		db.writeChanges(); // Führe alle bisherigen Änderungen and der
		// Datenbank tatsächlich aus (Commit)

		db.writeElemente(elemente);
		db.writeChanges();// Führe Commit aus
		db.close();

		// // LADEN
		//
		// db.connect(fileName, "ich", "");
		//
		// waende = db.readWaende();
		//
		// // Es müssen 10 Wände gelesen werden, da die 11. letztendlich doch
		// nicht eingefügt wurde.
		// for (Wand wand : waende) {
		// System.out.println("Wand (" + wand.getID().toString() + ", " +
		// wand.getOeffnung().size() + ")");
		// }

		// db.close();

		// System.out.println("Anzahl der Elemente:(mit helfer) " + helfer);
		// System.out
		// .println("Anzahl der Elemente:(mit .size) " + elemente.size());

		// neuer test
		// System.out.println("Mengen-ID: " + a.getIdMenge());
		// System.out.println("Anzahl der Elemente: " + a.getElemente().size());

	}

}
