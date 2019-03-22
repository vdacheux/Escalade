package org.escalade.dao;

import java.util.List;

import org.escalade.beans.Utilisateur;

public interface UtilisateurDao {

	void createUtilisateur(Utilisateur user);

	List<Utilisateur> list();

	Utilisateur findUtilisateur(String identifiant);

	void updateUtilisateur(Utilisateur user);

	void deleteUtilisateur(String identifiant);

}
