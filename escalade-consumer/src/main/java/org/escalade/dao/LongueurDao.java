package org.escalade.dao;

import java.util.List;

import org.escalade.beans.Longueur;

public interface LongueurDao {

	void createLongueur(Longueur longueur, int voieId);

	List<Longueur> list();

	Longueur findLongueur(int id);

	List<Longueur> findLongueurByVoie(int voieId);

	List<Longueur> findLongueurBySecteur(int secteurId);

	List<Longueur> findLongueurBySite(int siteId);

	void updateLongueur(Longueur longueur);

	void deleteLongueur(int id);

}