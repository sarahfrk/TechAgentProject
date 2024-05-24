package Interface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Traitement.Produit;

import javax.swing.JButton;

public class MajProdVendeur extends JFrame {
	
	private JLabel lblnomProduit;
	private JLabel lblprix;
	private JLabel lblqualité;
	private JLabel lblfrais;
	
	private JTextField tfnomProduit;
	private JTextField tfprix;
	private JTextField tfqualité;
	private JTextField tffrais;

	private JButton btnValider;
	
	public MajProdVendeur(String nomfichier){
		
		setTitle("Ajout produit");
		setResizable(false);
		setPreferredSize(new Dimension(300,220));
		pack();
		setVisible(true);	
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent evt) {
	        	// affichage boite de dialogue de confirmation de quitter
	        	int confirmSupp = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter ?", "Confirmation de quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(confirmSupp == JOptionPane.OK_OPTION) setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				else setDefaultCloseOperation(JFrame.NORMAL); // fermer la fenetre
	        }
		});
		
		lblnomProduit = new JLabel("Produit");
		lblnomProduit.setBounds(28, 33, 46, 14);
		lblnomProduit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblnomProduit);
		
		lblprix = new JLabel("Prix");
		lblprix.setBounds(28, 61, 46, 14);
		lblprix.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblprix);
		
		lblqualité = new JLabel("Qualité");
		lblqualité.setBounds(28, 89, 46, 14);
		lblqualité.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblqualité);
		
		lblfrais = new JLabel("Frais livraison");
		lblfrais.setBounds(28, 123, 95, 14);
		lblfrais.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblfrais);
		
		tfnomProduit = new JTextField();
		tfnomProduit.setBounds(136, 31, 112, 20);
		getContentPane().add(tfnomProduit);
		tfnomProduit.setColumns(10);
		
		tfprix = new JTextField();
		tfprix.setBounds(136, 59, 112, 20);
		getContentPane().add(tfprix);
		tfprix.setColumns(10);
		
		tfqualité = new JTextField();
		tfqualité.setBounds(136, 89, 112, 21);
		getContentPane().add(tfqualité);
		tfqualité.setColumns(10);
		
		tffrais= new JTextField();
		tffrais.setBounds(136, 121, 112, 20);
		getContentPane().add(tffrais);
		tffrais.setColumns(10);
		
		btnValider= new JButton("Valider");
		btnValider.setBounds(97, 148, 89, 23);
		getContentPane().add(btnValider);
	
		btnValider.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					//ligne après modification
					String newline= tfnomProduit.getText()+" "+tfprix.getText()+" "+tfqualité.getText()+" "+tffrais.getText();
					
					try {
						addLine(nomfichier,newline);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					boolean exist = false;
					for(int i=0; i<ListeProduits.Produits.size();i++){
						if(ListeProduits.Produits.get(i).getNom().equals(tfnomProduit.getText())){
							int nb=ListeProduits.Produits.get(i).getNbV();
							ListeProduits.Produits.get(i).setNbV(nb+1);
							exist = true;
						}
					}
					if(!exist){
						ListeProduits.Produits.add(new Produit (tfnomProduit.getText(),1,"Fab://; Exp://"));
					}
					try {
						majListeProduits();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Mise a jour de la table du vendeur
					Object[] row = new Object[4];
					
					row[0] = tfnomProduit.getText();
					row[1] = tfprix.getText();
					row[2] = tfqualité.getText();
					row[3] = tffrais.getText();
					
				    VendeurFrame.getModel().addRow(row);
				    MajProdVendeur.this.dispose();

				}
		    	
		    });
		
		
		
	}
	
	public MajProdVendeur(String nomfichier, String nomProd, String prix, String qualité, String frais, int numligne){
		
		setTitle("Modification produit");
		setResizable(false);
		setPreferredSize(new Dimension(300,210));
		pack();
		setVisible(true);	
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent evt) {
	        	// affichage boite de dialogue de confirmation de quitter
	        	int confirmSupp = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter ?", "Confirmation de quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(confirmSupp == JOptionPane.OK_OPTION) setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				else setDefaultCloseOperation(JFrame.NORMAL); // fermer la fenetre
	        }
		});
		
	
		lblnomProduit = new JLabel("Produit");
		lblnomProduit.setBounds(28, 33, 46, 14);
		lblnomProduit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblnomProduit);
		
		lblprix = new JLabel("Prix");
		lblprix.setBounds(28, 61, 46, 14);
		lblprix.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblprix);
		
		lblqualité = new JLabel("Qualité");
		lblqualité.setBounds(28, 89, 46, 14);
		lblqualité.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblqualité);
		
		lblfrais = new JLabel("Frais livraison");
		lblfrais.setBounds(28, 123, 95, 14);
		lblfrais.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(lblfrais);
		
		tfnomProduit = new JTextField(nomProd);
		tfnomProduit.setBounds(136, 31, 112, 20);
		getContentPane().add(tfnomProduit);
		tfnomProduit.setColumns(10);
		
		tfprix = new JTextField(prix);
		tfprix.setBounds(136, 59, 112, 20);
		getContentPane().add(tfprix);
		tfprix.setColumns(10);
		
		tfqualité = new JTextField(qualité);
		tfqualité.setBounds(136, 89, 112, 21);
		getContentPane().add(tfqualité);
		tfqualité.setColumns(10);
		
		tffrais= new JTextField(frais);
		tffrais.setBounds(136, 121, 112, 20);
		getContentPane().add(tffrais);
		tffrais.setColumns(10);
		
		btnValider= new JButton("Valider");
		btnValider.setBounds(97, 148, 89, 23);
		getContentPane().add(btnValider);
		
		 btnValider.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					//ligne avant modification
					String oldline=nomProd+" "+prix+" "+qualité+" "+frais;
					
					//ligne après modification
					String newline= tfnomProduit.getText()+" "+tfprix.getText()+" "+tfqualité.getText()+" "+tffrais.getText();
					
					//Mise a jour dans le fichier du vendeur
					
					try {
						updateLine(nomfichier, oldline, newline);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					MajProdVendeur.this.dispose();
					
					//Mise a jour de la table du vendeur
					VendeurFrame.getModel().setValueAt(tfnomProduit.getText(),numligne,0);
					VendeurFrame.getModel().setValueAt(tfprix.getText(),numligne,1);
					VendeurFrame.getModel().setValueAt(tfqualité.getText(),numligne,2);
					VendeurFrame.getModel().setValueAt(tffrais.getText(),numligne,3);
				}
		    	
		    });
	
	}

	 private  void addLine(String nomfichier, String lineToAdd) throws IOException {
			
			File data = new File("./Vendeurs/"+nomfichier);
			//int cpt=0;
			BufferedReader reader = new BufferedReader(new FileReader(data));
		    BufferedWriter writer = new BufferedWriter(new FileWriter(data, true));
		    String line="";
		    int cpt=0;
		    while ((line = reader.readLine()) != null)
		    {
		    	cpt++;
		    }
		    if(cpt!=0)writer.newLine();
		    writer.write(lineToAdd);
		    writer.flush();
		    writer.close();

		}
	 
	private void updateLine(String nomfichier, String toUpdate, String updated) throws IOException {
		
		File data = new File("./Vendeurs/"+nomfichier);
	    BufferedReader file = new BufferedReader(new FileReader(data));
	    BufferedWriter writer = new BufferedWriter(new FileWriter("tmp.txt", true));
	    String line="";
	    int cpt=0;
	    while ((line = file.readLine()) != null)
	    {
	        line = line.replace(toUpdate, updated);
	        if(cpt != 0) writer.newLine();
		    writer.write(line);
		    writer.flush();
		    cpt++;
	    }
	    writer.close();
	    file.close();

	    Files.copy(Paths.get("tmp.txt"), Paths.get("./Vendeurs/"+nomfichier), StandardCopyOption.REPLACE_EXISTING);
		Files.delete(Paths.get("tmp.txt"));
	}
	
	
	 
	 public static void majListeProduits() throws IOException{

		   	String nomfichier= "ListeProduits";
		    BufferedWriter writer = new BufferedWriter(new FileWriter("tmp.txt", true));
		    String line="";
		    int cpt=0;
		    for(int i=0; i<ListeProduits.Produits.size();i++){
		    	line=ListeProduits.Produits.get(i).getNom()+" "+ListeProduits.Produits.get(i).getNbV()+" "+ListeProduits.Produits.get(i).getInfo();
		    	System.out.println(line);   
		    	if(cpt !=0) writer.newLine();
				    writer.write(line);
				    writer.flush();
				    cpt++;

		    }
		
		    writer.close();
		    Files.copy(Paths.get("tmp.txt"), Paths.get("./fichiers/"+nomfichier+".txt"), StandardCopyOption.REPLACE_EXISTING);
			Files.delete(Paths.get("tmp.txt"));
	 }
}
