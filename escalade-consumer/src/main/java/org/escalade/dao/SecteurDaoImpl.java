package org.escalade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.escalade.beans.Secteur;

@Named
public class SecteurDaoImpl implements SecteurDao {

	private final DaoFactory daoFactory;

	@Inject
	SecteurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void createSecteur(Secteur secteur) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO secteur(nom, secteur_id) VALUES(?, ?);");
			preparedStatement.setString(1, secteur.getNom());
			preparedStatement.setLong(2, secteur.getId());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Secteur> list() {
		final List<Secteur> secteurs = new ArrayList<Secteur>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT nom, secteur_id FROM secteur;");

			while (resultat.next()) {
				final String nom = resultat.getString("nom");
				final int id = (int) resultat.getLong("id");

				final Secteur secteur = new Secteur();
				secteur.setNom(nom);
				secteur.setId(id);

				secteurs.add(secteur);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return secteurs;
	}

	@Override
	public Secteur findSecteur(int id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT secteur_id FROM secteur WHERE secteur_id = ?");

			if (resultat.next()) {
				final String nom = resultat.getString("nom");
				final Secteur secteur = new Secteur();
				secteur.setNom(nom);

				return secteur;
			} else {
				return null;
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateSecteur(Secteur secteur) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("UPDATE secteur SET nom = ? WHERE secteur_id = ?");
			preparedStatement.setString(1, secteur.getNom());
			preparedStatement.setLong(2, secteur.getId());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteSecteur(int id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM secteur WHERE secteur_id = ?");
			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Secteur> findSecteurBySite(int siteId) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		final List<Secteur> secteurs = new ArrayList<Secteur>();

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM escalade.secteur WHERE site_id = ?");
			preparedStatement.setLong(1, siteId);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				final String nom = resultat.getString("nom");
				final int id = resultat.getInt("id");

				final Secteur secteur = new Secteur();
				secteur.setNom(nom);
				secteur.setId(id);

				secteurs.add(secteur);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return secteurs;
	}

}
