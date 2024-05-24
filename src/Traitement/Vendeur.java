package Traitement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Vector;

import Interface.AcheteurFrame;
import Interface.MainFrame;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Vendeur extends Agent {
	private static int nbVendeur;
	private static float prixmax=0f;
	public static int getnbVendeur(){
		return nbVendeur;
	}
	public static void setnbVendeur(){
		nbVendeur++;
		System.out.println(nbVendeur+"54545");
	}
	public static void setnbVendeur2(int i){
		nbVendeur=i;
	}
	protected void  setup(){
		//nbVendeur++;
		ACLMessage msg;
		Produit p=null;
		String ligne="";
		ACLMessage m = null;  
		BufferedReader br = null;
		prixmax = Float.valueOf(AcheteurFrame.getJTprixmax().getText());
		int cpt=0;
		
		while(!MainFrame.termine){
			m=receive();
			
			if(m!=null)
			   { 
					InputStream ips;
					int valeur = MainFrame.ListeAgentsVendeurs.get(getLocalName());
					if(valeur==0){
						for(int i=0; i<AcheteurFrame.agentVendeurs.length;i++){
							try {
								nbVendeur--;
								cpt++;
								if(AcheteurFrame.agentVendeurs[i].getName().equals(getLocalName())){
									if(AcheteurFrame.agentVendeurs[i] !=null) 
										AcheteurFrame.agentVendeurs[i].kill();
								}
							} catch (StaleProxyException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					else{
						try {
							
							ips = new FileInputStream("./Vendeurs/"+getLocalName()+".txt");
							InputStreamReader ipsr=new InputStreamReader(ips);
							 br=new BufferedReader(ipsr);
						} catch (FileNotFoundException e) { e.printStackTrace(); }
						
						//rechercher le produit demande dans le fichier et save it
						try {
							while ((ligne=br.readLine())!=null){
								String [] produit = ligne.split(" ");
								
								if(produit[0].equals(m.getContent()) && Float.valueOf(produit[1]) <= prixmax){
									p= new Produit(produit[0], Float.valueOf(produit[1]), Integer.valueOf(produit[2]), Float.valueOf(produit[3]));
									System.out.println("produit"+p.toString());
									System.out.println(getLocalName());
								}
							}
						} catch (IOException e) { e.printStackTrace();}
							
						 try{
							 
							 ACLMessage reply=m.createReply();
							 reply.setPerformative(ACLMessage.INFORM);
							 reply.setContentObject(p);
							 reply.setLanguage("JavaSerialization");
							 send(reply);      
							 //doWait(300);  ???
						 } catch (IOException e ) {e.printStackTrace();}	
					}
			   }
					
		}
			
		for(int i=0; i<cpt;i++) nbVendeur++;	
	   }

public static  int nbLignes(String fichier) throws IOException {  
		
		
		LineNumberReader nbr;
		nbr = new LineNumberReader(new FileReader(new File(fichier)));
		nbr.skip(Long.MAX_VALUE);
		int nbLignes = nbr.getLineNumber();
		nbr.close();
	return nbLignes;
}

	}

	


