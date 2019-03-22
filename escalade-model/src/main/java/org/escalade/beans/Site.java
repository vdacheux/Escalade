package org.escalade.beans;

import java.util.ArrayList;
import java.util.List;

public class Site {

	private String nom;
	private String description;
	private String departement;
	private int id;
	private final List<Topo> topos = new ArrayList<>();

	public String getDescription() {
		return this.description;
	}

	public List<Topo> getTopos() {
		return this.topos;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDepartement() {
		return this.departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

}
