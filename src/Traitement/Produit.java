package Traitement;

import jade.util.leap.Serializable;

public class Produit implements Serializable {

	private int qualit�;
	private float frais;
	private String nom;
	private float prix;
	private String info;
	private int nbV;
	
	public Produit(String nom, float prix, int qualit�, float frais){
		this.nom=nom;
		this.prix=prix;
		this.qualit�=qualit�;
		this.frais=frais;
		
	}
	
	public Produit(String nom, int nbV, String info){
		this.nom=nom;
		this.nbV=nbV;
		this.setInfo(info);
	}
	public String toString(){
		 return nom+" "+prix+" "+qualit�+" "+frais;
	}
	
	public String toString2(){
		 return nom+" "+nbV+" "+info;
	}
	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getQualit�() {
		return qualit�;
	}

	public void setQualit�(int qualit�) {
		this.qualit� = qualit�;
	}

	public float getFrais() {
		return frais;
	}

	public void setFrais(float frais) {
		this.frais = frais;
	}

	public int getNbV() {
		return nbV;
	}

	public void setNbV(int nbV) {
		this.nbV=nbV;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}


	
	
}
