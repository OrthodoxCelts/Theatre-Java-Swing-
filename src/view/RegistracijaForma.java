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

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RegistracijaForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfImePrezime;
	private JTextField tfUser;
	private JPasswordField pfPass;
	private ArrayList<Gledalac>alUser=new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistracijaForma frame = new RegistracijaForma();
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
	public RegistracijaForma() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 478, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 204, 153));
		panel.setBounds(34, 24, 396, 246);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUnesitePod = new JLabel("Za uspesnu registraciju unesite svoje podatke");
		lblUnesitePod.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUnesitePod.setBounds(59, 23, 285, 14);
		panel.add(lblUnesitePod);
		
		JLabel lblImeIPrezime = new JLabel("Ime i Prezime");
		lblImeIPrezime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImeIPrezime.setBounds(40, 73, 102, 14);
		panel.add(lblImeIPrezime);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(40, 109, 102, 14);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(40, 146, 102, 14);
		panel.add(lblPassword);
		
		tfImePrezime = new JTextField();
		tfImePrezime.setBounds(170, 70, 174, 20);
		panel.add(tfImePrezime);
		tfImePrezime.setColumns(10);
		
		tfUser = new JTextField();
		tfUser.setBounds(170, 106, 174, 20);
		panel.add(tfUser);
		tfUser.setColumns(10);
		
		JButton btnRegistrujMe = new JButton("Registruj me");
		btnRegistrujMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userIzBaze=null;
				ArrayList<Gledalac>alGledalac=new ArrayList<>();
				try {
				String imePrezime=tfImePrezime.getText().toString();
				String user=tfUser.getText().toString();
				String pass=String.valueOf(pfPass.getPassword());
				for(Gledalac gl:Kontroler.getInstanca().vratiGledaoce()) {
					userIzBaze=gl.getUser();
					if(user.equals(userIzBaze)) {
						JOptionPane.showMessageDialog(null, "Uneseni username vec postoji u bazi, molimo Vas da izaberete drugi");
						return;
					}
				}
				Kontroler.getInstanca().zavrsiRegistraciju(imePrezime, user, pass);
				JOptionPane.showMessageDialog(null, "Dobrodosli " + imePrezime + ", uspesno ste izvrsili registraciju. Ulogujte se sa svojim kredencijalima da bi nastavili sa radom u aplikaciji");
				CloseFrame();
					
				
				}catch (Exception e2) {
				// TODO: handle exception
					
					JOptionPane.showMessageDialog(null, "Doslo je do greske pri procesu registracije");
				}
				obrisiPolja();		
			}
		});
		
		btnRegistrujMe.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String userIzBaze=null;
					try {
					String imePrezime=tfImePrezime.getText().toString();
					String user=tfUser.getText().toString();
					String pass=String.valueOf(pfPass.getPassword());
					for(Gledalac gl:Kontroler.getInstanca().vratiGledaoce()) {
						userIzBaze=gl.getUser();
						if(user.equals(gl.getUser())) {
							JOptionPane.showMessageDialog(null, "Uneseni username vec postoji u bazi, molimo Vas da izaberete drugi");
							return;
						}
					}
					Kontroler.getInstanca().zavrsiRegistraciju(imePrezime, user, pass);
					JOptionPane.showMessageDialog(null, "Dobrodosli " + imePrezime + ", uspesno ste izvrsili registraciju. Ulogujte se sa svojim kredencijalima da bi nastavili sa radom u aplikaciji");
					CloseFrame();
						
					}catch (Exception e2) {
					// TODO: handle exception
						
						JOptionPane.showMessageDialog(null, "Doslo je do greske pri procesu registracije");
					}
					obrisiPolja();
				}
			}
		});
		btnRegistrujMe.setBounds(133, 195, 113, 23);
		panel.add(btnRegistrujMe);
		
		pfPass = new JPasswordField();
		pfPass.setBounds(170, 143, 174, 17);
		panel.add(pfPass);
	}

	protected void CloseFrame() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	protected void obrisiPolja() {
		// TODO Auto-generated method stub
		tfImePrezime.setText("");
		tfUser.setText("");
		pfPass.setText("");
	}
}
