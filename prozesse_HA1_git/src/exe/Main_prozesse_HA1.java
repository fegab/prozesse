package exe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import database.Database;
import model.Element;
import model.Menge;
import model.Sense;

public class Main_prozesse_HA1 {

	public static void main(String[] args) throws Exception {

		ArrayList<Menge> mengen = new ArrayList<Menge>();
		Map<UUID, Element> elemente = new HashMap<>();
		// Sense testSenseMenge = new Sense("testSenseMenge");

		// Menge a = new Menge(UUID.randomUUID(), elemente, "test");
		int helfer = 0;

		// Erstellen von 5 Mengen mit je 10 Elementen
		for (int i = 1; i <= 5; i++) {
			Sense testSenseMenge = new Sense("Menge: " + i);
			Menge menge = new Menge(testSenseMenge);

			menge.setIdMenge(UUID.randomUUID());

			System.out.println(menge.getSense().getName() + ": "
					+ menge.getIdMenge());
			mengen.add(menge);

			for (int u = 0; u < 10; u++) {
				Sense testSenseElement = new Sense("Element");
				Element newele = new Element(UUID.randomUUID(),
						testSenseElement);
				newele.getSense().setName("Element-Nr.: " + u);
				menge.getElemente().put(newele.getId(), newele);

			}

		}

		// Testausgabe
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Testausgabe der 5 Mengen mit je 10 Elementen:");

		for (int i = 0; i < mengen.size(); i++) {
			System.out.println(mengen.get(i).getSense().getName()
					+ " hat UUID: " + mengen.get(i).getIdMenge() + " und "
					+ mengen.get(i).getElemente().size() + " Elemente");

			System.out.println("Elemente der Menge: ");

			for (UUID id : mengen.get(i).getElemente().keySet()) {
				Element e = mengen.get(i).getElemente().get(id);
				helfer += 1;

				System.out.println("Element " + helfer + ": " + e.getId()
						+ " -->" + e.getSense().getName());

			}

			System.out.println(" ");
			System.out.println(" ");

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
		db.writeMenge(mengen);
		db.writeChanges();// Führe Commit aus
		db.close();

		// LADEN

		db.connect(fileName, "fegab", "");

		elemente = db.readElemente();

		for (UUID id : elemente.keySet()) {
			Element e = elemente.get(id);
			helfer += 1;
			System.out.println("hallo!");
			System.out.println(helfer + ": " + e.getId() + " "
					+ e.getSense().getName());

		}

		db.close();

		// System.out.println("Anzahl der Elemente:(mit helfer) " + helfer);
		// System.out.println("Anzahl der Elemente:(mit .size) " +
		// elemente.size());

	}

}
