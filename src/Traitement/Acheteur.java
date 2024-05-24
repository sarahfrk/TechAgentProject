package Traitement;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; 

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Interface.AcheteurFrame;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Acheteur extends Agent {
	
	private Produit produit;
	private ACLMessage envoye;
	private ACLMessage reçu;
	private String nom;
	private String importancePrix;
	private String importanceQualité;
	private String importanceFrais;
	private float prixmax;
	private int bestVendeur;
	private float p_prix;
	private float p_qualité;
	private float p_frais;
	Vector<Float> poids = new Vector<Float>();
	Vector<proposition> preferences = new Vector<proposition>();
	
	@SuppressWarnings("null")
	public void setup() {
		
			Object[] args = getArguments();
			
			if(args!=null){
			nom= AcheteurFrame.getNomProduit();
			importancePrix = AcheteurFrame.getImportancePrix();
			importanceQualité = AcheteurFrame.getImportanceQualité();
			importanceFrais = AcheteurFrame.getImportanceFrais();
			poids = retorunerPoids(importancePrix,importanceQualité,importanceFrais);		
			p_prix=poids.get(0);
			p_qualité=poids.get(1);
			p_frais=poids.get(2);
			
			envoye = new ACLMessage(ACLMessage.INFORM);
			for(int i=0;i<Vendeur.getnbVendeur();i++){				
				envoye.addReceiver(new AID("Vendeur"+i, AID.ISLOCALNAME));
			}
			envoye.setContent(nom);
			send(envoye);
			
			bestVendeur = 0;
			
			int i=0;
			Produit prod=null;
			String sender=null;
			boolean exist=false;
		//	boolean termine=false;
			while( i<Vendeur.getnbVendeur())
		 	{  
		 		reçu=blockingReceive();	
		 		if(reçu!=null && !reçu.getContent().equals("Desactive"))
		 		{
		 			
					try {
						//recevoir les propositions de vendeurs
						prod=(Produit) reçu.getContentObject();
						sender=reçu.getSender().getLocalName();
						if(prod!=null){
							exist=true;
						
						if(prod.getNom()!=""){
						
							proposition p= new proposition(sender,prod,(float)0);
							preferences.add(p);	
						}}
										
					} catch(UnreadableException e){System.err.println(getLocalName()+ " catched exception "+e.getMessage());}
				}
				  i++; 
		
			}

		//	Normalisation : AJOUTER LA NORMALISATION, JE L AI ACCIDENTTELLEMENT EFFACE
			
			if(exist){
			for(int j=0; j<preferences.size();j++){
				float pref= (preferences.get(j).getProduit().getPrix()/15*p_prix)
						+(preferences.get(j).getProduit().getFrais()*p_frais)
						+(preferences.get(j).getProduit().getQualité()/8*p_qualité);
				
				
				preferences.get(j).setResultat(pref);
			}
			
			float prefmax= preferences.get(0).getResultat();
			for(int k=1; k<preferences.size();k++){
				if(preferences.get(k).getResultat()>prefmax) prefmax=preferences.get(k).getResultat();
			}
			
			for(int j=0; j<preferences.size();j++){
				preferences.get(j).setResultat(preferences.get(j).getResultat()/prefmax);
				
			}
			if(max(preferences) != null){
				Vector<proposition> winner = max(preferences);
				
				Object[] win = new Object[5];
				for(int j=0; j<winner.size(); j++){
				win[0] = winner.get(j).getVendeur();
				win[1] =winner.get(j).getProduit().getNom();
				win[2] =winner.get(j).getProduit().getPrix();
				win[3] =winner.get(j).getProduit().getQualité();
				win[4] =winner.get(j).getProduit().getFrais();
			    AcheteurFrame.getModel().addRow(win);
		
				}
			}
			else {
				AcheteurFrame.AucuneOffre();
			}

	
			}
			else {
				AcheteurFrame.AucuneOffre();
			}
			}
		
	
		
	}


	
	//Il faut changer et retourner un objet avec nom vendeur et produit 

	public Vector<proposition> max(Vector<proposition> p){
		
		int x=0;
		Vector<proposition> meilleures= new Vector<proposition>();
		float a= p.get(0).getResultat();
		for(int i=1;i<p.size();i++){
		
			if(p.get(i).getResultat()>a) {a=p.get(i).getResultat(); x=i;
			}
		}

		meilleures.add(p.get(x));
		
		for(int i=0; i<p.size();i++){
			if(p.get(i).getResultat()==p.get(x).getResultat() && i != x){
			
				meilleures.add(p.get(i));
			}
		}
		return meilleures;
	}
	
	public Vector<Float> retorunerPoids(String importancePrix, String importanceQualité, String importanceFrais){
		
		Vector<Float> poids = new Vector<Float>();
		float poidsPrix=0f;
		float poidsQualité =0f;
		float poidsFrais =0f;
		
	    /////////PRix//////////
	    if(importancePrix.equals("Très important")) poidsPrix = -0.6f;
	    if(importancePrix.equals("Important")) poidsPrix = -0.3f;
	    if(importancePrix.equals("Moyennement important")) poidsPrix = -0.2f;
	    if(importancePrix.equals("Peu important")) poidsPrix = -0.1f;
	    
	    /////Qualité//////////
		if(importanceQualité.equals("Très importante")) poidsQualité = 0.6f;
		if(importanceQualité.equals("Importante")) poidsQualité = 0.3f;
		if(importanceQualité.equals("Moyennement importante")) poidsQualité = 0.2f;
		if(importanceQualité.equals("Peu importante")) poidsQualité = 0.1f;
	
		/////Qualité//////////
		if(importanceFrais.equals("Très important")) poidsFrais = -0.6f;
		if(importanceFrais.equals("Important")) poidsFrais = -0.3f;
		if(importanceFrais.equals("Moyennement important")) poidsFrais = -0.2f;
		if(importanceFrais.equals("Peu important")) poidsFrais = -0.1f;
		
		poids.add(poidsPrix);
		poids.add(poidsQualité);
		poids.add(poidsFrais);
	return poids;
	}
}
