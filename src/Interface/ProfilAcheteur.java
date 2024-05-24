package Interface;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Traitement.Produit;
import jade.wrapper.StaleProxyException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ProfilAcheteur extends JFrame {


	private static DefaultTableModel model ;
	private JPasswordField passwordField;
	private static JTable table_1;
	
	public ProfilAcheteur(String nomAcheteur) throws IOException {
		
	    setTitle("Profil <<"+nomAcheteur+" >>");
	    setIconImage(Toolkit.getDefaultToolkit().getImage("./img/Iconeprofile.png"));
	    setPreferredSize(new Dimension(650, 400));
	    pack();
	    setVisible(true);	
	    setLocationRelativeTo(null);
	    getContentPane().setLayout(null);
		
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
		
		JLabel lblListeDesProduits = new JLabel("Historique des Achats ");
		lblListeDesProduits.setBounds(10, 11, 175, 22);
		getContentPane().add(lblListeDesProduits);
		

	
		//Les donn�es du tableau
		File fichierHisto = new File ("./Acheteurs/"+nomAcheteur+".txt");
		Object  columns[] = {"Produit", "Vendeur", "Prix", "Prix max", "Frais Livraison", "Date Achat"};
		if(fichierHisto.exists()){
	    Object[][] data = loadValues("./Acheteurs/"+nomAcheteur+".txt");
	    
	   
		model = new DefaultTableModel();
	    model.setDataVector(data, columns);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(10, 31, 438, 319);
	    getContentPane().add(scrollPane);
	    table_1 = new JTable(model);
	    table_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	    scrollPane.setViewportView(table_1);
		}
		else{
			model = new DefaultTableModel();
		    model.setColumnIdentifiers(columns);
		    
		    JScrollPane scrollPane = new JScrollPane();
		    scrollPane.setBounds(10, 31, 438, 319);
		    getContentPane().add(scrollPane);
		    table_1 = new JTable(model);
		    table_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		    scrollPane.setViewportView(table_1);
		    
		    String message = "Impossible de charger l'historique: fichier introuvable";
		    JOptionPane.showMessageDialog(new JFrame(), message, "",
		        JOptionPane.ERROR_MESSAGE);
		}
	    JPanel panel = new JPanel();
	    panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    panel.setBounds(459, 146, 165, 108);
	    getContentPane().add(panel);
	    panel.setLayout(null);
	    
	    JButton btnModifierMotDe = new JButton("Valider");
	    btnModifierMotDe.setBounds(10, 74, 141, 23);
	    btnModifierMotDe.setBackground(SystemColor.controlHighlight);
	    btnModifierMotDe.setFont(new Font("Tahoma", Font.BOLD, 12));
	    btnModifierMotDe.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnModifierMotDe.setFocusPainted(false);
	    panel.add(btnModifierMotDe);
	    btnModifierMotDe.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				    
					try {
					    char[] p= passwordField.getPassword();
						String password = new String(p);
						File fichier = new File("./fichiers/LoginAcheteurs.txt");
						if(fichier.exists()){
							updatePwd("./fichiers/LoginAcheteurs.txt", nomAcheteur, password );
								String message = "Mot de passe mis � jour";
							    JOptionPane.showMessageDialog(new JFrame(), message, "",
							        JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							String message = "Erreur: fichier introuvable";
						    JOptionPane.showMessageDialog(new JFrame(), message, "",
						        JOptionPane.ERROR_MESSAGE);
						}
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    	
	    } });
	 
	    passwordField = new JPasswordField();
	    passwordField.setBounds(10, 35, 141, 28);
	    panel.add(passwordField);
	    
	    JLabel lblNouveauMotDe = new JLabel("Nouveau mot de passe");
	    lblNouveauMotDe.setFont(new Font("Tahoma", Font.BOLD, 11));
	    lblNouveauMotDe.setBounds(10, 9, 141, 15);
	    panel.add(lblNouveauMotDe);
	    
	    JButton btnSupprimerLhistorique = new JButton("Supprimer l'historique");
	    btnSupprimerLhistorique.setBounds(470, 276, 141, 33);
	    btnSupprimerLhistorique.setBackground(SystemColor.controlHighlight);
	    btnSupprimerLhistorique.setFont(new Font("Tahoma", Font.BOLD, 11));
	    btnSupprimerLhistorique.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnSupprimerLhistorique.setFocusPainted(false);
	    getContentPane().add(btnSupprimerLhistorique);
	    btnSupprimerLhistorique.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					File fichier = new File("./Acheteurs/"+nomAcheteur+".txt");
					if(fichier.exists()){
						clearHistorique("./Acheteurs/"+nomAcheteur+".txt");
						String message = "Historique supprim�";
					    JOptionPane.showMessageDialog(new JFrame(), message, "",
					        JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						String message = "Impossible de supprimer: fichier introuvable";
					    JOptionPane.showMessageDialog(new JFrame(), message, "",
					        JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				model.setRowCount(0);
				
			}});
	  
	    
	    JButton btnQuitter = new JButton("Quitter");
	    btnQuitter.setBounds(470, 317, 141, 33);
	    btnQuitter.setBackground(SystemColor.controlHighlight);
	    btnQuitter.setFont(new Font("Tahoma", Font.BOLD, 13));
	    btnQuitter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	    btnQuitter.setFocusPainted(false);
	    btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

	        	int confirmSupp = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter ?", "Confirmation de quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(confirmSupp == JOptionPane.OK_OPTION) ProfilAcheteur.this.dispose();
				else setDefaultCloseOperation(JFrame.NORMAL); // fermer la fenetre
			}});
	    getContentPane().add(btnQuitter);
	    
	    JPanel panel_1 = new JPanel();
	    panel_1.setBounds(459, 11, 165, 124);
	    getContentPane().add(panel_1);
	    panel_1.setLayout(null);
	    
	    JLabel lblProfil = new JLabel(new ImageIcon(addImage("./img/profilAcheteur.png")));
	    lblProfil.setBounds(10, 11, 145, 102);
	    panel_1.add(lblProfil);

	}
	
	public static  Object[][] loadValues(String fichier) throws IOException {  
		
		
			LineNumberReader nbr;
			nbr = new LineNumberReader(new FileReader(new File(fichier)));
			nbr.skip(Long.MAX_VALUE);
			int nbLignes = nbr.getLineNumber();
			nbr.close();

		Object[][] data = new Object[nbLignes+1][6] ;
		
		
		try {
			String nom;
			String vendeur;
			String prix;
			String frais;
			String qualit�;
			String date;
 
			BufferedReader br = new BufferedReader(new FileReader(fichier));
 
				String line="";
				int i=0; 
				while((line=br.readLine())!=null){
					String[] produit = line.split(" ");
					
						nom= produit[0];
						vendeur = produit[1];
						prix = produit[2];
						qualit� = produit[3];
						frais = produit[4];
						date = produit[5];
						
						data[i][0]= nom;
						data[i][1]= vendeur;
						data[i][2]= prix;
						data[i][3]= qualit�;
						data[i][4]= frais;	
						data[i][5]= date;
				i++;}
			
 
			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	return data;
	}

	private void clearHistorique(String nomfichier) throws IOException{

		PrintWriter writer = new PrintWriter(nomfichier);
		writer.print("");
		writer.close();
		
	}
	
private void updatePwd(String nomfichier, String useer, String password) throws IOException {
		
		String newLine="";
	    String oldLine ="";
	    String line=""; 
	    
		File data = new File(nomfichier);
	    BufferedReader reader = new BufferedReader(new FileReader(data));
	    
				
	    while((line=reader.readLine())!=null ){
					String[] user = line.split(" ");
					if( user[0].equals(useer)){
						
						oldLine = user[0]+" "+user[1];
						newLine = useer+" "+password;
						System.out.println(oldLine);
						System.out.println(newLine);
					}
				}
		reader.close();

		reader = new BufferedReader(new FileReader(data));
		BufferedWriter writer = new BufferedWriter(new FileWriter("tmp.txt", true));
	    int cpt=0;
	    while ((line = reader.readLine()) != null)
	    {
	        line = line.replace(oldLine, newLine);
	        if(cpt != 0) writer.newLine();
		    writer.write(line);
		    writer.flush();
		    cpt++;
	    }
	    writer.close();
	    reader.close();

	    Files.copy(Paths.get("tmp.txt"), Paths.get(nomfichier), StandardCopyOption.REPLACE_EXISTING);
		Files.delete(Paths.get("tmp.txt"));
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
	public static DefaultTableModel getModel() {
		return model;
	}

	public static void setModel(DefaultTableModel model) {
		ProfilAcheteur.model = model;
	}


}