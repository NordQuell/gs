package de.bo.geminm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DbRepo {

	private Connection con;

	private final String selectStm = "SELECT CONTENT FROM GEMEINDESCHLUESSEL WHERE PLZ = ? AND ORT = ?";

	public void initTable() throws SQLException {
		con = DriverManager.getConnection("jdbc:h2:tcp://localhost/C:/S&N Dev/H2 Databases/Gemeinde", "sa", "");
		String initSql = "INSERT INTO GEMEINDESCHLUESSEL (PLZ, ORT, CONTENT)" + "VALUES (?, ?, ?)";
		try (PreparedStatement stmInsert = con.prepareStatement(initSql);) {
			for (int i = 0; i < 1000000; i++) {
				stmInsert.setString(1, String.valueOf(i));
				stmInsert.setString(2, "Ort " + i);
				stmInsert.setString(3, "{vkz: " + i + ", gemeindeschluessel: " + i + "}");
				//stmInsert.executeUpdate();
			}
		}

	}

	public String getGemeindeschluessel(String plz, String ort) throws SQLException {
		try (PreparedStatement stm = con.prepareStatement(selectStm)) {
			stm.setString(1, plz);
			stm.setString(2, ort);
			try (ResultSet rs = stm.executeQuery()) {
				while (rs.next()) {
					return rs.getString("CONTENT");
				}
			}
		}
		return null;
	}

}
