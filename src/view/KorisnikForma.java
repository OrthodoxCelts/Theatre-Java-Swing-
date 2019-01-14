package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domen.Gledalac;
import domen.Pozoriste;
import domen.Predstave;
import domen.Rezervacije;
import domen.Ulaznice;
import kontroler.Kontroler;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.awt.event.ActionEvent;

public class KorisnikForma extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox cbPozoriste;
	private JTextField tfBrUlaznica;
	private JTextField tfPretraga;
	protected int ID_Pred, ID_Gled;
	private String ImePozorista, nazivPredstave, scenaIzvodjenja, vremeIzvodjenja, datumIzvodjenja, nazivPozoriste;
	private int brRezUl, brojRaspolozivihUlaznica, cenaUlaznica;
	private ArrayList<Predstave>alSort=new ArrayList<>();
	private DefaultTableModel dtm1=new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public KorisnikForma(String user) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 888, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUlogovaniSte = new JLabel("");
		lblUlogovaniSte.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUlogovaniSte.setBounds(33, 407, 381, 14);
		contentPane.add(lblUlogovaniSte);
		Gledalac gledalac=Kontroler.getInstanca().ucitajGledaoca(user);
		lblUlogovaniSte.setText("Ulogovani ste kao korisnik " + gledalac.getImePrezime());
		
		JButton btnIzvrsiRezervaciju = new JButton("Izvrsi rezervaciju");
		ID_Gled = gledalac.getId_Gledalac();
		btnIzvrsiRezervaciju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				brRezUl= 0;
				vratiIDPredstave();
			
				try {
					brRezUl=Integer.parseInt(tfBrUlaznica.getText().toString());
					
					
					if (brojRaspolozivihUlaznica==0) {
						
						JOptionPane.showMessageDialog(null, "Zao nam je sve ulaznice za ovu predstavu su vec rezervisane");
						
					}else if(brRezUl>=1 && brRezUl<=brojRaspolozivihUlaznica && brojRaspolozivihUlaznica>=1){
						
						Kontroler.getInstanca().izvrsiRezervaciju(ID_Pred, ID_Gled, brRezUl);
						brojRaspolozivihUlaznica=brojRaspolozivihUlaznica-brRezUl;
						Kontroler.getInstanca().promeniBrRaspUl(ID_Pred, brojRaspolozivihUlaznica);
						
						JOptionPane.showMessageDialog(null, "Uspesno ste rezervisali karte za predstavu " + nazivPredstave + " pozorista " + ImePozorista);
					}else {
					
					JOptionPane.showMessageDialog(null, "Broj nije ispravan ili nemamo taj broj ulaznica na raspolaganju. Unesite ispravan broj rezervacija tako sto cete prvo pogledati broj raspolozivih ulaznica");
					}
				}catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Doslo je do greske pri upisu rezervacije");
				}
				osveziTabelu();
				obrisiPoljeRezUlaznice();
			}
		});
		btnIzvrsiRezervaciju.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIzvrsiRezervaciju.setBounds(664, 449, 141, 23);
		contentPane.add(btnIzvrsiRezervaciju);
		
		
		JButton btnPretraga = new JButton("Pretrazi");
		btnPretraga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				pretragaPredstava();
				obrisiPoljePretraga();
			}
		});
		btnPretraga.setBounds(724, 67, 115, 23);
		contentPane.add(btnPretraga);
		
		JLabel lblRepertoar = new JLabel("Repertoar");
		lblRepertoar.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblRepertoar.setBounds(373, 21, 153, 28);
		contentPane.add(lblRepertoar);
		
		JLabel lblBrojUlaznicaZa = new JLabel("broj ulaznica za rezervaciju");
		lblBrojUlaznicaZa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBrojUlaznicaZa.setBounds(546, 407, 166, 14);
		contentPane.add(lblBrojUlaznicaZa);
		
		tfBrUlaznica = new JTextField();
		tfBrUlaznica.setBounds(753, 404, 86, 20);
		contentPane.add(tfBrUlaznica);
		tfBrUlaznica.setColumns(10);
		
		cbPozoriste = new JComboBox();
		cbPozoriste.addItem("Repertoar");
		cbPozoriste.addItem("-- -- -- --");
		cbPozoriste.addItem("Sortiraj - Cena");
		cbPozoriste.addItem("-- -- -- --");
		cbPozoriste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nazivPozoriste = (String) cbPozoriste.getSelectedItem();
				
				for(Predstave pr1:Kontroler.getInstanca().vratiRepertoar()) {
					if(nazivPozoriste.equals("Repertoar")) {
						osveziTabelu();
					}else if(nazivPozoriste.equals("Sortiraj - Cena")) {
						Collections.sort(alSort, Predstave.StuNameComparator);
						ucitajSortiranoPoCeni();
					}else if(nazivPozoriste.equals(pr1.getImePozorista())) {
						osveziTabelu(nazivPozoriste);
					}
				}
			}
		});
		cbPozoriste.setBounds(33, 68, 141, 20);
		contentPane.add(cbPozoriste);
		
		tfPretraga = new JTextField();
		tfPretraga.setBounds(502, 68, 194, 20);
		contentPane.add(tfPretraga);
		tfPretraga.setColumns(10);
		
		table = new JTable(dtm1);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table);
		scrollPane.setBounds(33, 107, 806, 270);
		contentPane.add(scrollPane);
		
		table.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				int red=table.getSelectedRow();
				
				String imePoz, id, naziv, scena, vreme, brojRaspUl, datum, cena=null;
				
				imePoz=(table.getModel().getValueAt(red, 0).toString());
				ImePozorista=imePoz;
				
				naziv=(table.getModel().getValueAt(red, 1).toString());
				nazivPredstave=naziv;
				
				datum=(table.getModel().getValueAt(red, 2).toString());
				datumIzvodjenja=datum;
				
				vreme=(table.getModel().getValueAt(red, 3).toString());
				vremeIzvodjenja=vreme;
				
				scena=(table.getModel().getValueAt(red, 4).toString());
				scenaIzvodjenja=scena;
				
				brojRaspUl=(table.getModel().getValueAt(red, 5).toString());
				brojRaspolozivihUlaznica=Integer.valueOf(brojRaspUl);
				
				cena=(table.getModel().getValueAt(red, 6).toString());
				cenaUlaznica=Integer.valueOf(cena);

			}
		});
		
		scrollPane.setViewportView(table);
		
		JButton btnPregledajSvojeRezervacije = new JButton("Pregledaj svoje rezervacije");
		btnPregledajSvojeRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				KorisnikForma2 kf2=new KorisnikForma2(ID_Gled);
				kf2.setVisible(true);
			}
		});
		btnPregledajSvojeRezervacije.setBounds(310, 461, 213, 23);
		contentPane.add(btnPregledajSvojeRezervacije);
		
		JButton btnOsveziTabelu = new JButton("Osvezi tabelu");
		btnOsveziTabelu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				osveziTabelu();
			}
		});
		btnOsveziTabelu.setBounds(33, 461, 115, 23);
		contentPane.add(btnOsveziTabelu);
		
		
		Object[]kolone=new Object[7];
		kolone[0]="Pozoriste";
		kolone[1]="Predstava";
		kolone[2]="Datum";
		kolone[3]="Vreme";
		kolone[4]="Scena";
		kolone[5]="Broj ulaznica";
		kolone[6]="Cena po kom";
		
		dtm1.addColumn(kolone[0]);
		dtm1.addColumn(kolone[1]);
		dtm1.addColumn(kolone[2]);
		dtm1.addColumn(kolone[3]);
		dtm1.addColumn(kolone[4]);
		dtm1.addColumn(kolone[5]);
		dtm1.addColumn(kolone[6]);
		
		osveziTabelu();
		napuniCbPozoriste();
		alSort=Kontroler.getInstanca().vratiRepertoar();
		
	}

	protected void ucitajSortiranoPoCeni() {
		// TODO Auto-generated method stub
		Object[]redovi=new Object[7];
		dtm1.setRowCount(0);
		
		for(Predstave pr:alSort) {
			
			redovi[0]=pr.getImePozorista();
			redovi[1]=pr.getNazivPredstave();
			redovi[2]=pr.getDatumIzvodjenja();
			redovi[3]=pr.getVremeIzvodjenja();
			redovi[4]=pr.getScenaIzvodjenja();
			redovi[5]=pr.getBrojRaspolozivihUlaznica();
			redovi[6]=pr.getCenaUlaznica();
			
			dtm1.addRow(redovi);
		}
	}

	protected void osveziTabelu() {
		// TODO Auto-generated method stub
		
		Object[]redovi=new Object[7];
		dtm1.setRowCount(0);
		for(Predstave pr:Kontroler.getInstanca().vratiRepertoar()) {
			
			redovi[0]=pr.getImePozorista();
			redovi[1]=pr.getNazivPredstave();
			redovi[2]=pr.getDatumIzvodjenja();
			redovi[3]=pr.getVremeIzvodjenja();
			redovi[4]=pr.getScenaIzvodjenja();
			redovi[5]=pr.getBrojRaspolozivihUlaznica();
			redovi[6]=pr.getCenaUlaznica();
			
			dtm1.addRow(redovi);	
		}	
	}

	protected void osveziTabelu(String nazivPozoriste) {
		// TODO Auto-generated method stub
		
		Object[]redovi=new Object[7];
		dtm1.setRowCount(0);
		for(Predstave pr:Kontroler.getInstanca().vratiRepertoar(nazivPozoriste)) {
			
			redovi[0]=pr.getImePozorista();
			redovi[1]=pr.getNazivPredstave();
			redovi[2]=pr.getDatumIzvodjenja();
			redovi[3]=pr.getVremeIzvodjenja();
			redovi[4]=pr.getScenaIzvodjenja();
			redovi[5]=pr.getBrojRaspolozivihUlaznica();
			redovi[6]=pr.getCenaUlaznica();
			dtm1.addRow(redovi);
		}
	}

	private void napuniCbPozoriste() {
		// TODO Auto-generated method stub
		
		for (Pozoriste p:Kontroler.getInstanca().vratiPozorista())
			
			cbPozoriste.addItem(p.getImePozorista());
	}

	protected void vratiIDPredstave() {
		// TODO Auto-generated method stub
		for (Predstave pr:Kontroler.getInstanca().vratiRepertoar()) {
			if(nazivPredstave.equals(pr.getNazivPredstave())) {
				ID_Pred=pr.getId();
			}
		}
	}

	protected void obrisiPoljePretraga() {
		// TODO Auto-generated method stub
		tfPretraga.setText("");
		
	}
	
	protected void obrisiPoljeRezUlaznice() {
		// TODO Auto-generated method stub
	
		tfBrUlaznica.setText("");
	}
	
	protected void pretragaPredstava() {
		// TODO Auto-generated method stub
		
		String nazivPredstave=tfPretraga.getText();
		dtm1.setRowCount(0);
		
		for (Predstave pr:Kontroler.getInstanca().vratiRepertoar()) {
			
			if(pr.getNazivPredstave().toLowerCase().contains(tfPretraga.getText().toLowerCase())) {
				String imePoz=pr.getImePozorista();
				String nazivPred=pr.getNazivPredstave();
				String datumIzv=pr.getDatumIzvodjenja();
				String vremeIzv=pr.getVremeIzvodjenja();
				String scenaIz=pr.getScenaIzvodjenja();
				String brojRaspUl=String.valueOf(pr.getBrojRaspolozivihUlaznica());
				String cenaUl=String.valueOf(pr.getCenaUlaznica());
			
				
				Object[]niz= {imePoz, nazivPred, datumIzv, vremeIzv, scenaIz, brojRaspUl, cenaUl};
				dtm1.addRow(niz);
				
			} 
		}
		
	}
}
