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

import org.escalade.beans.Site;

@Named
public class SiteDaoImpl implements SiteDao {

	private final DaoFactory daoFactory;

	@Inject
	SiteDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void createSite(Site site) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("INSERT INTO site(nom, description, departement, site_id) VALUES(?, ?, ?, ?);");
			preparedStatement.setString(1, site.getNom());
			preparedStatement.setString(2, site.getDescription());
			preparedStatement.setString(3, site.getDepartement());
			preparedStatement.setLong(4, site.getId());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Site> list() {
		final List<Site> sites = new ArrayList<Site>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT nom, description, departement, site_id FROM site;");

			while (resultat.next()) {
				final String nom = resultat.getString("nom");
				final String description = resultat.getString("description");
				final String departement = resultat.getString("departement");
				final int id = (int) resultat.getLong("id");

				final Site site = new Site();
				site.setNom(nom);
				site.setDescription(description);
				site.setDepartement(departement);
				site.setId(id);

				sites.add(site);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return sites;
	}

	@Override
	public Site findSite(int id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT site_id FROM site WHERE site_id = ?");

			if (resultat.next()) {
				final String nom = resultat.getString("nom");
				final Site site = new Site();
				site.setNom(nom);

				return site;
			} else {
				return null;
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateSite(Site site) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("UPDATE site SET nom = ?, description = ?, departement = ? WHERE site_id = ?");
			preparedStatement.setString(1, site.getNom());
			preparedStatement.setString(2, site.getDescription());
			preparedStatement.setString(3, site.getDepartement());
			preparedStatement.setLong(4, site.getId());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteSite(int id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM site WHERE site_id = ?");
			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

}
