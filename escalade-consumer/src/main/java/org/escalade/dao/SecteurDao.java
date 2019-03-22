package org.escalade.dao;

import java.util.List;

import org.escalade.beans.Secteur;

public interface SecteurDao {

	void createSecteur(Secteur secteur);

	List<Secteur> list();

	Secteur findSecteur(int id);

	Secteur findSecteurBySite(int siteId);

	void updateSecteur(Secteur secteur);

	void deleteSecteur(int id);

}