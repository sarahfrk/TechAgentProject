package Interface;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Traitement.Produit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JPanel;


public class VendeurFrame extends JFrame {

	private static JTable table;
	private static JTable Liste_produits;
	private static DefaultTableModel model ;
	private static JScrollPane scrollPane;
	private JButton btnActiverAgent;
	private JButton btnDesactiverAgent;
	private  JButton btnProfil ;
	private JButton btnModifier;
	public VendeurFrame(String nomVendeur) throws IOException {
		
	    setTitle(nomVendeur);
	    setPreferredSize(new Dimension(800, 500));
	    setResizable(false);
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/achat.png"));
	    pack();
	    setVisible(true);	
	    setLocationRelativeTo(null);
		
	    addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent evt) {
	        	// affichage boite de dialogue de confirmation de quitter
	        	int confirmSupp = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter ?", "Confirmation de quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(confirmSupp == JOptionPane.OK_OPTION) {
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				else setDefaultCloseOperation(JFrame.NORMAL); // fermer la fenetre
	        }
		});
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(20, 348, 519, 62);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		//Ajouter
		
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(10, 11, 153, 40);
		panel_1.add(btnAjouter);
		btnAjouter.setBackground(new Color(205, 133, 63)); // Marron clair
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAjouter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnAjouter.setFocusPainted(false);
		ImageIcon iconeAjout = new ImageIcon(AcheteurFrame.addImage("./img/ajout.png"));
		 btnAjouter.setIcon(iconeAjout);
		
		
		//Modifier
		btnModifier = new JButton("Modifier");
		btnModifier.setBounds(183, 11, 153, 40);
		panel_1.add(btnModifier);
		btnModifier.setBackground(new Color(205, 133, 63)); // Marron clair
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnModifier.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnModifier.setFocusPainted(false);
		ImageIcon iconeModif= new ImageIcon(AcheteurFrame.addImage("./img/edit.png"));
		btnModifier.setIcon(iconeModif);
		
		//Supprimer
			JButton btnSupprimer = new JButton("Supprimer");
			btnSupprimer.setBounds(356, 11, 153, 40);
			panel_1.add(btnSupprimer);
			btnSupprimer.setBackground(new Color(205, 133, 63)); // Marron clair
			btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 13));
			btnSupprimer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			btnSupprimer.setFocusPainted(false);
			ImageIcon iconeSupp = new ImageIcon(AcheteurFrame.addImage("./img/supp.png"));
			 btnSupprimer.setIcon(iconeSupp);
			
			btnSupprimer.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					int i = Liste_produits.getSelectedRow();
					if(i >=0){
									
						String nomProduit= (String) model.getValueAt(i, 0);
						String prix = (String) model.getValueAt(i, 1);
						//String qualité = (String) model.getValueAt(i, 2);
						String frais = (String) model.getValueAt(i, 3);
						
						String nomfichier = "./Vendeurs/"+nomVendeur+".txt";
						String maligne = nomProduit+" "+prix+" "+frais;					
						for(int j=0; j<ListeProduits.Produits.size();j++){
							if(ListeProduits.Produits.get(j).getNom().equals(nomProduit)){
								int nb=ListeProduits.Produits.get(j).getNbV();
								if(nb==0) continue;
								else{
								ListeProduits.Produits.get(j).setNbV(nb-1);
								}
							}
						}
						try {
							MajProdVendeur.majListeProduits();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						model.removeRow(i);
						try {
							supprimerLigne(nomfichier, maligne);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
						
					
					}
					else{
						
						String message = "Impossible de supprimer";
						    JOptionPane.showMessageDialog(new JFrame(), message, "",
						        JOptionPane.ERROR_MESSAGE);
					}
				}
			
	    });
			btnModifier.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int i = Liste_produits.getSelectedRow();
					Object row[] = new Object[4];
					String nomfichier = nomVendeur+".txt";
					if(i >=0 ){
						row[0] = model.getValueAt(i, 0);
						row[1] = model.getValueAt(i, 1);
						row[2] = model.getValueAt(i, 2);
						row[3] = model.getValueAt(i, 3);
						MajProdVendeur modif = new MajProdVendeur(nomfichier, row[0].toString(), row[1].toString(), row[2].toString(), row[3].toString(), i);
						modif.setVisible(true);
					}
					
					else{
						String message = "Aucune ligne selectionnée";
					    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
					        JOptionPane.ERROR_MESSAGE);
					}
				}
				
			});
			
			btnAjouter.addActionListener(new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					MajProdVendeur ajout = new MajProdVendeur(nomVendeur+".txt");
					ajout.setVisible(true);
				}
	    	
	    });
		
		JLabel lblListeDesProduits = new JLabel("Liste des produits de "+nomVendeur);
		lblListeDesProduits.setBounds(20, 11, 212, 22);
		getContentPane().add(lblListeDesProduits);
		lblListeDesProduits.setOpaque(false);
		lblListeDesProduits.setForeground(new Color(139, 69, 19)); // Marron clair

		
		scrollPane= new JScrollPane();
		scrollPane.setBounds(20, 46, 519, 288);
		getContentPane().add(scrollPane);
		scrollPane.setOpaque(false);
		
	
		//Les données du tableau
	    Object[][] data = loadValues("./Vendeurs/"+nomVendeur+".txt");

	    
	    //Table des produits
	    createTable(data);
	    getContentPane().add(getScrollPane());
		
		Object[] row = new Object[4];
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(561, 46, 223, 171);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setOpaque(false);
	    
	    /*btnDesactiverAgent = new JButton("Desactiver L'agent");
	    btnDesactiverAgent.setBounds(20, 68, 180, 40);
	    panel.add(btnDesactiverAgent);
	    btnDesactiverAgent.setBackground(SystemColor.controlHighlight);
	    btnDesactiverAgent.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnDesactiverAgent.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnDesactiverAgent.setFocusPainted(false);
	    ImageIcon iconeDesac = new ImageIcon(AcheteurFrame.addImage("./img/desactiver.png"));
		 btnDesactiverAgent.setIcon(iconeDesac);
		
	    btnDesactiverAgent.addActionListener(new ActionListener(){
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    			MainFrame.ListeAgentsVendeurs.replace(nomVendeur, 0);
	    			String message = "Agent désactivé";
	    		    JOptionPane.showMessageDialog(new JFrame(), message, "",
	    		        JOptionPane.INFORMATION_MESSAGE);
	    		    btnDesactiverAgent.setVisible(false);
	    		    btnActiverAgent.setVisible(true);
	    	}
	    	
	    });
	    
	    btnActiverAgent = new JButton("Activer L'agent");
	    btnActiverAgent.setBounds(21, 68, 179, 40);
	    panel.add(btnActiverAgent);
	    btnActiverAgent.setBackground(SystemColor.controlHighlight);
	    btnActiverAgent.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnActiverAgent.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnActiverAgent.setFocusPainted(false);
	    ImageIcon iconeAc = new ImageIcon(AcheteurFrame.addImage("./img/activer.png"));
		 btnActiverAgent.setIcon(iconeAc);
		
	    btnActiverAgent.addActionListener(new ActionListener(){
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    			MainFrame.ListeAgentsVendeurs.replace(nomVendeur, 0, 1);
	    			String message = "Agent Activé";
	    		    JOptionPane.showMessageDialog(new JFrame(), message, "",
	    		        JOptionPane.INFORMATION_MESSAGE);
	    		    btnDesactiverAgent.setVisible(true);
	    		    btnActiverAgent.setVisible(false);
	    	}
	    	
	    });
		*/
	    ImageIcon iconeProfile = new ImageIcon(AcheteurFrame.addImage("./img/iconeprofile2.png"));
	    btnProfil = new JButton("Profil");
	    btnProfil.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnProfil.setFocusPainted(false);
	    btnProfil.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnProfil.setBackground(SystemColor.controlHighlight);
	    btnProfil.setBounds(20, 17, 180, 40);
	    btnProfil.setSelectedIcon(null);
	    btnProfil.setIcon(iconeProfile);
	    btnProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProfilVendeur profil;
				try {
					profil = new ProfilVendeur(nomVendeur);
					profil.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}});
	    
	    panel.add(btnProfil);
	    
	    JButton btnQuitter = new JButton("Exit");
	    btnQuitter.setBounds(20, 119, 180, 36);
	    panel.add(btnQuitter);
	    btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnQuitter.setFocusPainted(false);
	    btnQuitter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnQuitter.setBackground(SystemColor.controlHighlight);
	    ImageIcon iconequitter = new ImageIcon(AcheteurFrame.addImage("./img/annuler.png"));
		 btnQuitter.setIcon(iconequitter);
		
	    /*
	    JPanel panel_2 = new JPanel();
	    panel_2.setBounds(573, 250, 193, 139);
	    getContentPane().add(panel_2);
	    panel_2.setLayout(null);
	    
	    JLabel label = new JLabel(new ImageIcon(AcheteurFrame.addImage("./img/vendeur.png")));
	    label.setBounds(0, 0, 193, 139);
	    panel_2.add(label);
	    */
	    btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

	        	int confirmSupp = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter ?", "Confirmation de quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(confirmSupp == JOptionPane.OK_OPTION) VendeurFrame.this.dispose();
				else setDefaultCloseOperation(JFrame.NORMAL); // fermer la fenetre
			}});
	    
	    JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(-15, 0, 800, 500);
        getContentPane().add(lblNewLabel);
        lblNewLabel.setIcon(new ImageIcon("./img/vend.jpg"));
}	   
	
	public static  Object[][] loadValues(String fichier) throws IOException {  
		
		
			LineNumberReader nbr;
			nbr = new LineNumberReader(new FileReader(new File(fichier)));
			nbr.skip(Long.MAX_VALUE);
			int nbLignes = nbr.getLineNumber();
			nbr.close();

		Object[][] data = new Object[nbLignes+1][4] ;
		
		
		try {
			String nom;
			String prix;
			String frais;
			//String qualité;
 
			BufferedReader br = new BufferedReader(new FileReader(fichier));
 
				String line="";
				int i=0; 
				while((line=br.readLine())!=null){
					
							String[] produit = line.split(" ");
							
							nom= produit[0];
							prix = produit[1];
							//qualité = produit[2];
							frais = produit[3];
							
							data[i][0]= nom;
							data[i][1]= prix;
							//data[i][2]= qualité;
							data[i][3]= frais;	
					
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
		VendeurFrame.model = model;
	}


	public void supprimerLigne(String nomfichier, String ligneSupp) throws IOException{
	
		BufferedReader reader = null;
		BufferedWriter writer = null;
		File tempFile = new File("Temp.txt");
		File inputFile = new File(nomfichier);
		int cpt=0;
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			writer = new BufferedWriter(new FileWriter(tempFile, true));
			String lineToRemove = ligneSupp;
			String currentLine;
			
			while((currentLine = reader.readLine()) != null ) {
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(lineToRemove)) {
			    		;continue;
			    }
			    else{
			    	
				    if(cpt!=0) writer.newLine();
				    writer.write(currentLine);
				    writer.flush();
			    	cpt++; 
			    	
			    }
			}
			writer.close();
			reader.close();
			Files.copy(Paths.get("Temp.txt"), Paths.get(nomfichier), StandardCopyOption.REPLACE_EXISTING);
			Files.delete(Paths.get("Temp.txt"));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createTable( Object[][] data){
		
		//Les titres des colonnes
	    Object  columns[] = {"Produit", "Prix"};
	    
		model = new DefaultTableModel();
	    model.setDataVector(data, columns);
	    Liste_produits = new JTable(model);	
	    getScrollPane().setViewportView(Liste_produits);
	    
	}

	public static JScrollPane getScrollPane() {
		return scrollPane;
	}

	public static void setScrollPane(JScrollPane scrollPane) {
		VendeurFrame.scrollPane = scrollPane;
	}
}
