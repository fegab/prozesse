package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

		// UUID wird als String (UUIDs haben immer 36 Zeichen) gespeichert

		String strcreateTableMengen = "CREATE TABLE Mengen(index INT PRIMARY KEY, uuid VARCHAR(36) NOT NULL);";
		String strcreateTableElements = "CREATE TABLE Elements(index INT PRIMARY KEY, uuid VARCHAR(36) NOT NULL, MengenUUID VARCHAR(36),RelationOut,RelationIn);";
		String strcreateTableRelations = "CREATE TABLE Relations(index INT PRIMARY KEY, uuid CARCHAR(36) NOT NULL);";

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
		statement.execute("DROP TABLE IF EXISTS Mengen;");
		statement.execute("DROP TABLE IF EXISTS Elements;");
		statement.execute("DROP TABLE IF EXISTS Relations;");

	}

}
