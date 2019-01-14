package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domen.Gledalac;
import kontroler.Kontroler;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.InputMap;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LoginForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JCheckBox cbAdmin;
	protected boolean userDB, passDB, userAdminDB, passAdminDB;
	protected int ID_Gled;
	private JPasswordField pfPass;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForma frame = new LoginForma();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForma() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_Welcome = new JPanel();
		panel_Welcome.setBackground(SystemColor.activeCaption);
		panel_Welcome.setBounds(53, 32, 479, 40);
		contentPane.add(panel_Welcome);
		panel_Welcome.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Dobrodosli u aplikaciju za rezervaciju pozorisnih karata");
		lblWelcome.setBounds(57, 11, 375, 17);
		panel_Welcome.add(lblWelcome);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWelcome.setBackground(SystemColor.activeCaption);
		
		JPanel panel_Login = new JPanel();
		panel_Login.setBackground(SystemColor.activeCaption);
		panel_Login.setBounds(53, 124, 479, 245);
		contentPane.add(panel_Login);
		panel_Login.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(48, 44, 91, 14);
		panel_Login.add(lblUsername);
		
		tfUser = new JTextField();
		tfUser.setBounds(194, 41, 227, 20);
		panel_Login.add(tfUser);
		tfUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(48, 81, 91, 14);
		panel_Login.add(lblPassword);
		
		cbAdmin = new JCheckBox("admin");
		
		InputMap im = cbAdmin.getInputMap();
		KeyStroke existingKeyStroke = KeyStroke.getKeyStroke("SPACE");
		KeyStroke addedKeyStroke = KeyStroke.getKeyStroke("ENTER");
		im.put(addedKeyStroke, im.get(existingKeyStroke));
		existingKeyStroke = KeyStroke.getKeyStroke("released SPACE");
		addedKeyStroke = KeyStroke.getKeyStroke("released ENTER");
		im.put(addedKeyStroke, im.get(existingKeyStroke));
		
		cbAdmin.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				super.keyPressed(e);
			}
			}});
		cbAdmin.setBounds(324, 117, 97, 23);
		panel_Login.add(cbAdmin);
		
		JButton btnLogin = new JButton("Login");
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String user=tfUser.getText().toString();
				String pass=String.valueOf(pfPass.getPassword());
				//String pass=pfPass.getText().toString();
				userDB=Kontroler.getInstanca().vratiUserDB(user);
				passDB=Kontroler.getInstanca().vratiPassDB(pass);
				userAdminDB=Kontroler.getInstanca().vratiAdminUserDB(user);
				passAdminDB=Kontroler.getInstanca().vratiAdminPassDB(pass);
				
				if(cbAdmin.isSelected()) {
					
					if(userAdminDB==true && passAdminDB==true) {
						AdminForma af=new AdminForma();
						af.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Uneti podaci nisu ispravni. Molimo vas da unesete tacan username i password!");
						
					}
					}else if(userDB==true && passDB==true) {
						KorisnikForma kf=new KorisnikForma(user);
						kf.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(null, "Uneti podaci nisu ispravni. Molimo vas da unesete tacan username i password!");
						
				}
				obrisiPolja();
				deselektujCheckBox();
			}
		});
		
		btnLogin.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					
					String user=tfUser.getText().toString();
					String pass=String.valueOf(pfPass.getPassword());
					userDB=Kontroler.getInstanca().vratiUserDB(user);
					passDB=Kontroler.getInstanca().vratiPassDB(pass);
					userAdminDB=Kontroler.getInstanca().vratiAdminUserDB(user);
					passAdminDB=Kontroler.getInstanca().vratiAdminPassDB(pass);
					
					if(cbAdmin.isSelected()) {
						
						if(userAdminDB==true && passAdminDB==true) {
							AdminForma af=new AdminForma();
							af.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "Uneti podaci nisu ispravni. Molimo vas da unesete tacan username i password!");
							
						}
						}else if(userDB==true && passDB==true) {
							KorisnikForma kf=new KorisnikForma(user);
							kf.setVisible(true);
							
						}else {
							JOptionPane.showMessageDialog(null, "Uneti podaci nisu ispravni. Molimo vas da unesete tacan username i password!");
							
					
					}
					obrisiPolja();
					deselektujCheckBox();
				} 
				
		}});
		;
		
		btnLogin.setBounds(178, 153, 89, 23);
		panel_Login.add(btnLogin);
		
		pfPass = new JPasswordField();
		pfPass.setBounds(194, 78, 227, 20);
		panel_Login.add(pfPass);
		
		JLabel lblNoviKorisnik = new JLabel("Novi korisnik?");
		lblNoviKorisnik.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNoviKorisnik.setBounds(231, 206, 91, 14);
		panel_Login.add(lblNoviKorisnik);
		
		JButton btnRegistracija = new JButton("Registracija");
		btnRegistracija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				RegistracijaForma rf=new RegistracijaForma();
				rf.setVisible(true);
			}
		});
		
		btnRegistracija.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					RegistracijaForma rf=new RegistracijaForma();
					rf.setVisible(true);
				}
			}
		});
		btnRegistracija.setBounds(332, 202, 111, 23);
		panel_Login.add(btnRegistracija);
			
	}

	protected void deselektujCheckBox() {
		// TODO Auto-generated method stub
		cbAdmin.setSelected(false);
	}

	protected void obrisiPolja() {
		// TODO Auto-generated method stub
		tfUser.setText("");
		pfPass.setText("");
	}
}
