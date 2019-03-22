package org.escalade.beans;

public class Longueur {

	private String cotation;
	private int id;
	private int voieId;

	public String getCotation() {
		return this.cotation;
	}

	public int getId() {
		return this.id;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVoieId() {
		return this.voieId;
	}

	public void setVoieId(int voieId) {
		this.voieId = voieId;
	}

}
