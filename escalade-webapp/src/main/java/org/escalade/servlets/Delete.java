package org.escalade.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.escalade.dao.DaoFactory;
import org.escalade.dao.UtilisateurDao;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;

	@Override
	public void init() throws ServletException {
		final DaoFactory daoFactory = DaoFactory.getInstance();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Delete() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("utilisateurs", this.utilisateurDao.list());
		this.getServletContext().getRequestDispatcher("/WEB-INF/Utilisateurs.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String user = request.getParameter("utilisateur.identifiant");
		this.utilisateurDao.deleteUtilisateur(user);

		request.setAttribute("utilisateurs", this.utilisateurDao.list());

		this.getServletContext().getRequestDispatcher("/WEB-INF/Utilisateurs.jsp").forward(request, response);
	}

}
