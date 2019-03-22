package org.escalade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.escalade.beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {

	private final DaoFactory daoFactory;

	UtilisateurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void createUtilisateur(Utilisateur user) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO utilisateur(identifiant, password, mail, nom, prenom) VALUES(?, ?, ?, ?, ?);");
			preparedStatement.setString(1, user.getIdentifiant());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getMail());
			preparedStatement.setString(4, user.getNom());
			preparedStatement.setString(5, user.getPrenom());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Utilisateur> list() {
		final List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT identifiant, password, mail, nom, prenom FROM utilisateur;");

			while (resultat.next()) {
				final String identifiant = resultat.getString("identifiant");
				final String password = resultat.getString("password");
				final String mail = resultat.getString("mail");
				final String nom = resultat.getString("nom");
				final String prenom = resultat.getString("prenom");

				final Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdentifiant(identifiant);
				utilisateur.setPassword(password);
				utilisateur.setMail(mail);
				utilisateur.setNom(nom);
				utilisateur.setPrenom(prenom);

				utilisateurs.add(utilisateur);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return utilisateurs;
	}

	@Override
	public Utilisateur findUtilisateur(String identifiant) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT identifiant, password FROM utilisateur WHERE identifiant = ?");

			if (resultat.next()) {
				final String password = resultat.getString("password");
				final Utilisateur utilisateur = new Utilisateur();
				utilisateur.setIdentifiant(identifiant);
				utilisateur.setPassword(password);

				return utilisateur;
			} else {
				return null;
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateUtilisateur(Utilisateur user) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("UPDATE utilisateur SET identifiant = ?, password = ? WHERE identifiant = ?");
			preparedStatement.setString(1, user.getIdentifiant());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getIdentifiant());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteUtilisateur(String identifiant) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM utilisateur WHERE identifiant = ?");
			preparedStatement.setString(1, identifiant);

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

}
