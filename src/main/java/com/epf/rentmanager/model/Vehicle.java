package com.epf.rentmanager.model;

public class Vehicle {

	private int id;
	private String constructor;
	private String model;
	private int nbPlace;

	public Vehicle(int id, String constructor, String model, int nbPlace) {
		super();
		this.id = id;
		this.constructor = constructor;
		this.model = model;
		this.nbPlace = nbPlace;
	}

	public int getId() {
		return id;
	}

	public String getConstructor() {
		return constructor;
	}

	public void setConstructor(String constructor) {
		this.constructor = constructor;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getNbPlaces() {
		return nbPlace;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlace = nbPlaces;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", constructor=" + constructor + ", model=" + model + ", nbPlace=" + nbPlace + "]";
	}

}
