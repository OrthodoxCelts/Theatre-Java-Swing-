package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domen.Pozoriste;
import domen.Predstave;
import kontroler.Kontroler;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class AdminForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfPozoriste;
	private JTextField tfNazivPred;
	private JComboBox cbPozoriste;
	private JComboBox cbPredstave;
	protected String nazivPred, scena;
	private String imePoz, imePozorista, imePredstave, vremeIzv, datumIzv;
	private JTextField tfScena;
	private int ID_Poz, ID_Pred, brojUl, cenaUl;
	private JTextField tfVremeIzv;
	private JTextField tfDatumIzv;
	private JTextField tfbrojUlProdaja;
	private JTextField tfCenaUl;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminForma frame = new AdminForma();
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
	public AdminForma() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_Pozoriste = new JPanel();
		panel_Pozoriste.setBackground(new Color(173, 216, 230));
		panel_Pozoriste.setBounds(49, 30, 516, 74);
		contentPane.add(panel_Pozoriste);
		panel_Pozoriste.setLayout(null);
		
		JLabel lblImePozorista = new JLabel("Ime Pozorista");
		lblImePozorista.setBounds(36, 11, 86, 14);
		panel_Pozoriste.add(lblImePozorista);
		
		tfPozoriste = new JTextField();
		tfPozoriste.setBounds(187, 11, 276, 20);
		panel_Pozoriste.add(tfPozoriste);
		tfPozoriste.setColumns(10);
		
		JButton btnUnesiPoz = new JButton("Unesi pozoriste");
		btnUnesiPoz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String naziv=tfPozoriste.getText().toString();
				
				for(Pozoriste p:Kontroler.getInstanca().vratiPozorista()) {
					if(naziv.equals(p.getImePozorista())) {
						JOptionPane.showMessageDialog(null, "Takvo ime pozorista vec postoji");
						obrisiPoljaPozoriste();
						return;
					}
				}
					if(naziv.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Niste uneli ime pozorista");
						obrisiPoljaPozoriste();
						return;
					}else {
						Kontroler.getInstanca().upisiPozoriste(naziv);
						
						JOptionPane.showMessageDialog(null, "Uspesno ste uneli novo pozoriste");
					}
				
				}catch (Exception e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Doslo je do greske pri upisu");
				}
				obrisiPoljaPozoriste();
				obrisiCbPozoriste();
				napuniCbPozoriste();
			}
		});
		btnUnesiPoz.setBounds(173, 42, 122, 23);
		panel_Pozoriste.add(btnUnesiPoz);
		
		JPanel panel_Predstava = new JPanel();
		panel_Predstava.setBackground(new Color(135, 206, 250));
		panel_Predstava.setBounds(49, 140, 516, 213);
		contentPane.add(panel_Predstava);
		panel_Predstava.setLayout(null);
		
		cbPozoriste = new JComboBox();
		cbPozoriste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				imePozorista = (String) cbPozoriste.getSelectedItem();
			}
		});
		cbPozoriste.setBounds(293, 8, 191, 20);
		panel_Predstava.add(cbPozoriste);
		
		JLabel lblImePozorista_1 = new JLabel("Ime Pozorista");
		lblImePozorista_1.setBounds(46, 11, 133, 14);
		panel_Predstava.add(lblImePozorista_1);
		
		tfNazivPred = new JTextField();
		tfNazivPred.setBounds(264, 43, 220, 20);
		panel_Predstava.add(tfNazivPred);
		tfNazivPred.setColumns(10);
		
		JLabel lblNazivPredstave = new JLabel("Naziv predstave");
		lblNazivPredstave.setBounds(46, 46, 133, 14);
		panel_Predstava.add(lblNazivPredstave);
		
		JLabel lblScenaIzvodjenja = new JLabel("Scena izvodjenja (Mala/Velika)");
		lblScenaIzvodjenja.setBounds(46, 81, 191, 14);
		panel_Predstava.add(lblScenaIzvodjenja);
		
		JButton btnUnesiPredstavu = new JButton("Unesi predstavu");
		btnUnesiPredstavu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String datumIzBaze=null;
				String nazivPredstave=null;
				try {
				nazivPred=tfNazivPred.getText().toString();
				scena=tfScena.getText().toString();
				vremeIzv=tfVremeIzv.getText().toString();
				datumIzv=tfDatumIzv.getText().toString();
				
				if(nazivPred.isEmpty() || scena.isEmpty() || vremeIzv.isEmpty() || datumIzv.isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "Izaberite pozoriste i unesite ime, scenu, vreme i datum izvodjenja predstave");
					
					return;
				}
				
				for(Predstave pr:Kontroler.getInstanca().vratiPredstave()) {
					//datumIzBaze=pr.getDatumIzvodjenja();
					//nazivPredstave=pr.getNazivPredstave();
					if(nazivPred.equals(pr.getNazivPredstave()) || scena.equals(pr.getScenaIzvodjenja()) || vremeIzv.equals(pr.getVremeIzvodjenja()) || datumIzv.equals(pr.getDatumIzvodjenja())) {
					//if(datumIzv.equals(datumIzBaze)) {
						//if(nazivPred.equals(nazivPredstave))
						JOptionPane.showMessageDialog(null, "Takva predstava vec postoji u zadatim parametrima. Promenite ime predstave ili datum izvodjenja.");
						return;
					}
				}
				
				for(Pozoriste p:Kontroler.getInstanca().vratiPozorista()) {
					
					if(imePozorista.equals(p.getImePozorista())){
					
					ID_Poz=p.getId_Poz();
					Kontroler.getInstanca().upisiPredstavu(ID_Poz, nazivPred, scena, vremeIzv, datumIzv);
					obrisiPoljaPredstave();
					obrisiCbPredstave();
					napuniCbPredstave();
				
				JOptionPane.showMessageDialog(null, "Uspesno je unesena nova predstava");
					}
				}
				}catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Doslo je do greske pri unosu");
				}
			}
		});
		
		btnUnesiPredstavu.setBounds(171, 181, 133, 23);
		panel_Predstava.add(btnUnesiPredstavu);
		
		
		tfScena = new JTextField();
		tfScena.setBounds(264, 78, 220, 20);
		panel_Predstava.add(tfScena);
		tfScena.setColumns(10);
		
		JLabel lblVremeIzvodjenja = new JLabel("Vreme izvodjenja (HH:MM:SS)");
		lblVremeIzvodjenja.setBounds(46, 115, 191, 14);
		panel_Predstava.add(lblVremeIzvodjenja);
		
		JLabel lblDatumIzvodjenja = new JLabel("Datum izvodjenja (YYYY:MM:DD)");
		lblDatumIzvodjenja.setBounds(46, 150, 191, 14);
		panel_Predstava.add(lblDatumIzvodjenja);
		
		tfVremeIzv = new JTextField();
		tfVremeIzv.setBounds(264, 115, 220, 20);
		panel_Predstava.add(tfVremeIzv);
		tfVremeIzv.setColumns(10);
		
		tfDatumIzv = new JTextField();
		tfDatumIzv.setText("");
		tfDatumIzv.setBounds(264, 150, 220, 20);
		panel_Predstava.add(tfDatumIzv);
		tfDatumIzv.setColumns(10);
		
		JLabel lblUnesiteNovoPozoriste = new JLabel("Unesite novo pozoriste");
		lblUnesiteNovoPozoriste.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUnesiteNovoPozoriste.setBounds(49, 11, 161, 14);
		contentPane.add(lblUnesiteNovoPozoriste);
		
		JLabel lblUnesiteNovuPredstavu = new JLabel("Unesite novu predstavu");
		lblUnesiteNovuPredstavu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUnesiteNovuPredstavu.setBounds(49, 115, 173, 14);
		contentPane.add(lblUnesiteNovuPredstavu);
	
		JPanel panel_Ulaznice = new JPanel();
		panel_Ulaznice.setBackground(new Color(0, 153, 255));
		panel_Ulaznice.setBounds(49, 389, 516, 143);
		contentPane.add(panel_Ulaznice);
		panel_Ulaznice.setLayout(null);
		
		JLabel lblCenaUlaznica = new JLabel("Cena ulaznica");
		lblCenaUlaznica.setBounds(27, 81, 133, 14);
		panel_Ulaznice.add(lblCenaUlaznica);
		
		tfCenaUl = new JTextField();
		tfCenaUl.setBounds(263, 78, 220, 20);
		panel_Ulaznice.add(tfCenaUl);
		tfCenaUl.setText("0");
		tfCenaUl.setColumns(10);
		
		JLabel lblBrojUlaznicaZa = new JLabel("Broj ulaznica za prodaju");
		lblBrojUlaznicaZa.setBounds(27, 46, 158, 14);
		panel_Ulaznice.add(lblBrojUlaznicaZa);
		
		tfbrojUlProdaja = new JTextField();
		tfbrojUlProdaja.setBounds(263, 43, 220, 20);
		panel_Ulaznice.add(tfbrojUlProdaja);
		tfbrojUlProdaja.setText("0");
		tfbrojUlProdaja.setColumns(10);
		
		JLabel lblNazivPredstave_1 = new JLabel("Naziv predstave");
		lblNazivPredstave_1.setBounds(27, 11, 133, 14);
		panel_Ulaznice.add(lblNazivPredstave_1);
		
		
		cbPredstave = new JComboBox();
		cbPredstave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				imePredstave = (String) cbPredstave.getSelectedItem();
				
			}
		});
		cbPredstave.setBounds(291, 8, 192, 20);
		panel_Ulaznice.add(cbPredstave);
		
		JButton btnAzurUlaznice = new JButton("Azuriraj podatke za ulaznice");
		btnAzurUlaznice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				
				brojUl=Integer.parseInt(tfbrojUlProdaja.getText().toString());
				cenaUl=Integer.parseInt(tfCenaUl.getText().toString());
				if(brojUl==0 || cenaUl==0) {
					JOptionPane.showMessageDialog(null, "Niste uneli broj raspolozivih ulaznica kao ni njihovu cenu");
					obrisiPoljaUlaznice();
					return;
				}
					
				for(Predstave pr:Kontroler.getInstanca().vratiPredstave())
					
					if(imePredstave.equals(pr.getNazivPredstave())){
					
						ID_Pred=pr.getId();
						Kontroler.getInstanca().upisiPodUlaznica(ID_Pred, brojUl, cenaUl);
					
				JOptionPane.showMessageDialog(null, "Uspesno ste uneli nove podatke za ulaznice");
					}	
				}catch (Exception e3) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Doslo je do greske pri unosu");
				}
				obrisiPoljaUlaznice();
			}
		});
		btnAzurUlaznice.setBounds(151, 109, 200, 23);
		panel_Ulaznice.add(btnAzurUlaznice);
		
		JLabel lblUnesiUlaznice = new JLabel("Unesite podatke za ulaznice");
		lblUnesiUlaznice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUnesiUlaznice.setBounds(49, 364, 211, 14);
		contentPane.add(lblUnesiUlaznice);
		
		JButton btnPregled = new JButton("Pregled aktuelnih rezervacija");
		btnPregled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminForma2 af2=new AdminForma2();
				af2.setVisible(true);
			}
		});
		btnPregled.setBounds(49, 543, 240, 23);
		contentPane.add(btnPregled);
		
		JButton btnAzurPod = new JButton("Admin podaci");
		btnAzurPod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AdminForma3 af3=new AdminForma3();
				af3.setVisible(true);
			}
		});
		btnAzurPod.setBounds(392, 543, 173, 23);
		contentPane.add(btnAzurPod);
		
		napuniCbPozoriste();
		napuniCbPredstave();
	}


	protected void obrisiPoljaUlaznice() {
		// TODO Auto-generated method stub
		tfbrojUlProdaja.setText("0");
		tfCenaUl.setText("0");
	}

	protected void obrisiCbPredstave() {
		// TODO Auto-generated method stub
		
		cbPredstave.removeAllItems();
	}

	private void napuniCbPozoriste() {
		// TODO Auto-generated method stub
		
		for (Pozoriste p:Kontroler.getInstanca().vratiPozorista())
			
			cbPozoriste.addItem(p.getImePozorista());
	}
	
	
	private void napuniCbPredstave() {
		// TODO Auto-generated method stub
		
		for (Predstave p2:Kontroler.getInstanca().vratiPredstave())
			
			cbPredstave.addItem(p2.getNazivPredstave());
		
	}
	
	private void obrisiCbPozoriste() {
		// TODO Auto-generated method stub
			
			cbPozoriste.removeAllItems();
	}
	
	protected void obrisiPoljaPozoriste() {
		// TODO Auto-generated method stub
		tfPozoriste.setText("");
		
	}
	
	protected void obrisiPoljaPredstave() {
		// TODO Auto-generated method stub
		tfNazivPred.setText("");
		tfNazivPred.setText("");
		tfScena.setText("");
		tfVremeIzv.setText("");
		tfDatumIzv.setText("");
		
	}
}
