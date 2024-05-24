package Interface;

import java.awt.Dimension;
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

public class ListeVendeurs extends JFrame{


	private static JTable ListeVendeurs;
	private static DefaultTableModel model ;
	private static JScrollPane scrollPaneListeVendeurs;
	
	public ListeVendeurs()  {
		
	    setTitle("Liste des vendeurs");
	    setPreferredSize(new Dimension(530, 400));
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    pack();
	    setVisible(true);	
	    setLocationRelativeTo(null);
	    getContentPane().setLayout(null);
	
		
		JLabel lblListeDesProduits = new JLabel("Liste des vendeurs");
		lblListeDesProduits.setBounds(20, 11, 117, 22);
		getContentPane().add(lblListeDesProduits);
		
		scrollPaneListeVendeurs= new JScrollPane();
		scrollPaneListeVendeurs.setBounds(20, 46, 482, 186);
		getContentPane().add(getScrollPane());
	
		//Les données du tableau
	    Object[][] data = null;
		try {
			data = loadValues("./fichiers/ListeVendeurs.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    
	    //Table des produits
	    createTable(data);
	    getContentPane().add(scrollPaneListeVendeurs);
	   
}
	
	public static  Object[][] loadValues(String fichier) throws IOException {  
		
		
		LineNumberReader nbr;
		nbr = new LineNumberReader(new FileReader(new File(fichier)));
		nbr.skip(Long.MAX_VALUE);
		int nbLignes = nbr.getLineNumber();

		nbr.close();

	Object[][] data = new Object[nbLignes+1][3] ;
	
	
	try {
		String nom;
		String adresse;
		String tel;
		

		BufferedReader br = new BufferedReader(new FileReader(fichier));

			String line="";
			int i=0; 
			while((line=br.readLine())!=null){
				String[] vendeur = line.split(" ");
				
					nom= vendeur[0];
					adresse = vendeur[1];
					tel = vendeur[2];

					data[i][0]= nom;
					data[i][1]= tel;
					data[i][2]=adresse;

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
		ListeVendeurs.setModel( model);
	}

	

	public static void createTable( Object[][] data){
		
		//Les titres des colonnes
	    Object  columns[] = {"Vendeur", "Telephone", "Adresse"};
	    
		model = new DefaultTableModel();
	    model.setDataVector(data, columns);
	    ListeVendeurs = new JTable(model);	
	    getScrollPane().setViewportView(ListeVendeurs);
	    
	}
	

	public static JScrollPane getScrollPane() {
		return scrollPaneListeVendeurs;
	}

	public static void setScrollPane(JScrollPane scrollPane) {
		scrollPaneListeVendeurs=scrollPane;
	}

}
