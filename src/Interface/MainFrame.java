package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Traitement.Produit;
import Traitement.Vendeur;
import jade.wrapper.StaleProxyException;

import javax.swing.JComboBox;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;

public class MainFrame extends JFrame{

	  private JPanel PanelUser;
	  private JButton btnConnexion;
	  private JButton btnInscription;
	  public static boolean termine = false;
	  public static HashMap<String,Integer> ListeAgentsVendeurs = new HashMap<String,Integer>();
	  private JLabel lblNewLabel;
	  private JPanel panel;
	  
	public MainFrame(){
		
		try {
			Vendeur.setnbVendeur2(Vendeur.nbLignes("./fichiers/listeVendeurs.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Vendeur.getnbVendeur());
		setTitle("Enchères");
		setResizable(false);
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/logo.jpg"));
		setPreferredSize(new Dimension(700, 500));//340
		
		pack();
		setVisible(true);	
		setLocationRelativeTo(null);
			
		addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent evt) {
	        	// affichage boite de dialogue de confirmation de quitter
	        	int confirmSupp = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter ?", "Confirmer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(confirmSupp == JOptionPane.OK_OPTION) {
					if(AcheteurFrame.getAgentAcheteur() != null){
						try {
							AcheteurFrame.getAgentAcheteur().kill();
							termine=true;
							for(int i=0; i<AcheteurFrame.agentVendeurs.length; i++){							
								if(AcheteurFrame.agentVendeurs[i] != null)
									AcheteurFrame.agentVendeurs[i].kill();		
							}
						} catch (StaleProxyException e1) {}
					}
					
					if(AcheteurFrame.rt!=null)AcheteurFrame.rt.shutDown();
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
				}
				else setDefaultCloseOperation(JFrame.NORMAL); // fermer la fenetre
	        }
		});
	    getContentPane().setLayout(null);
	            
	            panel = new JPanel();
	            panel.setBounds(0, -28, 344, 249);
	            panel.setSize(688, 498);

	            getContentPane().add(panel);
	            panel.setLayout(null);
	            
	            btnConnexion = new JButton("Login To Your Account");
	            btnConnexion.setBounds(27, 115, 200, 53);
	            
	            panel.add(btnConnexion);
	            btnConnexion.addActionListener(new ActionListener() {
	            	public void actionPerformed(ActionEvent arg0) {
	            	}
	            });
	            btnConnexion.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 13));
	            btnConnexion.setForeground(Color.BLACK);
	            btnConnexion.setContentAreaFilled(false);
	            btnConnexion.setBorderPainted( false );
	            btnConnexion.setFocusPainted(false);
	            btnConnexion.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent arg0) {
	    				
	    					Authentification authentification = new Authentification();
	    					authentification.setVisible(true);
	    			}});
	            
	            
	            btnInscription = new JButton("Regester");
	            btnInscription.setBounds(27, 160, 200, 53);
	            panel.add(btnInscription);
	            btnInscription.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 13));
	            btnInscription.setForeground(Color.BLACK);
	            btnInscription.setContentAreaFilled(false);
	            btnInscription.setBorderPainted( false );
	            btnInscription.setAlignmentX(CENTER_ALIGNMENT);
	            btnInscription.setAlignmentY(CENTER_ALIGNMENT);
	            btnInscription.setFocusPainted(false);
	            btnInscription.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent arg0) {
	    				
	    					Inscription inscription = new Inscription();
	    					inscription.setVisible(true);
	    			}});
	            JLabel lblBienvenue = new JLabel("Enchère SII");
	            lblBienvenue.setForeground(Color.WHITE);
	            lblBienvenue.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 15));
	            lblBienvenue.setBounds(125, 49, 111, 35);
	            panel.add(lblBienvenue);
	            
	            lblNewLabel = new JLabel("");
	            lblNewLabel.setBounds(-15, 0, 700, 500);
	            panel.add(lblNewLabel);
	            lblNewLabel.setIcon(new ImageIcon("./img/login.jpg"));
       
	//   setContentPane(panel_1);
	    setVisible(true);
	    
	    //Réccupérer vendeurs et produits
	    reccupererAgentsVendeurs();
	    reccupererProduits();
	
	   
}

public void reccupererAgentsVendeurs(){
		
		String ligne="";
		BufferedReader br = null;
		InputStream ips;
		try {
			ips = new FileInputStream("./fichiers/ListeVendeurs.txt");
			InputStreamReader ipsr=new InputStreamReader(ips);
			 br=new BufferedReader(ipsr);
		} catch (FileNotFoundException e) { e.printStackTrace(); }
		
		//rechercher le produit demande dans le fichier et save it
		try {
			while ((ligne=br.readLine())!=null){
				String [] vendeur = ligne.split(" ");
					ListeAgentsVendeurs.put(vendeur[0], 1);
				}
			}
		 catch (IOException e) { e.printStackTrace();}
			
	}

	@SuppressWarnings("null")
	public static  void reccupererProduits(){  
		
		try {
			String nom;
			String nbVendeurs;
			String infos = null;
			Produit p=null;
			BufferedReader br = new BufferedReader(new FileReader("./fichiers/ListeProduits.txt"));
			String line="";
			int i=0; 
			while((line=br.readLine())!=null){
				String[] produit = line.split(" ");
				
					nom= produit[0];
					nbVendeurs=produit[1];	
					infos = produit[2];
					
					p= new Produit(nom,Integer.valueOf(nbVendeurs),infos);
					ListeProduits.Produits.add(p);
			i++;}
		
	
			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage addImage(String path){
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myPicture;
	}
}
