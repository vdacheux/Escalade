package org.escalade.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.inject.Named;

@Named
public class DaoFactory {
	private final String url = "jdbc:postgresql://localhost:5432/escalade";
	// private final String username;
	// private final String password;

	// DaoFactory(String url, String username, String password) {
	// this.url = url;
	// this.username = username;
	// this.password = password;
	// }

	public DaoFactory() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (final ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.url, "escalade_admin", "250812");
	}

	public UtilisateurDao getUtilisateurDao() {
		return new UtilisateurDaoImpl(this);
	}
}