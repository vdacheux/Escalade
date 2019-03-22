package org.escalade.beans;

public class Voie {

	private String nom;
	private String cotation;
	private Integer hauteur;
	private Integer points;
	private int id;
	private int secteurId;

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCotation() {
		return this.cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}

	public Integer getHauteur() {
		return this.hauteur;
	}

	public void setHauteur(Integer hauteur) {
		this.hauteur = hauteur;
	}

	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSecteurId() {
		return this.secteurId;
	}

	public void setSecteurId(int secteurId) {
		this.secteurId = secteurId;
	}

}
