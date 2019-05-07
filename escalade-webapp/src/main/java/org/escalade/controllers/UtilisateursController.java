package org.escalade.controllers;

import javax.inject.Inject;

import org.escalade.dao.UtilisateurDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilisateursController {
	private UtilisateurDao utilisateurDao;

	@Inject
	public void setUtilisateurDao(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	@GetMapping("/")
	public String afficherBonjour(final Model request) {
		request.addAttribute("utilisateurs", this.utilisateurDao.list());
		return "Utilisateurs";
	}

}
