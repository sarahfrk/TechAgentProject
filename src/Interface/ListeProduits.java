package Interface;

import java.awt.Dimension ;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Traitement.Produit;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ListeProduits extends JFrame {

	private static JTable ListeProduits;
	private static DefaultTableModel model ;
	private static JScrollPane scrollPaneListeProduits;
	public static Vector<Produit> Produits = new Vector<Produit>() ;
	public ListeProduits()  {
		
	    setTitle("Liste des produits");
	    setPreferredSize(new Dimension(530, 400));
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    pack();
	    setVisible(true);	
	    setLocationRelativeTo(null);
	    getContentPane().setLayout(null);
		
	  	JLabel lblListeDesProduits = new JLabel("Liste des produits");
		lblListeDesProduits.setBounds(20, 11, 117, 22);
		getContentPane().add(lblListeDesProduits);
		
		scrollPaneListeProduits= new JScrollPane();
		scrollPaneListeProduits.setBounds(20, 46, 482, 186);
		getContentPane().add(getScrollPane());
	
		//Les données du tableau
	    Object[][] data = null;
		try {
			data = loadValues("./fichiers/ListeProduits.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    
	    //Table des produits
	    createTable(data);
	    getContentPane().add(scrollPaneListeProduits);
	   
}
	
	@SuppressWarnings("null")
	public static  Object[][] loadValues(String fichier) throws IOException {  
		
			LineNumberReader nbr;
			nbr = new LineNumberReader(new FileReader(new File(fichier)));
			nbr.skip(Long.MAX_VALUE);
			int nbLignes = nbr.getLineNumber();

			nbr.close();

		Object[][] data = new Object[nbLignes+1][3] ;
		
		
		try {
			String nom;
			String infos;
			String nbVendeurs;
			Produit p =null;
			List<String> s = null;
			BufferedReader br = new BufferedReader(new FileReader(fichier));
 
				String line="";
				int i=0; 
				while((line=br.readLine())!=null){
					String[] produit = line.split(" ");
					
						nom= produit[0];
						nbVendeurs=produit[1];
						infos = produit[2];		
						if(Integer.valueOf(nbVendeurs)==0) continue;
						else{
							data[i][0]= nom;
							data[i][1]= nbVendeurs;
							data[i][2]= infos;
						
							p= new Produit(nom,Integer.valueOf(nbVendeurs),infos);
							Produits.add(p);
						}
						
				i++;}
			
 
			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	return data;
	}
	
	public static DefaultTableModel getModel() {
		return model;
	}

	public static void setModel(DefaultTableModel model) {
		ListeProduits.setModel(model);
	}

	

	public static void createTable( Object[][] data){
		
		//Les titres des colonnes
	    Object  columns[] = {"Produit","Disponibilité",  "Informations"};
	    
		model = new DefaultTableModel();
	    model.setDataVector(data, columns);
	   
	    ListeProduits = new JTable(model);	
	    getScrollPane().setViewportView(ListeProduits);
	    
	}
	

	public static JScrollPane getScrollPane() {
		return scrollPaneListeProduits;
	}

	public static void setScrollPane(JScrollPane scrollPane) {
		scrollPaneListeProduits = scrollPane;
	}

}