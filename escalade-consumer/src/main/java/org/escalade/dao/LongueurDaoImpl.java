package org.escalade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.escalade.beans.Longueur;

public class LongueurDaoImpl implements LongueurDao {

	private final DaoFactory daoFactory;

	LongueurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void createLongueur(Longueur longueur, int voieId) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO longueur(cotation, longueur_id) VALUES(?, ?);");
			preparedStatement.setString(1, longueur.getCotation());
			preparedStatement.setLong(2, longueur.getId());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Longueur> list() {
		final List<Longueur> longueurs = new ArrayList<Longueur>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT cotation, longueur_id FROM longueur;");

			while (resultat.next()) {
				final String cotation = resultat.getString("cotation");
				final int id = (int) resultat.getLong("id");

				final Longueur longueur = new Longueur();
				longueur.setCotation(cotation);
				longueur.setId(id);

				longueurs.add(longueur);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return longueurs;
	}

	@Override
	public Longueur findLongueur(int id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT longueur_id FROM longueur WHERE longueur_id = ?");

			if (resultat.next()) {
				final String cotation = resultat.getString("cotation");
				final Longueur longueur = new Longueur();
				longueur.setCotation(cotation);

				return longueur;
			} else {
				return null;
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateLongueur(Longueur longueur) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("UPDATE longueur SET cotation = ? WHERE longueur_id = ?");
			preparedStatement.setString(1, longueur.getCotation());
			preparedStatement.setLong(2, longueur.getId());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteLongueur(int id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM longueur WHERE longueur_id = ?");
			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Longueur> findLongueurByVoie(int voieId) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		final List<Longueur> longueurs = new ArrayList<Longueur>();

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM escalade.longueur WHERE voie_id = ?");
			preparedStatement.setLong(1, voieId);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				final String cotation = resultat.getString("cotation");
				final int id = resultat.getInt("id");

				final Longueur longueur = new Longueur();
				longueur.setCotation(cotation);
				longueur.setVoieId(voieId);
				longueur.setId(id);

				longueurs.add(longueur);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return longueurs;
	}

	@Override
	public List<Longueur> findLongueurBySecteur(int secteurId) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		final List<Longueur> longueurs = new ArrayList<Longueur>();

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"SELECT * FROM escalade.longueur, escalade.voie WHERE longueur.voie_id = voie.voie_id AND voie.secteur_id = '?'");
			preparedStatement.setLong(1, secteurId);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				final String cotation = resultat.getString("cotation");
				final int voieId = resultat.getInt("voieId");
				final int id = resultat.getInt("id");

				final Longueur longueur = new Longueur();
				longueur.setCotation(cotation);
				longueur.setVoieId(voieId);
				longueur.setId(id);

				longueurs.add(longueur);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return longueurs;
	}

	@Override
	public List<Longueur> findLongueurBySite(int idSite) {
		// TODO Auto-generated method stub
		return null;
	}

}
