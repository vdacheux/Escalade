package org.escalade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.escalade.beans.Commentaire;
import org.escalade.beans.Site;
import org.escalade.beans.Utilisateur;

public class CommentaireDaoImpl implements CommentaireDao {

	private final DaoFactory daoFactory;

	CommentaireDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void createCommentaire(Commentaire comment, Utilisateur user, Site site) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO commentaire(message, date, commentaire_id, utilisateur_id, site_id) VALUES(?, ?, ?, ?, ?);");
			preparedStatement.setString(1, comment.getMessage());
			preparedStatement.setString(2, comment.getDate());
			preparedStatement.setLong(3, comment.getId());
			preparedStatement.setLong(4, user.getId());
			preparedStatement.setLong(5, site.getId());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Commentaire> list() {
		final List<Commentaire> commentaires = new ArrayList<Commentaire>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT message, date, commentaire_id FROM commentaire;");

			while (resultat.next()) {
				final String message = resultat.getString("message");
				final String date = resultat.getString("date");
				final int id = resultat.getInt("id");

				final Commentaire commentaire = new Commentaire();
				commentaire.setMessage(message);
				commentaire.setDate(date);
				commentaire.setId(id);

				commentaires.add(commentaire);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return commentaires;
	}

	@Override
	public Commentaire findCommentaire(int id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT commentaire_id FROM commentaire WHERE commentaire_id = ?");

			if (resultat.next()) {
				final String message = resultat.getString("message");
				final Commentaire commentaire = new Commentaire();
				commentaire.setMessage(message);

				return commentaire;
			} else {
				return null;
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Commentaire> findCommentaireByUtilisateur(int userId) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		final List<Commentaire> commentaires = new ArrayList<Commentaire>();

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("SELECT commentaire_id FROM commentaire WHERE utilisateur_id = ?");
			preparedStatement.setLong(1, userId);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				final String message = resultat.getString("message");
				final String date = resultat.getString("date");
				final int id = resultat.getInt("id");

				final Commentaire commentaire = new Commentaire();
				commentaire.setMessage(message);
				commentaire.setDate(date);
				commentaire.setId(id);

				commentaires.add(commentaire);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return commentaires;
	}

	@Override
	public List<Commentaire> findCommentaireByUtilisateurIdentifiant(String identifiant) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		final List<Commentaire> commentaires = new ArrayList<Commentaire>();

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"SELECT * FROM escalade.commentaire, escalade.utilisateur WHERE commentaire.utilisateur_id = utilisateur.utilisateur_id AND utilisateur.identifiant = '?'");
			preparedStatement.setString(1, identifiant);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				final String message = resultat.getString("message");
				final String date = resultat.getString("date");
				final int id = resultat.getInt("id");

				final Commentaire commentaire = new Commentaire();
				commentaire.setMessage(message);
				commentaire.setDate(date);
				commentaire.setId(id);

				commentaires.add(commentaire);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return commentaires;
	}

	@Override
	public void updateCommentaire(Commentaire comment) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("UPDATE commentaire SET message = ?, date = ? WHERE commentaire_id = ?");
			preparedStatement.setString(1, comment.getMessage());
			preparedStatement.setString(2, comment.getDate());
			preparedStatement.setLong(3, comment.getId());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteCommentaire(int id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM commentaire WHERE commentaire_id = ?");
			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Commentaire> findCommentaireBySite(int siteId) {
		return null;
	}

}
