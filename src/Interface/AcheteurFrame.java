package Interface;

import javax.swing.JFrame;  
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Traitement.Acheteur;
import Traitement.Produit;
import Traitement.Vendeur;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.leap.HashMap;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.*;

public class AcheteurFrame extends JFrame {

	private static JTable VendeurGagnant;
	private static JTextField JTprixmax;
	private static DefaultTableModel model;
	private static JLabel lblWinner;
	private static JLabel lblprixmaxvide;
	private static String nomProduit;
	private static String importancePrix;
	private static String importanceQualité;
	private static String importanceFrais;
	
	public static Runtime rt ;
	static ProfileImpl profile ;
	static ContainerController mc ;
	public static AgentController[] agentVendeurs;
    private static AgentController agentAcheteur;
    
	

	private static float prixmax;
 
	public AcheteurFrame(String nomAcheteur) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/icone_buy.png"));
		
	    setTitle("Buyer "+nomAcheteur);
	    setPreferredSize(new Dimension(800, 550));
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    pack();
	    setVisible(true);	
	    setLocationRelativeTo(null);
	    getContentPane().setLayout(null);
	    
	    /*
	    JPanel panel = new JPanel();
	    panel.setBounds(27, 209, 242, 151);
	    panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    getContentPane().add(panel);
	    panel.setLayout(null);
	    
	    JLabel lblprix = new JLabel("Prix");
	    lblprix.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    lblprix.setBounds(10, 24, 43, 26);
	    panel.add(lblprix);
	    
	    JComboBox cbprix = new JComboBox();
	    cbprix.setBounds(85, 25, 148, 26);
	    panel.add(cbprix);
	    cbprix.setModel(new DefaultComboBoxModel(new String[] {"Tr\u00E8s important", "Important", "Moyennement important", "Peu important"}));
	    
	    JLabel lblqualité = new JLabel("Qualit\u00E9");
	    lblqualité.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    lblqualité.setBounds(10, 66, 64, 17);
	    panel.add(lblqualité);
	    
	    JComboBox cbqualite = new JComboBox();
	    cbqualite.setBounds(85, 62, 148, 26);
	    panel.add(cbqualite);
	    cbqualite.setModel(new DefaultComboBoxModel(new String[] {"Tr\u00E8s importante", "Importante", "Moyennement importante", "Peu importante"}));
	    
	    JLabel lblfrais = new JLabel("Frais");
	    lblfrais.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    lblfrais.setBounds(11, 103, 42, 17);
	    panel.add(lblfrais);
	    
	    JComboBox cbfrais = new JComboBox();
	    cbfrais.setBounds(85, 99, 148, 26);
	    panel.add(cbfrais);
	    cbfrais.setModel(new DefaultComboBoxModel(new String[] {"Tr\u00E8s important", "Important", "Moyennement important", "Peu important"}));
	    */
	    /*
	    JPanel Menu = new JPanel();
	    Menu.setBounds(0, 11, 784, 47);
	    Menu.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    Menu.setForeground(Color.GRAY);
	    Menu.setBackground(new Color(173, 216, 230));

	    getContentPane().add(Menu);
	    Menu.setLayout(null);*/
	    
	    //ImageIcon iconeListe = new ImageIcon(addImage("./img/liste.png"));
	    
	    JButton btnConsulterListeProduits = new JButton("Produits");
	    //btnConsulterListeProduits.setBackground(new Color(255, 102, 51));
	    btnConsulterListeProduits.setSelectedIcon(null);
	    btnConsulterListeProduits.setForeground(Color.BLACK);
	    btnConsulterListeProduits.setFont(new Font("Tahoma", Font.BOLD, 14));
	    btnConsulterListeProduits.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnConsulterListeProduits.setBounds(351, 0, 172, 47);
	    btnConsulterListeProduits.setFocusPainted(false);
	    //btnConsulterListeProduits.setIcon(iconeListe);
	    //Menu.add(btnConsulterListeProduits);
	    
	    /*
	    JButton btnListeDesVendeurs = new JButton("Liste des vendeurs");
	    btnListeDesVendeurs.setBounds(183, 0, 172, 47);
	    Menu.add(btnListeDesVendeurs);
	    btnListeDesVendeurs.setBackground(new Color(255, 102, 51));
	    btnListeDesVendeurs.setForeground(Color.WHITE);
	    btnListeDesVendeurs.setFont(new Font("Tahoma", Font.BOLD, 14));
	    btnListeDesVendeurs.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnListeDesVendeurs.setIcon(iconeListe);
	    btnListeDesVendeurs.setFocusPainted(false);
	    */
	    //ImageIcon iconeProfile = new ImageIcon(addImage("./img/profil.png"));
	    JButton btnProfile = new JButton("Profil");
	    btnProfile.setBounds(523, 0, 100, 47);
	    //Menu.add(btnProfile);
	    btnProfile.setSelectedIcon(null);
	    //btnProfile.setBackground(new Color(0, 51, 51));
	    btnProfile.setFont(new Font("Tahoma", Font.BOLD, 14));
	    btnProfile.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnProfile.setFocusPainted(false);
	    //btnProfile.setIcon(iconeProfile);
	    btnProfile.setForeground(Color.BLACK);
	    btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProfilAcheteur profil;
				try {
					profil = new ProfilAcheteur(nomAcheteur);
					profil.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}});
	    
	    JButton btnDconnexion = new JButton("D\u00E9connexion");
	    btnDconnexion.setBounds(656, 0, 128, 47);
	    //Menu.add(btnDconnexion);
	    btnDconnexion.setForeground(Color.BLACK);
	    btnDconnexion.setFont(new Font("Tahoma", Font.BOLD, 14));
	    //btnDconnexion.setBackground(new Color(0, 51, 51));
	    btnDconnexion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnDconnexion.setFocusPainted(false);
	    btnDconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// affichage boite de dialogue de confirmation de quitter
	        	int confirmSupp = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment vous déconnecter ?", "Confirmation de quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(confirmSupp == JOptionPane.OK_OPTION) AcheteurFrame.this.dispose();
				else setDefaultCloseOperation(JFrame.NORMAL); // fermer la fenetre
			}});

	    JLabel lblBienvenue = new JLabel(nomAcheteur);
	    lblBienvenue.setForeground(SystemColor.textHighlightText);
	    lblBienvenue.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
	    lblBienvenue.setBounds(10, 15, 153, 18);
	    //Menu.add(lblBienvenue);
	    
	    JPanel panelProduit = new JPanel();
	    panelProduit.setBounds(27, 86, 242, 112);
	    panelProduit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    getContentPane().add(panelProduit);
	    panelProduit.setLayout(null);
	    
	    JLabel lblPrixMax = new JLabel("Prix max");
	    lblPrixMax.setBounds(10, 73, 49, 16);
	    panelProduit.add(lblPrixMax);
	    lblPrixMax.setFont(new Font("Tahoma", Font.PLAIN, 13));
	  
	    lblprixmaxvide = new JLabel("*");
	    lblprixmaxvide.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblprixmaxvide.setBounds(73, 74, 20, 16);
	    lblprixmaxvide.setForeground(Color.RED);
	    panelProduit.add(lblprixmaxvide);
	    lblprixmaxvide.setVisible(false);
	    
	    JLabel lblProduit = new JLabel("Produit");
	    lblProduit.setBounds(10, 24, 40, 16);
	    panelProduit.add(lblProduit);
	    lblProduit.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    
	    JTprixmax = new JTextField();
	    JTprixmax.setBounds(85, 69, 144, 27);
	    panelProduit.add(JTprixmax);
	    JTprixmax.setColumns(10);
	    
	    String[] produits=null;
	    try {
			produits = loadValues("./fichiers/ListeProduits.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JComboBox cbProduit = new JComboBox();
	    cbProduit.setBounds(85, 17, 144, 33);
	    cbProduit.setModel(new DefaultComboBoxModel<String>(produits));
	    panelProduit.add(cbProduit);
	    /*
	    JPanel panel_1 = new JPanel();
	    panel_1.setBounds(406, 237, 284, 219);
	    getContentPane().add(panel_1);
	    panel_1.setLayout(null);
	    JLabel picLabel = new JLabel(new ImageIcon(addImage("./img/buyer.png")));
	    picLabel.setBounds(10, 11, 264, 197);
	    panel_1.add(picLabel);
	      */
	     
	    
	    ImageIcon iconeRechercher = new ImageIcon(addImage("./img/loupe.png"));
	    JButton btnRechercher = new JButton("Rechercher");
	    btnRechercher.setBounds(27, 371, 242, 40);
	    btnRechercher.setBackground(SystemColor.YELLOW);
	    btnRechercher.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnRechercher.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnRechercher.setFocusPainted(false);
	    btnRechercher.setIcon(iconeRechercher);
	    getContentPane().add(btnRechercher);
	    btnRechercher.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(JTprixmax.getText().isEmpty()){
					lblprixmaxvide.setVisible(true);
				}
				else{
				if(agentAcheteur != null){
						try {
							agentAcheteur.kill();
							for(int i=0; i<AcheteurFrame.agentVendeurs.length; i++){							
								if(AcheteurFrame.agentVendeurs[i] != null)
									AcheteurFrame.agentVendeurs[i].kill();		
							}
						} catch (StaleProxyException e1) {}
				}
				setNomProduit(cbProduit.getSelectedItem().toString());
				setImportancePrix("Tr\\u00E8s important");
				setImportanceQualité("Tr\\u00E8s important");
				setImportanceFrais("Tr\\u00E8s important");
				AgentLanceur(nomAcheteur,cbProduit.getSelectedItem().toString());
				}
			}});
	    
	    Object  columns[] = {"Vendeur", "Produit", "Prix"};
	    
		model = new DefaultTableModel();
	    VendeurGagnant = new JTable(model);	
	    VendeurGagnant.setForeground(SystemColor.windowBorder);
	    VendeurGagnant.setFont(new Font("Tahoma", Font.BOLD, 10));
	    model.setColumnIdentifiers(columns);
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(313, 86, 461, 88);
	    scrollPane.setViewportView(VendeurGagnant);	    
	    getContentPane().add(scrollPane);
	    
	    
	    ImageIcon iconeValider = new ImageIcon(addImage("./img/valider.png"));
	    JButton btnValiderLachat = new JButton("Valider");
	    btnValiderLachat.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnValiderLachat.setBackground(Color.GREEN);
	    btnValiderLachat.setBounds(312, 186, 227, 40);
	    btnValiderLachat.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnValiderLachat.setFocusPainted(false);
	    btnValiderLachat.setIcon(iconeValider);
	    getContentPane().add(btnValiderLachat); 
		btnValiderLachat.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object[] row = new Object[6];
				int i = VendeurGagnant.getSelectedRow();
				String nomfichier = nomAcheteur+".txt";
				if(i >=0 ){
					Date actuelle = new Date();
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String date = dateFormat.format(actuelle);

					String contenuLblPrixMax = lblPrixMax.getText();
					float prixMax2 = Float.parseFloat(JTprixmax.getText());

					row[0] = model.getValueAt(i, 0); //produit
					row[1] = model.getValueAt(i, 1); //achteur
					row[2] = model.getValueAt(i, 2); //prix init
					row[3] = prixMax2; //model.getValueAt(i, 3);  prix propose
					row[4] = 7;
					

					String vendeur = "./Vendeurs/Historique/H"+row[0]+".txt";
					String line=row[1].toString()+" "+row[0].toString()+" "+row[2].toString()+" "+row[3].toString()+" "+row[4].toString()+" "+date;
					String linev = row[1].toString()+" "+nomAcheteur+" "+row[2].toString()+" "+row[3].toString()+" "+row[4].toString()+" "+date;
					try {
						addLine("./Acheteurs/"+nomfichier,line);
						addLine(vendeur, linev);
						String message = row[1]+" a été ajouté à votre panier";
					    JOptionPane.showMessageDialog(new JFrame(), message, "Validation Achat",
					        JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				else{
					String message = "Aucune ligne selectionnée";
				    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
				        JOptionPane.ERROR_MESSAGE);
				}
			}
	    	
	    });
		
		//Annuler Achat
	    ImageIcon iconeAnnuler = new ImageIcon(addImage("./img/annuler.png"));
	    JButton btnAnnuler = new JButton("Annuler ");
	    btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnAnnuler.setBackground(Color.RED);
	    btnAnnuler.setBounds(549, 186, 225, 40);
	    btnAnnuler.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnAnnuler.setFocusPainted(false);
	    btnAnnuler.setIcon(iconeAnnuler);
	    getContentPane().add(btnAnnuler);
	    btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.setRowCount(0);
				JTprixmax.setText("");
				cbProduit.setSelectedIndex(0);
				/*cbprix.setSelectedIndex(0);
				cbqualite.setSelectedIndex(0);
				cbfrais.setSelectedIndex(0);*/
			}});
	    
	    //Réinitailiser
	    /*
	    ImageIcon iconeReset = new ImageIcon(addImage("./img/reset.png"));
	    JButton btnReinitialiser = new JButton("R\u00E9initialiser ");
	    btnReinitialiser.setBackground(SystemColor.controlHighlight);
	    btnReinitialiser.setBounds(27, 421, 242, 40);
	    btnReinitialiser.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnReinitialiser.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnReinitialiser.setFocusPainted(false);
	    btnReinitialiser.setIcon(iconeReset);
	    getContentPane().add(btnReinitialiser);
	    btnReinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTprixmax.setText("");
				cbProduit.setSelectedIndex(0);
				cbprix.setSelectedIndex(0);
				cbqualite.setSelectedIndex(0);
				cbfrais.setSelectedIndex(0);
			}});
	    btnListeDesVendeurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					ListeVendeurs vendeurs = new ListeVendeurs();
					vendeurs.setVisible(true);
			}});
	    */
	    btnConsulterListeProduits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					ListeProduits produits = new ListeProduits();
					produits.setVisible(true);
			}});
	
	    addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent evt) {
	        	// affichage boite de dialogue de confirmation de quitter
	        	int confirmSupp = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(confirmSupp == JOptionPane.OK_OPTION) {
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				else setDefaultCloseOperation(JFrame.NORMAL); // fermer la fenetre
	        }
		});
	    
	   
	    JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(-15, 0, 800, 550);
        getContentPane().add(lblNewLabel);
        lblNewLabel.setIcon(new ImageIcon("./img/enchere.jpg"));
	    
	} 
	

/*********************************END CLASS**************************************/
	   
/*********************************************taitement Agent************************************************/
	private void AgentLanceur (String nomAcheteur, String arg) {
		 rt = Runtime.instance();
		 rt.setCloseVM(true);
		 profile = new ProfileImpl("localhost",1099,"RSHP");
		 mc = rt.createMainContainer(profile);
		 agentVendeurs = new AgentController[Vendeur.getnbVendeur()];
		  try{
			  
			 setAgentAcheteur(mc.createNewAgent("Acheteur",Acheteur.class.getName(),new Object[]{arg}));
		 
			  for(int i=0; i<Vendeur.getnbVendeur();i++){
				  agentVendeurs[i]= mc.createNewAgent("Vendeur"+i,Vendeur.class.getName(), null);
				  System.out.println("i="+i);
				  System.out.println(agentVendeurs[i].toString());
			  }
	
			  getAgentAcheteur().start();
			  for(int i=0; i<agentVendeurs.length;i++){
				  agentVendeurs[i].start();
			  }
		  }
		  catch (Exception e) { 	
			  e.printStackTrace();   
		  }
		 
		
	}
	

/*******************Methods*****************************************************/
/**********************************Chargements données*****************************************/	
	public static  String[] loadValues(String fichier) throws IOException {  
		
		
		LineNumberReader nbr;
		nbr = new LineNumberReader(new FileReader(new File(fichier)));
		nbr.skip(Long.MAX_VALUE);
		int nbLignes = nbr.getLineNumber();
		nbr.close();
	
		String produits[] = new String[nbLignes+1];
	try {
	
		BufferedReader br = new BufferedReader(new FileReader(fichier));

			String line="";
			int i=0; 
			while((line=br.readLine())!=null){
				String[] produit = line.split(" ");
					produits[i]=produit[0];
			i++;}
		

		br.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
return produits;
}

/*************************************Msg aucune offre******************************/
	public static void AucuneOffre(){
		String message = "Aucune offre correspondante";
	    JOptionPane.showMessageDialog(new JFrame(), message, "",
	        JOptionPane.ERROR_MESSAGE);
	}
	
/**************************************Add Image*******************************/
	
	public static BufferedImage addImage(String path){
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myPicture;
	}
/********************************Ajouter AChat*****************************/
	 private  void addLine(String nomfichier, String lineToAdd) throws IOException {
			
			File data = new File(nomfichier);
		    BufferedWriter writer = new BufferedWriter(new FileWriter(data, true));
		    BufferedReader reader = new BufferedReader(new FileReader(data));
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
	 
/***********************Getters and Setters***********************************/
	public static String getNomProduit() {
		return nomProduit;
	}


	public static void setNomProduit(String nomProduit) {
		AcheteurFrame.nomProduit = nomProduit;
	}


	public static String getImportancePrix() {
		return importancePrix;
	}


	public static void setImportancePrix(String importancePrix) {
		AcheteurFrame.importancePrix = importancePrix;
	}


	public static String getImportanceQualité() {
		return importanceQualité;
	}


	public static void setImportanceQualité(String importanceQualité) {
		AcheteurFrame.importanceQualité = importanceQualité;
	}


	public static String getImportanceFrais() {
		return importanceFrais;
	}


	public static void setImportanceFrais(String importanceFrais) {
		AcheteurFrame.importanceFrais = importanceFrais;
	}


	public static float getPrixmax() {
		return prixmax;
	}


	public static void setPrixmax(float prixmax) {
		AcheteurFrame.prixmax = prixmax;
	}
	
	public static JTable getVendeurGagnant() {
		return VendeurGagnant;
	}


	public void setVendeurGagnant(JTable vendeurGagnant) {
		VendeurGagnant = vendeurGagnant;
	}


	public static DefaultTableModel getModel() {
		return model;
	}


	public void setModel(DefaultTableModel model) {
		this.model = model;
	}


	public static AgentController getAgentAcheteur() {
		return agentAcheteur;
	}


	public static void setAgentAcheteur(AgentController agentAcheteur) {
		AcheteurFrame.agentAcheteur = agentAcheteur;
	}

	public static JTextField getJTprixmax() {
		return JTprixmax;
	}


	public void setJTprixmax(JTextField jTprixmax) {
		JTprixmax = jTprixmax;
	}


	public static JLabel getLblWinner() {
		return lblWinner;
	}


	public static void setLblWinner(JLabel lblWinner) {
		AcheteurFrame.lblWinner = lblWinner;
	}


	
}
