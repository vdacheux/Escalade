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

import org.escalade.beans.Topo;

@Named
public class TopoDaoImpl implements TopoDao {

	private final DaoFactory daoFactory;

	@Inject
	TopoDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void createTopo(Topo topo) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("INSERT INTO topo(nom, description, topo_id) VALUES(?, ?, ?);");
			preparedStatement.setString(1, topo.getNom());
			preparedStatement.setString(2, topo.getDescription());
			preparedStatement.setLong(3, topo.getId());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Topo> list() {
		final List<Topo> topos = new ArrayList<Topo>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT nom, description, topo_id FROM topo;");

			while (resultat.next()) {
				final String nom = resultat.getString("nom");
				final String description = resultat.getString("description");
				final int id = (int) resultat.getLong("id");

				final Topo topo = new Topo();
				topo.setNom(nom);
				topo.setDescription(description);
				topo.setId(id);

				topos.add(topo);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return topos;
	}

	@Override
	public Topo findTopo(int id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = this.daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT topo_id FROM topo WHERE topo_id = ?");

			if (resultat.next()) {
				final String nom = resultat.getString("nom");
				final Topo topo = new Topo();
				topo.setNom(nom);

				return topo;
			} else {
				return null;
			}
		} catch (final SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateTopo(Topo topo) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("UPDATE topo SET nom = ?, description = ? WHERE site_id = ?");
			preparedStatement.setString(1, topo.getNom());
			preparedStatement.setString(2, topo.getDescription());
			preparedStatement.setLong(3, topo.getId());

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteTopo(int id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = this.daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM topo WHERE topo_id = ?");
			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Topo findTopoBySite(int siteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Topo findTopoByUtilisateur(int utilisateurId) {
		// TODO Auto-generated method stub
		return null;
	}

}
