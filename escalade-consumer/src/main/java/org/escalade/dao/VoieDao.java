package org.escalade.dao;

import java.util.List;

import org.escalade.beans.Voie;

public interface VoieDao {

	void createVoie(Voie voie);

	List<Voie> list();

	Voie findVoie(int id);

	Voie findVoieBySecteur(int secteurId);

	Voie findVoieBySite(int siteId);

	void updateVoie(Voie voie);

	void deleteVoie(int id);

}
