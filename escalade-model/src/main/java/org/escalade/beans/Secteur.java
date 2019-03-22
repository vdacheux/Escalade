package org.escalade.beans;

public class Secteur {

	private String nom;
	private int id;
	private int siteId;

	public String getNom() {
		return this.nom;
	}

	public int getId() {
		return this.id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSiteId() {
		return this.siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
}
