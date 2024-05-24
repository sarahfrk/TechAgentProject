package Traitement;

public class proposition {

	private String vendeur;
	private Produit produit;
	private float resultat;
	
	public proposition(String vendeur, Produit produit, float resultat){
		this.vendeur = vendeur;
		this.produit = produit;
		this.resultat = resultat;
	}
	
	public String toString(){
		return vendeur+" "+produit+" "+" "+resultat;
	}
	public String getVendeur() {
		return vendeur;
	}
	public void setVendeur(String vendeur) {
		this.vendeur = vendeur;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public float getResultat() {
		return resultat;
	}
	public void setResultat(float resultat) {
		this.resultat = resultat;
	}
	
	
}
