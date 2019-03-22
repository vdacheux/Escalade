package org.escalade.dao;

import java.util.List;

import org.escalade.beans.Commentaire;
import org.escalade.beans.Site;
import org.escalade.beans.Utilisateur;

public interface CommentaireDao {

	void createCommentaire(Commentaire comment, Utilisateur user, Site site);

	List<Commentaire> list();

	Commentaire findCommentaire(int id);

	List<Commentaire> findCommentaireByUtilisateur(int userId);

	List<Commentaire> findCommentaireByUtilisateurIdentifiant(String identifiant);

	List<Commentaire> findCommentaireBySite(int siteId);

	void updateCommentaire(Commentaire comment);

	void deleteCommentaire(int id);

}
