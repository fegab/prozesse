package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import model.Element;
import model.Menge;
import model.Relation;
import model.Sense;

public class Database {

	/**
	 * Enthält die aktuelle Verbindung zur Datenbank
	 */
	private Connection conn;

	/**
	 * Baut die Verbindung zu einer Datenbankdatei auf. In fileName muss der
	 * absolute (z.B. C:\Workspace\meineDatenbankDatei) ode relative Pfad (z.B.
	 * /meineDatenBank) stehen. Existiert die Datenbankdatei noch nicht, so wird
	 * automatische eine solche erstellt und geöffnet.
	 * 
	 * @param fileName
	 * @param userName
	 * @param password
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void connect(String fileName, String userName, String password)
			throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver"); // Treiberpr�fung
		conn = DriverManager.getConnection("jdbc:h2:" + fileName + "",
				userName, password);
	}

	/**
	 * Schließt die aktuelle Datenbankverbindung/Datenbankdatei
	 * 
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		conn.close();
	}

	/**
	 * Erstellt alle benötigten Tabellen in der Datenbank mit allen Spalten
	 * 
	 * @throws SQLException
	 */
	public void createTables() throws SQLException {
		Statement statement = conn.createStatement();
		System.out.println("createTables()");

		// UUID wird als String (UUIDs haben immer 36 Zeichen) gespeichert

		String strcreateTableMengen="CREATE TABLE mengen(index INT AUTO_INCREMENT PRIMARY KEY, uuid VARCHAR(36) NOT NULL, sense VARCHAR(36) NOT NULL);";
		String strcreateTableElements = "CREATE TABLE elements(index INT AUTO_INCREMENT PRIMARY KEY, uuid VARCHAR(36) NOT NULL, uuidMenge VARCHAR(36) NOT NULL,bedeutung VARCHAR(36) NOT NULL);";
		String strcreateTableRelations="CREATE TABLE relations(index INT AUTO_INCREMENT PRIMARY KEY, uuid VARCHAR(36) NOT NULL);";

		statement.execute(strcreateTableMengen);
		statement.execute(strcreateTableElements);
		statement.execute(strcreateTableRelations);

		statement.close();
	}

	/**
	 * Löscht alle Tabellen und die entsprechenden Eintr�ge
	 * 
	 * @throws SQLException
	 */
	public void clearTables() throws SQLException {
		Statement statement = conn.createStatement();
		System.out.println("clearTables()");
		statement.execute("DROP TABLE IF EXISTS mengen;");
		statement.execute("DROP TABLE IF EXISTS elements;");
		statement.execute("DROP TABLE IF EXISTS relations;");

	}

	public void removeMenge(Menge menge) throws SQLException {
		Statement statement = conn.createStatement();
		System.out.println("removeMenge()");
		String strRemoveMenge = "DELETE FROM wand WHERE uuid='"
				+ menge.getIdMenge().toString() + "';";

		statement.execute(strRemoveMenge);
	}

	public void removeElement(Element element) throws SQLException {
		Statement statement = conn.createStatement();
		System.out.println("clearElement()");
		String strRemoveElement = "DELETE FROM element WHERE uuid='"
				+ element.getId().toString() + "';";

		statement.execute(strRemoveElement);
	}
	
	public void removeRelation(Relation relation) throws SQLException {
		Statement statement = conn.createStatement();
		System.out.println("clearElement()");
		String strRemoveElement = "DELETE FROM relation WHERE uuid='"
				+ relation.getIdRelation().toString() + "';";

		statement.execute(strRemoveElement);
	}

	public void writeElemente(Map<UUID, Element> elemente) throws SQLException {

		String strWriteElement = "INSERT INTO elements(uuid,uuidMenge,bedeutung) VALUES(?,?,?);";
		System.out.println("writeElemente()");

		PreparedStatement prepStatement = conn
				.prepareStatement(strWriteElement);

		for (UUID id : elemente.keySet()) {
			// System.out.println("was passiert");
			Element e = elemente.get(id);
			prepStatement.setString(1, e.getId().toString());
			prepStatement.setString(2, UUID.randomUUID().toString());
			// prepStatement.setString(2, elemente.);
			prepStatement.setString(3, e.getSense().toString());
			prepStatement.executeUpdate();

		}

	}

	public Map<UUID, Element> readElemente() throws SQLException {

		Map<UUID, Element> elemente = new HashMap<>();

		Statement stmt = conn.createStatement();
		String query = "SELECT uuid,uuidMenge, bedeutung FROM elements";
		System.out.println("readElemente()");

		ResultSet rs = stmt.executeQuery(query); // Hole den MengenIterator mit
													// den Ergebnissen
		while (rs.next()) {
			String strUUID = rs.getString("uuid");
			String strUUIDm = rs.getString("uuidMenge");
			String bedeutung = rs.getString("bedeutung");

			UUID uuid = UUID.fromString(strUUID); // Wandle den String der UUID
			UUID uuidMenge = UUID.fromString(strUUIDm); // wieder in ein echtes
														// UUID Java-Objekt um

			Sense sen = new Sense(bedeutung);
			Element ele = new Element(uuid, sen);
			elemente.put(uuid, ele);
		}

		return elemente;
	}

	/**
	 * Schreibt alle Änderungen seit dem letzen Commit in die Datenbank
	 * 
	 * @throws SQLException
	 */
	public void writeChanges() throws SQLException {
		conn.commit();
	}

	/**
	 * Verwirft alle Änderungen seit dem letzen Commit
	 * 
	 * @throws SQLException
	 */
	public void discardChanges() throws SQLException {
		conn.rollback();
	}

	public void setTransactionOn(boolean b) {
		// TODO Auto-generated method stub

	}

}
