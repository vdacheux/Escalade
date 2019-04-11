package org.escalade.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.escalade.beans.Utilisateur;
import org.escalade.dao.UtilisateurDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Servlet implementation class ServletUtilisateurs
 */
@WebServlet("/ServletUtilisateurs")
public class ServletUtilisateurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;

	@Autowired
	public void setUtilisateurDao(UtilisateurDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	@Override
	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletUtilisateurs() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final Utilisateur utilisateurs = new Utilisateur();
		request.setAttribute("utilisateurs", this.utilisateurDao.list());
		this.getServletContext().getRequestDispatcher("/WEB-INF/Utilisateurs.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Utilisateur utilisateur = new Utilisateur();

		if (request.getParameter("envoyer") != null) {
			utilisateur.setIdentifiant(request.getParameter("identifiant"));
			utilisateur.setPassword(request.getParameter("password"));
			utilisateur.setMail(request.getParameter("mail"));
			utilisateur.setNom(request.getParameter("nom"));
			utilisateur.setPrenom(request.getParameter("prenom"));
			final Utilisateur utilisateurs = new Utilisateur();
			this.utilisateurDao.createUtilisateur(utilisateur);

		} else if (request.getParameter("supprimer") != null) {
			final String deleteUser = request.getParameter("deleteId");
			this.utilisateurDao.deleteUtilisateur(deleteUser);
		}
		request.setAttribute("utilisateurs", this.utilisateurDao.list());
		this.getServletContext().getRequestDispatcher("/WEB-INF/Utilisateurs.jsp").forward(request, response);
	}

}
