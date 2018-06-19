package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the couleurjante database table.
 * 
 */
@Entity
@NamedQuery(name="Couleurjante.findAll", query="SELECT c FROM Couleurjante c")
public class Couleurjante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCouleurJante;

	private double coefficient;

	private String finition;

	private String nom;

//	//bi-directional many-to-one association to Jante
//	@OneToMany(mappedBy="couleurjante")
//	private List<Jante> jantes;

	public Couleurjante() {
	}

	public int getIdCouleurJante() {
		return this.idCouleurJante;
	}

	public void setIdCouleurJante(int idCouleurJante) {
		this.idCouleurJante = idCouleurJante;
	}

	public double getCoefficient() {
		return this.coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public String getFinition() {
		return this.finition;
	}

	public void setFinition(String finition) {
		this.finition = finition;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}