package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import Traitement.Produit;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;

public class Authentification extends JFrame{
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblMotDePasse;
	private JLabel lblType;
	private JLabel lblpwdIncorrecte;
	private JLabel lbluserIncorrecte;
	private JPanel PanelUser;
	private JComboBox cbUser;
	private JLabel lblUtilisateur;
	private JButton btnAnnuler;
	private JButton btnConnexion;
	private JLabel label;
	private JPanel panel;
	private JPanel panel_2;
	private JLabel label_2;
	private JPanel panel_1;
	private JLabel label_1;
	private JPanel panel_3;
	private JLabel lblNewLabel;
	
	public Authentification(){
		getContentPane().setBackground(SystemColor.control);
		/*
		ImageIcon background = new ImageIcon("./img/Aut.jpg");
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setIcon(background);
		backgroundLabel.setBounds(0, 0, 350, 320);
		getContentPane().add(backgroundLabel);*/
		// Création d'un panneau de superposition
        JLayeredPane layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);

        // Ajout de l'image d'arrière-plan en tant que composant de base
        ImageIcon backgroundIcon = new ImageIcon("./img/Aut.jpg");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());
        layeredPane.add(backgroundLabel, new Integer(0));

		setBackground(Color.WHITE);
		setTitle("Authentification");
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/LoginIcone.png"));
		setResizable(false);
		setPreferredSize(new Dimension(350, 320));
		pack();
		setVisible(true);	
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		
		addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent evt) {
	        	// affichage boite de dialogue de confirmation de quitter
	        	int confirmSupp = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(confirmSupp == JOptionPane.OK_OPTION) setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				else setDefaultCloseOperation(JFrame.NORMAL); // fermer la fenetre
	        }
		});
		
/**************Choix utilisateur******************/
		
		PanelUser = new JPanel();
		
		String[] users ={ "Acheteur", "Vendeur"};
		
		panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.control);
		panel_3.setBounds(131, 45, 191, 176);
		getContentPane().add(panel_3);
		
		panel_3.setLayout(null);
		
		lbluserIncorrecte = new JLabel("Incorrect user");
		lbluserIncorrecte.setBounds(28, 88, 118, 14);
		panel_3.add(lbluserIncorrecte);
		lbluserIncorrecte.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lbluserIncorrecte.setForeground(Color.BLUE);
		lbluserIncorrecte.setVisible(false);
		
		lblpwdIncorrecte = new JLabel("Incorrect password");
		lblpwdIncorrecte.setBounds(28, 151, 118, 14);
		panel_3.add(lblpwdIncorrecte);
		lblpwdIncorrecte.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblpwdIncorrecte.setForeground(Color.BLUE);
		lblpwdIncorrecte.setVisible(false);
		
		cbUser = new JComboBox();
		cbUser.setBackground(new Color(245, 245, 245));
		cbUser.setBounds(28, 0, 163, 28);
		panel_3.add(cbUser);
		layeredPane.add(cbUser, new Integer(1));
		cbUser.setModel(new DefaultComboBoxModel<String>(users));
		
		
		textField = new JTextField();
		textField.setBackground(new Color(245, 245, 245));
		textField.setBounds(28, 55, 163, 28);
		panel_3.add(textField);
		textField.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(245, 245, 245));
		passwordField.setBounds(28, 113, 163, 28);
		panel_3.add(passwordField);
		
		panel = new JPanel();
		panel.setBounds(0, 55, 30, 28);
		panel_3.add(panel);
		panel.setBackground(SystemColor.control);
		panel.setLayout(null);
		
		label = new JLabel();
		label.setBackground(Color.WHITE);
		label.setBounds(0, 0, 30, 28);
		panel.add(label);
		
		panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 30, 28);
		panel_3.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.control);
		
		label_2 = new JLabel();
		label_2.setBackground(Color.WHITE);
		label_2.setBounds(0, 0, 30, 28);
		panel_2.add(label_2);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 113, 30, 28);
		panel_3.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.control);
		
		label_1 = new JLabel();   //new ImageIcon(addImage("./img/password.png"))
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(0, 0, 30, 28);
		panel_1.add(label_1);

		lblType = new JLabel("Username");
		lblType.setForeground(new Color(0, 0, 0));
		lblType.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblType.setBounds(19, 110, 103, 14);
		getContentPane().add(lblType);
		
		lblUtilisateur = new JLabel("User Type");
		lblUtilisateur.setForeground(new Color(0, 0, 0));
		lblUtilisateur.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUtilisateur.setBounds(19, 55, 103, 14);
		getContentPane().add(lblUtilisateur);
		
		lblMotDePasse = new JLabel("Password");
		lblMotDePasse.setForeground(new Color(0, 0, 0));
		lblMotDePasse.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMotDePasse.setBounds(19, 164, 103, 22);
		getContentPane().add(lblMotDePasse);
		
		btnConnexion = new JButton("Connection");
		btnConnexion.setForeground(new Color(0, 0, 0));
		btnConnexion.setBounds(34, 232, 128, 34);
		btnConnexion.setBackground(new Color(135, 206, 250));
		btnConnexion.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnConnexion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnConnexion.setFocusPainted(false);
		getContentPane().add(btnConnexion);
		layeredPane.add(btnConnexion, new Integer(1));
		

		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					BufferedReader br;
					try {
						
						String type = cbUser.getSelectedItem().toString();
						
						if(type.equals("Vendeur")) br = new BufferedReader(new FileReader(new File("./fichiers/LoginVendeurs.txt")));
						else br = new BufferedReader(new FileReader(new File("./fichiers/LoginAcheteurs.txt")));
						String line="";
						boolean trouve = false;
						char[] p= passwordField.getPassword();
						String password = new String(p);

							while((line=br.readLine())!=null && !trouve){
								String[] user = line.split(" ");
								if( user[0].equals(textField.getText())){
									if(user[1].equals(password)){
										Authentification.this.dispose();
										trouve = true;
										if(type.equals("Vendeur")){
											VendeurFrame vendeur = new VendeurFrame(user[0]);
											vendeur.setVisible(true);
										}
										
										else{
											AcheteurFrame acheteur = new AcheteurFrame(user[0]);
											acheteur.setVisible(true);
										}
										
									}
									else{
										lblpwdIncorrecte.setVisible(true);
									}
								}
								else{
									
									lbluserIncorrecte.setVisible(true);
									
								}
						}
							br.close();
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}});
		
				
				
		btnAnnuler = new JButton("Cancel");
		btnAnnuler.setForeground(new Color(0, 0, 0));
		btnAnnuler.setBounds(180, 232, 128, 34);
		btnAnnuler.setBackground(new Color(135, 206, 250));
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAnnuler.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnAnnuler.setFocusPainted(false);
		getContentPane().add(btnAnnuler);
		layeredPane.add(btnAnnuler, new Integer(1));
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// affichage boite de dialogue de confirmation de quitter
	        	int confirmSupp = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment annuler ?", "Confirmation de quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(confirmSupp == JOptionPane.OK_OPTION) Authentification.this.dispose();
				else setDefaultCloseOperation(JFrame.NORMAL); // fermer la fenetre
			}});
		
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
