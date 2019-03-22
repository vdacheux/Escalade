package org.escalade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.escalade.beans.Voie;

public class VoieDaoImpl implements VoieDao {

	private final DaoFactory daoFactory;

	VoieDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void createVoie(Voie voie) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO voie(nom, cotation, hauteur, points, voie_id) VALUES(?, ?, ?, ?, ?);");
			preparedStatement.setString(1, voie.getNom());
			preparedStatement.setString(2, voie.getCotation());
			preparedStatement.setLong(3, voie.getHauteur());
			preparedStatement.setLong(4, voie.getPoints());
			preparedStatement.setLong(5, voie.getId());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Voie> list() {
		final List<Voie> voies = new ArrayList<Voie>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT nom, cotation, hauteur, points, voie_id FROM site;");

			while (resultat.next()) {
				final String nom = resultat.getString("nom");
				final String cotation = resultat.getString("cotation");
				final Integer hauteur = (int) resultat.getLong("hauteur");
				final Integer points = (int) resultat.getLong("points");
				final int id = (int) resultat.getLong("id");

				final Voie voie = new Voie();
				voie.setNom(nom);
				voie.setCotation(cotation);
				voie.setHauteur(hauteur);
				voie.setPoints(points);
				voie.setId(id);

				voies.add(voie);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return voies;
	}

	@Override
	public Voie findVoie(int id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT voie_id FROM voie WHERE voie_id = ?");

			if (resultat.next()) {
				final String nom = resultat.getString("nom");
				final Voie voie = new Voie();
				voie.setNom(nom);

				return voie;
			} else {
				return null;
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateVoie(Voie voie) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"UPDATE voie SET nom = ?, cotation = ?, hauteur = ?, points = ? WHERE voie_id = ?");
			preparedStatement.setString(1, voie.getNom());
			preparedStatement.setString(2, voie.getCotation());
			preparedStatement.setLong(3, voie.getHauteur());
			preparedStatement.setLong(4, voie.getPoints());
			preparedStatement.setLong(5, voie.getId());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteVoie(int id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM voie WHERE voie_id = ?");
			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Voie findVoieBySecteur(int secteurId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Voie findVoieBySite(int siteId) {
		// TODO Auto-generated method stub
		return null;
	}

}
