package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Menge;

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

		String strcreateTableMengen = "CREATE TABLE mengen(index INT PRIMARY KEY, uuid VARCHAR(36) NOT NULL, elemente, sense);";
		String strcreateTableElements = "CREATE TABLE elements(index INT PRIMARY KEY, uuid VARCHAR(36) NOT NULL, MengenUUID VARCHAR(36),bedeutung,relations);";
		String strcreateTableRelations = "CREATE TABLE relations(index INT PRIMARY KEY, uuid CARCHAR(36) NOT NULL);";

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
		statement.execute("DROP TABLE IF EXISTS mengen;");
		statement.execute("DROP TABLE IF EXISTS elements;");
		statement.execute("DROP TABLE IF EXISTS relations;");

	}
	
//	public int writeMenge(Menge me) throws SQLException {

//		Statement stmt = conn.createStatement();
//		String query = "SELECT * FROM menge WHERE index="
//				+ Integer.toString(me.getIdMenge());
//		ResultSet rs = stmt.executeQuery(query);
//
//		if (!rs.next()) {
//
//			String strWriteMenge = "INSERT INTO menge(index INT PRIMARY KEY, uuid VARCHAR(36) NOT NULL, elemente, sense) VALUES(?,?,?,?);";
//
//			PreparedStatement prepStatement = conn
//					.prepareStatement(strWriteMenge);
//
//			// ? füllen
//			//prepStatement.setInt(1, me.getIndex());
//			prepStatement.setString(2, me.getIdMenge().toString());
//			//prepStatement.s(3, me.getElemente());
//			//prepStatement.setDouble(4, bp.getP0().getY());
//
//
//			prepStatement.executeUpdate();
//			return 0; // erstmalig hinzugefügt
//
//		} else {
//
//			String strUpdateMenge;
//			PreparedStatement prepStatement;
//			// keine neue Pavillon-UUID, wenn Bauteil verändert wird
//			if (!me.getPavillonId().toString().equals(strAnfangsUUID)) {
//				strUpdateBodenplatte = "UPDATE bodenplatte SET pavillonUUID= ? WHERE index= ?";
//				prepStatement = conn.prepareStatement(strUpdateBodenplatte);
//				prepStatement.setString(1, bp.getPavillonId().toString());
//				prepStatement.setInt(2, bp.getIndex());
//				prepStatement.executeUpdate();
//			}
//
//			strUpdateBodenplatte = "UPDATE bodenplatte SET x= ? WHERE index= ?";
//			prepStatement = conn.prepareStatement(strUpdateBodenplatte);
//			prepStatement.setDouble(1, bp.getP0().getX());
//			prepStatement.setInt(2, bp.getIndex());
//			prepStatement.executeUpdate();
//
//			return 1; // geupdated
//
//		}
//	}
	

}
